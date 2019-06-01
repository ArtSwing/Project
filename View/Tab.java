package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import DAO.DAO;
import Model.Google;
import Model.GoogleTab;
import Model.Sale;

public class Tab extends JFrame {
	private Browser browser = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	
	public Tab() {
		DAO dao;
		dao = new DAO();
		setTitle("카테고리별 판매 순위");
		setVisible(true);
		setResizable(true);
		setSize(800,600);

	browser.addLoadListener(new LoadAdapter() {
		@Override
		public void onFinishLoadingFrame(FinishLoadingEvent event) {
			if (event.isMainFrame()) {
				System.out.println("HTML 문서가 로드되었습니다.");
			}
		}
	});
			String title = "카테고리별 판매 순위";
			browser.loadHTML(new GoogleTab().getTabChart(title,dao.Sale_Tab()));
			add(browserView, BorderLayout.CENTER);
		
	}
}
