package com.mtala3t.steganography.ui;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.mtala3t.steganography.util.AudioFileHelper;

public class SelectInputFile extends WizardPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 561254082643024167L;

	private File selectedFile;
	private JTextField audioTextField;
	private JPanel selectPanel;
	private JLabel audioLabel;
	private JButton selectButton;
	private JButton playaudio;

	public SelectInputFile(String stepText) {
		super(stepText);
		initComponents();

	}

	private void initComponents() {
		selectPanel = new JPanel();
		audioTextField = new JTextField("");
		selectButton = new JButton();
		audioLabel = new JLabel("Please Select an Audio File");
		playaudio = new JButton("Play Audio");
		playaudio.setVisible(false);

		setLayout(new java.awt.BorderLayout());

		selectPanel.setLayout(new java.awt.BorderLayout());

		selectPanel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2));
		audioTextField.setEditable(false);
		selectPanel.add(playaudio, java.awt.BorderLayout.NORTH);

		playaudio.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playaudioActionPerformed(evt);
			}
		});

		selectPanel.add(audioTextField, java.awt.BorderLayout.CENTER);

		selectButton.setMnemonic('F');
		selectButton.setText("Select File");

		selectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectButtonActionPerformed(evt);
			}
		});

		selectPanel.add(selectButton, java.awt.BorderLayout.EAST);

		add(selectPanel, java.awt.BorderLayout.SOUTH);

		audioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		audioLabel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2));

		add(audioLabel);
	}

	private void playaudioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectButtonActionPerformed
		if (audioTextField.getText() == null) {
			JOptionPane.showMessageDialog(this, "Select an input file first");

		} else {

			System.out.println("The selected file info...");
			System.out.println("Audio File Folder Path: "
					+ selectedFile.getParent());
			System.out.println("Audio File Name: " + selectedFile.getName());
			String url = "file:///" + selectedFile.getParent() + "//";
			System.out.println("URL: " + url);

			AudioFileHelper.playAudio(url, selectedFile.getName());
		}
	}

	@SuppressWarnings("deprecation")
	private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {

		JFileChooser chooser = GuiUtils.getImageFileChooser();
		if (audioTextField.getText() != null)
			chooser.setSelectedFile(new java.io.File(audioTextField.getText()));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
			audioLabel.setText("Play Audio File");
			audioLabel.show();
			audioLabel.setVisible(true);

			audioTextField.setText(selectedFile.getAbsolutePath());
			playaudio.setVisible(true);
		}
	}

	public boolean doValidation() {
		if (audioTextField.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Please select an input file.");
			selectButton.requestFocus();
			return false;
		} else if (!new java.io.File(audioTextField.getText()).exists()) {
			JOptionPane.showMessageDialog(this,
					"File not found! Please select an valid file.");
			selectButton.requestFocus();
			return false;
		}
		return true;
	}

	public java.io.File getSelectedFile() {
		return selectedFile;
	}

}
