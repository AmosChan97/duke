import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    void showLoadingError() {
        System.out.println(":( OOPS!!! File path not found. Creating directory /data/data.txt");
    }

    void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }
}
