package com.mtala3t.steganography.ui;

public class EnterPasswordExtract extends WizardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1557951009773143397L;

	private javax.swing.JLabel panelLabel;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JPanel passwordPanel;

	private String password;

	/** Creates new form SelectOutputFile */
	public EnterPasswordExtract(String stepText) {
		super(stepText);
		initComponents();
		setFirstFocusable(passwordField);
	}

	private void initComponents() {// GEN-BEGIN:initComponents

		panelLabel = new javax.swing.JLabel();
		passwordPanel = new javax.swing.JPanel();
		passwordLabel = new javax.swing.JLabel();
		passwordField = new javax.swing.JPasswordField();

		setLayout(new java.awt.BorderLayout());

		panelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelLabel
				.setText("Enter the Password and Click Next to Start Extraction");
		panelLabel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2));
		add(panelLabel, java.awt.BorderLayout.CENTER);

		passwordPanel.setLayout(new java.awt.BorderLayout());

		passwordPanel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2));
		passwordLabel.setText("  Password  ");
		passwordPanel.add(passwordLabel, java.awt.BorderLayout.WEST);

		passwordPanel.add(passwordField, java.awt.BorderLayout.CENTER);

		add(passwordPanel, java.awt.BorderLayout.SOUTH);

	}

	public boolean doValidation() {
		password = new String(passwordField.getPassword());

		if (password.trim().length() == 0) {
			javax.swing.JOptionPane.showMessageDialog(this,
					"Enter a password/phrase");
			passwordField.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 * Getter for property password.
	 * 
	 * @return Value of property password.
	 */
	public String getPassword() {
		return password;
	}

}
