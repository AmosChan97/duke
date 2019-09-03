import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        super.execute(tasks, ui, storage);
        ui.showBye();
        this.isExit = true;
    }
}
