import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;



public class Main {

    static Scanner scanner = new Scanner(System.in);
    static File file = new File("not.txt");


    public static void main(String[] args) {

        while (true){
            System.out.println("Enter an option:");
            System.out.println("1. Add a note");
            System.out.println("2. Display all notes");
            System.out.println("3. Edit note");
            System.out.println("4. Delete note");
            System.out.println("5. Exit");

            // Invalid option, needed outside 'try'.
            int option = -1;

            try {

                option = scanner.nextInt();
                scanner.nextLine();

            } catch(InputMismatchException e) {

                System.out.println("Invalid input! Please enter a number!");
                scanner.nextLine();
                continue;
            }

            if (option > 5 || option < 1){
                System.out.println("Invalid option");
                continue;
            }

            switch(option){

                case 1:
                    addNote();
                    break;

                case 2:
                    showNotes();
                    break;

                case 3:
                    editNotes();
                    break;

                case 4:
                    deleteNote();
                    break;

                case 5:
                    System.out.println("Thank you for using our library!");
                    System.out.println("Now exiting the program... Goodbye!");
                    System.exit(0);
            }

        }


    }




        public static void addNote(){


            // Stores the user's input from the console to be saved in a file.

            System.out.println("Please enter what you want to save: ");
            String text;
            text = scanner.nextLine();



            // This code writes the user's input to a file.
            try {
                FileWriter fileWriter = new FileWriter("not.txt", true);
                fileWriter.write(text + System.lineSeparator());
                fileWriter.close();
                System.out.println("Note written successfully!");

            } catch (IOException e) {
                System.out.println("An error occurred while saving the note!" + e.getMessage());
            }

        }

        public static void showNotes(){


            // This code opens the file, reads the text, and displays it on the screen.
            try {

                if (isFileEmptyOrMissing()){
                    System.out.println("There is no present note!");
                    return;
                }

                ArrayList<String> notes = loadNotesFromFile();
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println((i + 1) + ". " + notes.get(i));
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while reading the note!" + e.getMessage());
            }
        }

        public static void editNotes(){


        // This code allows the user to edit their notes and handles possible errors.

            // The notes from the file are stored in an ArrayList.
            try{


                if (isFileEmptyOrMissing()){
                    System.out.println("There is no present note!");
                    return;
                }


                // Loads notes from the file into an ArrayList.
                ArrayList<String> notes = loadNotesFromFile();


                // Lists the notes on the screen with their corresponding numbers.
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println( (i + 1) + ". " + notes.get(i));
                }


                // Lets the user select a note to edit and updates it with a new note.
                int index = -1;

                while (true) {

                     System.out.println("Please enter the note you want to edit: ");
                     try {
                         int choice = scanner.nextInt();
                         scanner.nextLine();

                         index = choice - 1;

                         if (index >= 0 && index < notes.size()) {
                             break;
                         }else {
                             System.out.println("Invalid choice! Try again!");
                         }

                     } catch(InputMismatchException e) {
                         System.out.println("Invalid input! Please enter a number!");
                         scanner.nextLine();
                     }

                }


                System.out.println("Please enter the new note: ");
                String newNote = scanner.nextLine();
                notes.set(index, newNote);

                System.out.println("Note updated successfully!");



                // Updates the file with the modified list.
                saveNotesToFile(notes);


            }catch (IOException e) {
                System.out.println("An error occurred while editing the note!" + e.getMessage());
            }

        }

        public static void deleteNote(){

            // The notes from the file are stored in an ArrayList.
            try{


                if (isFileEmptyOrMissing()){
                    System.out.println("There is no present note!");
                    return;
                }


                // Loads notes from the file into an ArrayList.
                ArrayList<String> notes = loadNotesFromFile();


                // Lists the notes on the screen with their corresponding numbers.
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println( (i + 1) + ". " + notes.get(i));
                }


                // Lets the user select a note to edit and updates it with a new note.
                int index = -1;

                while (true) {
                    System.out.println("Please enter the note you want to delete: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    index = choice - 1;

                    if (index >= 0 && index < notes.size()) {
                        break;
                    }else {
                        System.out.println("Invalid choice! Try again!");
                    }
                }


                notes.remove(index);
                System.out.println("Note deleted successfully!");



                // Updates the file with the modified list.
                saveNotesToFile(notes);


            }catch (IOException e) {
                System.out.println("An error occurred while deleting the note!" + e.getMessage());
            }

        }





    // Helper methods to reduce duplicate code

        public static boolean isFileEmptyOrMissing(){
            // Checks if the file doesn't exist or is empty.
            return !file.exists() || file.length() == 0;

        }

        public static ArrayList<String> loadNotesFromFile() throws FileNotFoundException {
            ArrayList<String> notes = new ArrayList<>();
            Scanner scannerFile = new Scanner(file);

            while (scannerFile.hasNextLine()) {
                notes.add(scannerFile.nextLine());
            }

            scannerFile.close();
            return notes;
        }

        public static void saveNotesToFile(ArrayList<String> notes) throws IOException {
            FileWriter fileWriter = new FileWriter(file, false);

            for (String note : notes) {
                fileWriter.write(note + System.lineSeparator());
            }

            fileWriter.close();
        }



}


