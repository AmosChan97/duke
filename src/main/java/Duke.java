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
        while (!input.equals("bye")) {
            String[] splitStr = input.split(" ");
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(i + 1 + ". ");
                    System.out.println(list.get(i).toString());
                }
            } else if (splitStr[0].equals("done")) {
                int n = Integer.parseInt(splitStr[1]);
                list.get(n - 1).markAsDone();
            } else {
                if (splitStr[0].equals("deadline")) {
                    String tempD = input.substring(9);
                    String[] splitD = tempD.split(" /by ");
                    Deadline deadline = new Deadline(splitD[0], splitD[1]);
                    list.add(deadline);
                } else if (splitStr[0].equals("todo")) {
                    String tempT = input.substring(5);
                    Todo todo = new Todo(tempT);
                    list.add(todo);
                } else if (splitStr[0].equals("event")) {
                    String tempE = input.substring(6);
                    String[] splitE = tempE.split(" /at ");
                    Event event = new Event(splitE[0], splitE[1]);
                    list.add(event);
                } else {
                    Task t = new Task(input);
                    list.add(t);
                }
                System.out.println("Got it. I've added this task:\n"
                        + list.get(list.size() - 1).toString());
                System.out.printf("Now you have %d task(s) in the list.\n", list.size());
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
