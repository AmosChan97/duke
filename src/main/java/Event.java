import java.io.FileOutputStream;
import java.io.IOException;

public class Event extends Task {
    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    public Event (String i, String description, String at) {
        super(description);
        this.at = at;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
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
