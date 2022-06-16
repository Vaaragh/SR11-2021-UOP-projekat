package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.RegexP;
import managers.AdminManager;
import view.RegisterView;

public class RegisterController {
	
	private AdminManager adminModel;
	private RegisterView view;

	
	public RegisterController(RegisterView view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.adminModel = AdminManager.getInstance();
		initRegistrationChecker();
	}
	
	public void initController() {
		this.view.getFrame().setVisible(true);
	}
	
	public void initRegistrationChecker() {
		RegisterController rc = this;
		this.view.getRegisterBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Pattern idPattern = Pattern.compile(RegexP.ID.pattern);
				Matcher matcher = idPattern.matcher(view.getIdTextField().getText());

			}
			
		});
	}
}
