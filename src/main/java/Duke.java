import java.io.*;
import java.util.ArrayList;

public class Duke {
    private static boolean added;
    private static ArrayList<Task> list = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Constants.FILENAME).run();
    }
}