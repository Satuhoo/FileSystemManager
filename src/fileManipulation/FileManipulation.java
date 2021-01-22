package fileManipulation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class FileManipulation {

    public void printAllNames(File[] listOfFiles) {
        //Changes all files to strings and cut them for file names
        System.out.println("-----------");
        for (File file: listOfFiles){
            String name = file.toString();
            int lastChar = name.length();
            String fileName = name.substring(17, lastChar);
            System.out.println(fileName);
        }
    } 

    public void printFilesByExtension(File[] listOfFiles, Scanner scanner) {
        //Ask the extension from the user and check all files with the same extension
        System.out.println("Type extension: ");
        String ext = scanner.nextLine();
        System.out.println("-----------");

        for (File file: listOfFiles) {
            String name = file.toString();
            int lastChar = name.length();
            String fileName = name.substring(17, lastChar);
            if (file.toString().endsWith(ext)){
                System.out.println(fileName);
            }
        }
    }

    public void textFileInformation(File[] listOfFiles, Scanner scanner) {
        System.out.println("Type name of text file: ");
        String name = scanner.nextLine();
        System.out.println("-----------");
        try {
            File providedFile = getName(name, listOfFiles);
            getSize(providedFile);

        } catch (NullPointerException ex) {
            System.out.println("There is no file called " + name);
        }
    }

    public File getName(String name, File[] listOfFiles) {
        File providedFile = null;
        for (File file: listOfFiles) {
            if (file.toString().endsWith(name)){
                System.out.println(name);
                providedFile = file;
            }
        }
        return providedFile;
    }

    public void getSize(File name){
        try(Reader fileReader = new FileReader(name)){
            
            int count = 0;
    
            while((fileReader.read()) != -1) {
                count = count + 1;
            }
            System.out.println(count);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
