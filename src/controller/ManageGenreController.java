package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dialogWindows.ManageGenreDialog;
import managers.GenreManager;
import models.Genre;
import tools.Validator;

public class ManageGenreController {


private GenreManager genreModel;
private ManageGenreDialog view;
private Genre genre;
private boolean updateOperation;


public ManageGenreController(ManageGenreDialog view, Genre genre) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.genreModel = GenreManager.getInstance();
	this.genre = genre;
	this.updateOperation = true;
	initRegistrationChecker();
	initCancelBtn();
}

public ManageGenreController(ManageGenreDialog view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.genreModel = GenreManager.getInstance();
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
	if(!Validator.isUUIDFormat(info[2])) return 2;

	return -1;
}

public void initRegistrationChecker() {
	this.view.getSubmitBtn().addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<String> emptyCheckList = new ArrayList<String>();
			
			String id = view.getIdTextField().getText().trim();
			String name = view.getNameTextField().getText().trim();
			String description = view.getDescriptionTextArea().getText().trim();
			
			
			emptyCheckList.addAll(Arrays.asList(description, name, id, "false"));
			
			if (emptyCheckList.contains("")) {
				JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
			} else {
				
				StringBuilder sb = new StringBuilder();
				for (String s: emptyCheckList) {
					sb.append(s + "|");
				}
				

				String[] infoArray = sb.toString().split("\\|");
				String[] errorName = {"Description", "Name", "ID"};
				
				int errorIndex = validateFields(infoArray);
				if (errorIndex != -1) {
					JOptionPane.showMessageDialog(null,errorName[errorIndex] +" format Error", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					
					try {
						if(updateOperation) {
							if (genreModel.updateGenre(sb.toString().split("\\|"), genre.getIdentification())) {
								JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
	
								view.dispose();
								view.setVisible(false);
								return;
	
							} else {
								JOptionPane.showMessageDialog(null,"Info collision", "Error", JOptionPane.WARNING_MESSAGE);
							}
						}else {
							if (genreModel.createGenre(sb.toString().split("\\|"))) {
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
