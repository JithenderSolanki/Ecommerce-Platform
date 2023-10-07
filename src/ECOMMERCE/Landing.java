package ECOMMERCE;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Landing extends JFrame implements ActionListener {
	JButton b1, b2;
	JFrame f;

	Landing() {
		f = new JFrame();
		f.setTitle("Landing | Ecommerce");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(0, 0, screenSize.width, screenSize.height);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, screenSize.width, screenSize.height);
		panel.setLayout(null);

		JLabel l1 = new JLabel("Best Place for Clothing...");
		l1.setBounds(500, 250, 500, 50);
		l1.setFont(new Font("Serif", Font.PLAIN, 30));
		panel.add(l1);

		JLabel l2 = new JLabel("What would you want to be?");
		l2.setBounds(450, 300, 500, 50);
		l2.setFont(new Font("Serif", Font.PLAIN, 40));
		panel.add(l2);

		b1 = new JButton("Customer");
		b1.addActionListener(this);
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b1.setForeground(new Color(46, 139, 87));
		b1.setBackground(new Color(250, 250, 210));
		b1.setBounds(430, 370, 170, 39);
		panel.add(b1);

		b2 = new JButton("Seller");
		b2.addActionListener(this);
		b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b2.setForeground(new Color(139, 69, 19));
		b2.setBackground(new Color(255, 235, 205));
		b2.setBounds(660, 370, 170, 39);
		panel.add(b2);

		ImageIcon imageIcon = new ImageIcon("C:\\EcommerceImages\\Landing.jpg");
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(screenSize.width, screenSize.height, java.awt.Image.SCALE_SMOOTH); // scale
																													// it
		imageIcon = new ImageIcon(newimg);
		JLabel l = new JLabel(imageIcon);
		l.setBounds(0, 0, screenSize.width, screenSize.height);

		panel.add(l);
		f.add(panel);
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			f.dispose();
			new LoginPage().setVisible(true);

		} else if (e.getSource() == b2) {
			f.dispose();
			SellerPage s = new SellerPage();
			s.f.setVisible(true);

		}
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Landing l = new Landing();
	}
}
