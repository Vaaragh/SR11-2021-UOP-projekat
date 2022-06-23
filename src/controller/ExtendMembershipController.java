package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import dialogWindows.ExtendMembershipDialog;
import managers.MemberManager;
import models.Member;
import tools.Validator;

public class ExtendMembershipController {
	
	private MemberManager memberModel;
	private ExtendMembershipDialog view;
	private Member member;
	private double price = 0;
	private int newLength;
	
	public ExtendMembershipController(ExtendMembershipDialog view, Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.memberModel = MemberManager.getInstance();
		this.member = member;
		this.newLength = member.getMembershipLength();
		initCancelBtn();
		initCalcBtn();
		
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
	public int validateFields(String info) {
		if(!Validator.isNumberFormat(info)) return 0;
		
		return -1;
	}
	
	private void initCalcBtn() {
		this.view.getCalcBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String info = view.getExtensionField().getText();
				String errorName = "Extension";
				int errorIndex = validateFields(info);
				if (errorIndex != -1) {
					JOptionPane.showMessageDialog(null,errorName +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					int extension = Integer.parseInt(info);
					int monthlyPrice = member.getMembershipType().getPrice();
					newLength += extension * 30;

					if(extension > 11) {
						price += extension * monthlyPrice * 0.8;
					} else if(extension > 5) {
						price += extension * monthlyPrice * 0.9;
					} else {
						price += extension * monthlyPrice;
					}
					JOptionPane.showMessageDialog(null,"Amount due: " + price + "\nNew Length: " + newLength, "Yay!", JOptionPane.INFORMATION_MESSAGE);
					view.getMembershipLengthTextField().setText(String.valueOf(newLength));
					view.getLastPayementTextField().setText(LocalDate.now().toString());
					view.getAmountDueField().setText(String.valueOf(price));
					initSubmitBtn();
					
				}
				
			}
			
		});
		
	}
	
	private void initSubmitBtn() {
		this.view.getSubmitBtn().setEnabled(true);
		this.view.getSubmitBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				
//				member.setLastPayment(LocalDate.now());
//				member.setMembershipLength(newLength);
				
				
				
				String[] infoArray = {
						member.getAdress(),
						member.getFamilyName(),
						member.getFirstName(),
						member.getGender().toString(),
						member.getIdentification(),
						"true",
						"false",
						member.getJmbg(),
						LocalDate.now().toString(),
						String.valueOf(newLength),
						member.getMembershipNumber(),
						member.getMembershipType().getIdentification()
				};
				try {
					if (memberModel.updateMember(infoArray,member.getIdentification())) {
						JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
						
						view.dispose();
						view.setVisible(false);
						return;
					}else {
						JOptionPane.showMessageDialog(null,"Unexpected error", "Error", JOptionPane.WARNING_MESSAGE);

					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					e2.printStackTrace();
				}				
			}
			
		});
	}

}
