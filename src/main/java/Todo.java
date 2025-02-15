import java.io.FileOutputStream;
import java.io.IOException;

public class Todo extends Task {

    /**
     * Todo Constructor
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor from text file
     * @param i isDone status
     * @param description task description
     */
    public Todo(String i, String description) {
        super(description);
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string that is formatted for the text file
     * @return String
     */
    @Override
    public String toWriteFile() {
        int boolToInt = this.isDone ? 1 : 0;
        return "T | " + boolToInt + " | " + this.description + "\n";
    }

    @Override
    public void saveInFile() throws IOException {
        int boolToInt = this.isDone ? 1 : 0;
        String toWrite = "T | " + boolToInt + " | " + this.description + "\n";
        FileOutputStream f = new FileOutputStream(Constants.FILENAME, true);
        f.write(toWrite.getBytes());
        f.close();
    }
}
