import fileManipulation.*;

import java.io.File;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManipulation fileManipulation = new FileManipulation();

        //Creates list of files in resources folder
        File folder = new File("../src/resources"); 
        File[] listOfFiles = folder.listFiles();     

        //Keeps the interaction with user going until he/she will break it
        while (true) {
            System.out.println("-----------");
            System.out.println("What you want to do?");
            System.out.println("1: List all the file names \n2: Get files by extension \n3: Get information about .txt file \nB: Break");
            String option = scanner.nextLine(); 

            //Breaks the loop if input is B or b
            if (option.equals("B") || option.equals("b")){
                scanner.close();
                break;
            }

            //Check the input and call the right function
            switch (option) {
                case "1":
                    fileManipulation.printAllNames(listOfFiles);
                    break;
                case "2":
                    fileManipulation.printFilesByExtension(listOfFiles, scanner);
                    break;
                case "3":
                    fileManipulation.textFileInformation(listOfFiles, scanner);
                    break;
            }
        }

        
    }
}