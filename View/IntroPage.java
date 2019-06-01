package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.practice;
import java.awt.Font;

public class IntroPage extends JFrame {

	private JPanel contentPane;
	static Pos po;
	static practice p;
	static Emp em;
	static Chart c;
	static SaleChart sc;
	static Sil sil;
	public static IntroPage frame;
	IntroPage intro;
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
		setBounds(100, 100, 790, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_Pos = new JButton("\uD3EC\uC2A4\uAE30 \uD654\uBA74");
		btn_Pos.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		btn_Pos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				po = new Pos();
				po.setVisible(true);
				setVisible(false);
			}
		});
		btn_Pos.setBounds(163, 57, 189, 123);
		contentPane.add(btn_Pos);
		
		JButton btn_Menu = new JButton("\uBA54\uB274\uAD00\uB9AC");
		btn_Menu.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		btn_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p = new practice();
				p.setVisible(true);
				setVisible(false);
			}
		});
		btn_Menu.setBounds(431, 57, 189, 123);
		contentPane.add(btn_Menu);
		
		JButton btn_Tong = new JButton("\uD1B5\uACC4 \uC608\uC2DC (\uAD6C\uAE00 API)");
		btn_Tong.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		btn_Tong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = new Chart();
				c.setVisible(true);
				setVisible(false);
			}
		});
		btn_Tong.setBounds(507, 221, 233, 121);
		contentPane.add(btn_Tong);
		
		JButton btn_Jik = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		btn_Jik.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		btn_Jik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				em = new Emp();
				em.setVisible(true);
				setVisible(false);
			}
		});
		btn_Jik.setBounds(54, 219, 189, 123);
		contentPane.add(btn_Jik);
		
		
		
		JButton btn_sale = new JButton("\uD1B5\uACC4 \uCC28\uD2B8");
		btn_sale.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		btn_sale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sc = new SaleChart();
				sc.setVisible(true);
				setVisible(false);
				
			}
		});
		btn_sale.setBounds(280, 219, 189, 123);
		contentPane.add(btn_sale);
		
	}
}
