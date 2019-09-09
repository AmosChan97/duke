import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Tasks which have a deadline
 * Lists the task name and stores the deadline in Date format
 */
public class Deadline extends Task {
    protected Date dateTime;
    protected String by;

    /**
     * Deadline Constructor
     * @param description task descriprtion
     * @param by task deadline
     */
    public Deadline(String description, String by) {
        super(description);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.dateTime = sdf.parse(by);
        } catch (ParseException e) {
            System.out.println("Please enter date time format correctly: dd/mm/yyyy hhmm");
        }
        this.by = by;
    }

    /**
     * Deadline Constructor from text file
     * @param i isDone status
     * @param description
     * @param by
     */
    public Deadline(String i, String description, String by) {
        super(description);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.dateTime = sdf.parse(by);
        } catch (ParseException e) {
            System.out.println("Please enter date time format correctly: dd/mm/yyyy hhmm");
        }
        this.by = by;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }

    /**
     * Returns a string that is formatted for the text file
     * @return String
     */
    @Override
    public String toWriteFile() {
        int boolToInt = isDone ? 1 : 0;
        return "D | " + boolToInt + " | " + this.description + " | " + this.by + "\n";
    }

    /**
     * Appends the tasks to the text file
     * @throws IOException
     */
    @Override
    public void saveInFile() throws IOException {

        int boolToInt = isDone ? 1 : 0;
        String toWrite = "D | " + boolToInt + " | " + this.description + " | " + this.by + "\n";

        FileOutputStream f = new FileOutputStream(Constants.FILENAME, true);
        f.write(toWrite.getBytes());
        f.close();
    }
}
