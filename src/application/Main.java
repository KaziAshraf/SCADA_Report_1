package application;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	Connection conn;
	PreparedStatement pst;
	String sql;
	

	@Override
	public void start(Stage primaryStage) {
		try {
			conn = DBConnector.getConnection();
			primaryStage.initStyle(StageStyle.UNDECORATED);
			BorderPane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						Document doc = Jsoup.connect("http://www.pgcb.org.bd/PGCB/?a=pages/hourly_generation_loadshed_display.php").get();
						Elements elements = doc.select("div#main_container");

						int i = 0;
						for (Element element : elements) {
							String[] rawdata = new String[element.getElementsByTag("td").size()];
							while (element.getElementsByTag("td").size() > i) {
								rawdata[i] = element.getElementsByTag("td").get(i).text();

								i++;
							}
							
							int j = 3;
							java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
							while (j < rawdata.length) {
								Generation_Data data = new Generation_Data(rawdata[j], Integer.parseInt(rawdata[j+1]), Integer.parseInt(rawdata[j+2]));
								sql = "INSERT INTO [SCADA_Power_Generation_Report].[dbo].[Generation_table]"
										+ "([Description],[Day Peak],[Evening Peak],[Date])" + "values(?,?,?,?)";
								try {
									pst = conn.prepareStatement(sql);
									pst.setString(1, data.getDescription());
									pst.setInt(2, data.getDay_Peak());
									pst.setInt(3, data.getEvening_Peak());

									pst.setDate(4, sqlDate);
									pst.executeUpdate();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								j = j + 3;
							}
						}

					} catch (IOException e) {

					}

				}
			},0, 2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
