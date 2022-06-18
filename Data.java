import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Data {
    static int[] file2List(int size, String path) {
        System.out.println("Read : "+path);
        int[] list = new int[size];
        try {
            // Buffer of filereader
            BufferedReader br = new BufferedReader(new FileReader(path));
            int idx = 0;
            while (true) {
                // Read one line from file.
                String line = br.readLine();
                // If the line is nothing, break loop.
                if (line == null)
                    break;
                // Put data into list 
                list[idx] = Integer.parseInt(line);
                idx++;
                // Print process as index
                if (idx % 10000 == 0) {
                    System.out.println("Reading file... (" + idx + "/" + size + ")");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        return list;
    }

    static void printData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    static boolean compareList(int[] data1, int[] data2) {
        // Check if two arrays are equal.
        return Arrays.equals(data1, data2);
    }
}
