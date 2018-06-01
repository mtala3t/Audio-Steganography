package com.mtala3t.steganography.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioFileHelper {

	public static void playAudio(String url, String fileName) {

		URL baseURL = null;
		URL completeURL = null;
		try {
			baseURL = new URL(url);
			completeURL = new URL(baseURL, fileName);
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}

		AudioClip audioClip = Applet.newAudioClip(completeURL);
		audioClip.play();
	}

	/**
	 * This method to read the input audio file.
	 * 
	 * @param audioFilePath
	 */
	public static byte[] readAudio(String audioFilePath) {

		System.out.println("Reading the audio file......");

		AudioInputStream audioInputStream = null;
		byte[] audioBytes = null;
		File audioFile = new File(audioFilePath);

		try {
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
			// Set an arbitrary buffer size of 1024 frames.
			int numBytes = 154600 * bytesPerFrame;
			audioBytes = new byte[numBytes];

			try {

				audioInputStream.read(audioBytes);

			} catch (Exception ex) {
				System.out.println("Audio file error:" + ex);
			}
		} catch (Exception e) {
			System.out.println("Audio file error:" + e);
		}

		return audioBytes;
	}

	
}
