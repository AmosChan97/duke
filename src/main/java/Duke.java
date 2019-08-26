import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        ArrayList<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        boolean added;
        while (!input.equals("bye")) {
            added = false;
            String[] splitStr = input.split(" ");
            if (input.equals("list")) {
                try {
                    if (list.size() == 0) throw new DukeException("☹ OOPS!!! There are no tasks in your list");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.print(i + 1 + ". ");
                        System.out.println(list.get(i).toString());
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (splitStr[0].equals("done")) {
                try {
                    if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! Please add the index of the task you have completed");
                    int n = Integer.parseInt(splitStr[1]);
                    list.get(n - 1).markAsDone();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Input is not an integer");
                }
            } else {
                if (splitStr[0].equals("deadline")) {
                    try {
                        if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        String tempD = input.substring(9);
                        if (!tempD.contains(" /by ")) throw new DukeException("☹ OOPS!!! Please add a deadline for the task.");
                        String[] splitD = tempD.split(" /by ");
                        Deadline deadline = new Deadline(splitD[0], splitD[1]);
                        list.add(deadline);
                        added = true;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (splitStr[0].equals("todo")) {
                    try {
                        if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        String tempT = input.substring(5);
                        Todo todo = new Todo(tempT);
                        list.add(todo);
                        added = true;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (splitStr[0].equals("event")) {
                    try {
                        if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        String tempE = input.substring(6);
                        if (!tempE.contains(" /at ")) throw new DukeException("☹ OOPS!!! Please add a timing for this event.");
                        String[] splitE = tempE.split(" /at ");
                        Event event = new Event(splitE[0], splitE[1]);
                        list.add(event);
                        added = true;
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (added == true) {
                    System.out.println("Got it. I've added this task:\n"
                            + list.get(list.size() - 1).toString());
                    System.out.printf("Now you have %d task(s) in the list.\n", list.size());
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
