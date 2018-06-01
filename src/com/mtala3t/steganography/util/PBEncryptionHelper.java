/**
 * 
 */
package com.mtala3t.steganography.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author mtalaat
 *
 */
public class PBEncryptionHelper {

	private static final String algorithm = "PBEWithMD5AndDES";

	public static byte[] encrypt(byte[] plainText, char password[]) {

		System.out.println("Encrypting the plaintext message: "
				+ new String(plainText));
		System.out.println("The plain message size is: " + plainText.length);
		System.out
				.println("The encryption password length: " + password.length);
		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;

		byte[] cipherbuff = null;

		// Salt
		byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

		// Iteration count
		int count = 20;

		// Create PBE parameter set
		pbeParamSpec = new PBEParameterSpec(salt, count);

		try {

			pbeKeySpec = new PBEKeySpec(password);

			keyFac = SecretKeyFactory.getInstance(algorithm);
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			// Create PBE Cipher
			Cipher pbeCipher = Cipher.getInstance(algorithm);

			// Initialize PBE Cipher with key and parameters
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

			cipherbuff = pbeCipher.doFinal(plainText);
		} catch (Exception ex) {
			// Handle the error...
			System.out.println("Caught Exception while encryption: " + ex);
			ex.printStackTrace();
		}

		System.out.println("The encrypted message: " + new String(cipherbuff));
		System.out
				.println("The encrypted message length: " + cipherbuff.length);
		return cipherbuff;

	}

	/**
	 * This method to decrypt a cipher text.
	 */
	public static byte[] decrypt(byte[] cipherText, char password[]) {

		System.out.println("Decrypting the cipher message: "
				+ new String(cipherText));
		System.out.println("The cipher message size is: " + cipherText.length);
		System.out
				.println("The decryption password length: " + password.length);

		PBEKeySpec pbeKeySpec;
		PBEParameterSpec pbeParamSpec;
		SecretKeyFactory keyFac;

		byte[] plainText = null;

		// same salt as in encryption
		byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
				(byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

		// Same iteration count in encryption
		int count = 20;

		// Create PBE parameter set
		pbeParamSpec = new PBEParameterSpec(salt, count);

		try {

			pbeKeySpec = new PBEKeySpec(password);

			System.out.println("Decrypting the ciphertext ...");

			keyFac = SecretKeyFactory.getInstance(algorithm);
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			// Create PBE Cipher
			Cipher pbeCipher = Cipher.getInstance(algorithm);

			// Initialize PBE Cipher with key and parameters
			pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

			// Decrypt the cipher text
			try {
				plainText = pbeCipher.doFinal(cipherText);
				System.out.println("The decrypted message: "
						+ new String(plainText));

				System.out.println("The decrypted message length: "
						+ plainText.length);

			} catch (Exception ex) {
				System.out.println("Possible password missmatch!!\n");
				System.out.println("Caught Exception1: " + ex);
			}
		} catch (Exception ex) {
			// Handle the error...
			System.out.println("Caught Exception while decrypting: " + ex);
		}

		return plainText;
	}
}
