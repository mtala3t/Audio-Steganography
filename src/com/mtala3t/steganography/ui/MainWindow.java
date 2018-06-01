package com.mtala3t.steganography.ui;


import java.awt.Color;

import javax.swing.*;

import com.mtala3t.steganography.util.SoundManger;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9005253017639855720L;

	private JDesktopPane panel;

	private JMenuBar jMenuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;

	private JMenuItem extractMenu;
	private JMenuItem embedMenu;
	private JMenuItem exitmenu;
	private JMenuItem aboutMenu;

	public javax.swing.JLabel piclabel;

	public MainWindow() {
		initComponents();
		setSize(610, 455);
		setLocation(350, 150);
		setResizable(false);

	}

	private void initComponents() {

		panel = new JDesktopPane();
		panel.setBackground(Color.WHITE);

		jMenuBar = new JMenuBar();

		fileMenu = new JMenu();
		embedMenu = new JMenuItem();
		extractMenu = new JMenuItem();
		exitmenu = new JMenuItem();

		helpMenu = new JMenu();
		aboutMenu = new JMenuItem();

		setTitle("Audio Steganography by Mohamed Talaat");

		piclabel = new javax.swing.JLabel(new javax.swing.ImageIcon("Images/stego.jpg"));
		piclabel.setBounds(0, 0, 600, 400);
		add(piclabel);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		getContentPane().add(panel, java.awt.BorderLayout.CENTER);

		fileMenu.setMnemonic('F');
		fileMenu.setText("File");
		helpMenu.setMnemonic('h');
		helpMenu.setText("Help");
		embedMenu.setMnemonic('m');
		embedMenu.setText("Embed Data");
		embedMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				embedMenuActionPerformed(evt);
			}
		});
		fileMenu.add(embedMenu);
		extractMenu.setMnemonic('t');
		extractMenu.setText("Extract Data");
		extractMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				extractmenuActionPerformed(evt);
			}
		});

		fileMenu.add(extractMenu);
		exitmenu.setMnemonic('x');
		exitmenu.setText("Exit");
		exitmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitmenuActionPerformed(evt);
			}
		});
		helpMenu.add(aboutMenu);
		aboutMenu.setMnemonic('a');
		aboutMenu.setText("About");
		aboutMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuActionPerformed(evt);
			}
		});
		fileMenu.add(exitmenu);
		helpMenu.add(aboutMenu);
		jMenuBar.add(fileMenu);
		jMenuBar.add(helpMenu);

		setJMenuBar(jMenuBar);
		pack();
	}

	private void exitmenuActionPerformed(java.awt.event.ActionEvent evt) {

		System.exit(0);
	}

	private void extractmenuActionPerformed(java.awt.event.ActionEvent evt) {
		piclabel.setVisible(false);
		WizardFrame wf = new ExtractAction(this).getWizardFrame();
		panel.add(wf);
		wf.moveToFront();
	}

	private void embedMenuActionPerformed(java.awt.event.ActionEvent evt) {

		System.out.println("Embed Action Selected..");

		piclabel.setVisible(false);
		WizardFrame wf = new EmbedAction(this).getWizardFrame();
		panel.add(wf);
		wf.moveToFront();
	}

	private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) {
		javax.swing.JOptionPane.showMessageDialog(this,
				"Audio Steganography\n\n" + "Using Least Significant Bit Method.\n\n"+"Created by : Mohamed Talaat",
				"About", javax.swing.JOptionPane.PLAIN_MESSAGE);
	}

	private void exitForm(java.awt.event.WindowEvent evt) {

		//System.exit(0);
		Exit();

	}
	public void Exit() {
        int res=JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?","Exit Confirmation",JOptionPane.YES_NO_OPTION);
        
        if(res==JOptionPane.YES_OPTION) {
            
            SoundManger souManger=new SoundManger("Sounds/goodbye.wav");
            souManger.play();
            
            setVisible(false);
            dispose();
            System.exit(0);
           
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

}
