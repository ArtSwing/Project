package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.practice;
import View.Main;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntroPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroPage frame = new IntroPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IntroPage() {
		setTitle("\uB7F0\uB358\uBC14\uAC8C\uD2B8 \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_Pos = new JButton("\uD3EC\uC2A4\uAE30 \uD654\uBA74");
		btn_Pos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.start();
		
			}
		});
		btn_Pos.setBounds(54, 31, 189, 123);
		contentPane.add(btn_Pos);
		
		JButton btn_Menu = new JButton("\uBA54\uB274\uAD00\uB9AC");
		btn_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				practice p = new practice();
				
				p.run();
			}
		});
		btn_Menu.setBounds(401, 31, 189, 123);
		contentPane.add(btn_Menu);
		
		JButton btn_Tong = new JButton("\uB9E4\uC7A5\uD1B5\uACC4");
		btn_Tong.setBounds(401, 219, 189, 123);
		contentPane.add(btn_Tong);
		
		JButton btn_Jik = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		btn_Jik.setBounds(54, 219, 189, 123);
		contentPane.add(btn_Jik);
	}

}
