import java.util.*;

public class Princess {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    ArrayList<Task> storage = new ArrayList<>();



    System.out.println("    ____________________________________________________________");
    System.out.println("    Hello! I'm your Beautiful Princess");
    System.out.println("    What can I do for you?");
    System.out.println("    ____________________________________________________________");
    System.out.println();

    
    while (true) {
      String input = sc.nextLine(); String[] inputss = input.split(" "); String code = inputss[0];



      if (input.equals("bye"))    //exit loop
        break;

      System.out.println("    ____________________________________________________________");

      if (input.equals("list")) {     //listing out the storage
        System.out.println(" Here are the tasks in your list for your princess!");
        int num = 1;
        for (Task elem : storage) {
          System.out.println("     " + Integer.toString(num++) + "." +
            "[" + (elem.getIsTaskDone() ? "X" : " ") + "] " +
            elem.getTaskName());
          
        }
      } else if (code.equals("mark")) {
        
        int elemNum = Integer.parseInt(inputss[1]);
        Task elem = storage.get(elemNum-1);
        elem.markTask();
        System.out.println("     Nice! You are the best! Princess have help you marked this task as done:");
        System.out.println("     " + "[" + (elem.getIsTaskDone() ? "X" : " ") + "] " + elem.getTaskName());

      } else if (code.equals("unmark")) {
        int elemNum = Integer.parseInt(inputss[1]);
        Task elem = storage.get(elemNum-1);
        elem.unmarkTask();
        System.out.println("     Whattt?!?! Alright... Princess have marked this task as undone:");
        System.out.println("     " + "[" + (elem.getIsTaskDone() ? "X" : " ") + "] " + elem.getTaskName());
      }



      else {          //adding  new item into storage
        storage.add(new Task(input));
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