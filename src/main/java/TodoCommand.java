import java.io.IOException;

public class TodoCommand extends Command {
    private String string;
    public TodoCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        this.string = input.substring(5).replaceAll("\n", "");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Todo todo = new Todo(this.string);
        tasks.add(todo);
        storage.saveToFile(tasks);
        ui.showString("Got it, I've added this task: \n" +
                todo.toString() + "\n" +
                "Now you have " + tasks.size() + " tasks in your list");
    }
}
