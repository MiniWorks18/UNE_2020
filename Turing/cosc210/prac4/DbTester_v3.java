import java.sql.*;
import java.util.*;

public class  DbTester_v3{

	public static void main(String [] argv) throws Exception{

	    Connection conn = null;
	    try
	    {
	      Class.forName("org.postgresql.Driver");


      		String url = "jdbc:postgresql://localhost/tmcdon26_apps_prac_ass2.1";


	      conn = DriverManager.getConnection(url,"tmcdon26_apps", "220196038");
	    }
	    catch (ClassNotFoundException e)
	    {
	      e.printStackTrace();
	      System.exit(1);
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	      System.exit(2);
	    }
	    //Now we're connected up lets retrieve a list of employees


	    System.out.println("\n****Employees Currently Within the Database*****");
	    System.out.println();
	    //Print the column headings to the console
            System.out.printf("%-12s %-12s %n", "First Name","Last Name");
	    System.out.println("--------------------");

	    // First we specify our query
	    String query = "SELECT fname, lname,bdate,sex,dname,dlocation FROM employee, department, dept_locations WHERE dno = department.dnumber AND department.dnumber = dept_locations.dnumber;";
	    Statement stmt = null;
	    try {
		//Create an sql statement object
		stmt = conn.createStatement();
		//Execute the query
		ResultSet rs = stmt.executeQuery(query);
		//Iterate through the results and print to the console
		while (rs.next()) {
		    String fName = rs.getString("fname");
		    String lName = rs.getString("lname");
                    String bdate = rs.getString("bdate");
		    String sex = rs.getString("sex");
                    String dname = rs.getString("dname");
		    String dlocation = rs.getString("dlocation");
		    System.out.printf("%-12s %-12s %-12s %-12s %-12s %-12s %n", fName,lName,bdate,sex,dname,dlocation);
		}
	    } catch (SQLException e ) {
		System.out.println(e);
		conn.close();
		System.exit(1);
	    }
	    System.out.println("--------------------");
	    System.out.println("\nQuery Executed Successfully...exiting");
	    //Close the database connection
	    conn.close();
      }

}
