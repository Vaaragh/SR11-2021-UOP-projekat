package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import dialogWindows.ManageAdminDialog;
import dialogWindows.ManageBookCopyDialog;
import dialogWindows.ManageBookDialog;
import dialogWindows.ManageGenreDialog;
import dialogWindows.ManageLibrarianDialog;
import dialogWindows.ManageMemberDialog;
import dialogWindows.ManageMembershipDialog;
import dialogWindows.ManageRentalDialog;
import enums.RegexP;
import managers.AdminManager;
import managers.BookCopyManager;
import managers.BookManager;
import managers.GenreManager;
import managers.LibrarianManager;
import managers.LibraryManager;
import managers.MemberManager;
import managers.MembershipManager;
import managers.RentalManager;
import models.Admin;
import models.Book;
import models.BookCopy;
import models.Employee;
import models.Genre;
import models.Librarian;
import models.Member;
import models.Membership;
import models.Rental;
import tableModels.AdminTableModel;
import tableModels.BookCopyTableModel;
import tableModels.BookTableModel;
import tableModels.GenreTableModel;
import tableModels.LibrarianTableModel;
import tableModels.MemberTableModel;
import tableModels.MembershipTableModel;
import tableModels.RentalTableModel;
import view.MainView;



public class MainViewController {

	private MainView view;
	private Employee employee;
	
	public MainViewController(MainView view, Employee employee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view = view;
		this.employee = employee;
		if (employee instanceof Admin) {
			this.initAdminController();
		}else {
			this.initLibrarianController();
		}
	}
	
	public void initAdminController() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setVisible(true);
		this.initAdminTable();
		this.initAdminButtons();

		this.initLibrarianTable();
		this.initLibrarianButtons();
		
		this.initMembershipTable();
		this.initMembershipButtons();
		
		this.initMemberTable();
		this.initMemberButtons();
		
		this.initGenreTable();
		this.initGenreButtons();
		
		this.initBookTable();
		this.initBookButtons();
		
		this.initBookCopyTable();
		this.initBookCopyButtons();
		
