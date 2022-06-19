package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageMemberDialog;
import enums.RegexP;
import managers.MemberManager;
import managers.MembershipManager;

public class CreateMemberController {


	private MemberManager memberModel;
	private ManageMemberDialog view;

	
	public CreateMemberController(ManageMemberDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.memberModel = MemberManager.getInstance();
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
					
					Pattern idPattern = Pattern.compile(RegexP.ID.pattern);
					Pattern namePattern = Pattern.compile(RegexP.NAME.pattern);
					Pattern jmbgPattern = Pattern.compile(RegexP.JMBG.pattern);
					Pattern textPattern = Pattern.compile(RegexP.TEXT.pattern);
					Pattern wagePattern = Pattern.compile(RegexP.NUMBER.pattern);
				
					if (!idPattern.matcher(id).find() ||
						!namePattern.matcher(firstName).find() ||
						!namePattern.matcher(lastName).find() ||
						!jmbgPattern.matcher(jmbg).find() ||
						!textPattern.matcher(address).find()){
						
						JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder();
						for (String s: emptyCheckList) {
							sb.append(s + "|");
						}
						try {
							if (memberModel.createMember(sb.toString().split("\\|"))) {
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
