package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import managers.LibraryManager;
import models.Admin;
import models.Employee;
import models.Library;
import net.miginfocom.swing.MigLayout;
import tableModels.AdminTableModel;
import tableModels.BookCopyTableModel;
import tableModels.BookTableModel;
import tableModels.GenreTableModel;
import tableModels.LibrarianTableModel;
import tableModels.MemberTableModel;
import tableModels.MembershipTableModel;
import tableModels.RentalTableModel;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabsPanel;
	
	private JPanel libraryPanel = new JPanel(new MigLayout("align 50% 20%"));
	private JPanel adminPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel librarianPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel membershipPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel memberPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel genrePanel = new JPanel(new MigLayout("align 50%"));
	private JPanel bookPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel bookCopyPanel = new JPanel(new MigLayout("align 50%"));
	private JPanel rentalPanel = new JPanel(new MigLayout("align 50%"));	
	
	private AdminTableModel adminTableModel;
	private LibrarianTableModel librarianTableModel;
	private MembershipTableModel membershipTableModel;
	private MemberTableModel memberTableModel;
	private GenreTableModel genreTableModel;
	private BookTableModel bookTableModel;
	private BookCopyTableModel bookCopyTableModel;
	private RentalTableModel rentalTableModel;
	
	
	private JTable adminTable;
	private JTable librarianTable;
	private JTable membershipTable;
	private JTable memberTable;
	private JTable genreTable;
	private JTable bookTable;
	private JTable bookCopyTable;
	private JTable rentalTable;
	
	private JScrollPane adminScrollPanel;
	private JScrollPane librarianScrollPanel;
	private JScrollPane membershipScrollPanel;
	private JScrollPane memberScrollPanel;
	private JScrollPane genreScrollPanel;
	private JScrollPane bookScrollPanel;
	private JScrollPane bookCopyScrollPanel;
	private JScrollPane rentalScrollPanel;
	
	private JPanel libraryContentPanel = new JPanel(new MigLayout());
	private JPanel adminContentPanel = new JPanel(new MigLayout());
	private JPanel librarianContentPanel = new JPanel(new MigLayout());
	private JPanel membershipContentPanel = new JPanel(new MigLayout());
	private JPanel memberContentPanel = new JPanel(new MigLayout());
	private JPanel genreContentPanel = new JPanel(new MigLayout());
	private JPanel bookContentPanel = new JPanel(new MigLayout());
	private JPanel bookCopyContentPanel = new JPanel(new MigLayout());
	private JPanel rentalContentPanel = new JPanel(new MigLayout());
	
	private JButton addAdmin = new JButton("Add");        
	private JButton deleteAdmin = new JButton("Remove");  
	private JButton updateAdmin = new JButton("Update");  
	private JButton viewAdmin = new JButton("View");      
	
	private JButton addLibrarian = new JButton("Add");        
	private JButton deleteLibrarian = new JButton("Remove");  
	private JButton updateLibrarian = new JButton("Update");  
	private JButton viewLibrarian = new JButton("View");      

	private JButton addMembership = new JButton("Add");        
	private JButton deleteMembership = new JButton("Remove");  
	private JButton updateMembership = new JButton("Update");  
	private JButton viewMembership = new JButton("View");      
	
	private JButton addMember = new JButton("Add");        
	private JButton deleteMember = new JButton("Remove");  
	private JButton updateMember = new JButton("Update");  
	private JButton viewMember = new JButton("View");      
	private JButton extendMembership = new JButton("Extend Membership");

	private JButton addGenre = new JButton("Add");        
	private JButton deleteGenre = new JButton("Remove");  
	private JButton updateGenre = new JButton("Update");  
	private JButton viewGenre = new JButton("View");      
	
	private JButton addBook = new JButton("Add");        
	private JButton deleteBook = new JButton("Remove");  
	private JButton updateBook = new JButton("Update");  
	private JButton viewBook = new JButton("View");      
	
	private JButton addBookCopy = new JButton("Add");        
	private JButton deleteBookCopy = new JButton("Remove");  
	private JButton updateBookCopy = new JButton("Update");  
	private JButton viewBookCopy = new JButton("View");      
	
	private JButton addRental = new JButton("Add");
	private JButton deleteRental = new JButton("Remove");
	private JButton updateRental = new JButton("Update");
	private JButton viewRental = new JButton("View");
	
	
	private JButton updateLibrary = new JButton("Update");
	
	private JLabel libName = new JLabel("Name");
	private JLabel libAddress = new JLabel("Address");
	private JLabel libPhoneNumber = new JLabel("Phone");
	private JLabel libOpens = new JLabel("Opens");
	private JLabel libCloses = new JLabel("Closes");
	public JTextField libNameField = new JTextField();
	public JTextField libAddressField = new JTextField();
	public JTextField libPhoneField = new JTextField();
	public JTextField libOpensField = new JTextField();
	public JTextField libClosesField = new JTextField();
	
	public MainView(Employee employee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		setTitle("Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.20), (int)(screenSize.getHeight()*0.20),(int)(screenSize.getWidth()*0.6), (int)(screenSize.getHeight()*0.6));		
		if (employee instanceof Admin) {
			createAdminTabs();	
		} else {
			createLibrarianTabs();
		}
		
		
	}
	
	public void createLibrarianTabs() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		tabsPanel = new JTabbedPane();
		getContentPane().add(tabsPanel, BorderLayout.CENTER);
		
		tabsPanel.addTab("Memberships", null, membershipPanel, "Show all memberships");
		tabsPanel.addTab("Members", null, memberPanel, "Show all books copy");
		tabsPanel.addTab("Genres", null, genrePanel, "Show all genres");
		tabsPanel.addTab("Books", null, bookPanel, "Show all memberships");
		tabsPanel.addTab("Book Copies", null, bookCopyPanel, "Show all memberships");
		tabsPanel.addTab("Rentals", null, rentalPanel, "Show library info");
		tabsPanel.addTab("Library",null, libraryPanel, "Show all users");
		
		fillLibraryPanel();
		disableLibraryEdit();
		
		createMembershipTable();
		createMemberTable();
		createGenreTable();
		createBookTable();
		createBookCopyTable();
		createRentalTable();
		
		
		fillButtonPanel(membershipPanel, membershipContentPanel, Arrays.asList(addMembership, deleteMembership, updateMembership, viewMembership), "Manage Memberships");
		fillButtonPanel(memberPanel, memberContentPanel, Arrays.asList(addMember, deleteMember, updateMember, viewMember, extendMembership), "Manage Members");
		fillButtonPanel(genrePanel, genreContentPanel, Arrays.asList(addGenre, deleteGenre, updateGenre, viewGenre), "Manage Genres");
		fillButtonPanel(bookPanel, bookContentPanel, Arrays.asList(addBook, deleteBook, updateBook, viewBook), "Manage Books");
		fillButtonPanel(bookCopyPanel, bookCopyContentPanel, Arrays.asList(addBookCopy, deleteBookCopy, updateBookCopy, viewBookCopy), "Manage Book Copies");
		fillButtonPanel(rentalPanel, rentalContentPanel, Arrays.asList(addRental, deleteRental, updateRental, viewRental), "Manage Rentals");
	
	}
	
	public void createAdminTabs() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		tabsPanel = new JTabbedPane();
		getContentPane().add(tabsPanel, BorderLayout.CENTER);
		
		tabsPanel.addTab("Admins", null, adminPanel, "Show all admins");
		tabsPanel.addTab("Librarians", null, librarianPanel, "Show all librarians");
		tabsPanel.addTab("Memberships", null, membershipPanel, "Show all memberships");
		tabsPanel.addTab("Members", null, memberPanel, "Show all members");
		tabsPanel.addTab("Genres", null, genrePanel, "Show all genres");
		tabsPanel.addTab("Books", null, bookPanel, "Show all books");
		tabsPanel.addTab("Book Copies", null, bookCopyPanel, "Show all book copies");
		tabsPanel.addTab("Rentals", null, rentalPanel, "Show library rentals");
		tabsPanel.addTab("Library",null, libraryPanel, "Show Library info");
		tabsPanel.getComponent(3).setVisible(false);
		
		fillLibraryPanel();
		
		createAdminTable();
		createLibrarianTable();
		createMembershipTable();
		createMemberTable();
		createGenreTable();
		createBookTable();
		createBookCopyTable();
		createRentalTable();
		
		
		fillButtonPanel(adminPanel,adminContentPanel, Arrays.asList(addAdmin, deleteAdmin, updateAdmin, viewAdmin), "Manage Admins");
		fillButtonPanel(librarianPanel, librarianContentPanel, Arrays.asList(addLibrarian, deleteLibrarian, updateLibrarian, viewLibrarian), "Manage Librarians");
		fillButtonPanel(membershipPanel, membershipContentPanel, Arrays.asList(addMembership, deleteMembership, updateMembership, viewMembership), "Manage Memberships");
		fillButtonPanel(memberPanel, memberContentPanel, Arrays.asList(addMember, deleteMember, updateMember, viewMember, extendMembership), "Manage Members");
		fillButtonPanel(genrePanel, genreContentPanel, Arrays.asList(addGenre, deleteGenre, updateGenre, viewGenre), "Manage Genres");
		fillButtonPanel(bookPanel, bookContentPanel, Arrays.asList(addBook, deleteBook, updateBook, viewBook), "Manage Books");
		fillButtonPanel(bookCopyPanel, bookCopyContentPanel, Arrays.asList(addBookCopy, deleteBookCopy, updateBookCopy, viewBookCopy), "Manage Book Copies");
		fillButtonPanel(rentalPanel, rentalContentPanel, Arrays.asList(addRental, deleteRental, updateRental, viewRental), "Manage Rentals");
	
	}
	
	private void fillButtonPanel(JPanel parent,JPanel panel, List<JButton> buttons, String title) {
		parent.add(panel);
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
		for (JButton button: buttons) {
			panel.add(button);
		}
	}
	
	public void fillLibraryPanel() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		libraryPanel.add(libraryContentPanel);
		libraryContentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Library Info"));

		libraryContentPanel.add(libName);
		libraryContentPanel.add(libNameField, "width 200, wrap");
		libraryContentPanel.add(libAddress);
		libraryContentPanel.add(libAddressField, "width 200, wrap");
		libraryContentPanel.add(libPhoneNumber);
		libraryContentPanel.add(libPhoneField, "width 200, wrap");
		libraryContentPanel.add(libOpens);
		libraryContentPanel.add(libOpensField, "width 200, wrap");
		libraryContentPanel.add(libCloses);
		libraryContentPanel.add(libClosesField, "width 200, wrap");
		
		libraryContentPanel.add(updateLibrary);
		
		Library library = LibraryManager.getInstance().retFirst();
		
		libNameField.setText(library.getName());
		libAddressField.setText(library.getAdress());
		libPhoneField.setText(library.getPhone());
		libOpensField.setText(String.valueOf(library.getOpenFrom()));
		libClosesField.setText(String.valueOf(library.getOpenUntill()));	
	}
	
	private void disableLibraryEdit() {
		libNameField.setEditable(false);
		libAddressField.setEditable(false);
		libPhoneField.setEditable(false);
		libOpensField.setEditable(false);
		libClosesField.setEditable(false);
		updateLibrary.setEnabled(false);
	}
	
	private void createAdminTable() {
		adminTable = new JTable();
		adminScrollPanel = new JScrollPane(adminTable);
		adminPanel.add(adminScrollPanel, "wrap");
	}	
	private void createLibrarianTable() {
		librarianTable = new JTable();
		librarianScrollPanel = new JScrollPane(librarianTable);
		librarianPanel.add(librarianScrollPanel, "wrap");
	}	
	private void createMembershipTable() {
		membershipTable = new JTable();
		membershipScrollPanel = new JScrollPane(membershipTable);
		membershipPanel.add(membershipScrollPanel, "wrap");
	}	
	private void createMemberTable() {
		memberTable = new JTable();
		memberScrollPanel = new JScrollPane(memberTable);
		memberPanel.add(memberScrollPanel, "wrap");
	}	
	private void createGenreTable() {
		genreTable = new JTable();
		genreScrollPanel = new JScrollPane(genreTable);
		genrePanel.add(genreScrollPanel, "wrap");
	}	
	private void createBookTable() {
		bookTable = new JTable();
		bookScrollPanel = new JScrollPane(bookTable);
		bookPanel.add(bookScrollPanel, "wrap");
	}	
	private void createBookCopyTable() {
		bookCopyTable = new JTable();
		bookCopyScrollPanel = new JScrollPane(bookCopyTable);
		bookCopyPanel.add(bookCopyScrollPanel, "wrap");
	}	
	private void createRentalTable() {
		rentalTable = new JTable();
		rentalScrollPanel = new JScrollPane(rentalTable);
		rentalPanel.add(rentalScrollPanel, "wrap");
	}	

	public JTabbedPane getTabsPanel() {
		return tabsPanel;
	}

	public void setTabsPanel(JTabbedPane tabsPanel) {
		this.tabsPanel = tabsPanel;
	}

	public JPanel getLibraryPanel() {
		return libraryPanel;
	}

	public void setLibraryPanel(JPanel libraryPanel) {
		this.libraryPanel = libraryPanel;
	}

	public JPanel getAdminPanel() {
		return adminPanel;
	}

	public void setAdminPanel(JPanel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public JPanel getLibrarianPanel() {
		return librarianPanel;
	}

	public void setLibrarianPanel(JPanel librarianPanel) {
		this.librarianPanel = librarianPanel;
	}

	public JPanel getMembershipPanel() {
		return membershipPanel;
	}

	public void setMembershipPanel(JPanel membershipPanel) {
		this.membershipPanel = membershipPanel;
	}

	public JPanel getMemberPanel() {
		return memberPanel;
	}

	public void setMemberPanel(JPanel memberPanel) {
		this.memberPanel = memberPanel;
	}

	public JPanel getGenrePanel() {
		return genrePanel;
	}

	public void setGenrePanel(JPanel genrePanel) {
		this.genrePanel = genrePanel;
	}

	public JPanel getBookPanel() {
		return bookPanel;
	}

	public void setBookPanel(JPanel bookPanel) {
		this.bookPanel = bookPanel;
	}

	public JPanel getBookCopyPanel() {
		return bookCopyPanel;
	}

	public void setBookCopyPanel(JPanel bookCopyPanel) {
		this.bookCopyPanel = bookCopyPanel;
	}

	public JPanel getRentalPanel() {
		return rentalPanel;
	}

	public void setRentalPanel(JPanel rentalPanel) {
		this.rentalPanel = rentalPanel;
	}

	public JTable getAdminTable() {
		return adminTable;
	}

	public void setAdminTable(JTable adminTable) {
		this.adminTable = adminTable;
	}
	

	public JTable getLibrarianTable() {
		return librarianTable;
	}

	public void setLibrarianTable(JTable librarianTable) {
		this.librarianTable = librarianTable;
	}

	public JTable getMembershipTable() {
		return membershipTable;
	}

	public void setMembershipTable(JTable membershipTable) {
		this.membershipTable = membershipTable;
	}

	public JTable getMemberTable() {
		return memberTable;
	}

	public void setMemberTable(JTable memberTable) {
		this.memberTable = memberTable;
	}

	public JTable getGenreTable() {
		return genreTable;
	}

	public void setGenreTable(JTable genreTable) {
		this.genreTable = genreTable;
	}

	public JTable getBookTable() {
		return bookTable;
	}

	public void setBookTable(JTable bookTable) {
		this.bookTable = bookTable;
	}

	public JTable getBookCopyTable() {
		return bookCopyTable;
	}

	public void setBookCopyTable(JTable bookCopyTable) {
		this.bookCopyTable = bookCopyTable;
	}

	public JTable getRentalTable() {
		return rentalTable;
	}

	public void setRentalTable(JTable rentalTable) {
		this.rentalTable = rentalTable;
	}

	public JScrollPane getAdminScrollPanel() {
		return adminScrollPanel;
	}

	public void setAdminScrollPanel(JScrollPane adminScrollPanel) {
		this.adminScrollPanel = adminScrollPanel;
	}

	public JScrollPane getLibrarianScrollPanel() {
		return librarianScrollPanel;
	}

	public void setLibrarianScrollPanel(JScrollPane librarianScrollPanel) {
		this.librarianScrollPanel = librarianScrollPanel;
	}

	public JScrollPane getMembershipScrollPanel() {
		return membershipScrollPanel;
	}

	public void setMembershipScrollPanel(JScrollPane membershipScrollPanel) {
		this.membershipScrollPanel = membershipScrollPanel;
	}

	public JScrollPane getMemberScrollPanel() {
		return memberScrollPanel;
	}

	public void setMemberScrollPanel(JScrollPane memberScrollPanel) {
		this.memberScrollPanel = memberScrollPanel;
	}

	public JScrollPane getGenreScrollPanel() {
		return genreScrollPanel;
	}

	public void setGenreScrollPanel(JScrollPane genreScrollPanel) {
		this.genreScrollPanel = genreScrollPanel;
	}

	public JScrollPane getBookScrollPanel() {
		return bookScrollPanel;
	}

	public void setBookScrollPanel(JScrollPane bookScrollPanel) {
		this.bookScrollPanel = bookScrollPanel;
	}

	public JScrollPane getBookCopyScrollPanel() {
		return bookCopyScrollPanel;
	}

	public void setBookCopyScrollPanel(JScrollPane bookCopyScrollPanel) {
		this.bookCopyScrollPanel = bookCopyScrollPanel;
	}

	public JScrollPane getRentalScrollPanel() {
		return rentalScrollPanel;
	}

	public void setRentalScrollPanel(JScrollPane rentalScrollPanel) {
		this.rentalScrollPanel = rentalScrollPanel;
	}

	public JPanel getLibraryContentPanel() {
		return libraryContentPanel;
	}

	public void setLibraryContentPanel(JPanel libraryContentPanel) {
		this.libraryContentPanel = libraryContentPanel;
	}

	public JPanel getAdminContentPanel() {
		return adminContentPanel;
	}

	public void setAdminContentPanel(JPanel adminContentPanel) {
		this.adminContentPanel = adminContentPanel;
	}

	public JPanel getLibrarianContentPanel() {
		return librarianContentPanel;
	}

	public void setLibrarianContentPanel(JPanel librarianContentPanel) {
		this.librarianContentPanel = librarianContentPanel;
	}

	public JPanel getMembershipContentPanel() {
		return membershipContentPanel;
	}

	public void setMembershipContentPanel(JPanel membershipContentPanel) {
		this.membershipContentPanel = membershipContentPanel;
	}

	public JPanel getMemberContentPanel() {
		return memberContentPanel;
	}

	public void setMemberContentPanel(JPanel memberContentPanel) {
		this.memberContentPanel = memberContentPanel;
	}

	public JPanel getGenreContentPanel() {
		return genreContentPanel;
	}

	public void setGenreContentPanel(JPanel genreContentPanel) {
		this.genreContentPanel = genreContentPanel;
	}

	public JPanel getBookContentPanel() {
		return bookContentPanel;
	}

	public void setBookContentPanel(JPanel bookContentPanel) {
		this.bookContentPanel = bookContentPanel;
	}

	public JPanel getBookCopyContentPanel() {
		return bookCopyContentPanel;
	}

	public void setBookCopyContentPanel(JPanel bookCopyContentPanel) {
		this.bookCopyContentPanel = bookCopyContentPanel;
	}

	public JPanel getRentalContentPanel() {
		return rentalContentPanel;
	}

	public void setRentalContentPanel(JPanel rentalContentPanel) {
		this.rentalContentPanel = rentalContentPanel;
	}

	public JButton getAddAdmin() {
		return addAdmin;
	}

	public void setAddAdmin(JButton addAdmin) {
		this.addAdmin = addAdmin;
	}

	public JButton getDeleteAdmin() {
		return deleteAdmin;
	}

	public void setDeleteAdmin(JButton deleteAdmin) {
		this.deleteAdmin = deleteAdmin;
	}

	public JButton getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(JButton updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	public JButton getViewAdmin() {
		return viewAdmin;
	}

	public void setViewAdmin(JButton viewAdmin) {
		this.viewAdmin = viewAdmin;
	}

	public JButton getAddLibrarian() {
		return addLibrarian;
	}

	public void setAddLibrarian(JButton addLibrarian) {
		this.addLibrarian = addLibrarian;
	}

	public JButton getDeleteLibrarian() {
		return deleteLibrarian;
	}

	public void setDeleteLibrarian(JButton deleteLibrarian) {
		this.deleteLibrarian = deleteLibrarian;
	}

	public JButton getUpdateLibrarian() {
		return updateLibrarian;
	}

	public void setUpdateLibrarian(JButton updateLibrarian) {
		this.updateLibrarian = updateLibrarian;
	}

	public JButton getViewLibrarian() {
		return viewLibrarian;
	}

	public void setViewLibrarian(JButton viewLibrarian) {
		this.viewLibrarian = viewLibrarian;
	}

	public JButton getAddMembership() {
		return addMembership;
	}

	public void setAddMembership(JButton addMembership) {
		this.addMembership = addMembership;
	}

	public JButton getDeleteMembership() {
		return deleteMembership;
	}

	public void setDeleteMembership(JButton deleteMembership) {
		this.deleteMembership = deleteMembership;
	}

	public JButton getUpdateMembership() {
		return updateMembership;
	}

	public void setUpdateMembership(JButton updateMembership) {
		this.updateMembership = updateMembership;
	}

	public JButton getViewMembership() {
		return viewMembership;
	}

	public void setViewMembership(JButton viewMembership) {
		this.viewMembership = viewMembership;
	}

	public JButton getAddMember() {
		return addMember;
	}

	public void setAddMember(JButton addMember) {
		this.addMember = addMember;
	}

	public JButton getDeleteMember() {
		return deleteMember;
	}

	public void setDeleteMember(JButton deleteMember) {
		this.deleteMember = deleteMember;
	}

	public JButton getUpdateMember() {
		return updateMember;
	}

	public void setUpdateMember(JButton updateMember) {
		this.updateMember = updateMember;
	}

	public JButton getViewMember() {
		return viewMember;
	}

	public void setViewMember(JButton viewMember) {
		this.viewMember = viewMember;
	}

	public JButton getExtendMembership() {
		return extendMembership;
	}

	public void setExtendMembership(JButton extendMembership) {
		this.extendMembership = extendMembership;
	}

	public JButton getAddGenre() {
		return addGenre;
	}

	public void setAddGenre(JButton addGenre) {
		this.addGenre = addGenre;
	}

	public JButton getDeleteGenre() {
		return deleteGenre;
	}

	public void setDeleteGenre(JButton deleteGenre) {
		this.deleteGenre = deleteGenre;
	}

	public JButton getUpdateGenre() {
		return updateGenre;
	}

	public void setUpdateGenre(JButton updateGenre) {
		this.updateGenre = updateGenre;
	}

	public JButton getViewGenre() {
		return viewGenre;
	}

	public void setViewGenre(JButton viewGenre) {
		this.viewGenre = viewGenre;
	}

	public JButton getAddBook() {
		return addBook;
	}

	public void setAddBook(JButton addBook) {
		this.addBook = addBook;
	}

	public JButton getDeleteBook() {
		return deleteBook;
	}

	public void setDeleteBook(JButton deleteBook) {
		this.deleteBook = deleteBook;
	}

	public JButton getUpdateBook() {
		return updateBook;
	}

	public void setUpdateBook(JButton updateBook) {
		this.updateBook = updateBook;
	}

	public JButton getViewBook() {
		return viewBook;
	}

	public void setViewBook(JButton viewBook) {
		this.viewBook = viewBook;
	}

	public JButton getAddBookCopy() {
		return addBookCopy;
	}

	public void setAddBookCopy(JButton addBookCopy) {
		this.addBookCopy = addBookCopy;
	}

	public JButton getDeleteBookCopy() {
		return deleteBookCopy;
	}

	public void setDeleteBookCopy(JButton deleteBookCopy) {
		this.deleteBookCopy = deleteBookCopy;
	}

	public JButton getUpdateBookCopy() {
		return updateBookCopy;
	}

	public void setUpdateBookCopy(JButton updateBookCopy) {
		this.updateBookCopy = updateBookCopy;
	}

	public JButton getViewBookCopy() {
		return viewBookCopy;
	}

	public void setViewBookCopy(JButton viewBookCopy) {
		this.viewBookCopy = viewBookCopy;
	}

	public JButton getAddRental() {
		return addRental;
	}

	public void setAddRental(JButton addRental) {
		this.addRental = addRental;
	}

	public JButton getDeleteRental() {
		return deleteRental;
	}

	public void setDeleteRental(JButton deleteRental) {
		this.deleteRental = deleteRental;
	}

	public JButton getUpdateRental() {
		return updateRental;
	}

	public void setUpdateRental(JButton updateRental) {
		this.updateRental = updateRental;
	}

	public JButton getViewRental() {
		return viewRental;
	}

	public void setViewRental(JButton viewRental) {
		this.viewRental = viewRental;
	}

	public JButton getUpdateLibrary() {
		return updateLibrary;
	}

	public void setUpdateLibrary(JButton updateLibrary) {
		this.updateLibrary = updateLibrary;
	}

	public JLabel getLibName() {
		return libName;
	}

	public void setLibName(JLabel libName) {
		this.libName = libName;
	}

	public JLabel getLibAddress() {
		return libAddress;
	}

	public void setLibAddress(JLabel libAddress) {
		this.libAddress = libAddress;
	}

	public JLabel getLibPhoneNumber() {
		return libPhoneNumber;
	}

	public void setLibPhoneNumber(JLabel libPhoneNumber) {
		this.libPhoneNumber = libPhoneNumber;
	}

	public JLabel getLibOpens() {
		return libOpens;
	}

	public void setLibOpens(JLabel libOpens) {
		this.libOpens = libOpens;
	}

	public JLabel getLibCloses() {
		return libCloses;
	}

	public void setLibCloses(JLabel libCloses) {
		this.libCloses = libCloses;
	}

	public JTextField getLibNameField() {
		return libNameField;
	}

	public void setLibNameField(JTextField libNameField) {
		this.libNameField = libNameField;
	}

	public JTextField getLibAddressField() {
		return libAddressField;
	}

	public void setLibAddressField(JTextField libAddressField) {
		this.libAddressField = libAddressField;
	}

	public JTextField getLibPhoneField() {
		return libPhoneField;
	}

	public void setLibPhoneField(JTextField libPhoneField) {
		this.libPhoneField = libPhoneField;
	}

	public JTextField getLibOpensField() {
		return libOpensField;
	}

	public void setLibOpensField(JTextField libOpensField) {
		this.libOpensField = libOpensField;
	}

	public JTextField getLibClosesField() {
		return libClosesField;
	}

	public void setLibClosesField(JTextField libClosesField) {
		this.libClosesField = libClosesField;
	}

	public AdminTableModel getAdminTableModel() {
		return adminTableModel;
	}

	public void setAdminTableModel(AdminTableModel adminTableModel) {
		this.adminTableModel = adminTableModel;
	}

	public LibrarianTableModel getLibrarianTableModel() {
		return librarianTableModel;
	}

	public void setLibrarianTableModel(LibrarianTableModel librarianTableModel) {
		this.librarianTableModel = librarianTableModel;
	}

	public MembershipTableModel getMembershipTableModel() {
		return membershipTableModel;
	}

	public void setMembershipTableModel(MembershipTableModel membershipTableModel) {
		this.membershipTableModel = membershipTableModel;
	}

	public MemberTableModel getMembertableModel() {
		return memberTableModel;
	}

	public void setMembertableModel(MemberTableModel membertableModel) {
		this.memberTableModel = membertableModel;
	}

	public MemberTableModel getMemberTableModel() {
		return memberTableModel;
	}

	public void setMemberTableModel(MemberTableModel memberTableModel) {
		this.memberTableModel = memberTableModel;
	}

	public GenreTableModel getGenreTableModel() {
		return genreTableModel;
	}

	public void setGenreTableModel(GenreTableModel genreTableModel) {
		this.genreTableModel = genreTableModel;
	}

	public BookTableModel getBookTableModel() {
		return bookTableModel;
	}

	public void setBookTableModel(BookTableModel bookTableModel) {
		this.bookTableModel = bookTableModel;
	}

	public BookCopyTableModel getBookCopyTableModel() {
		return bookCopyTableModel;
	}

	public void setBookCopyTableModel(BookCopyTableModel bookCopyTableModel) {
		this.bookCopyTableModel = bookCopyTableModel;
	}

	public RentalTableModel getRentalTableModel() {
		return rentalTableModel;
	}

	public void setRentalTableModel(RentalTableModel rentalTableModel) {
		this.rentalTableModel = rentalTableModel;
	}
	
	
	
	////////
	
	
 
	
	
	
	
	
}
