package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enums.Gender;
import models.Admin;
import net.miginfocom.swing.MigLayout;

public class ManageAdminDialog extends JDialog {
	
	
	
	private JPanel panelTop;
	private JPanel panelBottom;
	
	private JLabel idLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel jmbgLabel;
	private JLabel adressLabel;
	private JLabel genderLabel;
	private JLabel wageLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField jmbgTextField;
	private JTextField adressTextField;
	private JComboBox<Gender> genderComboBox;
	private JTextField wageTextField;	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	
	private JButton submitBtn, cancelBtn;
	
	
	public ManageAdminDialog(JFrame parent, String title, boolean x, Admin admin, boolean check) {
		super(parent, "Update Admin", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineFilledComponents(admin);
		this.setUneditable(admin);
		
	}
	
	public ManageAdminDialog(JFrame parent, String title, boolean x, Admin admin) {
		super(parent, "Update Admin", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineFilledComponents(admin);
		
	}
	
	public ManageAdminDialog(JFrame parent, String title, boolean x) {
		super(parent, "Create Admin", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineEmptyComponents();
	}
	
	private void defineFilledComponents(Admin admin) {
		this.defineLabelsAndButtons();
		
		this.idTextField = new JTextField(admin.getIdentification());
		idTextField.setEditable(false);
		this.firstNameTextField = new JTextField(admin.getFirstName());
		this.lastNameTextField = new JTextField(admin.getFamilyName()); 
		this.jmbgTextField = new JTextField(admin.getJmbg());
		this.adressTextField = new JTextField(admin.getAdress()); 
		this.genderComboBox = new JComboBox<Gender>(Gender.values());
		this.genderComboBox.setSelectedItem(admin.getGender());
		this.wageTextField = new JTextField(String.valueOf(admin.getWage()));
		this.usernameTextField = new JTextField(admin.getUserName());
		this.passwordTextField = new JPasswordField(admin.getPassword());
		
		this.fillPanel();
		
	}
	

	private void defineEmptyComponents() {
		this.defineLabelsAndButtons();
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.firstNameTextField = new JTextField();
		this.lastNameTextField = new JTextField(); 
		this.jmbgTextField = new JTextField();
		this.adressTextField = new JTextField(); 
		this.genderComboBox = new JComboBox<Gender>(Gender.values()); 
		this.wageTextField = new JTextField();
		this.usernameTextField = new JTextField();
		this.passwordTextField = new JPasswordField();
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		this.idLabel = new JLabel("ID");
		this.firstNameLabel = new JLabel("Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.jmbgLabel = new JLabel("JMBG");
		this.adressLabel = new JLabel("Adress");
		this.genderLabel = new JLabel("Gender");
		this.wageLabel = new JLabel("Wage");
		this.usernameLabel = new JLabel("Username");
		this.passwordLabel = new JLabel("Password");
		
		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(firstNameLabel);
		panelTop.add(firstNameTextField, "width 50%, wrap");
		
		panelTop.add(lastNameLabel);
		panelTop.add(lastNameTextField, "width 50%, wrap");
		
		panelTop.add(jmbgLabel);
		panelTop.add(jmbgTextField, "width 50%, wrap");
		
		panelTop.add(adressLabel);
		panelTop.add(adressTextField, "width 50%, wrap");
		
		panelTop.add(genderLabel);
		panelTop.add(genderComboBox, "width 50%, wrap");
		
		panelTop.add(wageLabel);
		panelTop.add(wageTextField, "width 50%, wrap");
		
		panelTop.add(usernameLabel);
		panelTop.add(usernameTextField, "width 50%, wrap");
		
		panelTop.add(passwordLabel);
		panelTop.add(passwordTextField, "width 50%, wrap");
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(Admin admin) {
		this.idTextField.setEditable(false);
		this.firstNameTextField.setEditable(false);
		this.lastNameTextField.setEditable(false);
		this.jmbgTextField.setEditable(false);
		this.adressTextField.setEditable(false); 
		this.genderComboBox.setEnabled(false);
		this.wageTextField.setEditable(false);
		this.usernameTextField.setEditable(false);
		this.passwordTextField.setEditable(false);
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

	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public JLabel getLastNameLabel() {
		return lastNameLabel;
	}

	public void setLastNameLabel(JLabel lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	public JLabel getJmbgLabel() {
		return jmbgLabel;
	}

	public void setJmbgLabel(JLabel jmbgLabel) {
		this.jmbgLabel = jmbgLabel;
	}

	public JLabel getAdressLabel() {
		return adressLabel;
	}

	public void setAdressLabel(JLabel adressLabel) {
		this.adressLabel = adressLabel;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JLabel getWageLabel() {
		return wageLabel;
	}

	public void setWageLabel(JLabel wageLabel) {
		this.wageLabel = wageLabel;
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public void setLastNameTextField(JTextField lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	public JTextField getJmbgTextField() {
		return jmbgTextField;
	}

	public void setJmbgTextField(JTextField jmbgTextField) {
		this.jmbgTextField = jmbgTextField;
	}

	public JTextField getAdressTextField() {
		return adressTextField;
	}

	public void setAdressTextField(JTextField adressTextField) {
		this.adressTextField = adressTextField;
	}

	public JComboBox<Gender> getGenderComboBox() {
		return genderComboBox;
	}

	public void setGenderComboBox(JComboBox<Gender> genderComboBox) {
		this.genderComboBox = genderComboBox;
	}

	public JTextField getWageTextField() {
		return wageTextField;
	}

	public void setWageTextField(JTextField wageTextField) {
		this.wageTextField = wageTextField;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public void setUsernameTextField(JTextField usernameTextField) {
		this.usernameTextField = usernameTextField;
	}

	public JPasswordField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JPasswordField passwordTextField) {
		this.passwordTextField = passwordTextField;
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
