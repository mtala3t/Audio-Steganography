package com.mtala3t.steganography.ui;

public class EnterPasswordEmbed extends WizardPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6388403882064645871L;

	private String password;
	private javax.swing.JLabel panelLabel;
	private javax.swing.JPanel passwordPanel;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JLabel passwordLabel;

	public EnterPasswordEmbed(String display) {
		super(display);
		initComponents();
		setFirstFocusable(passwordField);
	}

	private void initComponents() {// GEN-BEGIN:initComponents

		panelLabel = new javax.swing.JLabel(
				"Enter the Password and Click Next to Start Embedding.");
		passwordPanel = new javax.swing.JPanel();
		passwordLabel = new javax.swing.JLabel();
		passwordField = new javax.swing.JPasswordField();

		setLayout(new java.awt.BorderLayout());

		panelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelLabel.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 0, 0), 2));
		add(panelLabel, java.awt.BorderLayout.CENTER);

		passwordPanel.setLayout(new javax.swing.BoxLayout(passwordPanel,
				javax.swing.BoxLayout.X_AXIS));

		passwordLabel.setText(" Password: ");
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
	public java.lang.String getPassword() {
		return password;
	}

}
