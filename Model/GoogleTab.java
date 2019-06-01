package Model;

import java.util.ArrayList;

public class GoogleTab {
	public String getTabChart(String title, ArrayList<Sale> sale) {
		
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"	<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"  <script type=\"text/javascript\">\r\n" + 
				"    google.charts.load(\"current\", {packages:['corechart']});\r\n" + 
				"    google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"    function drawChart() {\r\n" + 
				"      var data = google.visualization.arrayToDataTable([\r\n" + 
				"        ['카테고리', '판매수량' ],\r\n";
		for(int i=0; i< sale.size(); i++) {
			htmlString += "['" + sale.get(i).getStabname() + "', " + sale.get(i).getCounthot() + "],\r\n";
		System.out.println(htmlString);
		}
		
			htmlString += "      ]);\r\n" + 		
					"\r\n" + 
				"      var options = {\r\n" + 
				"        title: \"카테고리별 판매수량\",\r\n" + 
				"        width: 600,\r\n" + 
				"        height: 400,\r\n" + 
				"        bar: {groupWidth: \"50%\"},\r\n" + 
				"        legend: { position: \"none\" },\r\n" + 
				"      };\r\n" + 
				"      var chart = new google.visualization.ColumnChart(document.getElementById(\"columnchart_values\"));\r\n" + 
				"      chart.draw(data, options);\r\n" + 
				"  }\r\n" + 
				"  </script>\r\n" + 
				" </head>\r\n" +
				"<body>\r\n" +
				"<div id=\"columnchart_values\" style=\"width: 600px; height: 400px;\"></div>\r\n" +
				"	</body>\r\n" +
				"</html>";
		return htmlString;
	}
}
