package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageRentalDialog;
import enums.RegexP;
import managers.BookCopyManager;
import managers.RentalManager;

public class CreateRentalController  {


	private RentalManager rentalModel;
	private ManageRentalDialog view;

	
	public CreateRentalController(ManageRentalDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.rentalModel = RentalManager.getInstance();
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
					
					Pattern idPattern = Pattern.compile(RegexP.ID.pattern);

					
					if (!idPattern.matcher(id).find()){
						
						JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder();
						for (String s: emptyCheckList) {
							sb.append(s + "|");
						}
						try {
							if (rentalModel.createRental(sb.toString().split("\\|"))) {
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

