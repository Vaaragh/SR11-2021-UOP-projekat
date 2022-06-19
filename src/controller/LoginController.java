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
				System.out.println(usernameF + " " + passwordF);
				if (usernameF.equals("") || passwordF.equals("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					Employee staff = cl.checkAuthority(usernameF, passwordF);
					if (staff == null) {
						JOptionPane.showMessageDialog(null,"No matching employees", "Error", JOptionPane.WARNING_MESSAGE);						
					} else if (staff instanceof Admin) {
						System.out.println("Admin login " + staff);
						view.getFrame().dispose();
						view.getFrame().setVisible(false);
					} else {
						System.out.println("Librarian login "+ staff);
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
