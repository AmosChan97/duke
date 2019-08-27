import java.io.FileOutputStream;
import java.io.IOException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String i, String description, String by) {
        super(description);
        this.by = by;
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
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
