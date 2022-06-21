package dialogWindows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Membership;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ManageMembershipDialog extends JDialog {
		
	private JPanel panelTop;
	
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel priceLabel;
	
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField priceTextField;
	
	
	private JButton submitBtn, cancelBtn;
	
	
	public ManageMembershipDialog(JFrame parent, String title, boolean x, Membership membership, boolean check) {
		super(parent, "Update Membership", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineFilledComponents(membership);
		this.setUneditable(membership);
		
	}
	
	public ManageMembershipDialog(JFrame parent, String title, boolean x, Membership membership) {
		super(parent, "Update Membership", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineFilledComponents(membership);
		
	}
	
	public ManageMembershipDialog(JFrame parent, String title, boolean x) {
		super(parent, "Create Membership", x);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		this.panelTop = new JPanel(new MigLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(screenSize.getWidth()*0.35), (int)(screenSize.getHeight()*0.30),(int)(screenSize.getWidth()*0.25), (int)(screenSize.getHeight()*0.35));
		this.defineEmptyComponents();
	}
	
	private void defineFilledComponents(Membership membership) {
		this.defineLabelsAndButtons();
		
		this.idTextField = new JTextField(membership.getIdentification());
		this.nameTextField = new JTextField(membership.getName());
		this.priceTextField = new JTextField(String.valueOf(membership.getPrice())); 
		
		
		this.fillPanel();
		
	}
	

	private void defineEmptyComponents() {
		this.defineLabelsAndButtons();
		
		this.idTextField = new JTextField(UUID.randomUUID().toString());
		this.nameTextField = new JTextField();
		this.priceTextField = new JTextField(); 
		
		
		this.fillPanel();
	}
	
	private void defineLabelsAndButtons() {
		this.idLabel = new JLabel("ID");
		this.nameLabel = new JLabel("Name");
		this.priceLabel = new JLabel("Price");
		
		
		this.submitBtn = new JButton("Submit");
		this.cancelBtn = new JButton("Cancel");
		
	}
	
	private void fillPanel() {
		panelTop.add(idLabel);
		panelTop.add(idTextField, "width 50%, wrap");
		
		panelTop.add(nameLabel);
		panelTop.add(nameTextField, "width 50%, wrap");
		
		panelTop.add(priceLabel);
		panelTop.add(priceTextField, "width 50%, wrap");
		
		
		panelTop.add(submitBtn);
		panelTop.add(cancelBtn);
		
		this.add(panelTop);
	}
	
	public void setUneditable(Membership membership) {
		this.idTextField.setEditable(false);
		this.nameTextField.setEditable(false);
		this.priceTextField.setEditable(false);
		
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

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(JLabel priceLabel) {
		this.priceLabel = priceLabel;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public void setIdTextField(JTextField idTextField) {
		this.idTextField = idTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public void setPriceTextField(JTextField priceTextField) {
		this.priceTextField = priceTextField;
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
