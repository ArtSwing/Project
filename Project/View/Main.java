package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

import DAO.practice;

import javax.swing.GroupLayout.Alignment;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JButton button = new JButton();
	private JTextField textField;
	private String[] columnNames= new String[4];
	private String[][] rowData=new String[4][4] ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class myTableModel extends DefaultTableModel{
		
		String dat;
		
		JButton button=new JButton("");
		
		myTableModel(String tname){
			
			super(rowData,columnNames);
			
			dat=tname;
			
		}
		
		public boolean isCellEditable(int row,int cols){
			
			if( dat=="owntable"){
			
				if(cols==0){return false;}
			
			}
			
			return true;
			
		}

	}         
	/**
	 * Create the frame.
	 */
	
	
	public Main() {
		setTitle("London Bagutee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<Model.Food> bean = new ArrayList<Model.Food>();
		
		Model.Food a = new Model.Food();
		a.setId(1);
		a.setImgUrl("t");
		a.setName("a");
		a.setPrice(500);
		
		Model.Food b = new Model.Food();
		b.setId(2);
		b.setImgUrl("r");
		b.setName("b");
		b.setPrice(10000);
		
		bean.add(a);
		bean.add(b);
		
		for (Model.Food beans : bean) {
			System.out.println(beans);
		}
		
		
	
		columnNames=new String[] { "¸Þ´º¸í", "´Ü°¡", "¼ö·®","°¡°Ý" };
		
		rowData = new String[][]{
				
					{ "", "", "", "" }
					};
	
		
		TableModel model=new myTableModel("owntable");
		button.addActionListener(new ActionListener(){
		    
			@Override
			public void actionPerformed(ActionEvent event){
					
			}
		}
	);
		
		
		JButton btn_del = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		btn_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_del.setBounds(808, 373, 97, 36);
		contentPane.add(btn_del);
		
		JButton button_1 = new JButton("\uACB0\uC81C\uD558\uAE30");
		button_1.setBounds(624, 373, 97, 36);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setText("\uC804\uCCB4\uC8FC\uBB38 : 6   \uCD1D \uAC00\uACA9 :11000\uC6D0");
		textField.setBounds(662, 335, 194, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(581, 144, 351, 181);
		contentPane.add(scrollPane);
		
			table = new JTable(rowData,columnNames);
			scrollPane.setViewportView(table);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setToolTipText("");
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(83, 79, 446, 265);
			contentPane.add(tabbedPane);
			
			JPanel sikbbangpanel = new JPanel();
			sikbbangpanel.setToolTipText("");
			tabbedPane.addTab("\uC2DD\uBE75", null, sikbbangpanel, null);
			sikbbangpanel.setLayout(null);
			
			JButton btn_gamja = new JButton("");
			btn_gamja.setBounds(12, 10, 83, 71);
			sikbbangpanel.add(btn_gamja);
			btn_gamja.setIcon(new ImageIcon(Main.class.getResource("/images/gamja.PNG")));
			
			JButton btn_gokmul = new JButton("");
			btn_gokmul.setBounds(109, 13, 77, 71);
			sikbbangpanel.add(btn_gokmul);
			btn_gokmul.setIcon(new ImageIcon(Main.class.getResource("/images/\uACE1\uBB3C\uC2DD\uBE75.PNG")));
			
			JButton btn_danhobak = new JButton("");
			btn_danhobak.setBounds(229, 8, 83, 76);
			sikbbangpanel.add(btn_danhobak);
			btn_danhobak.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btn_danhobak.setIcon(new ImageIcon(Main.class.getResource("/images/\uB2E8\uD638\uBC15\uAC80\uC740\uAE68.PNG")));
			
			JButton btn_denish = new JButton("");
			btn_denish.setBounds(18, 132, 77, 71);
			sikbbangpanel.add(btn_denish);
			btn_denish.setIcon(new ImageIcon(Main.class.getResource("/images/\uB370\uB2C8\uC26C\uC2DD\uBE75.PNG")));
			
			JButton btn_morning = new JButton("");
			btn_morning.setBounds(115, 132, 77, 71);
			sikbbangpanel.add(btn_morning);
			btn_morning.setIcon(new ImageIcon(Main.class.getResource("/images/\uBAA8\uB2DD\uD1A0\uC2A4\uD2B8.PNG")));
			
			JButton btn_bam = new JButton("");
			btn_bam.setBounds(226, 132, 86, 71);
			sikbbangpanel.add(btn_bam);
			btn_bam.setIcon(new ImageIcon(Main.class.getResource("/images/\uBC24\uC2DD\uBE75.PNG")));
			
			JLabel label = new JLabel("\uAC10\uC790\uC2DD\uBE75");
			label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label.setBounds(22, 96, 57, 15);
			sikbbangpanel.add(label);
			
			JLabel label_1 = new JLabel("\uACE1\uBB3C\uC2DD\uBE75");
			label_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label_1.setBounds(121, 96, 57, 15);
			sikbbangpanel.add(label_1);
			
			JLabel label_2 = new JLabel("\uB2E8\uD638\uBC15 \uAC80\uC740\uAE68");
			label_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label_2.setBounds(241, 96, 86, 15);
			sikbbangpanel.add(label_2);
			
			JLabel label_3 = new JLabel("\uB370\uB2C8\uC26C \uC2DD\uBE75");
			label_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label_3.setBounds(24, 201, 77, 15);
			sikbbangpanel.add(label_3);
			
			JLabel label_4 = new JLabel("\uBAA8\uB2DD \uD1A0\uC2A4\uD2B8");
			label_4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label_4.setBounds(111, 202, 77, 15);
			sikbbangpanel.add(label_4);
			
			JLabel label_5 = new JLabel("\uBC24\uC2DD\uBE75");
			label_5.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			label_5.setBounds(235, 202, 77, 15);
			sikbbangpanel.add(label_5);
			
			
			btn_gamja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			JPanel francepanel = new JPanel();
			tabbedPane.addTab("\uD504\uB791\uC2A4\uBE75", null, francepanel, null);
			
			JButton btn_grain = new JButton("");
			btn_grain.setBounds(12, 10, 72, 44);
			btn_grain.setIcon(new ImageIcon(Main.class.getResource("/images/grain.PNG")));
			btn_grain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			JLabel label_12 = new JLabel("\uADF8\uB808\uC778\uBC14\uAC8C\uD2B8");
			label_12.setBounds(12, 64, 82, 15);
			label_12.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
			JButton btn_berry = new JButton("");
			btn_berry.setBounds(96, 10, 72, 44);
			btn_berry.setIcon(new ImageIcon(Main.class.getResource("/images/berry.PNG")));
			
			JLabel lblBerry = new JLabel("\uD06C\uB80C\uBCA0\uB9AC\uBE75");
			lblBerry.setBounds(96, 64, 72, 15);
			lblBerry.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
			JButton btn_dduk = new JButton("");
			btn_dduk.setBounds(12, 89, 72, 44);
			btn_dduk.setIcon(new ImageIcon(Main.class.getResource("/images/dduk.PNG")));
			
			JLabel label_15 = new JLabel("\uCC30\uB5A1\uBE0C\uB808\uB4DC");
			label_15.setBounds(12, 143, 72, 15);
			label_15.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
			JButton btn_homil = new JButton("");
			btn_homil.setBounds(96, 89, 72, 44);
			btn_homil.setIcon(new ImageIcon(Main.class.getResource("/images/homil.PNG")));
			
			JLabel label_16 = new JLabel("\uD638\uBC00\uD638\uB450\uBE75");
			label_16.setBounds(96, 143, 107, 15);
			label_16.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
			JButton btn_japa = new JButton("");
			btn_japa.setBounds(195, 10, 72, 44);
			btn_japa.setIcon(new ImageIcon(Main.class.getResource("/images/japa.PNG")));
			
			JLabel label_14 = new JLabel("\uC790\uD30C\uD0C0");
			label_14.setBounds(203, 64, 64, 15);
			label_14.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
			JButton btn_hodu = new JButton("");
			btn_hodu.setBounds(195, 89, 72, 44);
			btn_hodu.setIcon(new ImageIcon(Main.class.getResource("/images/hodu.PNG")));
			
			JLabel label_17 = new JLabel("\uD638\uB450\uAC74\uD3EC\uB3C4\uBE75");
			label_17.setBounds(195, 143, 75, 15);
			label_17.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			francepanel.setLayout(null);
			francepanel.add(btn_grain);
			francepanel.add(btn_berry);
			francepanel.add(btn_japa);
			francepanel.add(label_12);
			francepanel.add(lblBerry);
			francepanel.add(label_14);
			francepanel.add(btn_dduk);
			francepanel.add(btn_homil);
			francepanel.add(btn_hodu);
			francepanel.add(label_15);
			francepanel.add(label_16);
			francepanel.add(label_17);
			
			JPanel donutpanel = new JPanel();
			tabbedPane.addTab("\uB3C4\uB11B", null, donutpanel, null);
			donutpanel.add(new JLabel("A Panel"));
			donutpanel.setVisible(false);
			donutpanel.setLayout(null);
			
			JButton btn_very = new JButton("");
			btn_very.setIcon(new ImageIcon(Main.class.getResource("/images/very.PNG")));
			btn_very.setBounds(12, 10, 83, 71);
			donutpanel.add(btn_very);
			
			JButton btn_choco = new JButton("");
			btn_choco.setIcon(new ImageIcon(Main.class.getResource("/images/choco.PNG")));
			btn_choco.setBounds(109, 13, 77, 71);
			donutpanel.add(btn_choco);
			
			JButton btn_stick = new JButton("");
			btn_stick.setIcon(new ImageIcon(Main.class.getResource("/images/stick.PNG")));
			btn_stick.setBounds(229, 10, 83, 76);
			donutpanel.add(btn_stick);
			
			JButton btn_biskit = new JButton("");
			btn_biskit.setIcon(new ImageIcon(Main.class.getResource("/images/biskit.PNG")));
			btn_biskit.setBounds(18, 119, 77, 71);
			donutpanel.add(btn_biskit);
			
			JButton btn_feschu = new JButton("");
			btn_feschu.setIcon(new ImageIcon(Main.class.getResource("/images/feschu.PNG")));
			btn_feschu.setBounds(109, 119, 77, 71);
			donutpanel.add(btn_feschu);
			
			JButton btn_chuing = new JButton("");
			btn_chuing.setIcon(new ImageIcon(Main.class.getResource("/images/chuing.PNG")));
			btn_chuing.setBounds(229, 119, 86, 71);
			donutpanel.add(btn_chuing);
			
			JLabel label_6 = new JLabel("\uBCA0\uB9AC\uBCA0\uB9AC\uB2EC\uB9C1");
			label_6.setBounds(22, 91, 107, 15);
			donutpanel.add(label_6);
			
			JLabel label_7 = new JLabel("\uCD08\uCF54\uD638\uB450\uB2EC\uB9C1");
			label_7.setBounds(119, 91, 74, 15);
			donutpanel.add(label_7);
			
			JLabel label_8 = new JLabel("\uB2EC\uCF64\uD55C\uCD08\uCF54\uC2A4\uD2F1");
			label_8.setBounds(229, 94, 86, 15);
			donutpanel.add(label_8);
			
			JLabel label_9 = new JLabel("\uC194\uC9C1\uB2F4\uBC31 \uBE44\uC2A4\uD0B7");
			label_9.setBounds(18, 200, 97, 15);
			donutpanel.add(label_9);
			
			JLabel label_10 = new JLabel("\uCC39\uC300\uD328\uC2A4\uCE04\uB9AC\uB3C4\uB11B");
			label_10.setBounds(116, 197, 112, 15);
			donutpanel.add(label_10);
			
			JLabel label_11 = new JLabel("\uCD08\uCF54\uCE04\uC789\uC2A4\uD0C0");
			label_11.setBounds(229, 200, 77, 15);
			donutpanel.add(label_11);
			
			JPanel hotpanel = new JPanel();
			tabbedPane.addTab("\uD56B\uBE0C\uB798\uB4DC", null, hotpanel, null);
			hotpanel.setLayout(null);
			
			JButton btn_hanip = new JButton("");
			btn_hanip.setIcon(new ImageIcon(Main.class.getResource("/images/honip.PNG")));
			btn_hanip.setBounds(12, 10, 71, 49);
			hotpanel.add(btn_hanip);
			
			JButton btn_ttotia = new JButton("");
			btn_ttotia.setIcon(new ImageIcon(Main.class.getResource("/images/ttotia.PNG")));
			btn_ttotia.setBounds(95, 10, 71, 49);
			hotpanel.add(btn_ttotia);
			
			JButton btn_mexican = new JButton("");
			btn_mexican.setIcon(new ImageIcon(Main.class.getResource("/images/mexican.PNG")));
			btn_mexican.setBounds(178, 10, 71, 49);
			hotpanel.add(btn_mexican);
			
			JButton btn_hamegg = new JButton("");
			btn_hamegg.setIcon(new ImageIcon(Main.class.getResource("/images/morning.PNG")));
			btn_hamegg.setBounds(12, 104, 71, 49);
			hotpanel.add(btn_hamegg);
			
			JButton btn_twist = new JButton("");
			btn_twist.setIcon(new ImageIcon(Main.class.getResource("/images/twist.PNG")));
			btn_twist.setBounds(95, 104, 71, 49);
			hotpanel.add(btn_twist);
			
			JButton btn_jori = new JButton("");
			btn_jori.setIcon(new ImageIcon(Main.class.getResource("/images/jori.PNG")));
			btn_jori.setBounds(178, 104, 71, 49);
			hotpanel.add(btn_jori);
			
			JLabel label_13 = new JLabel("\uD55C\uC785\uC3D9\uD1A0\uC2A4\uD2B8");
			label_13.setBounds(12, 69, 78, 15);
			hotpanel.add(label_13);
			
			JLabel label_18 = new JLabel("\uC18C\uC2DC\uC9C0\uB610\uB760\uC544");
			label_18.setBounds(88, 69, 78, 15);
			hotpanel.add(label_18);
			
			JLabel label_19 = new JLabel("\uD584\uC5D0\uADF8\uBAA8\uB2DD");
			label_19.setBounds(12, 163, 78, 15);
			hotpanel.add(label_19);
			
			JLabel label_20 = new JLabel("\uD2B8\uC704\uC2A4\uD2B8");
			label_20.setBounds(95, 163, 78, 15);
			hotpanel.add(label_20);
			
			JLabel label_21 = new JLabel("\uC18C\uC2DC\uC9C0\uC870\uB9AC\uBE75");
			label_21.setBounds(171, 163, 78, 15);
			hotpanel.add(label_21);
			
			JLabel label_22 = new JLabel("\uBA55\uC2DC\uCE78\uADF8\uB9B4");
			label_22.setBounds(188, 69, 78, 15);
			hotpanel.add(label_22);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 993, 36);
			contentPane.add(menuBar);
			
			JMenu menu = new JMenu("\uAD00\uB9AC\uC790\uBAA8\uB4DC");
			menuBar.add(menu);
			
			JMenuItem menuItem = new JMenuItem("\uD1B5\uACC4");
			menu.add(menuItem);
			hotpanel.setVisible(false);
			francepanel.setVisible(false);
		
	}
	 public void start() {
         Main panel = new Main();
         JFrame st = new JFrame();
         
         st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         st.add(panel);
         st.setSize(540,400);
         st.setVisible(true);
     }
	

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
