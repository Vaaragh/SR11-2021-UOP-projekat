package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import managers.MembershipManager;
import models.Member;
import models.Membership;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ExtendMembershipDialog  extends JDialog {
	
	
	
	private JPanel panelTop;
	


	private JLabel membershipNumberLabel;
	private JLabel lastPaymentLabel;
	private JLabel membershipLengthLabel;
	private JLabel membershipTypeLabel;
	private JLabel activeLabel;
	
	private JLabel priceLabel;
	private JLabel priceField;
	
	

	private JTextField membershipNumberTextField;
	private JTextField lastPayementTextField;
	private JTextField membershipLengthTextField;
	private JComboBox<Membership> membershipTypeBox;
	private JCheckBox activeCheckBox;
	
	
	private JButton submitBtn, cancelBtn;

	
	
	public ExtendMembershipDialog(JFrame parent, String title, boolean x, Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		super(parent, "Update Member", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		this.defineFilledComponents(member);
		
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineFilledComponents(Member member) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		DefaultComboBoxModel membershipNames = new DefaultComboBoxModel();
		for (Membership membership: MembershipManager.getInstance().membershipStatusList(false)) {
			membershipNames.addElement(membership.getName());
		}
		
		this.membershipNumberTextField = new JTextField(member.getMembershipNumber());
		this.lastPayementTextField = new JTextField(member.getLastPayment().toString());
		this.membershipLengthTextField = new JTextField(String.valueOf(member.getMembershipLength()));
		this.membershipTypeBox = new JComboBox<Membership>(membershipNames);
		
		this.activeCheckBox = new JCheckBox();
		this.activeCheckBox.setSelected(member.isActive());
		
		
		this.fillPanel();
		
	}
	
	
	private void defineLabelsAndButtons() {
		this.membershipNumberLabel = new JLabel("Mem. Number");
		this.lastPaymentLabel = new JLabel("Last Payement");
		this.membershipLengthLabel = new JLabel("Mem. Length");
		this.membershipTypeLabel = new JLabel("Mem. Type");
		this.activeLabel = new JLabel("Active");
		
		this.priceLabel = new JLabel("Amount due: ");
		this.priceField = new JLabel("$0.00");

		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		
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
		
		panelTop.add(priceLabel);
		panelTop.add(priceField, "width 50%, wrap");
	
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


	public JLabel getPriceLabel() {
		return priceLabel;
	}


	public void setPriceLabel(JLabel priceLabel) {
		this.priceLabel = priceLabel;
	}


	public JLabel getPriceField() {
		return priceField;
	}


	public void setPriceField(JLabel priceField) {
		this.priceField = priceField;
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


	public void setMembershipLengthTextField(JTextField membershipLengthTextField) {
		this.membershipLengthTextField = membershipLengthTextField;
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
