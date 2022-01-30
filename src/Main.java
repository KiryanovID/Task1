import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        try {
            File file;
            if (args.length > 0) {
                file = new File(args[0]);
            } else {
                System.out.println("Введите имя файла:");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                file = new File(br.readLine());
            }
            System.out.println(calculate(file));
        } catch (IOException e) {
            System.out.println("Файл не найден!");

        }
    }

    public static String calculate(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<Short> list = new ArrayList<>();
        int count = 0;
        while (scanner.hasNext() && (count <= 1000)) {
            list.add(scanner.nextShort());
            count++;
        }
        scanner.close();
        Collections.sort(list);
        int sum = 0;
        for (Short x : list) {
            sum += x;
        }
        int getPercentile90 = (int) Math.round(0.9 * list.size());
        double getPercentile901 = 0.9 * list.size();
        int median = (int) Math.round(0.5 * list.size());
        String format = "%.2f";
        String formattedPercentile90 = String.format(format, (float) list.get(getPercentile90 - 1));
        String formattedMedian = String.format(format, (float) list.get(median - 1));
        String formattedMax = String.format(format, (float) list.get(0));
        String formattedMin = String.format(format, (float) list.get(list.size() - 1));
        String formattedAverage = String.format(format, (float) sum / list.size());
        String result;
        result = formattedPercentile90 + "\n" + formattedMedian + "\n" +
                formattedMax + "\n" + formattedMin + "\n" + formattedAverage;

        return result;
    }
}
