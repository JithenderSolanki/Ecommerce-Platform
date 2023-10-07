package ECOMMERCE;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginPage extends JFrame implements ActionListener {
	private JPanel panel;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton login, signup;

	public LoginPage() {
		createTables ctb = new createTables();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("LoginPage");
		panel = new JPanel();
		panel.setBounds(0, 0, screenSize.width, screenSize.height);
		panel.setLayout(null);

		JLabel l1 = new JLabel("USERID  ");
		l1.setBounds(530, 200, 93, 50);
		l1.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(l1);
		JLabel l2 = new JLabel("PASSWORD  ");
		l2.setBounds(530, 270, 150, 24);
		l2.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(l2);
		textField = new JTextField();
		textField.setBounds(650, 210, 170, 30);
		textField.setFont(new Font("Serif", Font.BOLD, 20));
		panel.add(textField);
		passwordField = new JPasswordField();
		passwordField.setBounds(650, 270, 170, 30);
		passwordField.setFont(new Font("Serif", Font.BOLD, 20));
		panel.add(passwordField);
		login = new JButton("Login");
		login.addActionListener(this);
		login.setFont(new Font("Serif", Font.BOLD, 20));
		login.setBackground(new Color(255, 196, 12));
		login.setBounds(610, 330, 113, 39);
		login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(login);
		signup = new JButton("SignUp");
		signup.setFont(new Font("Serif", Font.BOLD, 20));
		signup.addActionListener(this);

		signup.setBackground(new Color(255, 196, 12));
		signup.setBounds(740, 330, 113, 39);
		signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(signup);
		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\loginPagePicture.jpg");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(screenSize.width, screenSize.height, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);

		JLabel l = new JLabel(imageIcon);
		l.setBounds(0, 0, screenSize.width, screenSize.height);

		Border border = BorderFactory.createLineBorder(Color.white, 1);
		imageIcon = new ImageIcon("C:\\EcommerceImages\\backButton1.png"); // load the image to a imageIcon
		image = imageIcon.getImage(); // transform it
		newimg = image.getScaledInstance(40, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		imageIcon = new ImageIcon(newimg);

		JButton back_btn = new JButton(imageIcon);
		back_btn.setBounds(40, 30, 30, 30);
		back_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Landing l = new Landing();

			}

		});
		back_btn.setBackground(Color.white);
		panel.add(back_btn);
		panel.add(l);
		setContentPane(panel);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == login) {
			int count = 0;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project1",
						"root", "root");
				Statement stmt = con.createStatement();
				DatabaseMetaData dbm = con.getMetaData();
				ResultSet tables = dbm.getTables(null, null, "customer", null);
				if (tables.next()) {
					ResultSet rs = stmt.executeQuery("select count(*) from customer");
					if (rs.next())
						count = rs.getInt(1);
				}
				if (count >= 1) {
					String s_id = textField.getText();
					int id = 0;
					if (!s_id.isEmpty())
						id = Integer.parseInt(s_id);
					char[] a = passwordField.getPassword();
					String p = new String(a);
					if (!p.isEmpty() && !s_id.isEmpty()) {
						String sql1 = "select * from customer where customer_id = " + id + " and customer_password ='"
								+ p + "'";
						ResultSet rs1 = stmt.executeQuery(sql1);
						if (rs1.next()) {
							Global.ID = rs1.getInt("customer_id");
							Global.CName = rs1.getString("customer_first_name");
							dispose();
							HOME s = new HOME();
							s.f.setVisible(true);
							s.homePage();
						} else
							JOptionPane.showMessageDialog(this, "Invalid Login", "Warning Message",
									JOptionPane.WARNING_MESSAGE);
					} else
						JOptionPane.showMessageDialog(this, "Invalid Login", "Warning Message",
								JOptionPane.WARNING_MESSAGE);
				} else
					JOptionPane.showMessageDialog(this, "Invalid Credentials", "Warning Message",
							JOptionPane.WARNING_MESSAGE);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (ae.getSource() == signup) {
			dispose();
			new CustomerSignUp().setVisible(true);
		}
	}

	public static void main(String[] args) {
		new LoginPage().setVisible(true);
	}

}