package View;

import javax.swing.JFrame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import DAO.DAO;
import Model.Google;
import Model.GoogleAPI;
import Model.PieElement;
import Model.Sale;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

public class Sil extends JFrame {
	
	private Browser browser = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	
	public Sil() {
		DAO dao;
		Sale sale;
		dao = new DAO();
		setTitle("�α�ǰ��");
		setVisible(true);
		setResizable(true);
		setSize(800,600);
	


	browser.addLoadListener(new LoadAdapter() {
		@Override
		public void onFinishLoadingFrame(FinishLoadingEvent event) {
			if (event.isMainFrame()) {
				System.out.println("HTML ������ �ε�Ǿ����ϴ�.");
			}
		}
	});
			String title = "�α�ǰ�� (5������)";
			browser.loadHTML(new Google().getHotChart(title,dao.Sale_Hot()));
			add(browserView, BorderLayout.CENTER);
		
	}
}
