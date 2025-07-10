import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;



public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true){
            System.out.println("Enter an option:");
            System.out.println("1. Add a note");
            System.out.println("2. Display all notes");
            System.out.println("3. Edit note");
            System.out.println("4. Delete note");
            System.out.println("5. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option > 4 || option < 1){
                System.out.println("Invalid option");
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


        ///
        // These methods will help the user to do whatever they want.
        ///

        public static void addNote(){


            // This code stores what the user typed to console and wants to save in a file.

            System.out.println("Please enter what you want to save: ");
            String text;
            text = scanner.nextLine();



            // This code will use the previous code and write what the user wants to save to a file.
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


            // This code will open the file and read the text then shows it to the screen.
            try {
                File file = new File("not.txt");

                if ( !file.exists() || file.length() == 0 ) {
                    System.out.println("There is no present note!");
                    return;
                }

                Scanner scannerFile = new Scanner(file);
                while (scannerFile.hasNextLine()) {
                    System.out.println(scannerFile.nextLine());

                }
                scannerFile.close();

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while reading the note!" + e.getMessage());
            }
        }

        public static void editNotes(){


        // This code will let the user edit their notes and tries to catch errors.

            // Here file's notes are being stored to an ArrayList and then being named.
            try{
                File file = new File("not.txt");

                if ( !file.exists() || file.length() == 0 ) {
                    System.out.println("There is no present note!");
                    return;
                }

                // I created an ArrayList and add the note to it.
                ArrayList<String> notes = new ArrayList<>();
                Scanner scannerFile = new Scanner(file);

                while (scannerFile.hasNextLine()) {

                    notes.add(scannerFile.nextLine());
                }
                scannerFile.close();



                // This lists the notes on the screen with numbers.
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println( (i + 1) + ". " + notes.get(i));
                }


                // Lets the user choose which list they want to edit and edits them with a new note.
                int index = -1;

                 while (true) {
                     System.out.println("Please enter the note you want to edit: ");
                     int choice = scanner.nextInt();
                     scanner.nextLine();

                    index = choice - 1;

                     if (index >= 0 && index < notes.size()) {
                         break;
                     }else {
                         System.out.println("Invalid choice! Try again!");
                     }
                 }


                System.out.println("Please enter the new note: ");
                String newNote = scanner.nextLine();

                notes.set(index, newNote);
                System.out.println("Note updated successfully!");



                // Updates the list.
                FileWriter fileWriter = new FileWriter("not.txt", false);
                for (String note : notes) {
                    fileWriter.write(note + System.lineSeparator());
                }
                fileWriter.close();


            }catch (IOException e) {
                System.out.println("An error occurred while editing the note!" + e.getMessage());
            }

        }

        public static void deleteNote(){

            // Here file's notes are being stored to an ArrayList and then being named.
            try{
                File file = new File("not.txt");

                if ( !file.exists() || file.length() == 0 ) {
                    System.out.println("There is no present note!");
                    return;
                }

                // I created an ArrayList and add the note to it.
                ArrayList<String> notes = new ArrayList<>();
                Scanner scannerFile = new Scanner(file);

                while (scannerFile.hasNextLine()) {

                    notes.add(scannerFile.nextLine());
                }
                scannerFile.close();



                // This lists the notes on the screen with numbers.
                for (int i = 0; i < notes.size(); i++) {
                    System.out.println( (i + 1) + ". " + notes.get(i));
                }


                // Lets the user choose which list they want to edit and edits them with a new note.
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



                // Updates the list.
                FileWriter fileWriter = new FileWriter("not.txt", false);
                for (String note : notes) {
                    fileWriter.write(note + System.lineSeparator());
                }
                fileWriter.close();


            }catch (IOException e) {
                System.out.println("An error occurred while deleting the note!" + e.getMessage());
            }

        }




    }


