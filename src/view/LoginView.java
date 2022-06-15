package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LoginView {
	
	private JFrame frame;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginBtn;
	private JPanel panel;
	
	public LoginView(String title) {
		this.frame = new JFrame(title);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.40),(int)(screenSize.getWidth()*0.2), (int)(screenSize.getHeight()*0.15));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel = new JPanel(new MigLayout());
		
		this.usernameLabel = new JLabel("  Username");
		this.passwordLabel = new JLabel("Password");
		
		this.usernameTextField = new JTextField();
		this.passwordTextField = new JPasswordField();
		
		this.loginBtn = new JButton("Login");
	
		frame.add(panel);

		panel.add(usernameLabel);
		panel.add(usernameTextField, "width 50%, wrap");
		panel.add(passwordLabel, "gap unrelated");
		panel.add(passwordTextField, "width 50%, wrap");
		panel.add(loginBtn, "center");		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
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

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public void setUsernameTextField(JTextField usernameTextField) {
		this.usernameTextField = usernameTextField;
	}

	public JButton getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(JButton loginBtn) {
		this.loginBtn = loginBtn;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPasswordField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(JPasswordField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}
	
	
	
	
	
	
	
	
	
}
