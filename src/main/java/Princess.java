import java.util.*;

public class Princess {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    ArrayList<String> storage = new ArrayList<>();



    System.out.println("    ____________________________________________________________");
    System.out.println("    Hello! I'm your Beautiful Princess");
    System.out.println("    What can I do for you?");
    System.out.println("    ____________________________________________________________");
    System.out.println();

    String input = "";
    while (true) {
      input = sc.nextLine();
      if (input.equals("bye"))    //exit loop
        break;

      System.out.println("    ____________________________________________________________");

      if (input.equals("list")) {     //listing out the storage
        int num = 1;
        for (String elem : storage) {
          System.out.println("     " + Integer.toString(num++) + ". " + elem);
          
        }
      }
      else {          //adding  new item into storage
        storage.add(input);
        System.out.println("     added: " + input);

      }
      
      System.out.println("    ____________________________________________________________");
      System.out.println();
    }


    System.out.println("    ____________________________________________________________");
    System.out.println("    Bye. Hope to see you again soon!");
    System.out.println("    ____________________________________________________________");




  }

}