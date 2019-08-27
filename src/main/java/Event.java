import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {
    protected Date dateTime;
    protected String at;

    public Event (String description, String at) {
        super(description);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.dateTime = sdf.parse(at);
        } catch (ParseException e) {
            System.out.println("Please enter date time format correctly: dd/mm/yyyy hhmm");
        }
        this.at = at;
    }

    public Event (String i, String description, String at) {
        super(description);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.dateTime = sdf.parse(at);
        } catch (ParseException e) {
            System.out.println("Please enter date time format correctly: dd/mm/yyyy hhmm");
        }
        this.at = at;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }

    @Override
    public void saveInFile() throws IOException {
        int boolToInt = isDone ? 1 : 0;
        String toWrite = "E | " + boolToInt + " | " + this.description + " | " + this.at + "\n";
        FileOutputStream f = new FileOutputStream(Constants.FILENAME, true);
        f.write(toWrite.getBytes());
        f.close();
    }
}
