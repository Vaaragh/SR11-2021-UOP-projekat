package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageBookDialog;
import enums.RegexP;
import managers.BookManager;
import managers.GenreManager;

public class CreateBookController {


	private BookManager bookModel;
	private ManageBookDialog view;

	
	public CreateBookController(ManageBookDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.bookModel = BookManager.getInstance();
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
				String author = view.getAuthorTextField().getText().trim();
				String description = view.getDescriptionTextArea().getText().trim();
				String genre = "";
				String type;
				try {
					type = GenreManager.getInstance().genreStatusList(false).get(view.getGenreTypeBox().getSelectedIndex()).getIdentification();
					genre = type;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String language = view.getLanguageComboBox().getSelectedItem().toString().trim();
				String publish = view.getPublishDateTextField().getText().trim();
				
				
				
				
				
				
				emptyCheckList.addAll(Arrays.asList(author, description, genre, id, "false", language, title, publish));
				
				if (emptyCheckList.contains("")) {
					JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
				} else {
					
					Pattern idPattern = Pattern.compile(RegexP.ID.pattern);

					Pattern wagePattern = Pattern.compile(RegexP.NUMBER.pattern);
				
					if (!idPattern.matcher(id).find()){
						
						JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
					} else {
						StringBuilder sb = new StringBuilder();
						for (String s: emptyCheckList) {
							sb.append(s + "|");
						}
						try {
							if (bookModel.createBook(sb.toString().split("\\|"))) {
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
