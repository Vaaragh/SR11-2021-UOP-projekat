package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageMemberDialog;
import managers.MemberManager;
import managers.MembershipManager;
import models.Member;
import tools.Validator;

public class ManageMemberController {


	private MemberManager memberModel;
	private ManageMemberDialog view;
	private Member member;
	private boolean updateOperation;

	
	public ManageMemberController(ManageMemberDialog view, Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.memberModel = MemberManager.getInstance();
		this.member = member;
		this.updateOperation = true;
		initRegistrationChecker();
		initCancelBtn();
	}
	
	public ManageMemberController(ManageMemberDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.memberModel = MemberManager.getInstance();
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
	
	public int validateFields(String[] info) {
		if(!Validator.isTextFormat(info[0])) return 0;
		if(!Validator.isNameFormat(info[1])) return 1;
		if(!Validator.isNameFormat(info[2])) return 2;
		if(!Validator.isUUIDFormat(info[4])) return 3;
		if(!Validator.isJmbgFormat(info[7])) return 4;
		if(!Validator.isDateFormat(info[8])) return 5;
		if(!Validator.isNumberFormat(info[9])) return 6;
		if(!Validator.isUUIDFormat(info[10])) return 7;

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
				String membershipNumber = view.getMembershipNumberTextField().getText().trim();
				String lastPayement = view.getLastPayementTextField().getText().trim();
				String membershipLength = view.getMembershipLengthTextField().getText().trim();
				String membershipType = "";
				try {
					String type = MembershipManager.getInstance().membershipStatusList(false).get(view.getMembershipTypeBox().getSelectedIndex()).getIdentification();
					membershipType = type;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					e2.printStackTrace();
				}
				String active = String.valueOf(view.getActiveCheckBox().isSelected());

				emptyCheckList.addAll(Arrays.asList(address, lastName, firstName, gender, id, active, "false", jmbg, lastPayement, membershipLength, membershipNumber, membershipType));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {

					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					
					String[] infoArray = sb.toString().split("\\|");
					String[] errorName = {"Address", "Last name", "Name", "ID", "JMBG", "Last Payment", "Membership length", "Membership Id"};
					
					int errorIndex = validateFields(infoArray);
					if (errorIndex != -1) {
						JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						
						try {
							if(updateOperation) {
							
								if (memberModel.updateMember(infoArray,member.getIdentification())) {
									JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
									view.dispose();
									view.setVisible(false);
									return;
	
								} else {
									JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								if (Integer.parseInt(infoArray[9])>3) {
									JOptionPane.showMessageDialog(null,"Maximum membership length is 3 months on registration", "Error", JOptionPane.WARNING_MESSAGE);

								}else {
									if (memberModel.createMember(infoArray)) {
										JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
										view.dispose();
										view.setVisible(false);
										return;
	
									} else {
										JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
									}
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
