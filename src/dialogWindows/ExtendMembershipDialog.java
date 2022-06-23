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
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.40));
		
		
	}
	
	
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void defineEmptyComponents() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		this.defineLabelsAndButtons();
		
		DefaultComboBoxModel membershipNames = new DefaultComboBoxModel();
		for (Membership membership: MembershipManager.getInstance().membershipStatusList(false)) {
			membershipNames.addElement(membership.getName());
		}
		
		
		
		
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		this.firstNameLabel = new JLabel("Name");
		this.lastNameLabel = new JLabel("Last Name");
		this.lastPaymentLabel = new JLabel("Last Payement");
		this.membershipLengthLabel = new JLabel("Mem. Length");
		this.membershipTypeLabel = new JLabel("Mem. Type");

		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		
		panelTop.add(firstNameLabel);
		panelTop.add(firstNameTextField, "width 50%, wrap");
		
		panelTop.add(lastNameLabel);
		panelTop.add(lastNameTextField, "width 50%, wrap");
		
		
		panelTop.add(lastPaymentLabel);
		panelTop.add(lastPayementTextField, "width 50%, wrap");
		
		panelTop.add(membershipLengthLabel);
		panelTop.add(membershipLengthTextField, "width 50%, wrap");
		
		panelTop.add(membershipTypeLabel);
		panelTop.add(membershipTypeField, "width 50%, wrap");
		
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	


}
