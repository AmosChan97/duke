import java.io.IOException;

public class DeleteCommand extends Command{
    private int n;
    public DeleteCommand(String[] splitStr) throws DukeException {
        if (splitStr.length == 1)
            throw new DukeException("☹ OOPS!!! Please add the index of the task you want to remove");
        this.n = Integer.parseInt(splitStr[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.n < 1 || this.n > tasks.size()) throw new DukeException("☹ OOPS!!! That task is not in your list");
        ui.showString("Noted! I've removed this task: \n" +
                tasks.get(this.n - 1).toString());
        tasks.remove(this.n -1);
        storage.saveToFile(tasks);
        ui.showString("Now you have " + tasks.size() + " tasks in your list.");
    }
}
