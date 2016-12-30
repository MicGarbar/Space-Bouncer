package spaceBouncer.utility.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public static boolean exists(String filePath){
        java.io.File file = new java.io.File(filePath);
        return file.exists();
    }

}
