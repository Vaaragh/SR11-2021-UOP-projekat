package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

import dialogWindows.ManageAdminDialog;
import managers.AdminManager;
import models.Admin;
import tableModels.AdminTableModel;
import view.MainView;



public class MainViewController {

	private MainView view;
	
	public MainViewController(MainView view) {
		this.view = view;
	}
	
	public void initController() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setVisible(true);
		this.initAdminTable();
		this.intiAdminButtons();
	
		}
	

	public void initAdminTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setAdminTableModel(new AdminTableModel(AdminManager.getInstance().adminStatusList(false)));
		this.view.getAdminTable().setModel(this.view.getAdminTableModel());
	}
	
	public void intiAdminButtons() {
		this.view.getViewAdmin().addActionListener(e -> {
			try {
				this.viewAdmin();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateAdmin().addActionListener(e -> {
			try {
				this.updateAdmin();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddAdmin().addActionListener(e -> {
			try {
				this.createAdmin();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteAdmin().addActionListener(e -> {
			try {
				this.deleteAdmin();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewAdmin() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getAdminTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Admin admin = AdminManager.getInstance().adminStatusList(false).get(this.view.getAdminTable().getSelectedRow());
		ManageAdminDialog manageDialog = new ManageAdminDialog(this.view, "", true, admin, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateAdmin() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getAdminTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Admin admin = AdminManager.getInstance().adminStatusList(false).get(this.view.getAdminTable().getSelectedRow());
		ManageAdminDialog manageDialog = new ManageAdminDialog(this.view, "", true, admin);
		CreateAdminController control = new CreateAdminController(manageDialog);
		control.initController();
		this.initAdminTable();


	}
	
	public void createAdmin() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageAdminDialog manageDialog = new ManageAdminDialog(this.view, "", true);
		CreateAdminController control = new CreateAdminController(manageDialog);
		control.initController();
		this.initAdminTable();


	}
	
	public void deleteAdmin() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getAdminTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Admin admin = AdminManager.getInstance().adminStatusList(false).get(this.view.getAdminTable().getSelectedRow());
		System.out.println(admin);
		AdminManager.getInstance().deleteAdmin(admin.getIdentification());
		this.initAdminTable();
	}
	
}