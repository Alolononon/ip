
public class UI {

    public UI() {

    }

    public void showDivider() {
        System.out.println("    ____________________________________________________________");
    }

    public void showWelcomeMessage() {
        showDivider();
        System.out.println("     Hello! I'm your Beautiful Princess");
        System.out.println("     What can I do for you?");
        showDivider();
        System.out.println();
    }

    public void showHelpMessage() {
        System.out.println("     " + "below are the commands! Command me boi!");
        System.out.println("       " + "list");
        System.out.println("       " + "delete [index]");
        System.out.println("       " + "mark [index]");
        System.out.println("       " + "unmark [index]");
        System.out.println("       " + "todo [taskname]");
        System.out.println("       " + "deadline [taskname] /by [deadline]");
        System.out.println("       " + "event [taskname] /from [date/time] /to [date/time]");
        System.out.println("       " + "find [keyword]");
        System.out.println("       " + "bye");
    }

    public void showEndingDivider() {
        showDivider();
        System.out.println();
    }

    public void showEndingMessage() {
        showDivider();
        System.out.println("     Bye. Hope to see you again soon!");
        showDivider();
    }




}




