package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Member;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ExtendMembershipDialog extends JDialog {
	
	
	
	private JPanel panelTop;
	
	
	private JLabel priceLabel;
	private JLabel amountDueLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel lastPaymentLabel;
	private JLabel membershipLengthLabel;
	private JLabel membershipTypeLabel;
	
	
	private JLabel priceField;
	private JLabel amountDueField;
	private JLabel firstNameTextField;
	private JLabel lastNameTextField;
	private JLabel lastPayementTextField;
	private JLabel membershipLengthTextField;
	private JLabel membershipTypeField;
	
	private JLabel extensionLabel;
	private JTextField extensionField;
	
	private JButton submitBtn, cancelBtn, calcBtn;
	
	
	
	public ExtendMembershipDialog(JFrame parent, String title, boolean x, Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Extend Membership", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		fillElements(member);
		
	}
	
	
	
	

	private void fillElements(Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineElements();
		
		this.firstNameTextField = new JLabel(member.getFirstName());
		this.lastNameTextField = new JLabel(member.getFamilyName());
		this.membershipTypeField = new JLabel(member.getMembershipType().getName());
		this.priceField = new JLabel(String.valueOf(member.getMembershipType().getPrice()));
		this.lastPayementTextField = new JLabel(member.getLastPayment().toString());
		this.membershipLengthTextField = new JLabel(String.valueOf(member.getMembershipLength()));
	
		this.fillPanel();
	}
	
	private void defineElements() {
		this.firstNameLabel = new JLabel("Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.membershipTypeLabel = new JLabel("Mem. Type");
		this.priceLabel = new JLabel("Price per month");
		this.lastPaymentLabel = new JLabel("Last Payement");
		this.membershipLengthLabel = new JLabel("Mem. Length");
		this.amountDueLabel = new JLabel("Amount due");
		
		this.firstNameTextField = new JLabel();
		this.lastNameTextField = new JLabel();
		this.membershipTypeField = new JLabel();
		this.priceField = new JLabel();
		this.lastPayementTextField = new JLabel();
		this.membershipLengthTextField = new JLabel();
		this.amountDueField = new JLabel("0,00 RSD");
		
		this.extensionLabel = new JLabel("Extend by (Months)");
		this.extensionField = new JTextField();
		
		
		this.submitBtn = new JButton("Submit");
		this.submitBtn.setEnabled(false);
		this.cancelBtn = new JButton("Cancel");
		this.calcBtn = new JButton("Calculate");		
	}
	
	private void fillPanel() {
		
		panelTop.add(firstNameLabel);
		panelTop.add(firstNameTextField, "width 50%, wrap");
		
		panelTop.add(lastNameLabel);
		panelTop.add(lastNameTextField, "width 50%, wrap");
		
		panelTop.add(membershipTypeLabel);
		panelTop.add(membershipTypeField, "width 50%, wrap");
		
		panelTop.add(priceLabel);
		panelTop.add(priceField, "width 50%, wrap");
		
		panelTop.add(lastPaymentLabel);
		panelTop.add(lastPayementTextField, "width 50%, wrap");
		
		panelTop.add(membershipLengthLabel);
		panelTop.add(membershipLengthTextField, "width 50%, wrap");
		
		panelTop.add(extensionLabel);
		panelTop.add(extensionField, "width 50%, wrap");
		
		
		panelTop.add(amountDueLabel);
		panelTop.add(amountDueField, "width 50%, wrap");
		
		
		panelTop.add(calcBtn, "wrap");	
		panelTop.add(new JLabel(" "), "wrap");
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}

	public JPanel getPanelTop() {
		return panelTop;
	}

	public void setPanelTop(JPanel panelTop) {
		this.panelTop = panelTop;
	}

	public JLabel getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(JLabel priceLabel) {
		this.priceLabel = priceLabel;
	}

	public JLabel getAmountDueLabel() {
		return amountDueLabel;
	}

	public void setAmountDueLabel(JLabel amountDueLabel) {
		this.amountDueLabel = amountDueLabel;
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

	public JLabel getPriceField() {
		return priceField;
	}

	public void setPriceField(JLabel priceField) {
		this.priceField = priceField;
	}

	public JLabel getAmountDueField() {
		return amountDueField;
	}

	public void setAmountDueField(JLabel amountDueField) {
		this.amountDueField = amountDueField;
	}

	public JLabel getFirstNameTextField() {
		return firstNameTextField;
	}

	public void setFirstNameTextField(JLabel firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	public JLabel getLastNameTextField() {
		return lastNameTextField;
	}

	public void setLastNameTextField(JLabel lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	public JLabel getLastPayementTextField() {
		return lastPayementTextField;
	}

	public void setLastPayementTextField(JLabel lastPayementTextField) {
		this.lastPayementTextField = lastPayementTextField;
	}

	public JLabel getMembershipLengthTextField() {
		return membershipLengthTextField;
	}

	public void setMembershipLengthTextField(JLabel membershipLengthTextField) {
		this.membershipLengthTextField = membershipLengthTextField;
	}

	public JLabel getMembershipTypeField() {
		return membershipTypeField;
	}

	public void setMembershipTypeField(JLabel membershipTypeField) {
		this.membershipTypeField = membershipTypeField;
	}

	public JLabel getExtensionLabel() {
		return extensionLabel;
	}

	public void setExtensionLabel(JLabel extensionLabel) {
		this.extensionLabel = extensionLabel;
	}

	public JTextField getExtensionField() {
		return extensionField;
	}

	public void setExtensionField(JTextField extensionField) {
		this.extensionField = extensionField;
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

	public JButton getCalcBtn() {
		return calcBtn;
	}

	public void setCalcBtn(JButton calcBtn) {
		this.calcBtn = calcBtn;
	}
}
