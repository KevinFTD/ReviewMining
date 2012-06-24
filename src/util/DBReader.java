package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ZOLReview;

public class DBReader {
	
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String connURL = "jdbc:mysql://localhost/zolreview";
	private static String userName = "root";
	private static String userPwd = "sjtucit";
		
	public static List<ZOLReview> readZOLReview (){
		List<ZOLReview> result = new ArrayList<ZOLReview>();
		
		try {
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(connURL, userName, userPwd);
			Statement stmt = conn.createStatement();
			//System.out.println("Connection Successful!");
			
			String sql = "select * from review";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				result.add(new ZOLReview(rs.getString("advantage"), rs.getString("disadvantage"), rs.getString("summary")));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return result;
	}

}
