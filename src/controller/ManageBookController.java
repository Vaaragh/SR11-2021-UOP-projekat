package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageBookDialog;
import managers.BookManager;
import managers.GenreManager;
import models.Book;
import tools.Validator;

public class ManageBookController {


	private BookManager bookModel;
	private ManageBookDialog view;
	private Book book;
	private boolean updateOperation;


	
	public ManageBookController(ManageBookDialog view, Book book) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookModel = BookManager.getInstance();
		this.book = book;
		this.updateOperation = true;
		initRegistrationChecker();
		initCancelBtn();
	}
	
	public ManageBookController(ManageBookDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookModel = BookManager.getInstance();
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
		if(!Validator.isNameFormat(info[0])) return 0;
		if(!Validator.isTextFormat(info[1])) return 1;
		if(!Validator.isUUIDFormat(info[3])) return 2;
		if(!Validator.isTextFormat(info[6])) return 3;
		if(!Validator.isYearFormat(info[7])) return 4;
		
		return -1;
	}
	
	public void initRegistrationChecker() {
		this.view.getSubmitBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> emptyCheckList = new ArrayList<String>();
				
				String id = view.getIdTextField().getText().trim();
				String title = view.getTitleTextField().getText().trim();
				String author = view.getAuthorTextField().getText().trim();
				String description = view.getDescriptionTextArea().getText().trim();
				String genre = "";
				String type;
				try {
					type = GenreManager.getInstance().genreStatusList(false).get(view.getGenreTypeBox().getSelectedIndex()).getIdentification();
					genre = type;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					e2.printStackTrace();
				}
				String language = view.getLanguageComboBox().getSelectedItem().toString().trim();
				String publish = view.getPublishDateTextField().getText().trim();
				
				emptyCheckList.addAll(Arrays.asList(author, description, genre, id, "false", language, title, publish));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {
					
					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}

					String[] infoArray = sb.toString().split("\\|");
					String[] errorName = {"Author", "Description", "ID", "Title", "Publish Year"};

					int errorIndex = validateFields(infoArray);
					if (errorIndex != -1) {
						JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						
						try { 
							if(updateOperation) {
								if (bookModel.updateBook(sb.toString().split("\\|"), book.getIdentification())) {
									JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
									view.dispose();
									view.setVisible(false);
									return;
	
								} else {
									JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
	
								}
							}else {
								if (bookModel.createBook(sb.toString().split("\\|"))) {
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
