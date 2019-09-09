import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Abstract class from which all Commands are the extended from
 */
abstract public class Command {
    protected boolean isExit;

    /**
     *
     * @param tasks task list
     * @param ui user interface
     * @param storage handles read write of text file
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {};
    public boolean isExit() {
        return this.isExit;
    }

    /**
     *
     * @param dateTime
     * @return
     */
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
