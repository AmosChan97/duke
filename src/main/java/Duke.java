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
                    System.out.print(i + 1);
                    System.out.println(". " + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                }
            } else if (splitStr[0].equals("done")) {
                int n = Integer.parseInt(splitStr[1]);
                list.get(n - 1).completeTask();
            } else {
                Task t = new Task(input);
                list.add(t);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
