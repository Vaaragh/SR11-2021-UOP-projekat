package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
private JPanel panelBottom;

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


public ManageRentalDialog(JFrame parent, String title, boolean x, Rental rental, boolean check) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	super(parent, "Update Rental", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
	this.defineFilledComponents(rental);
	this.setUneditable(rental);
	
}

public ManageRentalDialog(JFrame parent, String title, boolean x, Rental rental) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	super(parent, "Update Rental", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
	this.defineFilledComponents(rental);
	
}

public ManageRentalDialog(JFrame parent, String title, boolean x) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	super(parent, "Create Rental", x);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(parent);
	this.panelTop = new JPanel(new MigLayout());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
	this.defineEmptyComponents();
}

@SuppressWarnings({ "unchecked", "rawtypes" })
private void defineFilledComponents(Rental rental) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.defineLabelsAndButtons();
	


	DefaultComboBoxModel employee = new DefaultComboBoxModel();
	for (Admin admin: AdminManager.getInstance().adminStatusList(false)) {
		employee.addElement(admin.getIdentification());
	}
	for (Librarian librarian : LibrarianManager.getInstance().librarianStatusList(false)) {
		employee.addElement(librarian.getIdentification());
	}
	
	DefaultComboBoxModel members = new DefaultComboBoxModel();
	for (Member member: MemberManager.getInstance().memberStatusList(false)) {
		members.addElement(member.getIdentification());
	}
	int[] indices = new int[rental.getBookList().keySet().size()];
	DefaultListModel<String> books = new DefaultListModel();
	int counter = 0;
	int posCounter = 0;
	for (BookCopy book: BookCopyManager.getInstance().bookCopyStatusList(false)) {
		books.addElement(book.getIdentification());
		for (BookCopy bk : rental.getBookList().values()) {
			if (book.getIdentification().equals(bk.getIdentification())) {
				indices[posCounter] = counter;
				posCounter += 1;
			}
		}
		counter += 1;
	}

	this.idTextField = new JTextField(rental.getIdentification());
	this.rentDateField = new JTextField(rental.getRentalDate().toString());
	this.dueDateField = new JTextField(rental.getDueDate().toString());
	this.employeeBox = new JComboBox(employee);
	employeeBox.setSelectedItem(rental.getEmployee().getIdentification());
	this.memberBox = new JComboBox(members);
	memberBox.setSelectedItem(rental.getMember().getIdentification());
	this.bookBox = new JList(books);
	bookBox.setSelectedIndices(indices);

	this.fillPanel();
	
}


@SuppressWarnings({ "rawtypes", "unchecked" })
private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
	this.defineLabelsAndButtons();
	DefaultComboBoxModel employee = new DefaultComboBoxModel();
	for (Admin admin: AdminManager.getInstance().adminStatusList(false)) {
		employee.addElement(admin.getIdentification());
	}
	for (Librarian librarian : LibrarianManager.getInstance().librarianStatusList(false)) {
		employee.addElement(librarian.getIdentification());
	}
	
	DefaultComboBoxModel members = new DefaultComboBoxModel();
	for (Member member: MemberManager.getInstance().memberStatusList(false)) {
		members.addElement(member.getIdentification());
	}

	DefaultListModel<String> books = new DefaultListModel();

	for (BookCopy book: BookCopyManager.getInstance().bookCopyStatusList(false)) {
		books.addElement(book.getIdentification());
	}
	
	
	this.idTextField = new JTextField(UUID.randomUUID().toString());
	this.rentDateField = new JTextField();
	this.dueDateField = new JTextField();
	this.employeeBox = new JComboBox(employee);
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

public JPanel getPanelTop() {
	return panelTop;
}

public void setPanelTop(JPanel panelTop) {
	this.panelTop = panelTop;
}

public JPanel getPanelBottom() {
	return panelBottom;
}

public void setPanelBottom(JPanel panelBottom) {
	this.panelBottom = panelBottom;
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


}
