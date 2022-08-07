package org.corodiak.scfakedeveloper.tool;

import java.util.Scanner;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.registry.AlgorithmRegistry;

public class JasyptUtil {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("========== Jasypt Encrypt Util ==========");
		System.out.println("Encryption Algorithm List");
		System.out.println(AlgorithmRegistry.getAllPBEAlgorithms());

		System.out.print("Algorithm Type (recommend : PBEWithSHA256And128BitAES-CBC-BC) : ");
		String algorithmType = sc.nextLine();

		System.out.print("Encryption Key : ");
		String key = sc.nextLine();

		System.out.print("Num of Target : ");
		int num = Integer.parseInt(sc.nextLine());

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setProvider(new BouncyCastleProvider());
		encryptor.setPoolSize(2);
		encryptor.setPassword(key);
		encryptor.setAlgorithm(algorithmType);

		while (num-- > 0) {
			System.out.print("data : ");
			String data = sc.nextLine();
			String enc = encryptor.encrypt(data);
			assert data == encryptor.decrypt(enc);
			System.out.println("enc : " + enc);
		}
	}
}