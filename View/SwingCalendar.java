package View;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.*;

public class SwingCalendar extends JFrame {

	SwingCalendar calender;
	DefaultTableModel model;
	Calendar cal = new GregorianCalendar();
	JLabel label;
	JTable table;

	SwingCalendar(SaleChart salechart, JButton source) {
		calender = this;
		this.setTitle("Swing Calandar");
		this.setSize(300, 200);
		getContentPane().setLayout(new BorderLayout());
		this.setVisible(true);

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JButton b1 = new JButton("<-");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		JButton b2 = new JButton("->");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(b1, BorderLayout.WEST);
		panel.add(label, BorderLayout.CENTER);
		panel.add(b2, BorderLayout.EAST);

		String[] columns = { "일", "월", "화", "수", "목", "금", "토" };
		model = new DefaultTableModel(null, columns);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		JScrollPane pane = new JScrollPane(table);

		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(pane, BorderLayout.CENTER);

		this.updateMonth();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable jtable = (JTable) e.getSource();
				DefaultTableModel m = (DefaultTableModel) jtable.getModel();
				int row = jtable.getSelectedRow(); // 선택된 테이블의 행값
				int col = jtable.getSelectedColumn(); // 선택된 테이블의 열값
				System.out.println("--------------------------");
				System.out.println(m.getValueAt(row, col)); // 선택된 위치의 값을 얻어내서 출력

				String from = String.valueOf(cal.get(Calendar.YEAR)) + "-" + String.valueOf(cal.get(Calendar.MONTH) + 1)
						+ "-" + (m.getValueAt(row, col));
				System.out.println(from);
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-M-d");
				try {
					Date date = transFormat.parse(from);
					System.out.println();
					if (source.getName().equals("start")) {
						salechart.cal_start(date);
					} else {
						salechart.cal_end(date);
					}
					calender.setVisible(false);
					calender.dispose();
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	void updateMonth() {
		cal.set(Calendar.DAY_OF_MONTH, 1);

		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = cal.get(Calendar.YEAR);
		label.setText(month + " " + year);

		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

		model.setRowCount(0);
		model.setRowCount(weeks);

		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}
	}
}