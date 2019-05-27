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
	setTitle("���");
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
			String title = "�Ǹ� ���";
			ArrayList<PieElement> list = new ArrayList<PieElement>();
			list.add(new PieElement("1����",40));
			list.add(new PieElement("2����",30));
			list.add(new PieElement("3����",20));
			list.add(new PieElement("4����",70));
			browser.loadHTML(new GoogleAPI().getPieChart(title,list));
			add(browserView, BorderLayout.CENTER);
		
	}
}
