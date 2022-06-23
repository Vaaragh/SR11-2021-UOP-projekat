package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageMembershipDialog;
import managers.MembershipManager;
import models.Membership;
import tools.Validator;

public class ManageMembershipController {



private MembershipManager membershipModel;
private ManageMembershipDialog view;
private Membership membership;
private boolean updateOperation;



public ManageMembershipController(ManageMembershipDialog view, Membership membership) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.membershipModel = MembershipManager.getInstance();
	this.membership = membership;
	this.updateOperation = true;
	initRegistrationChecker();
	initCancelBtn();
}

public ManageMembershipController(ManageMembershipDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.membershipModel = MembershipManager.getInstance();
	this.updateOperation = false;
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
//id, "false", name, price
//"ID", "Name", "Price"

public int validateFields(String[] info) {
	if(!Validator.isUUIDFormat(info[0])) return 0;
	if(!Validator.isNameFormat(info[2])) return 1;
	if(!Validator.isNumberFormat(info[3])) return 2;

	return -1;
}

public void initRegistrationChecker() {
	this.view.getSubmitBtn().addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<String> emptyCheckList = new ArrayList<String>();
			
			String id = view.getIdTextField().getText().trim();
			String name = view.getNameTextField().getText().trim();
			String price = view.getPriceTextField().getText().trim();
			
			
			emptyCheckList.addAll(Arrays.asList(id, "false", name, price));
			
			if (emptyCheckList.contains("")) {
				JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
			} else {

				StringBuilder sb = new StringBuilder();
				for (String s: emptyCheckList) {
					sb.append(s + "|");
				}
				
				String[] infoArray = sb.toString().split("\\|");
				String[] errorName = {"ID", "Name", "Price"};
				
				int errorIndex = validateFields(infoArray);
				if (errorIndex != -1) {
					JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						if(updateOperation) {
							if (membershipModel.updateMembership(sb.toString().split("\\|"), membership.getIdentification() )) {
							JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);

							view.dispose();
							view.setVisible(false);
							return;
	
							} else {
								JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							if (membershipModel.createMembership(sb.toString().split("\\|"))) {
								JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);

								view.dispose();
								view.setVisible(false);
								return;

							} else {
								JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);

							}
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
