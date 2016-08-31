import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBTry{

	private static final String url = "jdbc:mysql://localhost:3306/student_db";
	private static final String user = "root";
	private static final String password = "gr0M1t_repeaT";

	public static void main(String args[]){


		String className = "com.mysql.jdbc.Driver";

		String sql1 = "SELECT * FROM Students";
		String sql2 = "SELECT * FROM Subjects";
		String sql3 = "SELECT Marks.id, Students.fio,  Subjects.subject,  Marks.mark,  Marks.date FROM  Students,  Subjects,  Marks WHERE ((Students.id = Marks.student_id) AND (Subjects.id = Marks.subject_id)) ORDER BY Marks.id";
				
		try{
			Class.forName(className);
			try (Connection con = DriverManager.getConnection(url, user, password); Statement stmt1 = con.createStatement();
				Statement stmt2 = con.createStatement(); Statement stmt3 = con.createStatement();){

				try(ResultSet rs1 = stmt1.executeQuery(sql1);){
					System.out.println("id   first_name");
					while (rs1.next()) {
						int col1 = rs1.getInt("id");
						String col2 = rs1.getString("fio");
						System.out.println(col1 + "   " + col2);
					}
					System.out.println("======================");
				}
				
				try(ResultSet rs2 = stmt2.executeQuery(sql2);){
					System.out.println("id   subject");
					while (rs2.next()) {
						int col1 = rs2.getInt("id");
						String col2 = rs2.getString("subject");
						System.out.println(col1 + "   " + col2);
					}
					System.out.println("======================");
				}
			
				try(ResultSet rs3 = stmt3.executeQuery(sql3);){
					System.out.println("| id   | fio                     | subject        |       mark       |      date     |");
					while (rs3.next()) {
						int col1 = rs3.getInt("id");
						String col2 = rs3.getString("fio");
						String col3 = rs3.getString("subject");
						int col4 = rs3.getInt("mark");
						String col5 = rs3.getString("date");
						System.out.println("   " + col1 + "   " + col2 + "           " + col3 + "               " + col4 + "              " + col5);
					}
					System.out.println("======================");
				}
			
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}