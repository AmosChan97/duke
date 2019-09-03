import java.io.IOException;

public class DeadlineCommand extends Command {
    String[] splitD;
    public DeadlineCommand(String input, String[] splitStr) throws DukeException {
        if (splitStr.length == 1) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        String tempD = input.substring(9);
        if (!tempD.contains(" /by ")) throw new DukeException("☹ OOPS!!! Please add a deadline for the task.");
        this.splitD = tempD.split(" /by ");
        if (!isValidDateTime(splitD[1])) throw  new DukeException("Please enter correct date time format: dd/mm/yyyy hhmm");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Deadline deadline = new Deadline(splitD[0], splitD[1]);
        tasks.add(deadline);
        storage.saveToFile(tasks);
        ui.showString("Got it. I've added this task:\n" +
                deadline.toString() + "\n" +
                "Now you have " + tasks.size() + " task(s) in the list.");
    }
}
