package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageBookCopyDialog;
import enums.RegexP;
import managers.BookCopyManager;
import managers.BookManager;
import models.BookCopy;

public class UpdateBookCopyController {


	private BookCopyManager bookCopyModel;
	private ManageBookCopyDialog view;
	private BookCopy bookCopy;

	
	public UpdateBookCopyController(ManageBookCopyDialog view, BookCopy bookCopy) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookCopyModel = BookCopyManager.getInstance();
		this.bookCopy = bookCopy;
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
				String title = view.getTitleTextField().getText().trim();
				String book = "";
				String temp;
				try {
					temp = BookManager.getInstance().bookStatusList(false).get(view.getBookBox().getSelectedIndex()).getIdentification();
					book = temp;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					// TODO Auto-generated catch block
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
					
					Pattern idPattern = Pattern.compile(RegexP.ID.pattern);

					
					if (!idPattern.matcher(id).find()){
						
						JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder();
						for (String s: emptyCheckList) {
							sb.append(s + "|");
						}
						try {
							if (bookCopyModel.updateBookCopy(sb.toString().split("\\|"), bookCopy.getIdentification())) {
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
