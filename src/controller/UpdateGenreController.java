package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageGenreDialog;
import enums.RegexP;
import managers.GenreManager;
import models.Genre;

public class UpdateGenreController {


private GenreManager genreModel;
private ManageGenreDialog view;
private Genre genre;


public UpdateGenreController(ManageGenreDialog view, Genre genre) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.view = view;
	this.genreModel = GenreManager.getInstance();
	this.genre = genre;
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
			String description = view.getDescriptionTextArea().getText().trim();
			
			
			emptyCheckList.addAll(Arrays.asList(description, name, id, "false"));
			
			if (emptyCheckList.contains("")) {
				JOptionPane.showMessageDialog(null,"All fields are required.", "Error", JOptionPane.WARNING_MESSAGE);				
			} else {
				
				Pattern idPattern = Pattern.compile(RegexP.ID.pattern);
				Pattern namePattern = Pattern.compile(RegexP.NAME.pattern);
				Pattern descriptionPattern = Pattern.compile(RegexP.TEXT.pattern);
				
			
				if (!idPattern.matcher(id).find() ||
					!namePattern.matcher(name).find() ||
					!descriptionPattern.matcher(description).find()) {
					
					JOptionPane.showMessageDialog(null,"One or more type mismatches", "Error", JOptionPane.WARNING_MESSAGE);
				} else {
					StringBuilder sb = new StringBuilder();
					for (String s: emptyCheckList) {
						sb.append(s + "|");
					}
					try {
						if (genreModel.updateGenre(sb.toString().split("\\|"), genre.getIdentification())) {
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
