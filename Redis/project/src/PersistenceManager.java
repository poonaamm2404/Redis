import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class PersistenceManager {
    private String fileName = "redis-data.txt";
    public void save(RedisStore store) {
        try {
            FileWriter writer = new FileWriter(fileName);
            HashMap<String, String> data = store.getAllData();
            for (String key : data.keySet()) {
                writer.write(key + "=" + data.get(key) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }
    public void load(RedisStore store) {
        try {
            Scanner scanner = new Scanner(new File(fileName));//creates a Scanner that can read that file.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=", 2);
                String key = parts[0];
                String value = parts[1];
                store.loadData(key, value);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found.");
        }
    }
}