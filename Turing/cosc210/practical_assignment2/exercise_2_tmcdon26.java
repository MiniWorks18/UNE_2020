import java.util.*;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.lang.Object;


public class exercise_2_tmcdon26 {
  public static void main(String [] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    Connection conn = null;

    pl("******************************************************************");
    pl("");
    pl("                       Welcome to MovieDirect");
    pl("");
    pl("******************************************************************");
    pl("");

    p("database: ");
    String database = keyboard.next();
    p("user: ");
    String user = keyboard.next();
    p("password: ");
    String password = keyboard.next();

    pl("");

    // After collecting database, user, password, connect to sql
    try{
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost/"+database;
      conn = DriverManager.getConnection(url,user,password);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        System.exit(1);
    } catch (SQLException e){
        e.printStackTrace();
        System.exit(2);
    }

    // Array for string values per input expected
    // Format: {question, answer, column name, data type}
    String[][] new_movie =
      {{"id for new movie: ", "", "movie_id","int"},
      {"title for the new movie: ", "", "movie_title","string"},
      {"director's first name: ","", "director_first_name", "string"},
      {"director's last name: ", "", "director_last_name", "string"},
      {"genre of the movie: ", "", "genre", "string"},
      {"media type: ","", "media_type", "string"},
      {"movie's studio: ","", "studio_name", "string"},
      {"retail price of the movie: ", "", "retail_price", "int"},
      {"number of copies in stock: ", "", "current_stock", "int"}};

    //// Attempted use of java date systems to pass the release_date to sql,
    //// didn't work.
    // SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    // java.util.Date date = sdf.parse("20/02/2020");
    // long millis = date.getTime();

    try {
    // Loops over every question in new_movie nested array
      for (int i = 0; i < new_movie.length; i++) {
        p("Please enter the "+new_movie[i][0]); // Ask questions
        new_movie[i][1] = keyboard.next(); // Store answers
      }
    } catch (NumberFormatException err) {
      pl(err.toString());
    }

    pl("\n**** Inserting new movie *****");
    pl("");

    Statement stmt = null;
    try {
      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
        ResultSet.CONCUR_UPDATABLE);
      ResultSet uprs = stmt.executeQuery("SELECT * FROM movies");
      uprs.moveToInsertRow();
      for (int i = 0; i < new_movie.length; i++) {
        if (new_movie[i][3] == "int") { // If input is type integer
          uprs.updateInt(new_movie[i][2], Integer.parseInt(new_movie[i][1]));
        } else { // Default input type expected to be string
          uprs.updateString(new_movie[i][2], new_movie[i][1]);
        }
      }
      uprs.insertRow();
      uprs.beforeFirst();
      conn.close();
      pl("\nMovie Successfully Added.");
    } catch (SQLException e) {
      pl(e.toString());
      System.exit(1);
    }
  }
  public static void p(String line) {System.out.print(line);} // Print
  public static void pl(String line) {System.out.println(line);} // Println
}
