package Model;

import java.util.ArrayList;

public class Google {
	public String getHotChart(String title, ArrayList<Sale> sale) {
		
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"      google.charts.load('current', {'packages':['corechart']});\r\n" + 
				"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"\r\n" + 
				"      function drawChart() {\r\n" + 
				"\r\n" + 
				"        var data = google.visualization.arrayToDataTable([\r\n" + 
				"          ['빵이름', '판매량'],\r\n";
			for(int i=0; i< sale.size(); i++) {
				htmlString += "['" + sale.get(i).getSfoodname() + "', " + sale.get(i).getCounthot() + "],\r\n";
			System.out.println(htmlString);
			}
			
				htmlString += "        ]);\r\n" + 
				"\r\n" + 
				"        var options = {\r\n" + 
				"          title: '" + title + "'\r\n" + 
				"        };\r\n" + 
				"        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\r\n" + 
				"        chart.draw(data, options);\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <div id=\"piechart\" style=\"width: 900px; height: 500px;\"></div>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return htmlString;
	}
}
