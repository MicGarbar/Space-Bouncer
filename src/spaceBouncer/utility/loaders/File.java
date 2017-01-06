package spaceBouncer.utility.loaders;

import java.io.*;

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

    public static String loadOneLine(String filePath, int lineNumber){
        StringBuilder source = new StringBuilder();

        try{
            String line = "";
            int lineCounter = 0;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while((line = reader.readLine()) != null){
                lineCounter++;

                if(lineNumber == lineCounter){
                    source.append(line);
                }
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

        } catch (IOException ioe){
            ioe.printStackTrace();
            System.err.println("Odczytanie pliku nie powiodło się z następującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }

        return linesAmount;
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
