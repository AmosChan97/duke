import java.io.*;

/**
 * Main Duke class
 * Duke is a chatbot that manage tasks for the user
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructor for Duke
     * @param filePath path of text file containing task list
     */
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

    /**
     *  Main Duke logic run here
     */
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

    /**
     * Main function of Duke
     * @param args input from command line
     */
    public static void main(String[] args) {
        new Duke(Constants.FILENAME).run();
    }
}