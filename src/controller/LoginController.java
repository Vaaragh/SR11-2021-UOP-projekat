package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import managers.AdminManager;
import managers.LibrarianManager;
import models.Admin;
import models.Employee;
import models.Librarian;
import view.LoginView;
import view.MainView;

public class LoginController {
	
	private LibrarianManager librarianModel;
	private AdminManager adminModel;
	private LoginView view;
	
	
	public LoginController(LoginView view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.librarianModel = LibrarianManager.getInstance();
		this.adminModel = AdminManager.getInstance();	
		initLoginChecker();
	}
	
	
	
	public void initController() {
		this.view.getFrame().setVisible(true);
	}
	
	public void initLoginChecker() {
		LoginController cl = this;
		this.view.getLoginBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameF = view.getUsernameTextField().getText();
				String passwordF = new String(view.getPasswordTextField().getPassword());
				if (usernameF.equals("") || passwordF.equals("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Employee staff = cl.checkAuthority(usernameF, passwordF);
					if (staff == null) {
						JOptionPane.showMessageDialog(null,"No matching employees", "Error", JOptionPane.WARNING_MESSAGE);						
					} else {
						MainView mw;
						try {
							mw = new MainView(staff);
							@SuppressWarnings("unused")
							MainViewController mc = new MainViewController(mw, staff);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| IOException e2) {
							e2.printStackTrace();
						}
						view.getFrame().dispose();
						view.getFrame().setVisible(false);
					}
				}
				
			}
			
		});
	}
	
	public Employee checkAuthority(String userName, String password) {
		Employee employee = null;
		for (Admin admin: this.adminModel.adminStatusList(false)) {
			if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
				employee = admin;
			}
		}
		if (employee == null) {
			for (Librarian librarian : this.librarianModel.librarianStatusList(false)) {
				if (librarian.getUserName().equals(userName) && librarian.getPassword().equals(password)) {
					employee = librarian;
				}
			}
		}
		
		return employee;
	}
	
	
	

}
