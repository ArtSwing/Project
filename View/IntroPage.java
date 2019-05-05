package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.practice;
import View.Pos;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntroPage extends JFrame {

	private JPanel contentPane;
	static Pos po;
	static practice p;
	static Emp em;
	public static IntroPage frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new IntroPage();
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
				po = new Pos();
				po.setVisible(true);
				setVisible(false);
			}
		});
		btn_Pos.setBounds(54, 31, 189, 123);
		contentPane.add(btn_Pos);
		
		JButton btn_Menu = new JButton("\uBA54\uB274\uAD00\uB9AC");
		btn_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = new practice();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btn_Menu.setBounds(401, 31, 189, 123);
		contentPane.add(btn_Menu);
		
		JButton btn_Tong = new JButton("매장통계 (오픈예정) ");
		btn_Tong.setBounds(401, 219, 189, 123);
		contentPane.add(btn_Tong);
		
		JButton btn_Jik = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		btn_Jik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				em = new Emp();
				em.setVisible(true);
				setVisible(false);
			}
		});
		btn_Jik.setBounds(54, 219, 189, 123);
		contentPane.add(btn_Jik);
	}

}
