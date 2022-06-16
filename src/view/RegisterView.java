package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enums.Gender;
import net.miginfocom.swing.MigLayout;

public class RegisterView {
	
	private JFrame frame;

	private JPanel panel;
	
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
	
	private JButton registerBtn;
	
	public RegisterView(String title) {
		this.frame = new JFrame(title);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panel = new JPanel(new MigLayout());
		
		this.idLabel = new JLabel("ID");
		this.firstNameLabel = new JLabel("Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.jmbgLabel = new JLabel("JMBG");
		this.adressLabel = new JLabel("Adress");
		this.genderLabel = new JLabel("Gender");
		this.wageLabel = new JLabel("Wage");
		this.usernameLabel = new JLabel("Username");
		this.passwordLabel = new JLabel("Password");
		
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.firstNameTextField = new JTextField();
		this.lastNameTextField = new JTextField(); 
		this.jmbgTextField = new JTextField();
		this.adressTextField = new JTextField(); 
		this.genderComboBox = new JComboBox<Gender>(Gender.values()); 
		this.wageTextField = new JTextField();
		this.usernameTextField = new JTextField();
		this.passwordTextField = new JPasswordField();
		
		this.registerBtn = new JButton("Register");
		
		frame.add(panel);
		
		panel.add(idLabel);
		panel.add(idTextField, "width 50%, wrap");
		
		panel.add(firstNameLabel);
		panel.add(firstNameTextField, "width 50%, wrap");
		
		panel.add(lastNameLabel);
		panel.add(lastNameTextField, "width 50%, wrap");
		
		panel.add(jmbgLabel);
		panel.add(jmbgTextField, "width 50%, wrap");
		
		panel.add(adressLabel);
		panel.add(adressTextField, "width 50%, wrap");
		
		panel.add(genderLabel);
		panel.add(genderComboBox, "width 50%, wrap");
		
		panel.add(wageLabel);
		panel.add(wageTextField, "width 50%, wrap");
		
		panel.add(usernameLabel);
		panel.add(usernameTextField, "width 50%, wrap");
		
		panel.add(passwordLabel);
		panel.add(passwordTextField, "width 50%, wrap");
		
		panel.add(registerBtn);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
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

	public JButton getRegisterBtn() {
		return registerBtn;
	}

	public void setRegisterBtn(JButton registerBtn) {
		this.registerBtn = registerBtn;
	}
	
	
	

}
