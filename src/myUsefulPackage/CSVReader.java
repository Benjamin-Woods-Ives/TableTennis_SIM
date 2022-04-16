package myUsefulPackage;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    private String[][] allValues = new String[128][];

    public CSVReader(String fileName) {
        //instead of creating an object now can call static method to do the same thing
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {


                String[] values = line.split(",");
                //System.out.println(values[0]);
                if (count != 0) {
                    this.allValues[count - 1] = values;
                }
                count++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String[][] staticRead(String fileName, int fileHeight) {
        String[][] allValues = new String[fileHeight][];
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {


                String[] values = line.split(",");
                //System.out.println(values[0]);
                if (count != 0) {
                    allValues[count - 1] = values;
                }
                count++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allValues;
    }


    public String[][] getAll_values() {
        return allValues;
    }
}