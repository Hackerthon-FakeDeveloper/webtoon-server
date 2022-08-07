package org.corodiak.scfakedeveloper.config;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableEncryptableProperties
@Slf4j
public class JasyptConfig {
	@Value("${jasypt.encryptor.password}")
	String password;

	@Bean("jasyptEncryptorBean")
	public StringEncryptor stringEncryptor() {
		if (Strings.isNullOrEmpty(password)) {
			log.error("Jasypt Password Load Fail from ${jasypt.encryptor.password}");
		}
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setPoolSize(1);
		encryptor.setPassword(password);
		encryptor.setAlgorithm("PBEWithSHA256And128BitAES-CBC-BC");
		return encryptor;
	}
}
