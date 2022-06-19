package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageMembershipDialog;
import enums.RegexP;
import managers.MembershipManager;

public class CreateMembershipController{


private MembershipManager membershipModel;
private ManageMembershipDialog view;


public CreateMembershipController(ManageMembershipDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.membershipModel = MembershipManager.getInstance();
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
			String name = view.getNameTextField().getText().trim();
			String price = view.getPriceTextField().getText().trim();
			
			
			emptyCheckList.addAll(Arrays.asList(id, "false", name, price));
			
			if (emptyCheckList.contains("")) {
				JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
			} else {
				
				Pattern idPattern = Pattern.compile(RegexP.ID.pattern);
				Pattern namePattern = Pattern.compile(RegexP.NAME.pattern);
				Pattern pricePattern = Pattern.compile(RegexP.NUMBER.pattern);
				
			
				if (!idPattern.matcher(id).find() ||
					!namePattern.matcher(name).find() ||
					!pricePattern.matcher(price).find()) {
					
					JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					try {
						if (membershipModel.createMembership(sb.toString().split("\\|"))) {
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

