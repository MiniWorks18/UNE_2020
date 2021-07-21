
import java.io.* ;
import java.sql.*;
import java.util.*;

class CreateEmployee {


  public static void main(String args[]){



    // Database connection stuff

    Connection conn = null;
    try
    {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost/tmcdon26_apps_prac_2.1";
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

    InputStreamReader istream = new InputStreamReader(System.in);
    BufferedReader bufRead = new BufferedReader(istream);

    /* - Employee table. Here to show column list
    CREATE TABLE employee (
    fname character varying(15) NOT NULL,
    minit character varying(1),
    lname character varying(15) NOT NULL,
    ssn character(9) NOT NULL,
    bdate date,
    address character varying(50),
    sex character(1),
    salary numeric(10,2),
    superssn character(9),
    dno integer
    );

    */
    String firstName = "";
    String minit= "";
    String lastName= "";
    String ssn= "";
    String dno= "";
    int dno_int= -1;
    // try {
    //   System.out.println("Please Enter the employee's First Name: ");
    //   firstName = bufRead.readLine();
    //
    //   System.out.println("Please Enter the employee's middle initial: ");
    //   minit = bufRead.readLine();
    //
    //   System.out.println("Please Enter the employee's Last Name: ");
    //   lastName = bufRead.readLine();
    //
    //   System.out.println("Please Enter the employee's Ssn: ");
    //   ssn = bufRead.readLine();
    //
    //   System.out.println("Please Enter the employee's Department Number: ");
    //   dno = bufRead.readLine();
    //
    //   dno_int = Integer.parseInt(dno);
    //
    //   /*
    //
    //   Add the additional data fields here
    //
    //   */
    //
    //
    // }catch (IOException err) {
    //   System.out.println(err);
    // }catch(NumberFormatException err) {
    //   System.out.println(err);
    // }

    //Now lets insert a new row of data

    System.out.println("\n****Inserting a New Employee*****");
    System.out.println();


    // First we specify our query
    Statement stmt = null;
    try {
      //Create a new statement object - notice the additional arguments for inserting
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      //Get all record in the employee table
      ResultSet uprs = stmt.executeQuery("SELECT * FROM employee");
      /* - Employee table. Here to show column list
      CREATE TABLE employee (
      fname character varying(15) NOT NULL,
      minit character varying(1),
      lname character varying(15) NOT NULL,
      ssn character(9) NOT NULL,
      bdate date,
      address character varying(50),
      sex character(1),
      salary numeric(10,2),
      superssn character(9),
      dno integer
      );

      */
      //Create a new row in the ResultSet object
      uprs.moveToInsertRow();
      //Add new employee's information to the new row of data

      // This is where you will need to update the code to include
      // the data entered by the user.

      uprs.updateString("fname", firstName );
      uprs.updateString("minit", minit );
      uprs.updateString("lname", lastName );
      uprs.updateString("ssn", ssn );
      uprs.updateInt("dno", dno_int );
      //Insert the new row of data to the database
      uprs.insertRow();
      //Move the cursor back to the start of the ResultSet object
      uprs.beforeFirst();
      conn.close();
      System.out.println("\nQuery Executed Successfully...exiting");
    }catch (SQLException e ) {
      System.out.println(e);
      System.exit(1);
    }


  }


}
