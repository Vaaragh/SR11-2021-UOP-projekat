package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageAdminDialog;
import managers.AdminManager;
import models.Admin;
import tools.Validator;

public class UpdateAdminController {


	private AdminManager adminModel;
	private ManageAdminDialog view;
	private Admin admin;

	
	public UpdateAdminController(ManageAdminDialog view, Admin admin) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.adminModel = AdminManager.getInstance();
		this.admin = admin;
		initRegistrationChecker();
		initCancelBtn();
	}
	
	public void initController() {
		this.view.setVisible(true);
	}
	
	private void initCancelBtn() {
		this.view.getCancelBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
				view.setVisible(false);
			}
		
		});
	}
	
	public int validateFields(String[] info) {
		if(!Validator.isUUIDFormat(info[4])) return 0;
		if(!Validator.isNameFormat(info[2])) return 1;
		if(!Validator.isNameFormat(info[1])) return 2;
		if(!Validator.isJmbgFormat(info[6])) return 3;
		if(!Validator.isAddressFormat(info[0])) return 4;
		if(!Validator.isNumberFormat(info[9])) return 5;
		if(!Validator.isNickFormat(info[8])) return 6;
		if(!Validator.isNickFormat(info[7])) return 7;

		
		return -1;
	}
	
	public void initRegistrationChecker() {
		this.view.getSubmitBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> emptyCheckList = new ArrayList<String>();
				
				String id = view.getIdTextField().getText().trim();
				String firstName = view.getFirstNameTextField().getText().trim();
				String lastName = view.getLastNameTextField().getText().trim();
				String jmbg = view.getJmbgTextField().getText().trim();
				String address = view.getAdressTextField().getText().trim();
				String gender = view.getGenderComboBox().getSelectedItem().toString().trim();
				String wage = view.getWageTextField().getText().trim();
				String userName = view.getUsernameTextField().getText().trim();
				String password = new String(view.getPasswordTextField().getPassword()).trim();
				
				emptyCheckList.addAll(Arrays.asList(address, lastName, firstName, gender, id, "false", jmbg, password, userName, wage));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {

					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					
					String[] infoArray = sb.toString().split("\\|");
					String[] errorName = {"ID", "Name", "Last name", "JMBG", "Address", "Wage", "Username", "Password"};
					
					int errorIndex = validateFields(infoArray);
					if (errorIndex != -1) {
						JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
					
						try {
							if (adminModel.updateAdmin(infoArray, admin.getIdentification())) {
								JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
								view.dispose();
								view.setVisible(false);
								return;
	
							} else {
								JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
	
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			
		});
	}
}
