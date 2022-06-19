package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import enums.Binding;
import enums.Language;
import managers.BookManager;
import models.Book;
import models.BookCopy;
import net.miginfocom.swing.MigLayout;

public class ManageBookCopyDialog extends JDialog{
		
	private JPanel panelTop;
	private JPanel panelBottom;
	
	private JLabel idLabel;
	private JLabel titleLabel;
	
	private JLabel bookLabel;

	private JLabel pagesLabel;
	private JLabel printLabel;
	
	private JLabel bindingLabel;
	private JLabel languageLabel;
	private JLabel availableLabel;
	
	
	
	private JTextField idTextField;
	private JTextField titleTextField;
	private JComboBox<Book> bookBox;
	private JTextField pagesTextField;
	private JTextField printTextField;
	private JComboBox<Binding> bindingBox;
	private JComboBox<Language> languageBox;
	private JCheckBox availabelBox;
	
	
	
	private JButton submitBtn, cancelBtn;
	
	
	public ManageBookCopyDialog(JFrame parent, String title, boolean x, BookCopy bookCopy, boolean check) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update BookCopy", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineFilledComponents(bookCopy);
		this.setUneditable(bookCopy);
		
	}
	
	public ManageBookCopyDialog(JFrame parent, String title, boolean x, BookCopy bookCopy) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update BookCopy", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineFilledComponents(bookCopy);
		
	}
	
	public ManageBookCopyDialog(JFrame parent, String title, boolean x) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Create BookCopy", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.45));
		this.defineEmptyComponents();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineFilledComponents(BookCopy bookCopy) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		


		DefaultComboBoxModel bookNames = new DefaultComboBoxModel();
		for (Book book: BookManager.getInstance().bookStatusList(false)) {
			bookNames.addElement(book.getOriginalTitle());
		}
		
		this.idTextField = new JTextField(bookCopy.getIdentification());
		this.titleTextField = new JTextField(bookCopy.getTitle());
		
		this.bookBox = new JComboBox(bookNames);
		this.bookBox.setSelectedIndex(bookNames.getIndexOf(bookCopy.getBook().getOriginalTitle()));
		
		this.pagesTextField = new JTextField(String.valueOf(bookCopy.getNumberOfPages()));
		this.printTextField = new JTextField(String.valueOf(bookCopy.getPrintDate()));
		
		this.bindingBox = new JComboBox(Binding.values());
		this.bindingBox.setSelectedItem(bookCopy.getBinding());
		
		this.languageBox = new JComboBox(Language.values());
		this.languageBox.setSelectedItem(bookCopy.getPrintLanguage());
		this.availabelBox = new JCheckBox();
		this.availabelBox.setSelected(bookCopy.isAvailable());

		
		this.fillPanel();
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		DefaultComboBoxModel bookNames = new DefaultComboBoxModel();
		for (Book book: BookManager.getInstance().bookStatusList(false)) {
			bookNames.addElement(book.getOriginalTitle());
		}
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.titleTextField = new JTextField();
		
		this.bookBox = new JComboBox(bookNames);
		
		this.pagesTextField = new JTextField();
		this.printTextField = new JTextField();
		
		this.bindingBox = new JComboBox(Binding.values());
		
		this.languageBox = new JComboBox(Language.values());
		this.availabelBox = new JCheckBox();

		
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		
		this.idLabel = new JLabel("ID");
		this.titleLabel = new JLabel("Name");
		this.bookLabel = new JLabel("Book");
		this.pagesLabel = new JLabel("Pages");
		this.printLabel = new JLabel("Print Year");
		this.bindingLabel = new JLabel("Binding");
		this.languageLabel = new JLabel("Language");
		this.availableLabel = new JLabel("Available");
		
		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(titleLabel);
		panelTop.add(titleTextField, "width 50%, wrap");

		panelTop.add(bookLabel);
		panelTop.add(bookBox,"width 50%, wrap");
		
		panelTop.add(pagesLabel);
		panelTop.add(pagesTextField,"width 50%, wrap");
		
		panelTop.add(printLabel);
		panelTop.add(printTextField,"width 50%, wrap");
		
		panelTop.add(bindingLabel);
		panelTop.add(bindingBox, "width 50%, wrap");
		
		panelTop.add(languageLabel);
		panelTop.add(languageBox,"width 50%, wrap");
		
		panelTop.add(availableLabel);
		panelTop.add(availabelBox, "width 50%, wrap");
		
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(BookCopy bookCopy) {
		this.idTextField.setEditable(false);
		this.titleTextField.setEditable(false);
		this.idTextField.setEditable(false);
		this.bookBox.setEnabled(false);
		this.pagesTextField.setEditable(false);
		this.printTextField.setEditable(false);
		this.bindingBox.setEnabled(false);
		this.languageBox.setEnabled(false);
		
		
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

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JLabel getBookLabel() {
		return bookLabel;
	}

	public void setBookLabel(JLabel bookLabel) {
		this.bookLabel = bookLabel;
	}

	public JLabel getPagesLabel() {
		return pagesLabel;
	}

	public void setPagesLabel(JLabel pagesLabel) {
		this.pagesLabel = pagesLabel;
	}

	public JLabel getPrintLabel() {
		return printLabel;
	}

	public void setPrintLabel(JLabel printLabel) {
		this.printLabel = printLabel;
	}

	public JLabel getBindingLabel() {
		return bindingLabel;
	}

	public void setBindingLabel(JLabel bindingLabel) {
		this.bindingLabel = bindingLabel;
	}

	public JLabel getLanguageLabel() {
		return languageLabel;
	}

	public void setLanguageLabel(JLabel languageLabel) {
		this.languageLabel = languageLabel;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getTitleTextField() {
		return titleTextField;
	}

	public void setTitleTextField(JTextField titleTextField) {
		this.titleTextField = titleTextField;
	}

	public JComboBox<Book> getBookBox() {
		return bookBox;
	}

	public void setBookBox(JComboBox<Book> bookBox) {
		this.bookBox = bookBox;
	}

	public JTextField getPagesTextField() {
		return pagesTextField;
	}

	public void setPagesTextField(JTextField pagesTextField) {
		this.pagesTextField = pagesTextField;
	}

	public JTextField getPrintTextField() {
		return printTextField;
	}

	public void setPrintTextField(JTextField printTextField) {
		this.printTextField = printTextField;
	}

	public JComboBox<Binding> getBindingBox() {
		return bindingBox;
	}

	public void setBindingBox(JComboBox<Binding> bindingBox) {
		this.bindingBox = bindingBox;
	}

	public JComboBox<Language> getLanguageBox() {
		return languageBox;
	}

	public void setLanguageBox(JComboBox<Language> languageBox) {
		this.languageBox = languageBox;
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

	public JLabel getAvailableLabel() {
		return availableLabel;
	}

	public void setAvailableLabel(JLabel availableLabel) {
		this.availableLabel = availableLabel;
	}

	public JCheckBox getAvailabelBox() {
		return availabelBox;
	}

	public void setAvailabelBox(JCheckBox availabelBox) {
		this.availabelBox = availabelBox;
	}
	
	
	
}
