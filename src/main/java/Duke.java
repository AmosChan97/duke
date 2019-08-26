import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean added;
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            addTask(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void SaveTask(Task t) {
        t.saveInFile();
    }

    private static void list() {
        try {
            if (list.isEmpty()) throw new DukeException("☹ OOPS!!! There are no tasks in your list");
            for (int i = 0; i < list.size(); i++) {
                System.out.print(i + 1 + ". ");
                System.out.println(list.get(i).toString());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void markDone(String[] splitStr) {
        try {
            if (splitStr.length == 1)
                throw new DukeException("☹ OOPS!!! Please add the index of the task you have completed");
            int n = Integer.parseInt(splitStr[1]);
            list.get(n - 1).markAsDone();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Input is not an integer");

        }
    }

    private static void setDeadline(String input, String[] splitStr) {
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
    }

    private static void setToDo(String input, String[] splitStr) {
        try {
            if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            String tempT = input.substring(5);
            Todo todo = new Todo(tempT);
            list.add(todo);
            added = true;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setEvent(String input, String[] splitStr) {
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
    }

    private static void addTask(String input) {
        added = false;
        String[] splitStr = input.split(" ");
        if (input.equals("list")) {
            list();
        } else if (splitStr[0].equals("done")) {
            markDone(splitStr);
        } else {
            if (splitStr[0].equals("deadline")) {
                setDeadline(input, splitStr);
            } else if (splitStr[0].equals("todo")) {
                setToDo(input, splitStr);
            } else if (splitStr[0].equals("event")) {
                setEvent(input, splitStr);
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (added == true) {
                System.out.println("Got it. I've added this task:\n"
                        + list.get(list.size() - 1).toString());
                System.out.printf("Now you have %d task(s) in the list.\n", list.size());
            }
        }

    }


}