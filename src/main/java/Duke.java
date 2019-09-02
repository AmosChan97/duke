import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static boolean added;
    private static ArrayList<Task> list = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
    }

    public static void main(String[] args) {
        new Duke(Constants.FILENAME).run();
        fileToList();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            added = false;
            String[] splitStr = input.split(" ");
            switch (splitStr[0]) {
                case "list":
                    list();
                    break;
                case "done":
                    markDone(splitStr);
                    break;
                case "deadline":
                    setDeadline(input, splitStr);
                    break;
                case "todo":
                    setToDo(input, splitStr);
                    break;
                case "event":
                    setEvent(input, splitStr);
                    break;
                case "delete":
                    deleteTask(splitStr);
                    break;
                case "find":
                    findTask(input, splitStr);
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
            if (added) {
                SaveTask(list.get(list.size() -1));
                System.out.println("Got it. I've added this task:\n"
                        + list.get(list.size() - 1).toString());
                System.out.printf("Now you have %d task(s) in the list.\n", list.size());
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void deleteTask(String[] splitStr) {
        int n;
        try {
            if (splitStr.length == 1)
                throw new DukeException("☹ OOPS!!! Please add the index of the task you want to remove");
            n = Integer.parseInt(splitStr[1]);
            if (n < 1 || n > list.size()) throw new DukeException("☹ OOPS!!! That task is not in your list");
            File fileToRead = new File(Constants.FILENAME);
            Scanner scan_file = new Scanner(fileToRead);
            String line, toWrite = "";
            for (int i = 1; i < n; i++) {
                line = scan_file.nextLine();
                toWrite += line + "\n";
            }
            scan_file.nextLine();
            while (scan_file.hasNextLine()) {
                line = scan_file.nextLine();
                toWrite += line + "\n";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(Constants.FILENAME);
            fileOutputStream.write(toWrite.getBytes());
            System.out.println("Got it. I've removed this task:\n"
                    + list.get(n - 1).toString());
            list.remove(n - 1);
            System.out.printf("Now you have %d task(s) in the list.\n", list.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Input is not an integer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findTask(String input, String[] splitStr) {
        boolean found = false;
        try {
            if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! Please input a string to search");
            String textToFind = input.substring(5);
            for(int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(textToFind)) {
                    found = true;
                    System.out.println(i + 1 + ". " + list.get(i).toString());
                }
            }
            if (!found) System.out.println("No items match your search!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void SaveTask(Task t) {
        try {
            t.saveInFile();
        } catch (FileNotFoundException e) {
            System.out.println("Please make file " + Constants.FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        int n = 0;
        try {
            if (splitStr.length == 1)
                throw new DukeException("☹ OOPS!!! Please add the index of the task you have completed");
            n = Integer.parseInt(splitStr[1]);
            if (n < 1 || n > list.size()) throw new DukeException("☹ OOPS!!! That task is not in your list");
            list.get(n - 1).markAsDone();
            File fileToRead = new File(Constants.FILENAME);
            Scanner scan_file = new Scanner(fileToRead);
            String line, toWrite = "";
            while (scan_file.hasNextLine()) {
                line = scan_file.nextLine();
                if (line.contains(list.get(n - 1).description)) {
                    String[] lineSplit = line.split(" \\| ");
                    lineSplit[1] = "1";
                    line = lineSplit[0];
                    for (int i = 1; i < lineSplit.length; i++) {
                        line += " | " + lineSplit[i];
                    }
                }
                line += "\n";
                toWrite += line;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(Constants.FILENAME);
            fileOutputStream.write(toWrite.getBytes());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Input is not an integer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDeadline(String input, String[] splitStr) {
        try {
            if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            String tempD = input.substring(9);
            if (!tempD.contains(" /by ")) throw new DukeException("☹ OOPS!!! Please add a deadline for the task.");
            String[] splitD = tempD.split(" /by ");
            if (!isValidDateTime(splitD[1])) throw  new DukeException("Please enter correct date time format: dd/mm/yyyy hhmm");
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

    private static void fileToList () {
        try {
            File fileToRead = new File(Constants.FILENAME);
            Scanner scan_file = new Scanner(fileToRead);
            while (scan_file.hasNextLine()) {
                String line = scan_file.nextLine();
                String[] splitStr = line.split(" \\| ");
                switch (splitStr[0]) {
                    case "T" :
                        list.add(new Todo(splitStr[1], splitStr[2]));
                        break;
                    case "E" :
                        list.add(new Event(splitStr[1], splitStr[2], splitStr[3]));
                        break;
                    case "D" :
                        list.add(new Deadline(splitStr[1], splitStr[2], splitStr[3]));
                        break;
                    default:
                        list.add(new Task(splitStr[1]));
                }
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            File dir = new File("data");
            dir.mkdir();
        }
    }

    private static boolean isValidDateTime (String dateTime) {
        SimpleDateFormat dateTimeFormat =  new SimpleDateFormat("d/M/yyyy HHmm");
        SimpleDateFormat dateOnlyFormat =  new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeOnlyFormat =  new SimpleDateFormat("HHmm");
        try {
            dateTimeFormat.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}