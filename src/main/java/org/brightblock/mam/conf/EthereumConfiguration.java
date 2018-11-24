package org.brightblock.mam.conf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brightblock.mam.conf.settings.EthereumSettings;
import org.brightblock.mam.lightning.conf.LndServerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class EthereumConfiguration {

	private static final Logger logger = LogManager.getLogger(EthereumConfiguration.class);
	@Autowired private EthereumSettings ethereumSettings;

	@Bean
	public Web3j getWeb3() {
		Web3j web3;
		try {
			logger.info("Ethereum web3 connecting to: " + ethereumSettings.getHttpBase());
			web3 = Web3j.build(new HttpService(ethereumSettings.getHttpBase()));
		} catch (Exception e) {
			logger.info("Ethereum web3 connecting to: http://172.17.0.1:8545");
			try {
				web3 = Web3j.build(new HttpService("http://172.17.0.1:8545"));
			} catch (Exception e1) {
				logger.info("Ethereum web3 connecting to: http://localhost:8545");
				web3 = Web3j.build(new HttpService("http://localhost:8545"));
			}
		} 
		return web3;
	}

	@Bean
	public Credentials getCredentials() throws IOException, CipherException {
		if (ethereumSettings.isGanache()) {
			Credentials credentials = WalletUtils.loadBip39Credentials(ethereumSettings.getGanachePassword(), ethereumSettings.getGanacheSeed());
			logger.info("Ethereum ganache credentials address: " + credentials.getAddress());
			logger.info("Ethereum ganache credentials pubkey: " + credentials.getEcKeyPair().getPublicKey());
			return credentials;
		}
		InputStream initialStream = null;
		FileOutputStream fos = null;
		File tempFile = null;
		try {
			logger.info("Ethereum credentials loading from: " + ethereumSettings.getWalletPath());
			initialStream = LndServerApplication.class.getResourceAsStream(ethereumSettings.getWalletPath());
			byte[] data = new byte[initialStream.available()];
			initialStream.read(data);
			initialStream.close();
			tempFile = File.createTempFile("temp_keyfile", "txt", null);
			fos = new FileOutputStream(tempFile);
			fos.write(data);
			Credentials credentials = WalletUtils.loadCredentials(ethereumSettings.getWalletPassword(), tempFile);
			logger.info("Ethereum credentials loaded: " + credentials.getEcKeyPair().getPublicKey());
			return credentials;
		} catch (Exception e) {
			logger.info("Ethereum credentials: Error getting credentials.", e);
			throw new RuntimeException(e);
		} finally {
			tempFile.delete();
			initialStream.close();
			fos.close();
			// initialStream.close(); stream gets passed down to grpc so not closed here.
		}

	}
}
