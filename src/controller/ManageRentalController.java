package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageRentalDialog;
import managers.BookCopyManager;
import managers.RentalManager;
import models.Rental;
import tools.Validator;

public class ManageRentalController {


	private RentalManager rentalModel;
	private ManageRentalDialog view;
	private Rental rental;
	private boolean updateOperation;

	
	public ManageRentalController(ManageRentalDialog view, Rental rental) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.rentalModel = RentalManager.getInstance();
		this.rental = rental;
		this.updateOperation = true;
		initRegistrationChecker();
		initCancelBtn();
	}
	
	public ManageRentalController(ManageRentalDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.rentalModel = RentalManager.getInstance();
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
				try {
					BookCopyManager.getInstance().reserveBooks(rental);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e1) {
					e1.printStackTrace();
				}
				view.dispose();
				view.setVisible(false);
			}
		
		});
	}
	
	public int validateFields(String[] info) {
		if(!Validator.isDateFormat(info[1])) return 0;
		if(!Validator.isUUIDFormat(info[3])) return 1;
		if(!Validator.isDateFormat(info[6])) return 2;
		
		return -1;
	}
	
	public void initRegistrationChecker() {
		this.view.getSubmitBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> emptyCheckList = new ArrayList<String>();
				
				String id = view.getIdTextField().getText().trim();
				String rentDate = view.getRentDateField().getText();
				String dueDate = view.getDueDateField().getText();
				
				String employee = view.getEmployeeKeys().get(view.getEmployeeBox().getSelectedIndex());
			
				String member = view.getMemberKeys().get(view.getMemberBox().getSelectedIndex());
				
				int[] books = view.getBookBox().getSelectedIndices();
				String bookList = "";
				for (int bk: books) {
					try {
						bookList += view.getBookKeys().get(bk) + ";";
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					}
				}
							
				emptyCheckList.addAll(Arrays.asList(bookList, dueDate, employee, id, "false", member, rentDate));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {

					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					
					String[] infoArray = sb.toString().split("\\|");
					String[] errorName = {"Due date", "ID", "Rent date"};
					
					int errorIndex = validateFields(infoArray);
					if (errorIndex != -1) {
						JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						
						try {
							if(updateOperation) {
								if (rentalModel.updateRental(sb.toString().split("\\|"), rental.getIdentification())) {
									JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
									view.dispose();
									view.setVisible(false);
									return;
	
								} else {
									JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								if (rentalModel.createRental(sb.toString().split("\\|"))) {
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
