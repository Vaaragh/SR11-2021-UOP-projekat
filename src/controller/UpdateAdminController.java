package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageAdminDialog;
import enums.RegexP;
import managers.AdminManager;
import models.Admin;

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
					
					Pattern idPattern = Pattern.compile(RegexP.ID.pattern);
					Pattern namePattern = Pattern.compile(RegexP.NAME.pattern);
					Pattern jmbgPattern = Pattern.compile(RegexP.JMBG.pattern);
					Pattern textPattern = Pattern.compile(RegexP.TEXT.pattern);
					Pattern wagePattern = Pattern.compile(RegexP.NUMBER.pattern);
				
					if (!idPattern.matcher(id).find() ||
						!namePattern.matcher(firstName).find() ||
						!namePattern.matcher(lastName).find() ||
						!jmbgPattern.matcher(jmbg).find() ||
						!textPattern.matcher(address).find() ||
						!wagePattern.matcher(wage).find() ||
						!textPattern.matcher(userName).find() ||
						!textPattern.matcher(password).find()) {
						
						JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder();
						for (String s: emptyCheckList) {
							sb.append(s + "|");
						}
						try {
							if (adminModel.updateAdmin(sb.toString().split("\\|"),admin.getIdentification())) {
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
