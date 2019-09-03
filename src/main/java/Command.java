import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

abstract public class Command {
    protected boolean isExit;
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {};
    public boolean isExit() {
        return this.isExit;
    }

    protected static boolean isValidDateTime(String dateTime) {
        SimpleDateFormat dateTimeFormat =  new SimpleDateFormat("d/M/yyyy HHmm");
        try {
            dateTimeFormat.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
