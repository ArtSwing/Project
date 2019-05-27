package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Sale;
import javax.swing.JLabel;
import java.awt.Font;

public class SaleChart extends JFrame {
	
	private JPanel contentPane;
	JTextField txtfield;
	JTextField txtfield2;
	SaleChart salechart;
	public static IntroPage frame;
	
	public void cal_start(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtfield.setText(sdf.format(date));
		dateform();
	}
	public void cal_end(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtfield2.setText(sdf.format(date));
		dateform();
	}
	
	
	public SaleChart() {
		salechart = this;
		setTitle("≈Î∞Ë ∆‰¿Ã¡ˆ");
		contentPane = new JPanel();
		setBounds(100, 100, 770, 548);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		txtfield = new JTextField();
		txtfield.setBounds(44, 97, 116, 21);
		contentPane.add(txtfield);
		txtfield.setColumns(10);
		
		txtfield2 = new JTextField();
		txtfield2.setColumns(10);
		txtfield2.setBounds(234, 97, 116, 21);
		contentPane.add(txtfield2);
		
		JButton btn_start = new JButton("start");
		btn_start.setSelectedIcon(new ImageIcon("C:\\Users\\Yoo Han Bin\\Desktop\\Project\\Project\\images\\calender.png"));
		btn_start.setBounds(54, 128, 97, 36);
		contentPane.add(btn_start);
		btn_start.setName("start");
		btn_start.addActionListener(new Click(this));
		
		JButton btn_end = new JButton("end");
		btn_end.setBounds(244, 128, 97, 29);
		contentPane.add(btn_end);
		btn_end.setName("end");
		btn_end.addActionListener(new Click(this));
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		txtfield.setText(sdf.format(now));
		txtfield2.setText(sdf.format(now));
		
		JLabel label = new JLabel("\uC2DC\uC791\uC77C");
		label.setFont(new Font("±º∏≤", Font.BOLD, 15));
		label.setBounds(80, 72, 57, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\uB9C8\uC9C0\uB9C9\uC77C");
		lblNewLabel.setFont(new Font("±º∏≤", Font.BOLD, 15));
		lblNewLabel.setBounds(253, 72, 77, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("1. \uAE30\uAC04\uC744 \uC124\uC815\uD574 \uC8FC\uC138\uC694.");
		label_1.setFont(new Font("±º∏≤", Font.BOLD, 20));
		label_1.setBounds(80, 27, 261, 35);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("3. \uC778\uAE30\uD488\uBAA9 \uCC28\uD2B8");
		lblNewLabel_1.setFont(new Font("±º∏≤", Font.BOLD, 20));
		lblNewLabel_1.setBounds(489, 23, 213, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2. \uCE74\uD14C\uACE0\uB9AC\uBCC4 \uD310\uB9E4\uCC28\uD2B8");
		lblNewLabel_2.setFont(new Font("±º∏≤", Font.BOLD, 20));
		lblNewLabel_2.setBounds(80, 176, 276, 57);
		contentPane.add(lblNewLabel_2);
		dateform();
		
		
	}
	public void dateform() {
		try {
			String cal_start = txtfield.getText();
			String cal_end = txtfield2.getText();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date_start = sdf1.parse(cal_start);
			Date date_end = sdf1.parse(cal_end);
			
			
			ArrayList<Sale> sales ;// = new DAO().selectchart();
			
			
			System.out.println(date_start + " ~ " + date_end);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class Click implements ActionListener {
	
	SaleChart salechart;
	
	Click(SaleChart salechart){
		this.salechart = salechart;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new SwingCalendar(salechart, (JButton) e.getSource());
	}
}
