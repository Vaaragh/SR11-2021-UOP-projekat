package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Genre;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ManageGenreDialog  extends JDialog {
	
private JPanel panelTop;
private JPanel panelBottom;

private JLabel idLabel;
private JLabel nameLabel;
private JLabel descriptionLabel;

private JTextField idTextField;
private JTextField nameTextField;
private JTextArea descriptionTextArea;


private JButton submitBtn, cancelBtn;


public ManageGenreDialog(JFrame parent, String title, boolean x, Genre genre, boolean check) {
	super(parent, "Update Genre", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
	this.defineFilledComponents(genre);
	this.setUneditable(genre);
	
}

public ManageGenreDialog(JFrame parent, String title, boolean x, Genre genre) {
	super(parent, "Update Genre", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
	this.defineFilledComponents(genre);
	
}

public ManageGenreDialog(JFrame parent, String title, boolean x) {
	super(parent, "Create Genre", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
	this.defineEmptyComponents();
}

private void defineFilledComponents(Genre genre) {
	this.defineLabelsAndButtons();
	
	this.idTextField = new JTextField(genre.getIdentification());
	this.nameTextField = new JTextField(genre.getGenreName());
	this.descriptionTextArea = new JTextArea(genre.getGenreDescription()); 
	
	
	this.fillPanel();
	
}


private void defineEmptyComponents() {
	this.defineLabelsAndButtons();
	
	this.idTextField = new JTextField(UUID.randomUUID().toString());
	this.nameTextField = new JTextField();
	this.descriptionTextArea = new JTextArea(); 
	
	
	this.fillPanel();
}

private void defineLabelsAndButtons() {
	this.idLabel = new JLabel("ID");
	this.nameLabel = new JLabel("Name");
	this.descriptionLabel = new JLabel("Description");
	
	
	this.submitBtn = new JButton("Submit");
	this.cancelBtn = new JButton("Cancel");
	
}

private void fillPanel() {
	panelTop.add(idLabel);
	panelTop.add(idTextField, "width 50%, wrap");
	
	panelTop.add(nameLabel);
	panelTop.add(nameTextField, "width 50%, wrap");
	
	panelTop.add(descriptionLabel);
	panelTop.add(descriptionTextArea, "width 50%, wrap");
	descriptionTextArea.setPreferredSize( new Dimension( 300, 50 ) );
	descriptionTextArea.setLineWrap(true);
	
	panelTop.add(submitBtn);
	panelTop.add(cancelBtn);
	
	this.add(panelTop);
}

public void setUneditable(Genre genre) {
	this.idTextField.setEditable(false);
	this.nameTextField.setEditable(false);
	this.descriptionTextArea.setEditable(false);
	
	this.submitBtn.setEnabled(false);
	this.getCancelBtn().addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			setVisible(false);
		}
	
	});
	
	
}

public JPanel getPanelTop() {
	return panelTop;
}

public void setPanelTop(JPanel panelTop) {
	this.panelTop = panelTop;
}

public JPanel getPanelBottom() {
	return panelBottom;
}

public void setPanelBottom(JPanel panelBottom) {
	this.panelBottom = panelBottom;
}

public JLabel getIdLabel() {
	return idLabel;
}

public void setIdLabel(JLabel idLabel) {
	this.idLabel = idLabel;
}

public JLabel getNameLabel() {
	return nameLabel;
}

public void setNameLabel(JLabel nameLabel) {
	this.nameLabel = nameLabel;
}

public JLabel getDescriptionLabel() {
	return descriptionLabel;
}

public void setDescriptionLabel(JLabel descriptionLabel) {
	this.descriptionLabel = descriptionLabel;
}

public JTextField getIdTextField() {
	return idTextField;
}

public void setIdTextField(JTextField idTextField) {
	this.idTextField = idTextField;
}

public JTextField getNameTextField() {
	return nameTextField;
}

public void setNameTextField(JTextField nameTextField) {
	this.nameTextField = nameTextField;
}

public JTextArea getDescriptionTextArea() {
	return descriptionTextArea;
}

public void setDescriptionTextArea(JTextArea descriptionTextArea) {
	this.descriptionTextArea = descriptionTextArea;
}

public JButton getSubmitBtn() {
	return submitBtn;
}

public void setSubmitBtn(JButton submitBtn) {
	this.submitBtn = submitBtn;
}

public JButton getCancelBtn() {
	return cancelBtn;
}

public void setCancelBtn(JButton cancelBtn) {
	this.cancelBtn = cancelBtn;
}

}
