package com.mtala3t.steganography.ui;

import com.mtala3t.steganography.EmbeddingHandler;

public class EmbedAction implements WizardButtonListener {
	public WizardFrame wf;
	public SelectInputFile sif;
	public SelectOutputDirectory sof;
	public SelectDataToEmbed sdte;
	public EnterPasswordEmbed eoo;
	public EmbedExtractOptions options;
	public VerifyOptions vo;
	public ShowProcessDetails sepd;
	public ShowEmbeddedFile sopf;
	MainWindow mm;

	public EmbedAction(MainWindow m) {
		mm = m;
		wf = new WizardFrame("The Embedding Process", m);
		wf.addWizardButtonListener(this);
		sif = new SelectInputFile("1. Select an input audio file.");
		wf.addWizPanel(sif);
		sof = new SelectOutputDirectory("2. Select an output directory path.");
		wf.addWizPanel(sof);
		sdte = new SelectDataToEmbed("3. Select or enter text data to embed.");
		wf.addWizPanel(sdte);
		eoo = new EnterPasswordEmbed("4. Enter password.");
		wf.addWizPanel(eoo);
		vo = new VerifyOptions("5. Verify Embedding Options");
		wf.addWizPanel(vo);
		sepd = new ShowProcessDetails("6. Embedding text data into the audio");
		wf.addWizPanel(sepd);
		sopf = new ShowEmbeddedFile("7. View output audio file.");
		wf.addWizPanel(sopf);
		wf.fireSetFocus(0, 1);
	}

	public WizardFrame getWizardFrame() {
		return wf;
	}

	public void buttonClicked(WizardButtonEvent wbe) {
		if (wbe.getButtonType() == WizardButtonListener.NEXT
				&& wbe.getCard() == eoo) {
			options = new EmbedExtractOptions();
			options.setInputFile(sif.getSelectedFile());
			options.setOutputDirectory(sof.getOutputDirectory());
			options.setEmbedTextOrFile(sdte.isTextOrFile());
			if (sdte.isTextOrFile()) {
				options.setEmbedText(sdte.getEmbeddedText());
			} else {
				options.setEmbedFile(sdte.getSelectedFile());
			}
			options.setPassword(eoo.getPassword());

			options.createEmbedColumnData();
			vo.showChosenOptions(options);
		} else if (wbe.getButtonType() == WizardButtonListener.NEXT
				&& wbe.getCard() == vo) {

			java.io.ByteArrayOutputStream outBuffer = new java.io.ByteArrayOutputStream();
			System.setOut(new java.io.PrintStream(outBuffer));
			EmbeddingHandler.startEmbed(options);
			System.out.println("Embedding Process Completed.");
			System.setOut(System.out);
			sepd.addOutputLine(new String(outBuffer.toByteArray()));
		} else if (wbe.getButtonType() == WizardButtonListener.NEXT
				&& wbe.getCard() == sepd) {
			sopf.setImageFiles(options.getInputFile(), options.getOutputFile());
		}
	}

}
