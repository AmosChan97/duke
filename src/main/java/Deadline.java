import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    protected Date dateTime;
    protected String by;

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

    @Override
    public void saveInFile() throws IOException {
        int boolToInt = isDone ? 1 : 0;
        String toWrite = "D | " + boolToInt + " | " + this.description + " | " + this.by + "\n";
        FileOutputStream f = new FileOutputStream(Constants.FILENAME, true);
        f.write(toWrite.getBytes());
        f.close();
    }
}
