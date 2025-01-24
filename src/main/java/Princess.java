import java.util.*;

public class Princess {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);


    System.out.println("    ____________________________________________________________");
    System.out.println("    Hello! I'm Princess");
    System.out.println("    What can I do for you?");
    System.out.println("    ____________________________________________________________");
    System.out.println();

    String input = "";
    while (true) {
      input = sc.nextLine();
      if (input.equals("bye"))
        break;
      System.out.println("    ____________________________________________________________");
      System.out.println("    " + input);
      System.out.println("    ____________________________________________________________");
      System.out.println();
    }


    System.out.println("    ____________________________________________________________");
    System.out.println("    Bye. Hope to see you again soon!");
    System.out.println("    ____________________________________________________________");




  }

}