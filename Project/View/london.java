package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.GroupLayout.Alignment;


public class london extends JFrame {
	
   private JPanel contentPane;
   private JTable table;
   JPanel sikbbangpanel = new JPanel();
   JPanel donutpanel = new JPanel();
   JPanel francepanel = new JPanel();
   JPanel hotpanel = new JPanel();
   JButton button = new JButton();
   private JTextField textField;
   private String[] columnNames= new String[4];
   private String[][] rowData=new String[4][4] ;
   
    
   public london() {
      //������ ����
	   setTitle("London Bagutee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(4, 4, 4, 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		columnNames=new String[] {"�޴���", "����","����" };
		
		rowData = new String[][]{
				
					{ "���ڽĻ�", "2", "1500"},
					{ "��Ļ�", "1", "2500"},
					{ "��ȣ�ڰ�����", "3", "2000"}
					};
		
		button.addActionListener(new ActionListener(){
		    
			@Override
			public void actionPerformed(ActionEvent event){
					
			}
		}
	);
   }

   public static void main(String[] args) {
	   EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pos frame = new Pos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	     
      
      //���� �����°�
	   JPanel menupanel = new JPanel();
		menupanel.setBounds(35, 31, 122, 144);
		menupanel.setLayout(null);
		
        JButton btn = new JButton("����");
    		btn.addActionListener(new ActionListener() {
    			
         
         @Override
         public void actionPerformed(ActionEvent e) {
            Object bbb = e.getSource();
            if(bbb.equals("�Ļ�")){
            	JButton btn_sik = new JButton("�Ļ�");
            	btn_sik.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
        		btn_sik.setBounds(12, 10, 97, 23);
        		menupanel.add(btn_sik);
               menu1();
               
            }else if(bbb.equals("����")){
            	JButton btn_donut = new JButton("����");
        		btn_donut.setBounds(12, 43, 97, 23);
        		menupanel.add(btn_donut);
               menu2();
            }else if(bbb.equals("�ֺ극��")){
               //�츮�й�ư������� �޴�3�θ���
            	JButton btn_mil = new JButton("�ֺ극��");
        		btn_mil.setBounds(12, 76, 97, 23);
        		menupanel.add(btn_mil);
               menu3();
            }else if(bbb.equals("��������")){
               //��ư 4�ϰ�� �޴��θ���
            	JButton btn_france = new JButton("��������");
            	btn_france.setBounds(12, 109, 97, 23);
        		menupanel.add(btn_france);
        		btn_france.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        			}
        		});
               menu4();
            }
            
         }
      });
   }

   static void menu1() {
      //�޴�1  �����
      //�޴�1 �ǳڿ� ����� �ڵ�
	    JPanel sikbbangpanel = new JPanel();
		sikbbangpanel.setBounds(169, 22, 325, 224);
		sikbbangpanel.setLayout(null);
		
		JButton btn_gamja = new JButton("");
		btn_gamja.setBounds(12, 10, 83, 71);
		sikbbangpanel.add(btn_gamja);
		btn_gamja.setIcon(new ImageIcon(Pos.class.getResource("/images/gamja.PNG")));
		
		JButton btn_gokmul = new JButton("");
		btn_gokmul.setBounds(109, 13, 77, 71);
		sikbbangpanel.add(btn_gokmul);
		btn_gokmul.setIcon(new ImageIcon(Pos.class.getResource("/images/\uACE1\uBB3C\uC2DD\uBE75.PNG")));
		
		JButton btn_danhobak = new JButton("");
		btn_danhobak.setBounds(229, 8, 83, 76);
		sikbbangpanel.add(btn_danhobak);
		btn_danhobak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_danhobak.setIcon(new ImageIcon(Pos.class.getResource("/images/\uB2E8\uD638\uBC15\uAC80\uC740\uAE68.PNG")));
		
		JButton btn_denish = new JButton("");
		btn_denish.setBounds(18, 132, 77, 71);
		sikbbangpanel.add(btn_denish);
		btn_denish.setIcon(new ImageIcon(Pos.class.getResource("/images/\uB370\uB2C8\uC26C\uC2DD\uBE75.PNG")));
		
		JButton btn_morning = new JButton("");
		btn_morning.setBounds(115, 132, 77, 71);
		sikbbangpanel.add(btn_morning);
		btn_morning.setIcon(new ImageIcon(Pos.class.getResource("/images/\uBAA8\uB2DD\uD1A0\uC2A4\uD2B8.PNG")));
		
		JButton btn_bam = new JButton("");
		btn_bam.setBounds(226, 132, 86, 71);
		sikbbangpanel.add(btn_bam);
		btn_bam.setIcon(new ImageIcon(Pos.class.getResource("/images/\uBC24\uC2DD\uBE75.PNG")));
		
		JLabel label = new JLabel("\uAC10\uC790\uC2DD\uBE75");
		label.setFont(new Font("���� ���", Font.PLAIN, 12));
		label.setBounds(22, 96, 57, 15);
		sikbbangpanel.add(label);
		
		JLabel label_1 = new JLabel("\uACE1\uBB3C\uC2DD\uBE75");
		label_1.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_1.setBounds(121, 96, 57, 15);
		sikbbangpanel.add(label_1);
		
		JLabel label_2 = new JLabel("\uB2E8\uD638\uBC15 \uAC80\uC740\uAE68");
		label_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_2.setBounds(241, 96, 86, 15);
		sikbbangpanel.add(label_2);
		
		JLabel label_3 = new JLabel("\uB370\uB2C8\uC26C \uC2DD\uBE75");
		label_3.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_3.setBounds(24, 201, 77, 15);
		sikbbangpanel.add(label_3);
		
		JLabel label_4 = new JLabel("\uBAA8\uB2DD \uD1A0\uC2A4\uD2B8");
		label_4.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_4.setBounds(111, 202, 77, 15);
		sikbbangpanel.add(label_4);
		
		JLabel label_5 = new JLabel("\uBC24\uC2DD\uBE75");
		label_5.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_5.setBounds(235, 202, 77, 15);
		sikbbangpanel.add(label_5);
   }

   static void menu2() {
      //�޴�2  �����
      //�޴�2 �ǳڿ� ����� �ڵ�
     // sikbbangpanel.add("�Ļ�");
   }

   static void menu3() {
      //�޴�3  �����
      //�޴�3 �ǳڿ� ����� �ڵ�
     // hotpanel.add("�ֺ극��");
   }

   static void menu4() {
      //�޴�4  �����
      //�޴�4 �ǳڿ� ����� �ڵ�
     // francepanel.add("��������");
   }
}