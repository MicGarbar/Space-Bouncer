package spaceBouncer.utility.loaders;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class File {

    private File() {}

    public static String load(String filePath){
        StringBuilder source = new StringBuilder();

        try{
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while((line = reader.readLine()) != null){
                source.append(line).append("\n");
            }

            reader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.err.println("Odczytanie pliku nie powiodło się z następującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }

        return source.toString();
    }

    public static int linesAmount(String filePath){
        int linesAmount = 0;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while(reader.readLine() != null){
                linesAmount++;
            }

            reader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.err.println("Odczytanie pliku nie powiodło się z następującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }

        return linesAmount;
    }

    public static List<String> sortListByScore(String filePath){
        ArrayList<String> scoreList = new ArrayList<>();

        try{
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while((line = reader.readLine()) != null){
                scoreList.add(line);
            }

            Comparator<String> scoreComparator = new Comparator<String>() {
                @Override
                public int compare(String score1, String score2) {
                    return Integer.valueOf(score1.substring(18)).compareTo(Integer.valueOf(score2.substring(18)));
                }
            };

            Collections.sort(scoreList, scoreComparator);
            Collections.reverse(scoreList);

            reader.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.err.println("Odczytanie pliku nie powiodło się z następującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }

        return scoreList;
    }

    public static void save(String what, String where){
        StringBuilder source = new StringBuilder();

        try{
            if(exists(where)){
                source.append(load(where));
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(where));
            source.insert(0, what + "\n");
            writer.write(source.toString());

            writer.flush();
            writer.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
            System.err.println("Zapisanie do pliku nie powiodło się z następującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }
    }

    public static boolean exists(String filePath){
        java.io.File file = new java.io.File(filePath);
        return file.exists();
    }

}
