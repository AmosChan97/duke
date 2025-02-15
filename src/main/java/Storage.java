import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles read and write to text file to contain task list
 */
public class Storage {

    /**
     * Text file to read or write
     */
    private File fileToRead;

    public Storage(String filePath) {
        fileToRead = new File(filePath);
    }

    /**
     * Convert contents of text file to task list
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<String> load() throws FileNotFoundException {
        Scanner scan_file = new Scanner(fileToRead);
        ArrayList<String> textLoaded = new ArrayList<>();
        while (scan_file.hasNextLine()) {
            String line = scan_file.nextLine();
            textLoaded.add(line);
        }
        return textLoaded;
    }

    /**
     * Save task list to text file
     * @param tasks task list
     * @throws IOException
     */
    public void saveToFile(TaskList tasks) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileToRead);
        String toWrite = "";
        for (Task i : tasks) {
            toWrite += i.toWriteFile();
        }
        fileOutputStream.write(toWrite.getBytes());
        fileOutputStream.close();
    }
}
