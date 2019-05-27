package View;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import javax.swing.*;
import java.awt.*;
import Model.GoogleAPI;
import Model.PieElement;



public class Chart extends JFrame {
	
	private Browser browser = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	
	
	public Chart() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("통계");
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
			String title = "판매 통계";
			ArrayList<PieElement> list = new ArrayList<PieElement>();
			list.add(new PieElement("1번빵",40));
			list.add(new PieElement("2번빵",30));
			list.add(new PieElement("3번빵",20));
			list.add(new PieElement("4번빵",70));
			browser.loadHTML(new GoogleAPI().getPieChart(title,list));
			add(browserView, BorderLayout.CENTER);
		
	}
}
