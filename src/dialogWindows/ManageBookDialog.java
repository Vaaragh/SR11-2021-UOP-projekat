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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import enums.Language;
import managers.GenreManager;
import models.Book;
import models.Genre;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ManageBookDialog extends JDialog {
	
	private JPanel panelTop;
	
	private JLabel idLabel;
	private JLabel titleLabel;
	private JLabel authorLabel;
	private JLabel descriptionLabel;
	private JLabel genreLabel;
	private JLabel languageLabel;
	private JLabel publishLabel;
	
	private JTextField idTextField;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTextArea descriptionTextArea;
	private JComboBox<Genre> genreTypeBox;
	private JComboBox<Language> languageComboBox;
	private JTextField publishDateTextField;

	
	private JButton submitBtn, cancelBtn;

	
	
	public ManageBookDialog(JFrame parent, String title, boolean x, Book book, boolean check) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Book", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineFilledComponents(book);
		this.setUneditable(book);
		
	}
	
	public ManageBookDialog(JFrame parent, String title, boolean x, Book book) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Book", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineFilledComponents(book);
		
	}
	
	public ManageBookDialog(JFrame parent, String title, boolean x) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Create Book", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineEmptyComponents();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineFilledComponents(Book book) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		DefaultComboBoxModel genreNames = new DefaultComboBoxModel();
		for (Genre genre: GenreManager.getInstance().genreStatusList(false)) {
			genreNames.addElement(genre.getGenreName());
		}
		
		
		this.idTextField = new JTextField(book.getIdentification());
		this.titleTextField = new JTextField(book.getOriginalTitle());
		this.authorTextField = new JTextField(book.getAuthor()); 
		this.descriptionTextArea= new JTextArea(book.getDescription());
		
		this.genreTypeBox = new JComboBox<Genre>(genreNames);
		this.genreTypeBox.setSelectedItem(genreNames.getIndexOf(book.getGenre().getGenreName()));
		
		this.languageComboBox = new JComboBox<Language>(Language.values());
		this.languageComboBox.setSelectedItem(book.getOriginalLanguage());
		
		this.publishDateTextField = new JTextField(String.valueOf(book.getPublishDate()));
		
	
		
		this.fillPanel();
		
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		
		DefaultComboBoxModel genreNames = new DefaultComboBoxModel();
		for (Genre genre: GenreManager.getInstance().genreStatusList(false)) {
			genreNames.addElement(genre.getGenreName());
		}
		
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.titleTextField = new JTextField();
		this.authorTextField = new JTextField(); 
		this.descriptionTextArea= new JTextArea();
		this.genreTypeBox = new JComboBox<Genre>(genreNames);		
		this.languageComboBox = new JComboBox<Language>(Language.values());
		this.publishDateTextField = new JTextField();
		
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		this.idLabel = new JLabel("ID");
		this.titleLabel = new JLabel("Title");
		this.authorLabel = new JLabel("Author");
		this.descriptionLabel = new JLabel("Description");
		this.genreLabel = new JLabel("Genre");
		this.languageLabel = new JLabel("Language");
		this.publishLabel = new JLabel("Publish Date");
		
		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(titleLabel);
		panelTop.add(titleTextField, "width 50%, wrap");
		
		panelTop.add(authorLabel);
		panelTop.add(authorTextField, "width 50%, wrap");
		
		panelTop.add(descriptionLabel);
		panelTop.add(descriptionTextArea, "width 50%, wrap");
		
		panelTop.add(genreLabel);
		panelTop.add(genreTypeBox, "width 50%, wrap");
		
		panelTop.add(languageLabel);
		panelTop.add(languageComboBox, "width 50%, wrap");
		
		panelTop.add(publishLabel);
		panelTop.add(publishDateTextField, "width 50%, wrap");
		
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(Book book) {
		this.idTextField.setEditable(false);
		this.titleTextField.setEditable(false);
		this.authorTextField.setEditable(false);
		this.descriptionTextArea.setEditable(false);
		this.genreTypeBox.setEnabled(false); 
		this.languageComboBox.setEnabled(false);
		this.publishDateTextField.setEditable(false);

		

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

	public JLabel getAuthorLabel() {
		return authorLabel;
	}

	public void setAuthorLabel(JLabel authorLabel) {
		this.authorLabel = authorLabel;
	}

	public JLabel getDescriptionLabel() {
		return descriptionLabel;
	}

	public void setDescriptionLabel(JLabel descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}

	public JLabel getGenreLabel() {
		return genreLabel;
	}

	public void setGenreLabel(JLabel genreLabel) {
		this.genreLabel = genreLabel;
	}

	public JLabel getLanguageLabel() {
		return languageLabel;
	}

	public void setLanguageLabel(JLabel languageLabel) {
		this.languageLabel = languageLabel;
	}

	public JLabel getPublishLabel() {
		return publishLabel;
	}

	public void setPublishLabel(JLabel publishLabel) {
		this.publishLabel = publishLabel;
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

	public JTextField getAuthorTextField() {
		return authorTextField;
	}

	public void setAuthorTextField(JTextField authorTextField) {
		this.authorTextField = authorTextField;
	}

	public JTextArea getDescriptionTextArea() {
		return descriptionTextArea;
	}

	public void setDescriptionTextArea(JTextArea descriptionTextArea) {
		this.descriptionTextArea = descriptionTextArea;
	}

	public JComboBox<Genre> getGenreTypeBox() {
		return genreTypeBox;
	}

	public void setGenreTypeBox(JComboBox<Genre> genreTypeBox) {
		this.genreTypeBox = genreTypeBox;
	}

	public JComboBox<Language> getLanguageComboBox() {
		return languageComboBox;
	}

	public void setLanguageComboBox(JComboBox<Language> languageComboBox) {
		this.languageComboBox = languageComboBox;
	}

	public JTextField getPublishDateTextField() {
		return publishDateTextField;
	}

	public void setPublishDateTextField(JTextField publishDateTextField) {
		this.publishDateTextField = publishDateTextField;
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
