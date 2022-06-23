package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import managers.AdminManager;
import managers.BookCopyManager;
import managers.LibrarianManager;
import managers.MemberManager;
import models.Admin;
import models.BookCopy;
import models.Employee;
import models.Librarian;
import models.Member;
import models.Rental;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ManageRentalDialog extends JDialog{
		
	private JPanel panelTop;
	
	private JLabel idLabel;
	private JLabel rentDateLabel;
	private JLabel dueDateLabel;
	private JLabel employeeLabel;
	private JLabel memberLabel;
	private JLabel bookLabel;
	
	private JTextField idTextField;
	private JTextField rentDateField;
	private JTextField dueDateField;
	private JComboBox<Member> memberBox;
	private JComboBox<Employee> employeeBox;
	@SuppressWarnings("rawtypes")
	private JList bookBox;
	
	private JButton submitBtn, cancelBtn;

	
	private ArrayList<String> bookKeys = new ArrayList<String>();
	private ArrayList<BookCopy> bookObjects = new ArrayList<BookCopy>();
	private ArrayList<String> rentalBookKeys = new ArrayList<String>();
	
	private ArrayList<String> memberKeys = new ArrayList<String>();
	private ArrayList<Member> memberObjects = new ArrayList<Member>();
	private String memberId;
	
	private ArrayList<String> employeeKeys = new ArrayList<String>();
	private ArrayList<Employee> employeeObjects = new ArrayList<Employee>();
	private String employeeId;
	
	private String activeEmployee;
	
	
	public ManageRentalDialog(JFrame parent, String title, boolean x, Rental rental, boolean check, Employee employee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Rental", x);
		this.memberId = rental.getMember().getIdentification();
		this.employeeId = rental.getEmployee().getIdentification();
		this.activeEmployee = employee.getIdentification();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		findBookCopies(rental);
		fillBookListsView(rental);
		fillEmployeeLists();
		fillMemberLists();
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineFilledComponents(rental);
		this.setUneditable(rental);
		
	}
	
	public ManageRentalDialog(JFrame parent, String title, boolean x, Rental rental, Employee employee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Rental", x);
		this.memberId = rental.getMember().getIdentification();
		this.employeeId = rental.getEmployee().getIdentification();
		this.activeEmployee = employee.getIdentification();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		findBookCopies(rental);
		BookCopyManager.getInstance().freeUpBooks(rental);
		fillBookLists();
		fillEmployeeLists();
		fillMemberLists();
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineFilledComponents(rental);
		setUpdateConstants();
		
	}
	
	public ManageRentalDialog(JFrame parent, String title, boolean x, Employee employee) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Create Rental", x);
		this.activeEmployee = employee.getIdentification();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		fillBookLists();
		fillEmployeeLists();
		fillMemberLists();
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineEmptyComponents();
	}
	
	
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineFilledComponents(Rental rental) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		
	
		// Employee JComboBox Constructor
		DefaultComboBoxModel employees = new DefaultComboBoxModel();
		for (Employee employee: this.employeeObjects) {
			employees.addElement(employee.getFirstName());
		}
		int employeeIndex = 0;
		for (int i=0; i<this.employeeObjects.size(); i++) {
			if (this.employeeId.equals(this.employeeKeys.get(i))) {
				employeeIndex = i;
			}
		}		
		
		// Memeber JComboBox Constructor
		DefaultComboBoxModel members = new DefaultComboBoxModel();
		for (Member member: this.memberObjects) {
			members.addElement(member.getFirstName());
		}
		int memberIndex = 0;
		for (int i=0; i<this.memberObjects.size(); i++) {
			if (this.memberId.equals(this.memberObjects.get(i).getIdentification())) {
				memberIndex = i;
			}
		}
		
		
		
		// BookCopy JList Constructor
		DefaultListModel<String> books = new DefaultListModel();
		for (BookCopy book: this.bookObjects) {
			books.addElement(book.getTitle());
		}
		int[] bookIndices = new int[this.rentalBookKeys.size()];
		int counter = 0;
		for (int i=0; i< this.bookKeys.size();i++) {
			if (this.rentalBookKeys.contains(this.bookKeys.get(i))) {
				bookIndices[counter] = i;
				counter += 1;
			}
		}
		
	
		this.idTextField = new JTextField(rental.getIdentification());
		this.rentDateField = new JTextField(rental.getRentalDate().toString());
		this.dueDateField = new JTextField(rental.getDueDate().toString());
		this.employeeBox = new JComboBox(employees);
		employeeBox.setSelectedIndex(employeeIndex);
		this.memberBox = new JComboBox(members);
		memberBox.setSelectedIndex(memberIndex);
		this.bookBox = new JList(books);
		bookBox.setSelectedIndices(bookIndices);
	
		this.fillPanel();
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();

		// Employee JComboBox Constructor
		DefaultComboBoxModel employees = new DefaultComboBoxModel();
		for (Employee employee: this.employeeObjects) {
			employees.addElement(employee.getFirstName());
		}
		int employeeIndex = 0;
		for (int i=0; i<this.employeeObjects.size(); i++) {
			if (this.activeEmployee.equals(this.employeeKeys.get(i))) {
				employeeIndex = i;
			}
		}	
		
		
		
		// Memeber JComboBox Constructor
		DefaultComboBoxModel members = new DefaultComboBoxModel();
		for (Member member: this.memberObjects) {
			members.addElement(member.getFirstName());
		}
		
		
		
		// BookCopy JList Constructor
		DefaultListModel<String> books = new DefaultListModel();
		for (BookCopy book: this.bookObjects) {
			books.addElement(book.getTitle());
		}
		
		
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.rentDateField = new JTextField();
		this.dueDateField = new JTextField();
		this.employeeBox = new JComboBox(employees);
		employeeBox.setSelectedIndex(employeeIndex);
		this.memberBox = new JComboBox(members);
		this.bookBox = new JList(books);	
	
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		
		
	
		this.idLabel = new JLabel("ID");
		this.bookLabel = new JLabel("Books");
		this.rentDateLabel = new JLabel("Rent Date");
		this.dueDateLabel = new JLabel("Due Date");
		this.employeeLabel = new JLabel("Employee");
		this.memberLabel = new JLabel("Member");
	
		
		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
	
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(rentDateLabel);
		panelTop.add(rentDateField, "width 50%, wrap");
	
		panelTop.add(dueDateLabel);
		panelTop.add(dueDateField,"width 50%, wrap");
		
		panelTop.add(employeeLabel);
		panelTop.add(employeeBox,"width 50%, wrap");
		
		panelTop.add(memberLabel);
		panelTop.add(memberBox,"width 50%, wrap");
		
		panelTop.add(bookLabel);
		panelTop.add(bookBox, "width 50%, wrap");
	
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(Rental rental) {
		this.idTextField.setEditable(false);
		this.rentDateField.setEditable(false);
		this.dueDateField.setEditable(false);
		this.bookBox.setEnabled(false);
		this.memberBox.setEnabled(false);
		this.employeeBox.setEnabled(false);
		
		this.submitBtn.setEnabled(false);
		this.getCancelBtn().addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
			}
		
		});
		
		
	}
	
	public void setUpdateConstants() {
		this.idTextField.setEditable(false);
		this.rentDateField.setEditable(false);
		this.memberBox.setEnabled(false);
	}
	
	//ArrayList fillers
	
	private void fillBookListsView(Rental rental) {
		for (BookCopy bookCopy: rental.getBookList().values()) {
			this.bookKeys.add(bookCopy.getIdentification());
			this.bookObjects.add(bookCopy);
		}
	}
	
	private void fillBookLists() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		for (BookCopy bookCopy: BookCopyManager.getInstance().availableList()) {
			this.bookKeys.add(bookCopy.getIdentification());
			this.bookObjects.add(bookCopy);
		}
	}
	
	private void fillMemberLists() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		for (Member member: MemberManager.getInstance().membershipStatusList(true)) {
			this.memberKeys.add(member.getIdentification());
			this.memberObjects.add(member);
		}
	}
	
	private void fillEmployeeLists() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		for (Admin admin: AdminManager.getInstance().adminStatusList(false)) {
			this.employeeKeys.add(admin.getIdentification());
			this.employeeObjects.add(admin);
		}
		for (Librarian librarian: LibrarianManager.getInstance().librarianStatusList(false)) {
			this.employeeKeys.add(librarian.getIdentification());
			this.employeeObjects.add(librarian);
		}
	}
	
	
	//Object Id fillers
	
	private void findBookCopies(Rental rental) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.rentalBookKeys.addAll(rental.getBookList().keySet());		
	}
	
	public JPanel getPanelTop() {
		return panelTop;
	}
	
	public void setPanelTop(JPanel panelTop) {
		this.panelTop = panelTop;
	}
	
	public JLabel getIdLabel() {
		return idLabel;
	}
	
	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}
	
	public JLabel getRentDateLabel() {
		return rentDateLabel;
	}
	
	public void setRentDateLabel(JLabel rentDateLabel) {
		this.rentDateLabel = rentDateLabel;
	}
	
	public JLabel getDueDateLabel() {
		return dueDateLabel;
	}
	
	public void setDueDateLabel(JLabel dueDateLabel) {
		this.dueDateLabel = dueDateLabel;
	}
	
	public JLabel getEmployeeLabel() {
		return employeeLabel;
	}
	
	public void setEmployeeLabel(JLabel employeeLabel) {
		this.employeeLabel = employeeLabel;
	}
	
	public JLabel getMemberLabel() {
		return memberLabel;
	}
	
	public void setMemberLabel(JLabel memberLabel) {
		this.memberLabel = memberLabel;
	}
	
	public JLabel getBookLabel() {
		return bookLabel;
	}
	
	public void setBookLabel(JLabel bookLabel) {
		this.bookLabel = bookLabel;
	}
	
	public JTextField getIdTextField() {
		return idTextField;
	}
	
	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}
	
	public JTextField getRentDateField() {
		return rentDateField;
	}
	
	public void setRentDateField(JTextField rentDateField) {
		this.rentDateField = rentDateField;
	}
	
	public JTextField getDueDateField() {
		return dueDateField;
	}
	
	public void setDueDateField(JTextField dueDateField) {
		this.dueDateField = dueDateField;
	}
	
	public JComboBox<Member> getMemberBox() {
		return memberBox;
	}
	
	public void setMemberBox(JComboBox<Member> memberBox) {
		this.memberBox = memberBox;
	}
	
	public JComboBox<Employee> getEmployeeBox() {
		return employeeBox;
	}
	
	public void setEmployeeBox(JComboBox<Employee> employeeBox) {
		this.employeeBox = employeeBox;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public JList getBookBox() {
		return bookBox;
	}
	
	@SuppressWarnings("rawtypes")
	public void setBookBox(JList bookBox) {
		this.bookBox = bookBox;
	}
	
	public JButton getSubmitBtn() {
		return submitBtn;
	}
	
	public void setSubmitBtn(JButton submitBtn) {
		this.submitBtn = submitBtn;
	}
	
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	
	public void setCancelBtn(JButton cancelBtn) {
		this.cancelBtn = cancelBtn;
	}

	public ArrayList<String> getBookKeys() {
		return bookKeys;
	}

	public void setBookKeys(ArrayList<String> bookKeys) {
		this.bookKeys = bookKeys;
	}

	public ArrayList<BookCopy> getBookObjects() {
		return bookObjects;
	}

	public void setBookObjects(ArrayList<BookCopy> bookObjects) {
		this.bookObjects = bookObjects;
	}

	public ArrayList<String> getRentalBookKeys() {
		return rentalBookKeys;
	}

	public void setRentalBookKeys(ArrayList<String> rentalBookKeys) {
		this.rentalBookKeys = rentalBookKeys;
	}

	public ArrayList<String> getMemberKeys() {
		return memberKeys;
	}

	public void setMemberKeys(ArrayList<String> memberKeys) {
		this.memberKeys = memberKeys;
	}

	public ArrayList<Member> getMemberObjects() {
		return memberObjects;
	}

	public void setMemberObjects(ArrayList<Member> memberObjects) {
		this.memberObjects = memberObjects;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public ArrayList<String> getEmployeeKeys() {
		return employeeKeys;
	}

	public void setEmployeeKeys(ArrayList<String> employeeKeys) {
		this.employeeKeys = employeeKeys;
	}

	public ArrayList<Employee> getEmployeeObjects() {
		return employeeObjects;
	}

	public void setEmployeeObjects(ArrayList<Employee> employeeObjects) {
		this.employeeObjects = employeeObjects;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getActiveEmployee() {
		return activeEmployee;
	}

	public void setActiveEmployee(String activeEmployee) {
		this.activeEmployee = activeEmployee;
	}
	
	
	


}
