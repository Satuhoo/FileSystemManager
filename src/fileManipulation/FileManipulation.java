package fileManipulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileManipulation {
    Logging logging = new Logging();

    public String fileToString(File file) {
        //Formulates file name and return it
        String name = file.toString();
        int lastChar = name.length();
        String fileName = name.substring(17, lastChar);
        return fileName;
    }

    public void printAllNames(File[] listOfFiles) {
        LocalDateTime time = LocalDateTime.now();
        long start = System.currentTimeMillis();
        String log = "Printed all file names.";
        System.out.println("-----------");
        for (File file: listOfFiles){
            String fileName = fileToString(file);
            System.out.println(fileName);
        }
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
        
    } 

    public void printFilesByExtension(File[] listOfFiles, Scanner scanner) {
        //Ask the extension from the user and check all files with the same extension
        System.out.println("Type extension: ");
        String ext = scanner.nextLine();
        System.out.println("-----------");
        LocalDateTime time = LocalDateTime.now(); //Creates timestamp for logging
        long start = System.currentTimeMillis();
        String log = "Printed files by entered extension '" + ext + "'.";

        for (File file: listOfFiles) {
            if (file.toString().endsWith(ext)){
                String fileName = fileToString(file);
                System.out.println(fileName);
            }
        }
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
    }

    public void textFileInformation(File[] listOfFiles, Scanner scanner) {
        System.out.println("Type name of text file without extension: ");
        String name = scanner.nextLine();
        System.out.println("-----------");
        try {
            File providedFile = getName(name, listOfFiles); //Calls method which searches file with the entered name
            countCharacters(providedFile);
            countLines(providedFile);
            searchWord(providedFile, scanner);

        } catch (NullPointerException ex) { //If returned file is null (doesn't exists), prints info text
            System.out.println("There is no file called " + name);
        }
    }

    public File getName(String name, File[] listOfFiles) {
        LocalDateTime time = LocalDateTime.now();
        long start = System.currentTimeMillis();
        String log = "Searched file by entered name '" + name + "'.";
        File providedFile = null;
        for (File file: listOfFiles) {
            String fileName = fileToString(file);
            //Checks if the entered name matches for any of file names
            if (fileName.toLowerCase().equals(name.toLowerCase() + ".txt")){
                System.out.println(fileName);
                providedFile = file;
            }
        }
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
        return providedFile;
    }

    public void countCharacters(File name){
        LocalDateTime time = LocalDateTime.now();
        long start = System.currentTimeMillis();
        int count = 0;

        try(Reader fileReader = new FileReader(name)){
            
            //Reads all characters in the file and add them to amount
            while((fileReader.read()) != -1) {
                count = count + 1;
            }
            System.out.println("Characters: " + count);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String log = "The size of file was " + count + ".";
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
    }

    public void countLines(File name) {
        LocalDateTime time = LocalDateTime.now();
        long start = System.currentTimeMillis();
        int count = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(name))) {

            //Reads all lines in the file and add them to amount

            while ((bufferedReader.readLine()) != null) {
                count = count + 1;
            }
            System.out.println("Lines: " + count);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String log = "There was " + count + " lines in the file '" + name + "'.";
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
    }

    public void searchWord(File name, Scanner scanner) {
        System.out.println("\nType a word you want to search from the file: ");
        String word = scanner.nextLine();
        LocalDateTime time = LocalDateTime.now();
        long start = System.currentTimeMillis();
        String nextWord;
        Boolean wordExist = false; //default is false
        int wordFound = 0;

        try(Scanner reader = new Scanner(name)){
    
            reader.useDelimiter(" "); //Separator is space between words
    
            while(reader.hasNext()){ //Reads the file until there is no more words
                nextWord = reader.next();

                //Checks if the next word in the file matches for entered word
                if(nextWord.toLowerCase().contains(word.toLowerCase())){ 
                    wordFound++; 
                    wordExist = true;
                };
            }

            if (wordExist) {
                System.out.println("Word '" + word + "' exists in the file " + wordFound + " times");
            } else {
                System.out.println("Word '" + word + "' doesn't exists in the file");
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        String log = "The term '" + word + "' was found " + wordFound + " times.";
        long end = System.currentTimeMillis();
        logging.writeToLog(time, start, end, log);
    }
}
