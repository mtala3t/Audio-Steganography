package com.mtala3t.steganography.ui;

import javax.swing.table.AbstractTableModel;
import java.io.File;

public class EmbedExtractOptions extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1599034927820251062L;
	private File inputFile;
	private File outputDirectory;
	private File outputFile;
	private String embedText;
	private File embedFile;
	private boolean embedTextOrFile;
	private String password;

	private static final String inputFileDesc = "Input File";
	private static final String outputDirectoryDesc = "Output Directory";
	private static final String outputFileDesc = "Output File";
	private static final String embedTextDesc = "Text to be embedded";
	private static final String embedFileDesc = "File to be embedded";
	private static final String passwordDesc = "Password";

	String[] columnNames = { "Property", "Option Selected" };
	Object[][] columnData;

	public EmbedExtractOptions() {
	}

	public void createEmbedColumnData() {
		char[] mask = new char[password.length()];
		java.util.Arrays.fill(mask, '*');

		columnData = new Object[][] {
				{ inputFileDesc, inputFile.getAbsolutePath() },
				{ outputDirectoryDesc, outputDirectory.getAbsolutePath() },
				{
						(embedTextOrFile) ? embedTextDesc : embedFileDesc,
						(embedTextOrFile) ? embedText : embedFile
								.getAbsolutePath() },
				{ passwordDesc, new String(mask) } };
	}

	public void createExtractColumnData() {
		char[] mask = new char[password.length()];
		java.util.Arrays.fill(mask, '*');
		columnData = new Object[][] {
				{ inputFileDesc, inputFile.getAbsolutePath() },
				{ outputFileDesc, outputFile.getAbsolutePath() },
				{ passwordDesc, new String(mask) } };

	}

	public java.io.File createOutputFile(int suffixCount) {
		String outFileName = inputFile.getName().substring(0,
				inputFile.getName().lastIndexOf("."))
				+ ".au";
		outputFile = new java.io.File(outputDirectory.getAbsolutePath()
				+ System.getProperty("file.separator") + outFileName);
		int i = 1;
		while (outputFile.exists()) {
			outFileName = outFileName
					.substring(0, outFileName.lastIndexOf(".")) + (i++) + ".au";
			outputFile = new java.io.File(outputDirectory.getAbsolutePath()
					+ System.getProperty("file.separator") + outFileName);
			if (i > suffixCount)
				return null;
		}
		return outputFile;
	}

	public java.io.File getEmbedFile() {
		if (isEmbedTextOrFile()) {
			try {
				java.io.File tempFile = java.io.File
						.createTempFile("ctl", "dh");
				java.io.FileWriter writer = new java.io.FileWriter(tempFile);
				writer.write(getEmbedText());
				writer.close();
				return tempFile;
			} catch (java.io.IOException ioe) {
				String errorMessage = "Error creating temporary file";
				System.out.println("ALERT! :" + errorMessage);
				javax.swing.JOptionPane.showMessageDialog(null, errorMessage);
			}
		}
		return embedFile;
	}

	public String getColumnName(int index) {
		return columnNames[index];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return columnData.length;
	}

	public Object getValueAt(int row, int column) {
		return columnData[row][column];
	}

	public String toString() {
		return "\tInput File\t"
				+ inputFile.getAbsolutePath()
				+ "\n\n"
				+ "\tOutput Directory\t"
				+ inputFile.getAbsolutePath()
				+ "\n\n"
				+ ((embedTextOrFile) ? "\tText to be embedded\t" + embedText
						+ "\n\n" : "\tFile to be embedded\t"
						+ embedFile.getAbsolutePath() + "\n\n")
				+ "\tPassword\t" + password + "\n\n";

	}

	public void setEmbedFile(java.io.File embedFile) {
		this.embedFile = embedFile;
	}

	public java.lang.String getEmbedText() {
		return embedText;
	}

	public void setEmbedText(java.lang.String embedText) {
		this.embedText = embedText;
	}

	public boolean isEmbedTextOrFile() {
		return embedTextOrFile;
	}

	public void setEmbedTextOrFile(boolean embedTextOrFile) {
		this.embedTextOrFile = embedTextOrFile;
	}

	public java.io.File getInputFile() {
		return inputFile;
	}

	public void setInputFile(java.io.File inputFile) {
		this.inputFile = inputFile;
	}

	public java.io.File getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(java.io.File outputDirectory) {
		this.outputDirectory = outputDirectory;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.io.File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(java.io.File outputFile) {
		this.outputFile = outputFile;
	}

}
