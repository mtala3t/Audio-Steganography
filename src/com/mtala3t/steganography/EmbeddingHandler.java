package com.mtala3t.steganography;

import java.io.*;

import com.mtala3t.steganography.ui.EmbedExtractOptions;

public class EmbeddingHandler {

	public static void startEmbed(EmbedExtractOptions eo) {
		File inFile = null, outFile = null;
		FileOutputStream dataOut = null;
		String password = null, embedFileName = null;
		String inFilePath = null, outFilePath = null;

		inFile = eo.getInputFile();
		outFile = eo.createOutputFile(150);
		embedFileName = eo.getEmbedFile().getAbsolutePath();
		password = eo.getPassword();

		inFilePath = inFile.getAbsolutePath();
		outFilePath = outFile.getAbsolutePath();

		try {
			dataOut = new FileOutputStream(outFile);

			SteganographyHandler hide = new SteganographyHandler(inFilePath,
					embedFileName, outFilePath, password.toCharArray());

			if (hide.embed()) {

				System.out
						.println("The embedding process has been completed successfully.");
			} else
				System.out.println("Error occured while embedding.");

			dataOut.close();
		} catch (java.io.IOException e) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Error embedding audio");
			e.printStackTrace();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Error embedding audio");
			e.printStackTrace();
		}

	}
}
