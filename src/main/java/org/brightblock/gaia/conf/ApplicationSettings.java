package org.brightblock.gaia.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "application")
public class ApplicationSettings {
	private String confluenceBase;
	private String bitcoinBase;
	private String lightningCertFileName;
	private String lightningBase;
	private int lightningPortAlice;
	private int lightningPortBob;
	private String bitcoinRpcPasssword;
	private String bitcoinRpcUser;
	private String bitcoinTestRpcPasssword;
	private String bitcoinTestRpcUser;
	private String dropboxApiKey;
	private String dropboxApiSecret;

	public String getBitcoinRpcUser() {
		return bitcoinRpcUser;
	}

	public void setBitcoinRpcUser(String bitcoinRpcUser) {
		this.bitcoinRpcUser = bitcoinRpcUser;
	}

	public String getBitcoinRpcPasssword() {
		return bitcoinRpcPasssword;
	}

	public void setBitcoinRpcPasssword(String bitcoinRpcPasssword) {
		this.bitcoinRpcPasssword = bitcoinRpcPasssword;
	}

	public String getConfluenceBase() {
		return confluenceBase;
	}

	public void setConfluenceBase(String confluenceBase) {
		this.confluenceBase = confluenceBase;
	}

	public String getBitcoinBase() {
		return bitcoinBase;
	}

	public void setBitcoinBase(String bitcoinBase) {
		this.bitcoinBase = bitcoinBase;
	}

	public String getBitcoinTestRpcUser() {
		return bitcoinTestRpcUser;
	}

	public void setBitcoinTestRpcUser(String bitcoinTestRpcUser) {
		this.bitcoinTestRpcUser = bitcoinTestRpcUser;
	}

	public String getBitcoinTestRpcPasssword() {
		return bitcoinTestRpcPasssword;
	}

	public void setBitcoinTestRpcPasssword(String bitcoinTestRpcPasssword) {
		this.bitcoinTestRpcPasssword = bitcoinTestRpcPasssword;
	}

	public String getLightningBase() {
		return lightningBase;
	}

	public void setLightningBase(String lightningBase) {
		this.lightningBase = lightningBase;
	}

	public String getLightningCertFileName() {
		return lightningCertFileName;
	}

	public void setLightningCertFileName(String lightningCertFileName) {
		this.lightningCertFileName = lightningCertFileName;
	}

	public int getLightningPortAlice() {
		return lightningPortAlice;
	}

	public void setLightningPortAlice(int lightningPortAlice) {
		this.lightningPortAlice = lightningPortAlice;
	}

	public int getLightningPortBob() {
		return lightningPortBob;
	}

	public void setLightningPortBob(int lightningPortBob) {
		this.lightningPortBob = lightningPortBob;
	}

	public String getDropboxApiSecret() {
		return dropboxApiSecret;
	}

	public void setDropboxApiSecret(String dropboxApiSecret) {
		this.dropboxApiSecret = dropboxApiSecret;
	}

	public String getDropboxApiKey() {
		return dropboxApiKey;
	}

	public void setDropboxApiKey(String dropboxApiKey) {
		this.dropboxApiKey = dropboxApiKey;
	}
}
