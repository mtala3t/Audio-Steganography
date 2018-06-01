package com.mtala3t.steganography.ui;

public class VerifyOptions extends WizardPanel {

	javax.swing.JTable choiceTable;
	private String text = null;

	public VerifyOptions(String display) {
		super(display);
		text = display;
		initComponents();

	}

	public void showChosenOptions(EmbedExtractOptions eo) {
		javax.swing.JScrollPane viewPane = null;
		if (choiceTable == null && viewPane == null) {
			choiceTable = new javax.swing.JTable(eo);
			viewPane = new javax.swing.JScrollPane(choiceTable);
			add(viewPane, java.awt.BorderLayout.CENTER);
		} else {
			choiceTable.setModel(eo);
		}

	}

	private void initComponents() {
		messageLabel = new javax.swing.JLabel();

		setLayout(new java.awt.BorderLayout());
		if (text.contains("Embed")) {
			messageLabel.setText("Click Next to Start the Embedding Process");
		} else {
			messageLabel.setText("Click Next to Start the Extraction Process");
		}

		add(messageLabel, java.awt.BorderLayout.SOUTH);

	}

	public boolean doValidation() {
		return true;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel messageLabel;
	// End of variables declaration//GEN-END:variables

}
