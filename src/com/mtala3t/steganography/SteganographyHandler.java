package com.mtala3t.steganography;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.mtala3t.steganography.util.AudioFileHelper;
import com.mtala3t.steganography.util.PBEncryptionHelper;

/**
 * 
 * @author mtalaat
 *
 */
public class SteganographyHandler {

	private AudioInputStream audioInputStream;
	private byte[] audioBytes;
	private byte[] textDataBytes;

	private String outFile;
	private char password[];

	public SteganographyHandler(String inputAudioFilePath,
			String textDataFilePath, String outputFilePath, char pwd[]) {

		this.password = pwd;
		this.outFile = outputFilePath;
		this.audioBytes = AudioFileHelper.readAudio(inputAudioFilePath);
		this.textDataBytes = readTextDataFile(textDataFilePath);

		try {
			this.audioInputStream = AudioSystem.getAudioInputStream(new File(
					inputAudioFilePath));
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public SteganographyHandler(String inputAudioFilePath,
			String textDataFilePath, char pwd[]) {
		password = pwd;
		outFile = textDataFilePath;
		audioBytes = AudioFileHelper.readAudio(inputAudioFilePath);

	}

	/**
	 * This method that handles the embedding technique.
	 */
	public boolean embed() {

		System.out.println("Embedding the text message in the audio file ...");
		System.out.println("The plain text data length: "
				+ textDataBytes.length);

		// Encrypt the text data.
		byte[] encryptedTextBytes = null;
		try {
			encryptedTextBytes = PBEncryptionHelper.encrypt(textDataBytes,
					password);
			if (encryptedTextBytes.length * 8 > audioBytes.length)
				return false;

		} catch (Exception e) {
			System.out.println("Error while encrypting the plain text data: "
					+ e);
		}
		System.out.println("The encrypted message length: "
				+ encryptedTextBytes.length);

		// First encode the length of the encrypted text
		int messageLength = encryptedTextBytes.length;
		byte[] len = bit_conversion(messageLength);

		System.out
				.println("The audio bytes before embedding the message length: "
						+ audioBytes.length);

		audioBytes = encode_text(audioBytes, len, 0);
		System.out
				.println("The audio bytes after embedding the message length: "
						+ audioBytes.length);
		audioBytes = encode_text(audioBytes, encryptedTextBytes, 32);
		System.out.println("The audio bytes after embedding the text message: "
				+ audioBytes.length);

		System.out.println();

		// now write the byte array to an audio file
		File fileOut = new File(outFile);
		ByteArrayInputStream byteIS = new ByteArrayInputStream(audioBytes);
		AudioInputStream audioIS = new AudioInputStream(byteIS,
				audioInputStream.getFormat(), audioInputStream.getFrameLength());
		if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AU, audioIS)) {
			try {
				AudioSystem.write(audioIS, AudioFileFormat.Type.AU, fileOut);
				System.out.println("Steganographed AU file is written as "
						+ outFile + "...");
			} catch (Exception e) {
				System.err.println("Sound File write error");
			}
		}

		return true;
	}

	/**
	 * * This method that handles the extraction technique.
	 * 
	 * @return
	 */
	public boolean extract() {

		System.out
				.println("Extracting the encrypted text from the audio file ...");

		System.out
				.println("The audio bytes before extracting the encrypted message length: "
						+ audioBytes.length);

		boolean success = true;

		byte[] buff = decode_text(audioBytes);

		System.out.println("The extracted message length: " + buff.length);

		byte[] plainTextBytes = PBEncryptionHelper.decrypt(buff, password);

		try {
			System.out.println("Writing the decrypted hidden message to"
					+ outFile + " ...");
			FileOutputStream outfile = new FileOutputStream(outFile);
			outfile.write(plainTextBytes);
			outfile.close();
		} catch (Exception e) {
			System.out.println("Caught Exception: " + e);
		}

		return success;
	}

	private byte[] readTextDataFile(String inputFilePath) {

		byte[] buff = null;

		try {
			System.out
					.println("Reading the plaintext file ..." + inputFilePath);

			FileInputStream fis = new FileInputStream(inputFilePath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			int len = bis.available();
			buff = new byte[len];

			while (bis.available() != 0)
				len = bis.read(buff);
			bis.close();
			fis.close();

		} catch (Exception e) {
			System.out.println("Error while reading the text data file: " + e);
		}

		return buff;
	}

	private byte[] encode_text(byte[] data, byte[] addition, int offset)

	{

		// check that the data + offset will fit in the image

		if (addition.length + offset > data.length) {
			throw new IllegalArgumentException("File not long enough!");
		}

		// loop through each addition byte
		for (int i = 0; i < addition.length; ++i) {
			// loop through the 8 bits of each byte
			int add = addition[i];
			for (int bit = 7; bit >= 0; --bit, ++offset) // ensure the new
															// offset value
															// carries on
															// through both
															// loops
			{
				// assign an integer to b, shifted by bit spaces AND 1
				// a single bit of the current byte
				int b = (add >>> bit) & 1;

				// assign the bit by taking: [(previous byte value) AND 0xfe] OR
				// bit to add

				// changes the last bit of the byte in the image to be the bit
				// of addition

				data[offset] = (byte) ((data[offset] & 0xFE) | b);

			}

		}

		return data;

	}

	private byte[] decode_text(byte[] data)

	{

		int length = 0;
		int offset = 32;
		// loop through 32 bytes of data to determine text length
		for (int i = 0; i < 32; ++i) // i=24 will also work, as only the 4th
										// byte contains real data
		{
			length = (length << 1) | (data[i] & 1);
		}
		System.out.println("The extracted length is: " + length);

		byte[] result = new byte[length];

		// loop through each byte of text

		for (int b = 0; b < result.length; ++b)

		{

			// loop through each bit within a byte of text

			for (int i = 0; i < 8; ++i, ++offset)

			{

				// assign bit: [(new byte value) << 1] OR [(text byte) AND 1]

				result[b] = (byte) ((result[b] << 1) | (data[offset] & 1));

			}

		}

		return result;

	}

	private byte[] bit_conversion(int i) {

		return (new byte[] { 0, 0, 0, (byte) (i & 0x000000FF) });

	}

}