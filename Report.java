import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Report {
    public static void main(String[] args) {
        // WARNING:: Under method ask TOO LONG TIME to finish.
        // tryEntire();
        /// Meta Info
        int[] numOfData = { 10000, 50000, 100000, 500000, 1000000 };
        int[] dataIndex = { 0, 1, 2, 3, 4 };
        boolean[] isAlmost = { false, true };
        String[] sort = { "radix", "quick", "bubble" };
        long duration = trial(numOfData[0], dataIndex[0], isAlmost[0], sort[0]);
        System.out.println("Duration: " + duration + "[ms]");
    }   

    private static long trial(int numOfData, int dataIndex, boolean isAlmost, String sort) {
        /*
         * @param numOfData : Number of data { 10000, 50000, 100000, 500000, 1000000 }
         * 
         * @param dataIndex : { 0, 1, 2, 3, 4 }
         * 
         * @param isAlmost : Whether the data is almost sorted. { false, true }
         * 
         * @param sort : The method of sort { "radix", "quick", "bubble" } default is "radix"
         */
        int maxDigits = String.valueOf(numOfData).length() + 1;
        String almostString = isAlmost ? "_almost" : "";
        // <<Unsorted Data>>
        int[] data = Data.file2List(numOfData,
                String.format("./data/%d/%d_%d%s", numOfData, numOfData, dataIndex, almostString));
        // <<Sorted Data>>
        int[] answer = Data.file2List(numOfData,
                String.format("./data/%d/%d_%d_sorted", numOfData, numOfData, dataIndex));
        long startTime;
        // Sort
        // ===========================================
        switch (sort = sort.toLowerCase()) {
            case "bubble":
                startTime = System.currentTimeMillis();
                Sort.bubble_sort(data);
                break;
            case "quick":
                startTime = System.currentTimeMillis();
                Sort.quick_sort(data, 0, data.length - 1);
                break;
            case "radix":
            default:
                startTime = System.currentTimeMillis();
                Sort.radix_sort(data, maxDigits);
                break;
        }
        // ===========================================
        long endTime = System.currentTimeMillis();
        // <<Check if is same sorted data & answer>>
        System.out.println(Data.compareList(data, answer));
        // <<print sorted data>>
        // Data.printData(data);
        // <<Print duration of sorting.>>
        // System.out.println("Start: " + startTime + "[ms]");
        // System.out.println("End: " + endTime + "[ms]");
        long duration = endTime - startTime;
        // System.out.println("Duration: " + duration + "[ms]");
        return duration;
    }

    private static void writeAsFile(String path, String contents) {
        // Write contents in filepath 
        // (PrintWriter writer = new PrintWriter(new File("result.csv")))
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)),true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(contents);
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tryEntire() {
        /*
         * Try 10 times on all of case and save the result into result.csv
         */
        int[] numOfData = { 10000, 50000, 100000, 500000, 1000000 };
        int[] dataIndex = { 0, 1, 2, 3, 4 };
        boolean[] isAlmost = { false, true };
        String[] sort = { "radix", "quick", "bubble" };
        long duration;
        String content, almostString;
        String path = "./result.csv";
        writeAsFile(path, "DATA_NAME,SORT,TRIAL,DURATION[ms]\n");
        for (String s : sort) {
            for (int n : numOfData) {
                for (int i : dataIndex) {
                    for (boolean a : isAlmost) {
                        for (int l = 0; l < 10; l++) {
                            duration = trial(n, i, a, s);
                            // 
                            almostString = a ? "_almost" : "";
                            content = String.format("%d/%d/%d%s", n, n, i, almostString)
                                    + ","+ s
                                    + ","+ l
                                    + ","+duration+"\n";
                            writeAsFile(path, content);
                        }
                    }
                }
            }
        }
    }
}