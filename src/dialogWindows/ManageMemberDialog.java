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

import enums.Gender;
import managers.MembershipManager;
import models.Member;
import models.Membership;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ManageMemberDialog extends JDialog {
	
	
	
	private JPanel panelTop;
	
	private JLabel idLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel jmbgLabel;
	private JLabel adressLabel;
	private JLabel genderLabel;
	private JLabel membershipNumberLabel;
	private JLabel lastPaymentLabel;
	private JLabel membershipLengthLabel;
	private JLabel membershipTypeLabel;
	private JLabel activeLabel;
	
	
	private JTextField idTextField;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField jmbgTextField;
	private JTextField adressTextField;
	private JComboBox<Gender> genderComboBox;
	private JTextField membershipNumberTextField;
	private JTextField lastPayementTextField;
	private JTextField membershipLengthTextField;
	private JComboBox<Membership> membershipTypeBox;
	private JCheckBox activeCheckBox;
	
	
	private JButton submitBtn, cancelBtn;

	
	
	public ManageMemberDialog(JFrame parent, String title, boolean x, Member member, boolean check) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Member", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineFilledComponents(member);
		this.setUneditable(member);
		
	}
	
	public ManageMemberDialog(JFrame parent, String title, boolean x, Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Member", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineFilledComponents(member);
		setUpdateConstants();
		
	}
	
	public ManageMemberDialog(JFrame parent, String title, boolean x) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Create Member", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineEmptyComponents();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineFilledComponents(Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		DefaultComboBoxModel membershipNames = new DefaultComboBoxModel();
		for (Membership membership: MembershipManager.getInstance().membershipStatusList(false)) {
			membershipNames.addElement(membership.getName());
		}
		
		this.idTextField = new JTextField(member.getIdentification());
		this.firstNameTextField = new JTextField(member.getFirstName());
		this.lastNameTextField = new JTextField(member.getFamilyName()); 
		this.jmbgTextField = new JTextField(member.getJmbg());
		this.adressTextField = new JTextField(member.getAdress()); 
		
		this.genderComboBox = new JComboBox<Gender>(Gender.values());
		this.genderComboBox.setSelectedItem(member.getGender());
		
		this.membershipNumberTextField = new JTextField(member.getMembershipNumber());
		this.lastPayementTextField = new JTextField(member.getLastPayment().toString());
		this.membershipLengthTextField = new JTextField(String.valueOf(member.getMembershipLength()));
		this.membershipTypeBox = new JComboBox<Membership>(membershipNames);
		
		this.activeCheckBox = new JCheckBox();
		this.activeCheckBox.setSelected(member.isActive());
		
		
		this.fillPanel();
		
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		
		DefaultComboBoxModel membershipNames = new DefaultComboBoxModel();
		for (Membership membership: MembershipManager.getInstance().membershipStatusList(false)) {
			membershipNames.addElement(membership.getName());
		}
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.firstNameTextField = new JTextField();
		this.lastNameTextField = new JTextField(); 
		this.jmbgTextField = new JTextField();
		this.adressTextField = new JTextField(); 
		this.genderComboBox = new JComboBox<Gender>(Gender.values()); 
		this.membershipNumberTextField = new JTextField();
		this.lastPayementTextField = new JTextField();
		this.membershipLengthTextField = new JTextField();
		this.membershipTypeBox = new JComboBox<Membership>(membershipNames);
		this.activeCheckBox = new JCheckBox();
		
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		this.idLabel = new JLabel("ID");
		this.firstNameLabel = new JLabel("Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.jmbgLabel = new JLabel("JMBG");
		this.adressLabel = new JLabel("Adress");
		this.genderLabel = new JLabel("Gender");
		this.membershipNumberLabel = new JLabel("Mem. Number");
		this.lastPaymentLabel = new JLabel("Last Payement");
		this.membershipLengthLabel = new JLabel("Mem. Length");
		this.membershipTypeLabel = new JLabel("Mem. Type");
		this.activeLabel = new JLabel("Active");

		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(firstNameLabel);
		panelTop.add(firstNameTextField, "width 50%, wrap");
		
		panelTop.add(lastNameLabel);
		panelTop.add(lastNameTextField, "width 50%, wrap");
		
		panelTop.add(jmbgLabel);
		panelTop.add(jmbgTextField, "width 50%, wrap");
		
		panelTop.add(adressLabel);
		panelTop.add(adressTextField, "width 50%, wrap");
		
		panelTop.add(genderLabel);
		panelTop.add(genderComboBox, "width 50%, wrap");
		
		panelTop.add(membershipNumberLabel);
		panelTop.add(membershipNumberTextField, "width 50%, wrap");
		
		panelTop.add(lastPaymentLabel);
		panelTop.add(lastPayementTextField, "width 50%, wrap");
		
		panelTop.add(membershipLengthLabel);
		panelTop.add(membershipLengthTextField, "width 50%, wrap");
		
		panelTop.add(membershipTypeLabel);
		panelTop.add(membershipTypeBox, "width 50%, wrap");
		
		panelTop.add(activeLabel);
		panelTop.add(activeCheckBox, "width 50%, wrap");
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(Member member) {
		this.idTextField.setEditable(false);
		this.firstNameTextField.setEditable(false);
		this.lastNameTextField.setEditable(false);
		this.jmbgTextField.setEditable(false);
		this.adressTextField.setEditable(false); 
		this.genderComboBox.setEnabled(false);
		this.membershipNumberTextField.setEditable(false);
		this.lastPayementTextField.setEditable(false);
		this.membershipLengthTextField.setEditable(false);
		this.membershipTypeBox.setEnabled(false);
		this.activeCheckBox.setEnabled(false);
		

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
		this.jmbgTextField.setEditable(false);
		this.membershipNumberTextField.setEditable(false);
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

	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	public JLabel getLastNameLabel() {
		return lastNameLabel;
	}

	public void setLastNameLabel(JLabel lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	public JLabel getJmbgLabel() {
		return jmbgLabel;
	}

	public void setJmbgLabel(JLabel jmbgLabel) {
		this.jmbgLabel = jmbgLabel;
	}

	public JLabel getAdressLabel() {
		return adressLabel;
	}

	public void setAdressLabel(JLabel adressLabel) {
		this.adressLabel = adressLabel;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JLabel getMembershipNumberLabel() {
		return membershipNumberLabel;
	}

	public void setMembershipNumberLabel(JLabel membershipNumberLabel) {
		this.membershipNumberLabel = membershipNumberLabel;
	}

	public JLabel getLastPaymentLabel() {
		return lastPaymentLabel;
	}

	public void setLastPaymentLabel(JLabel lastPaymentLabel) {
		this.lastPaymentLabel = lastPaymentLabel;
	}

	public JLabel getMembershipLengthLabel() {
		return membershipLengthLabel;
	}

	public void setMembershipLengthLabel(JLabel membershipLengthLabel) {
		this.membershipLengthLabel = membershipLengthLabel;
	}

	public JLabel getMembershipTypeLabel() {
		return membershipTypeLabel;
	}

	public void setMembershipTypeLabel(JLabel membershipTypeLabel) {
		this.membershipTypeLabel = membershipTypeLabel;
	}

	public JLabel getActiveLabel() {
		return activeLabel;
	}

	public void setActiveLabel(JLabel activeLabel) {
		this.activeLabel = activeLabel;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	public void setLastNameTextField(JTextField lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	public JTextField getJmbgTextField() {
		return jmbgTextField;
	}

	public void setJmbgTextField(JTextField jmbgTextField) {
		this.jmbgTextField = jmbgTextField;
	}

	public JTextField getAdressTextField() {
		return adressTextField;
	}

	public void setAdressTextField(JTextField adressTextField) {
		this.adressTextField = adressTextField;
	}

	public JComboBox<Gender> getGenderComboBox() {
		return genderComboBox;
	}

	public void setGenderComboBox(JComboBox<Gender> genderComboBox) {
		this.genderComboBox = genderComboBox;
	}

	public JTextField getMembershipNumberTextField() {
		return membershipNumberTextField;
	}

	public void setMembershipNumberTextField(JTextField membershipNumberTextField) {
		this.membershipNumberTextField = membershipNumberTextField;
	}

	public JTextField getLastPayementTextField() {
		return lastPayementTextField;
	}

	public void setLastPayementTextField(JTextField lastPayementTextField) {
		this.lastPayementTextField = lastPayementTextField;
	}

	public JTextField getMembershipLengthTextField() {
		return membershipLengthTextField;
	}

	public void setMembershipLengthTextField(JTextField membershipLength) {
		this.membershipLengthTextField = membershipLength;
	}

	public JComboBox<Membership> getMembershipTypeBox() {
		return membershipTypeBox;
	}

	public void setMembershipTypeBox(JComboBox<Membership> membershipTypeBox) {
		this.membershipTypeBox = membershipTypeBox;
	}

	public JCheckBox getActiveCheckBox() {
		return activeCheckBox;
	}

	public void setActiveCheckBox(JCheckBox activeCheckBox) {
		this.activeCheckBox = activeCheckBox;
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
