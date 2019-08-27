import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void saveInFile() throws IOException{
        String toWrite = this.isDone + " | " + this.description;
        FileOutputStream f = new FileOutputStream(Constants.FILENAME, true);
        f.write(toWrite.getBytes());
        f.close();
    }
}