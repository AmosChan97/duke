import java.io.FileOutputStream;
import java.io.IOException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String i, String description) {
        super(description);
        this.isDone = i.equals("1");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
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
