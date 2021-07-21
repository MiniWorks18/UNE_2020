import java.io.* ;


class InputExample {
     public static void main(String args[])
     {
          // Create a new InputStreamReader and connecting to STDIN
          InputStreamReader istream = new InputStreamReader(System.in) ;

          // Create a new BufferedReader and connect it to the InputStreamReader
          // Now the plumbing is done, we can read from the BufferedReader
          BufferedReader bufRead = new BufferedReader(istream) ;

          /**********************************************************
          * The BufferedReader class a several methods for reading
          * input:
          *  readLine() - reads a line of input from the stream
          *  read() -     returns the integer representation of the
          *               next character in the stream.
          *  read(char[] cbuf,int off,int len)-
          *               Reads len characters to the buf.
          *
          **********************************************************/


          System.out.println("Welcome To My First Java Program");

          try {
               System.out.println("Please Enter In Your First Name: ");
               String firstName = bufRead.readLine();

               System.out.println("Please Enter In The Year You Were Born: ");
               String bornYear = bufRead.readLine();

               System.out.println("Please Enter In The Current Year: ");
               String thisYear = bufRead.readLine();

               int bYear = Integer.parseInt(bornYear);
               int tYear = Integer.parseInt(thisYear);

               int age = tYear-bYear;
               System.out.println("Hello " + firstName + " You are "
                   + age + " years old");
          }
          catch (IOException err) {
               System.out.println("Error reading line");
          }
          catch(NumberFormatException err) {
               System.out.println(err);
          }
     }
}