		this.initRentalTable();
		this.initRentalButtons();
		this.initLibraryUpdate();	

	}
	
	public void initLibrarianController() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setVisible(true);
		
	
		this.initMemberTable();
		this.initMemberButtons();
	
	}
	

	public void initAdminTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setAdminTableModel(new AdminTableModel(AdminManager.getInstance().adminStatusList(false)));
		this.view.getAdminTable().setModel(this.view.getAdminTableModel());
	}
	
	public void initAdminButtons() {
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
		UpdateAdminController control = new UpdateAdminController(manageDialog,admin);
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
	
	
	///////
	
	


	public void initLibrarianTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setLibrarianTableModel(new LibrarianTableModel(LibrarianManager.getInstance().librarianStatusList(false)));
		this.view.getLibrarianTable().setModel(this.view.getLibrarianTableModel());
	}
	
	public void initLibrarianButtons() {
		this.view.getViewLibrarian().addActionListener(e -> {
			try {
				this.viewLibrarian();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateLibrarian().addActionListener(e -> {
			try {
				this.updateLibrarian();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddLibrarian().addActionListener(e -> {
			try {
				this.createLibrarian();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteLibrarian().addActionListener(e -> {
			try {
				this.deleteLibrarian();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewLibrarian() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getLibrarianTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Librarian librarian = LibrarianManager.getInstance().librarianStatusList(false).get(this.view.getLibrarianTable().getSelectedRow());
		ManageLibrarianDialog manageDialog = new ManageLibrarianDialog(this.view, "", true, librarian, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateLibrarian() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getLibrarianTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Librarian librarian = LibrarianManager.getInstance().librarianStatusList(false).get(this.view.getLibrarianTable().getSelectedRow());
		ManageLibrarianDialog manageDialog = new ManageLibrarianDialog(this.view, "", true, librarian);
		UpdateLibrarianController control = new UpdateLibrarianController(manageDialog,librarian);
		control.initController();
		this.initLibrarianTable();


	}
	
	public void createLibrarian() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageLibrarianDialog manageDialog = new ManageLibrarianDialog(this.view, "", true);
		CreateLibrarianController control = new CreateLibrarianController(manageDialog);
		control.initController();
		this.initLibrarianTable();


	}
	
	public void deleteLibrarian() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getLibrarianTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Librarian librarian = LibrarianManager.getInstance().librarianStatusList(false).get(this.view.getLibrarianTable().getSelectedRow());
		System.out.println(librarian);
		LibrarianManager.getInstance().deleteLibrarian(librarian.getIdentification());
		this.initLibrarianTable();
	}
	
	
	
	
	///////////////
	
	
	
	
	

	public void initMembershipTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setMembershipTableModel(new MembershipTableModel(MembershipManager.getInstance().membershipStatusList(false)));
		this.view.getMembershipTable().setModel(this.view.getMembershipTableModel());
	}
	
	public void initMembershipButtons() {
		this.view.getViewMembership().addActionListener(e -> {
			try {
				this.viewMembership();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateMembership().addActionListener(e -> {
			try {
				this.updateMembership();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddMembership().addActionListener(e -> {
			try {
				this.createMembership();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteMembership().addActionListener(e -> {
			try {
				this.deleteMembership();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewMembership() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMembershipTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Membership membership = MembershipManager.getInstance().membershipStatusList(false).get(this.view.getMembershipTable().getSelectedRow());
		ManageMembershipDialog manageDialog = new ManageMembershipDialog(this.view, "", true, membership, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateMembership() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMembershipTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Membership membership = MembershipManager.getInstance().membershipStatusList(false).get(this.view.getMembershipTable().getSelectedRow());
		ManageMembershipDialog manageDialog = new ManageMembershipDialog(this.view, "", true, membership);
		UpdateMembershipController control = new UpdateMembershipController(manageDialog,membership);
		control.initController();
		this.initMembershipTable();


	}
	
	public void createMembership() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageMembershipDialog manageDialog = new ManageMembershipDialog(this.view, "", true);
		CreateMembershipController control = new CreateMembershipController(manageDialog);
		control.initController();
		this.initMembershipTable();


	}
	
	public void deleteMembership() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMembershipTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Membership membership = MembershipManager.getInstance().membershipStatusList(false).get(this.view.getMembershipTable().getSelectedRow());
		System.out.println(membership);
		MembershipManager.getInstance().deleteMembership(membership.getIdentification());
		this.initMembershipTable();
	}
	
	
	
	//////////////
	
	
	
	public void initMemberTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setMemberTableModel(new MemberTableModel(MemberManager.getInstance().memberStatusList(false)));
		this.view.getMemberTable().setModel(this.view.getMemberTableModel());
	}
	
	public void initMemberButtons() {
		this.view.getViewMember().addActionListener(e -> {
			try {
				this.viewMember();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateMember().addActionListener(e -> {
			try {
				this.updateMember();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddMember().addActionListener(e -> {
			try {
				this.createMember();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteMember().addActionListener(e -> {
			try {
				this.deleteMember();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getExtendMembership().addActionListener(e -> {
			System.out.println("Nesto");
		});
	}
	
	public void viewMember() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMemberTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Member member = MemberManager.getInstance().memberStatusList(false).get(this.view.getMemberTable().getSelectedRow());
		ManageMemberDialog manageDialog = new ManageMemberDialog(this.view, "", true, member, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateMember() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMemberTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Member member = MemberManager.getInstance().memberStatusList(false).get(this.view.getMemberTable().getSelectedRow());
		ManageMemberDialog manageDialog = new ManageMemberDialog(this.view, "", true, member);
		UpdateMemberController control = new UpdateMemberController(manageDialog,member);
		control.initController();
		this.initMemberTable();


	}
	
	public void createMember() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageMemberDialog manageDialog = new ManageMemberDialog(this.view, "", true);
		CreateMemberController control = new CreateMemberController(manageDialog);
		control.initController();
		this.initMemberTable();


	}
	
	public void deleteMember() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getMemberTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Member member = MemberManager.getInstance().memberStatusList(false).get(this.view.getMemberTable().getSelectedRow());
		System.out.println(member);
		MemberManager.getInstance().deleteMember(member.getIdentification());
		this.initMemberTable();
	}
	
	
	
	/////////////
	
	public void initGenreTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setGenreTableModel(new GenreTableModel(GenreManager.getInstance().genreStatusList(false)));
		this.view.getGenreTable().setModel(this.view.getGenreTableModel());
	}
	
	public void initGenreButtons() {
		this.view.getViewGenre().addActionListener(e -> {
			try {
				this.viewGenre();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateGenre().addActionListener(e -> {
			try {
				this.updateGenre();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddGenre().addActionListener(e -> {
			try {
				this.createGenre();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteGenre().addActionListener(e -> {
			try {
				this.deleteGenre();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewGenre() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getGenreTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Genre genre = GenreManager.getInstance().genreStatusList(false).get(this.view.getGenreTable().getSelectedRow());
		ManageGenreDialog manageDialog = new ManageGenreDialog(this.view, "", true, genre, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateGenre() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getGenreTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Genre genre = GenreManager.getInstance().genreStatusList(false).get(this.view.getGenreTable().getSelectedRow());
		ManageGenreDialog manageDialog = new ManageGenreDialog(this.view, "", true, genre);
		UpdateGenreController control = new UpdateGenreController(manageDialog,genre);
		control.initController();
		this.initGenreTable();


	}
	
	public void createGenre() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageGenreDialog manageDialog = new ManageGenreDialog(this.view, "", true);
		CreateGenreController control = new CreateGenreController(manageDialog);
		control.initController();
		this.initGenreTable();


	}
	
	public void deleteGenre() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getGenreTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Genre genre = GenreManager.getInstance().genreStatusList(false).get(this.view.getGenreTable().getSelectedRow());
		System.out.println(genre);
		GenreManager.getInstance().deleteGenre(genre.getIdentification());
		this.initGenreTable();
	}
	


	
	////////////
	
	
	
	public void initBookTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setBookTableModel(new BookTableModel(BookManager.getInstance().bookStatusList(false)));
		this.view.getBookTable().setModel(this.view.getBookTableModel());
	}
	
	public void initBookButtons() {
		this.view.getViewBook().addActionListener(e -> {
			try {
				this.viewBook();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateBook().addActionListener(e -> {
			try {
				this.updateBook();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddBook().addActionListener(e -> {
			try {
				this.createBook();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteBook().addActionListener(e -> {
			try {
				this.deleteBook();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewBook() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Book book = BookManager.getInstance().bookStatusList(false).get(this.view.getBookTable().getSelectedRow());
		ManageBookDialog manageDialog = new ManageBookDialog(this.view, "", true, book, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateBook() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Book book = BookManager.getInstance().bookStatusList(false).get(this.view.getBookTable().getSelectedRow());
		ManageBookDialog manageDialog = new ManageBookDialog(this.view, "", true, book);
		UpdateBookController control = new UpdateBookController(manageDialog,book);
		control.initController();
		this.initBookTable();


	}
	
	public void createBook() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageBookDialog manageDialog = new ManageBookDialog(this.view, "", true);
		CreateBookController control = new CreateBookController(manageDialog);
		control.initController();
		this.initBookTable();


	}
	
	public void deleteBook() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Book book = BookManager.getInstance().bookStatusList(false).get(this.view.getBookTable().getSelectedRow());
		System.out.println(book);
		BookManager.getInstance().deleteBook(book.getIdentification());
		this.initBookTable();
	}
	

///////////////
	
	
	
	
	

	public void initBookCopyTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setBookCopyTableModel(new BookCopyTableModel(BookCopyManager.getInstance().bookCopyStatusList(false)));
		this.view.getBookCopyTable().setModel(this.view.getBookCopyTableModel());
	}
	
	public void initBookCopyButtons() {
		this.view.getViewBookCopy().addActionListener(e -> {
			try {
				this.viewBookCopy();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateBookCopy().addActionListener(e -> {
			try {
				this.updateBookCopy();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddBookCopy().addActionListener(e -> {
			try {
				this.createBookCopy();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteBookCopy().addActionListener(e -> {
			try {
				this.deleteBookCopy();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewBookCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookCopyTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		BookCopy bookCopy = BookCopyManager.getInstance().bookCopyStatusList(false).get(this.view.getBookCopyTable().getSelectedRow());
		ManageBookCopyDialog manageDialog = new ManageBookCopyDialog(this.view, "", true, bookCopy, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateBookCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookCopyTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		BookCopy bookCopy = BookCopyManager.getInstance().bookCopyStatusList(false).get(this.view.getBookCopyTable().getSelectedRow());
		ManageBookCopyDialog manageDialog = new ManageBookCopyDialog(this.view, "", true, bookCopy);
		UpdateBookCopyController control = new UpdateBookCopyController(manageDialog,bookCopy);
		control.initController();
		this.initBookCopyTable();


	}
	
	public void createBookCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageBookCopyDialog manageDialog = new ManageBookCopyDialog(this.view, "", true);
		CreateBookCopyController control = new CreateBookCopyController(manageDialog);
		control.initController();
		this.initBookCopyTable();


	}
	
	public void deleteBookCopy() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getBookCopyTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		BookCopy bookCopy = BookCopyManager.getInstance().bookCopyStatusList(false).get(this.view.getBookCopyTable().getSelectedRow());
		System.out.println(bookCopy);
		BookCopyManager.getInstance().deleteBookCopy(bookCopy.getIdentification());
		this.initBookCopyTable();
	}
	
	
	
	////////
	
	
	

	public void initRentalTable() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.view.setRentalTableModel(new RentalTableModel(RentalManager.getInstance().rentalStatusList(false)));
		this.view.getRentalTable().setModel(this.view.getRentalTableModel());
	}
	
	public void initRentalButtons() {
		this.view.getViewRental().addActionListener(e -> {
			try {
				this.viewRental();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getUpdateRental().addActionListener(e -> {
			try {
				this.updateRental();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getAddRental().addActionListener(e -> {
			try {
				this.createRental();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
		this.view.getDeleteRental().addActionListener(e -> {
			try {
				this.deleteRental();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e1) {
				e1.printStackTrace();
			}
		});;
	}
	
	public void viewRental() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getRentalTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to view", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Rental rental = RentalManager.getInstance().rentalStatusList(false).get(this.view.getRentalTable().getSelectedRow());
		ManageRentalDialog manageDialog = new ManageRentalDialog(this.view, "", true, rental, true);
		manageDialog.setVisible(true);
	}
	
	
	public void updateRental() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getRentalTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to update", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Rental rental = RentalManager.getInstance().rentalStatusList(false).get(this.view.getRentalTable().getSelectedRow());
		ManageRentalDialog manageDialog = new ManageRentalDialog(this.view, "", true, rental);
		UpdateRentalController control = new UpdateRentalController(manageDialog,rental);
		control.initController();
		this.initRentalTable();


	}
	
	public void createRental() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		ManageRentalDialog manageDialog = new ManageRentalDialog(this.view, "", true);
		CreateRentalController control = new CreateRentalController(manageDialog);
		control.initController();
		this.initRentalTable();


	}
	
	public void deleteRental() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		if (this.view.getRentalTable().getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null,"Must select row to delete", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Rental rental = RentalManager.getInstance().rentalStatusList(false).get(this.view.getRentalTable().getSelectedRow());
		System.out.println(rental);
		RentalManager.getInstance().deleteRental(rental.getIdentification());
		this.initRentalTable();
	}
	
	
	
	
	//////////////
	
	
	
	
	
	public void initLibraryUpdate() throws IllegalAccessException, InvocationTargetException, IOException {
		view.getLibNameField().setEditable(true);
		view.getLibAddressField().setEditable(true);
		view.getLibPhoneField().setEditable(true);
		view.getLibOpensField().setEditable(true);
		view.getLibClosesField().setEditable(true);
		this.view.getUpdateLibrary().setEnabled(true);
		this.view.getUpdateLibrary().addActionListener(new ActionListener() {
			LibraryManager libraryModel = LibraryManager.getInstance();
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> emptyCheckList = new ArrayList<String>();
				
				
				String id = libraryModel.retFirst().getIdentification();
				String name = view.getLibNameField().getText().trim();
				String address = view.getLibAddressField().getText().trim();
				String phone = view.getLibPhoneField().getText().trim();
				String opens = view.getLibOpensField().getText().trim();
				String closes = view.getLibClosesField().getText().trim();
				
				
				
				emptyCheckList.addAll(Arrays.asList(address, id, "false", name, opens, closes, phone));
				
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
							if (libraryModel.updateLibrary(sb.toString().split("\\|"), libraryModel.retFirst().getIdentification())) {
								JOptionPane.showMessageDialog(null,"Congration, you done it", "Yay!", JOptionPane.INFORMATION_MESSAGE);
								view.fillLibraryPanel();
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