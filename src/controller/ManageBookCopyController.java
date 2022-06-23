package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageBookCopyDialog;
import managers.BookCopyManager;
import managers.BookManager;
import models.BookCopy;
import tools.Validator;

public class ManageBookCopyController {


	private BookCopyManager bookCopyModel;
	private ManageBookCopyDialog view;
	private BookCopy bookCopy;
	private boolean updateOperation;

	
	public ManageBookCopyController(ManageBookCopyDialog view, BookCopy bookCopy) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookCopyModel = BookCopyManager.getInstance();
		this.bookCopy = bookCopy;
		this.updateOperation = true;
		initRegistrationChecker();
		initCancelBtn();
	}
	
	public ManageBookCopyController(ManageBookCopyDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookCopyModel = BookCopyManager.getInstance();
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
		if(!Validator.isUUIDFormat(info[1])) return 0;
		if(!Validator.isUUIDFormat(info[2])) return 1;
		if(!Validator.isNumberFormat(info[5])) return 2;
		if(!Validator.isYearFormat(info[6])) return 3;
		if(!Validator.isTextFormat(info[8])) return 4;
		
		return -1;
	}	
	
	public void initRegistrationChecker() {
		this.view.getSubmitBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> emptyCheckList = new ArrayList<String>();
				
				String id = view.getIdTextField().getText().trim();
				String title = view.getTitleTextField().getText().trim();
				String book = "";
				String temp;
				try {
					temp = BookManager.getInstance().bookStatusList(false).get(view.getBookBox().getSelectedIndex()).getIdentification();
					book = temp;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					e2.printStackTrace();
				}
				String pages = view.getPagesTextField().getText().trim();
				String year = view.getPrintTextField().getText().trim();
				String binding = view.getBindingBox().getSelectedItem().toString();
				String language = view.getLanguageBox().getSelectedItem().toString();
				String available = String.valueOf(view.getAvailabelBox().isSelected());
								
				emptyCheckList.addAll(Arrays.asList(binding, book, id, available, "false", pages, year, language, title));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {
					
					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					
					String[] infoArray = sb.toString().split("\\|");
					String[] errorName = {"Book Id", "ID", "Pages", "Print Year", "Title"};
					
					int errorIndex = validateFields(infoArray);
					if (errorIndex != -1) {
						JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						
						try {
							if(updateOperation) {
								if (bookCopyModel.updateBookCopy(sb.toString().split("\\|"), bookCopy.getIdentification())) {
									JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
									view.dispose();
									view.setVisible(false);
									return;
	
								} else {
									JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
								}
							}else {
								if (bookCopyModel.createBookCopy(sb.toString().split("\\|"))) {
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
