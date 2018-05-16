package hashverifier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Argos
 */
public class Hashverifier {

    private static int collisions = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String location = "/shark_hash.txt";

        /*for (String line : openFile(location)) {
            System.out.println(line);
        }//*/

        compareRecords(openFile(location));
        
        
    }

    private static List<String> openFile(String location) throws IOException {
        List<String> records = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(location));
            String line = "";
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
            return records;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean hasSameSize(String str, String str2) {
        return str.length() == str2.length();
    }

    private static boolean equalsTo(String str, String str2) {
        return str.equals(str2);
    }

    private static boolean findCollisions(String str, String str2) {
        if (hasSameSize(str, str2)) {
            if (equalsTo(str, str2)) {
                collisions++;
                System.out.println("Collision found index: " + collisions + " [" + str + " " + str2 + "]");
                return true;
            }
        }
        return false;
    }

    private static void compareRecords(List<String> records) {
        
        for(int i = 0; i < records.size(); i++){
            String firstline = records.get(i);
            for(int j = i+1; j < records.size(); j++){
                String secondline = records.get(j);
                if(i != j){
                    //System.out.println("["+firstline+":"+secondline+"]");
                    findCollisions(firstline,secondline);
                }
            }
        }
        
    }

}
