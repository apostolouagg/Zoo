package com.p19015;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.p19015.Check.check5; //Κάνω import τη μέθοδο check5 από την κλάση check η οποία κληρονομεί από τη Main.

public class Main {

    public static List<Zoo> zooArrayList = new ArrayList<>(); //Δημιουργώ τη λίστα.

    public static void main(String[] args) {

        String i; //Κάνω την επιλογή String και όχι int για να διευκολυνθώ με τα exceptions.
        while (true) {
            Scanner selection = new Scanner(System.in);
            System.out.println("  "); // Γενικά όπου δείτε να βάζω κενά αφτάκια είναι για φαίνεται όμορφο το πρόγραμμα κατά την εκτέλεσή του.
            System.out.println("Zoo Application");
            System.out.println("Select a number from 1-7");
            System.out.println("1) See Zoo List");
            System.out.println("2) Add Animal");
            System.out.println("3) Search Animal By Code");
            System.out.println("4) Search Animal By Name");
            System.out.println("5) Edit Animal By Code");
            System.out.println("6) Delete Animal By Code");
            System.out.println("7) Exit Application");
            System.out.println("--------------------------------");

            while (true){
                System.out.println("Enter Your Option: ");
                try {
                    i = selection.nextLine();

                    //Χρησιμοποιώντας το Integer.parseInt() ελέγχω αν η παραπάνω String επιλογή είναι αριθμός και μάλιστα έγκυρος.
                    if (Integer.parseInt(i) > 0 && Integer.parseInt(i) <= 7){
                        break;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e){
                    System.out.println("This option is not valid. Please try again.");
                }
            }

            //Με βάση την επιλογή καλείται η ανάλογη μέθοδος.
            if (i.equals("1")) {
                showAnimals();
            } else if (i.equals("2")) {
                createAnimals();
            } else if (i.equals("3")) {
                searchAnimalByCode();
            } else if (i.equals("4")) {
                searchAnimalByName();
            } else if (i.equals("5")) {
                editAnimal();
            } else if (i.equals("6")) {
                deleteAnimal();
            } else {
                System.out.println("Closing application..."); //7η Επιλογή (Έξοδος από την εφαρμογή).
                System.exit(0);
            }
        }
    }



    //1η Επιλογή (Προβολή της λίστας).
    public static void showAnimals(){

        //Με το try-catch ελέγχω αν υπάρχει το αρχείο.
        try {
            //Ανοίγω το αρχείο Zoo.txt (αν δεν μου πετάξει exception) και παίρνω τη λίστα που έχω αποθηκεύσει και την βάζω στο zooArrayList.
            FileInputStream fileInputStream = new FileInputStream("Zoo.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            zooArrayList =(List<Zoo>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            /* Ελέγχω αν το μέγεθος της λίστας είναι άνω του 0, δηλαδή αν υπάρχει έστω ένα ζώο στη λίστα για να το εμφανίσει.
               Αν δεν είναι άνω του 0 τότε εμφανίζει το κατάλληλο μήνυμα. */
            if (zooArrayList.size() > 0){
                for (Zoo animal : zooArrayList) {
                    System.out.println(" ");

                    //Εμφάνιση αποτελεσμάτων
                    System.out.println("Animal's name: " + animal.getName());
                    System.out.println("Animal's type: " + animal.getType());
                    System.out.println("Animal's weight: " + animal.getWeight());
                    System.out.println("Animal's age: " + animal.getAge());
                    System.out.println("Animal's code: " + animal.getAnimalcode());
                }

            } else {
                System.out.println("There is/are no animal(s) in this list.");
            }

        } catch (Exception e){
            System.out.println("File not found.");
        }
    }



    //2η Επιλογή (Δημιουργία ζώου).
    public static void createAnimals() {

        Scanner selection = new Scanner(System.in);
        Zoo animal = new Zoo(); //Δημιουργώ το αντικείμενο.

        //Σε όλα τα παρακάτω πεδία χρησιμοποιείται επανάληψη έτσι ώστε να επαναληφθεί η εκάστοτε διαδικασία αν δώθει μη έγκυρο δεδομένο.
        while (true) {
            try {
                System.out.println("Set animal's name: ");
                String name = selection.nextLine();

                //Έλεγχος εγκυρότητας για το name(όνομα).
                if (name.isBlank() || name.matches(".*[0-9].*") || name.matches(".*[!,@#$%^&*()?;<>.=+_|-].*")) {
                    throw new Exception(); //Πετάει exception με το κατάλληλο μήνυμα σε περίπτωση λάθους.
                } else {
                    animal.setName(name); //Αποθήκευση έγκυρου δεδομένου στο αντικείμενο.
                    break; //Διακοπή επανάληψης.
                }
            } catch (Exception e){
                System.out.println("This animal's name is not valid. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Set animal's type: ");
                String type = selection.nextLine();

                //Έλεγχος εγκυρότητας για το type(ομοιοταξία).
                if (type.isBlank() || type.matches(".*[0-9].*") || type.matches(".*[!,@#$%^&*()?;<>.=+_|-].*")) {
                    throw new Exception(); //Πετάει exception με το κατάλληλο μήνυμα σε περίπτωση λάθους.
                } else {
                    animal.setType(type);//Αποθήκευση έγκυρου δεδομένου στο αντικείμενο.
                    break; //Διακοπή επανάληψης.
                }
            } catch (Exception e){
                System.out.println("This animal's type is not valid. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Set animals weight (example: 14.0): ");
                String w = selection.nextLine(); //Κάνω το βάρος String και όχι double για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε double παρακάτω.

                //Έλεγχος εγκυρότητας για το weight(βάρος).
                if (Double.parseDouble(w) > 0 && Double.parseDouble(w) <= 1000 && w.matches(".*[.].*")) {
                    double weight = Double.parseDouble(w); //Η μετατροπή σε double.
                    animal.setWeight(weight); //Αποθήκευση έγκυρου δεδομένου στο αντικείμενο.
                    break; //Διακοπή επανάληψης.
                } else {
                    throw new Exception(); //Πετάει exception με το κατάλληλο μήνυμα σε περίπτωση λάθους.
                }
            }catch (Exception e) {
                System.out.println("This animal's weight is not valid. Please try again.");
            }
        }

        while (true) {
            try {
                System.out.println("Set animal's age (example: 5): ");
                String a = selection.nextLine(); //Κάνω την ηλικία String και όχι int για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε int παρακάτω.

                //Έλεγχος εγκυρότητας για το age(ηλικία).
                if (Integer.parseInt(a) > 0 && Integer.parseInt(a) <= 80) {
                    int age = Integer.parseInt(a); //Η μετατροπή σε int.
                    animal.setAge(age); //Αποθήκευση έγκυρου δεδομένου στο αντικείμενο.
                    break; //Διακοπή επανάληψης.
                } else {
                    throw new Exception(); //Πετάει exception με το κατάλληλο μήνυμα σε περίπτωση λάθους.
                }
            } catch (Exception e){
                System.out.println("This animal's age is not valid. Please try again.");
            }
        }

        while (true) {
            System.out.println("Set animal's code (example: 1): ");
            String a = selection.nextLine(); //Κάνω τον κωδικό String και όχι int για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε int παρακάτω.

            //Έλεγχος εγκυρότητας για το animalcode(Κωδικό ζώου).
            if (Integer.parseInt(a) >= 0 && Integer.parseInt(a) <=100) {
                int animalcode = Integer.parseInt(a); //Η μετατροπή σε int.
                int j = check5(animalcode); //Καλώ τη μέθοδο check5.

                //Έλεγχος του j (Δείτε αρχείο Check.java).
                if (j > 0) {
                    System.out.println("This animal's code already exists. Please try again.");
                } else {
                    animal.setAnimalcode(animalcode);//Αποθήκευση έγκυρου δεδομένου στο αντικείμενο.
                    break; //Διακοπή επανάληψης.
                }
            } else {
                System.out.println("Animal's code should not be negative. Please try again.");
            }
        }

        System.out.println(" ");

        //Εκτυπώνω τα δεδομένα που εισήγαγε ο χρήστης.
        System.out.println("Animal's code: " + animal.getAnimalcode());
        System.out.println("Animal's name: " + animal.getName());
        System.out.println("Animal's type: " + animal.getType());
        System.out.println("Animal's weight: " + animal.getWeight());
        System.out.println("Animal's age: " + animal.getAge());
        System.out.println(" ");

        zooArrayList.add(animal); //Προσθέτω το αντικείμενο με τα δεδομένα(χαρ/κά) του στη λίστα.

        //Αποθήκευση της νέας λίστας στο αρχείο Zoo.txt με τα κατάλληλα exceptions.
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Zoo.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(zooArrayList);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //3η Επιλογή (Αναζήτηση ζώου βάσει κωδικού).
    public static void searchAnimalByCode() {

        try {
            //Ανοίγω το αρχείο Zoo.txt (αν δεν μου πετάξει exception) και παίρνω τη λίστα που έχω αποθηκεύσει και την βάζω στο zooArrayList.
            FileInputStream fileInputStream = new FileInputStream("Zoo.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            zooArrayList =(List<Zoo>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            System.out.println(" ");

            //Έλεγχος για το αν υπάρχει ζώο στη λίστα.
            if (zooArrayList.size() > 0) {
                Scanner selection = new Scanner(System.in);
                boolean flag = true; //
                int i = 0;
                while (flag == true) {
                    System.out.println("Enter animal's code: ");

                    try {
                        String c = selection.nextLine(); //Κάνω τον κωδικό String και όχι int για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε int παρακάτω.

                        //Έλεγχος για το αν υπάρχει ο κωδικός που εισήγαγε ο χρήστης.
                        if (Integer.parseInt(c) >= 0 && Integer.parseInt(c) <= 500) {
                            int code = Integer.parseInt(c); //Η μετατροπή σε int.

                            //Αναζήτηση σε όλη τη λίστα.
                            for (Zoo animal : zooArrayList) {
                                if (animal.getAnimalcode() == code) {
                                    i = 1; //Το i έγινε 1 αφού το ζώο βρέθηκε (Δείτε την παρακάτω if).
                                    flag = false; //Αλλαγή του flag με σκοπό τη διακοπή της while αφού το ζώο βρέθηκε.

                                    //Εμφάνιση αποτελεσμάτων.
                                    System.out.println("Searching result: ");
                                    System.out.println("Animal's code: " + animal.getAnimalcode());
                                    System.out.println("Animal's name: " + animal.getName());
                                    System.out.println("Animal's type: " + animal.getType());
                                    System.out.println("Animal's weight: " + animal.getWeight());
                                    System.out.println("Animal's age: " + animal.getAge());

                                    break; //Διακοπή της for αφού το ζώο βρέθηκε.
                                }
                            }

                            //Αν το i παραμείνει 0 τότε δεν υπάρχει ζώο με τον κωδικό που έβαλε ο χρήστης άρα πρέπει να εμφανιστεί το κατάλληλο μήνυμα.
                            if (i == 0) {
                                System.out.println("This animal's code does not exist. Please try again.");
                            }

                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("This animal's code is not valid. Please try again.");
                    }
                }

            } else {
                System.out.println("There is/are no animal(s) to search for.");
            }

        }catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    //4η Επιλογή(Αναζήτηση ζώου βάσει ονόματος).
    public static void searchAnimalByName() {

        try {
            //Ανοίγω το αρχείο Zoo.txt (αν δεν μου πετάξει exception) και παίρνω τη λίστα που έχω αποθηκεύσει και την βάζω στο zooArrayList.
            FileInputStream fileInputStream = new FileInputStream("Zoo.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            zooArrayList = (List<Zoo>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            System.out.println(" ");

            //Έλεγχος για το αν υπάρχει ζώο στη λίστα.
            if (zooArrayList.size() > 0) {
                Scanner selection = new Scanner(System.in);
                boolean flag = true;
                while (flag == true) {
                    System.out.println("Enter animal's name: ");



                    String name = selection.nextLine();

                    //Αναζήτηση σε όλη τη λίστα.
                    for (Zoo animal : zooArrayList) {
                        if (animal.getName().equals(name)) {
                            flag = false; //Το flag έγινε false αφού το ζώο βρέθηκε (Δείτε την παρακάτω if). Επίσης έχει στόχο και τη διακοπή της while.

                            //Εμφάνιση αποτελεσμάτων.
                            System.out.println("Searching result: ");
                            System.out.println("Animal's code: " + animal.getAnimalcode());
                            System.out.println("Animal's name: " + animal.getName());
                            System.out.println("Animal's type: " + animal.getType());
                            System.out.println("Animal's weight: " + animal.getWeight());
                            System.out.println("Animal's age: " + animal.getAge());

                        }
                    }

                    //Αν το flag παραμείνει false τότε δεν υπάρχει ζώο με το όνομα που έβαλε ο χρήστης άρα πρέπει να εμφανιστεί το κατάλληλο μήνυμα.
                    if (flag == true) {
                        System.out.println("This animal's name is not valid. Please try again.");
                    }
                }

            } else {
                System.out.println("There is/are no animal(s) to search for.");
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    //5η Επιλογή(Επεξεργασία ζώου).
    public static void editAnimal() {

        try {
            //Ανοίγω το αρχείο Zoo.txt (αν δεν μου πετάξει exception) και παίρνω τη λίστα που έχω αποθηκεύσει και την βάζω στο zooArrayList.
            FileInputStream fileInputStream = new FileInputStream("Zoo.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            zooArrayList = (List<Zoo>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            System.out.println(" ");

            //Έλεγχος για το αν υπάρχει ζώο στη λίστα.
            if (zooArrayList.size() > 0) {
                Scanner selection = new Scanner(System.in);
                boolean flag = true;
                int i = 0;
                while (flag == true) {
                    showAnimals();// Δείχνει τη λίστα των ζώων.
                    System.out.println("Enter animal's code you want to edit: ");
                    try {
                        String c = selection.nextLine(); //Κάνω τον κωδικό String και όχι int για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε int παρακάτω.

                        //Έλεγχος για το αν υπάρχει ο κωδικός που εισήγαγε ο χρήστης.
                        if (Integer.parseInt(c) >= 0 && Integer.parseInt(c) <= 500) {
                            int code = Integer.parseInt(c); //Η μετατροπή σε int.

                            //Αναζήτηση σε όλη τη λίστα.
                            for (Zoo animal : zooArrayList) {
                                if (animal.getAnimalcode() == code) {
                                    i = 1; //Δείτε την if μετά την επανάληψη for.
                                    flag = false; //Διακοπή της while.
                                    System.out.println(" ");

                                    //Εμφάνιση αποτελεσμάτων.
                                    System.out.println("Searching result: ");
                                    System.out.println("Animal's code: " + animal.getAnimalcode());
                                    System.out.println("Animal's name: " + animal.getName());
                                    System.out.println("Animal's type: " + animal.getType());
                                    System.out.println("Animal's weight: " + animal.getWeight());
                                    System.out.println("Animal's age: " + animal.getAge());


                                    //Ο παρακάτω κώδικας είναι ίδιος με τον κώδικα στην 2η επιλογή(Δημιουργία ζώου), άρα και τα σχόλια είναι ίδια.
                                    while (true) {
                                        System.out.println(" ");
                                        try {
                                            System.out.println("Set animal's new name: ");
                                            String name = selection.nextLine();
                                            if (name.isBlank() || name.matches(".*[0-9].*") || name.matches(".*[!,@#$%^&*()?;<>.=+_|-].*")) {
                                                throw new Exception();
                                            } else {
                                                animal.setName(name);
                                                break;
                                            }
                                        } catch (Exception e){
                                            System.out.println("This animal's name is not valid. Please try again.");
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.println("Set animal's new type: ");
                                            String type = selection.nextLine();
                                            if (type.isBlank() || type.matches(".*[0-9].*") || type.matches(".*[!,@#$%^&*()?;<>.=+_|-].*")) {
                                                throw new Exception();
                                            } else {
                                                animal.setType(type);
                                                break;
                                            }
                                        } catch (Exception e){
                                            System.out.println("This animal's type is not valid. Please try again.");
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.println("Set animals new weight (example: 12.7): ");
                                            String w = selection.nextLine();
                                            if (Double.parseDouble(w) > 0 && Double.parseDouble(w) <= 1000 && w.matches(".*[.].*")) {
                                                double weight = Double.parseDouble(w);
                                                animal.setWeight(weight);
                                                break;
                                            } else {
                                                throw new Exception();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("This animal's weight is not valid. Please try again.");
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.println("Set animal's new age (example: 13): ");
                                            String a = selection.nextLine();
                                            if (Integer.parseInt(a) > 0 && Integer.parseInt(a) <= 80) {
                                                int age = Integer.parseInt(a);
                                                animal.setAge(age);
                                                break;
                                            } else {
                                                throw new Exception();
                                            }
                                        } catch (Exception e){
                                            System.out.println("This animal's age is not valid. Please try again.");
                                        }
                                    }

                                    while (true) {
                                        System.out.println("Set animal's new code (example: 0): ");
                                        String a = selection.nextLine();

                                        if (Integer.parseInt(a) >= 0 && Integer.parseInt(a) <=100) {
                                            int animalcode = Integer.parseInt(a);
                                            int j = check5(animalcode);

                                            /* Βάζω επιπλέον το "animal.getAnimalcode != animalcode" έτσι ώστε ο χρήστης
                                               να μπορεί να βάλει τον ίδιο κωδικό που είχε το ζώο πριν το επεξεργαστεί. */
                                            if (j > 0 && animal.getAnimalcode() != animalcode) {
                                                System.out.println("This code already exists. Please try again.");
                                            } else {
                                                animal.setAnimalcode(animalcode);
                                                break;
                                            }
                                        } else {
                                            System.out.println("Animal's code should not be negative. Please try again.");
                                        }
                                    }

                                    System.out.println(" ");
                                    System.out.println("Animal's new code: " + animal.getAnimalcode());
                                    System.out.println("Animal's new name: " + animal.getName());
                                    System.out.println("Animal's new type: " + animal.getType());
                                    System.out.println("Animal's new weight: " + animal.getWeight());
                                    System.out.println("Animal's new age: " + animal.getAge());
                                    System.out.println(" ");

                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream("Zoo.txt");
                                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                                        objectOutputStream.writeObject(zooArrayList);
                                        objectOutputStream.close();
                                        fileOutputStream.close();
                                        System.out.println("Saved!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    break; //Διακοπή της for.

                                }
                            }

                            //Αν το i παραμείνει 0 τότε δεν υπάρχει ζώο με τον κωδικό που έβαλε ο χρήστης άρα πρέπει να εμφανιστεί το κατάλληλο μήνυμα.
                            if (i == 0){
                                System.out.println("This animal's code does not exist. Please try again.");
                            }

                        } else {
                            throw new Exception();
                        }

                    } catch (Exception e) {
                        System.out.println("This animal's code is not valid. Please try again.");
                    }
                }

            } else {
                System.out.println("There is/are no animal(s) to edit.");
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    //6η Επιλογή(Διαγραφή ζώου).
    public static void deleteAnimal() {

        try {
            //Ανοίγω το αρχείο Zoo.txt (αν δεν μου πετάξει exception) και παίρνω τη λίστα που έχω αποθηκεύσει και την βάζω στο zooArrayList.
            FileInputStream fileInputStream = new FileInputStream("Zoo.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            zooArrayList = (List<Zoo>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

            //Έλεγχος για το αν υπάρχει ζώο στη λίστα.
            if (zooArrayList.size() > 0) {
                System.out.println(" ");
                boolean flag = true;
                int i = 0;
                while (flag == true) {

                    try {
                        showAnimals(); //Δείχνει τη λίστα.
                        System.out.println(" ");
                        System.out.println("Enter the code of the animal you want to delete: ");
                        Scanner selection = new Scanner(System.in);
                        String c = selection.nextLine(); //Κάνω τον κωδικό String και όχι int για να διευκολυνθώ με τα exceptions. Κάνω μετατροπή σε int παρακάτω.
                        //Έλεγχος για το αν είναι σωστός ο κωδικός που εισήγαγε ο χρήστης.
                        if (Integer.parseInt(c) < 0 && Integer.parseInt(c) > 500) {
                            throw new Exception();
                        }

                        int code = Integer.parseInt(c); //Η μετατροπή σε int.

                        //Αναζήτηση σε όλη τη λίστα.
                        for (Zoo animal : zooArrayList) {
                            if (animal.getAnimalcode() == code) {
                                i = 1; //Δείτε την if μετά την επανάληψη for.
                                zooArrayList.remove(animal);
                                System.out.println(" ");
                                try {
                                    FileOutputStream fileOutputStream = new FileOutputStream("Zoo.txt");
                                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                                    objectOutputStream.writeObject(zooArrayList);
                                    objectOutputStream.close();
                                    fileOutputStream.close();
                                    System.out.println("Animal deleted successfully!");

                                    flag = false; //Έχει σκοπό τη διακοπή της while αφού το ζώο διαγράφτηκε επιτυχώς.

                                } catch (FileNotFoundException e) {
                                    System.out.println(e);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                break; //Διακοπή της for αφόυ το ζώο διαγράφτηκε επιτυχώς.

                            }
                        }

                        //Αν το i παραμείνει 0 τότε δεν υπάρχει ζώο με τον κωδικό που έβαλε ο χρήστης άρα πρέπει να εμφανιστεί το κατάλληλο μήνυμα.
                        if (i == 0){
                            System.out.println("This animal's code does not exist. Please try again.");
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    } catch (Exception e){
                        System.out.println("This animal's code is not valid. Please try again.");
                    }
                }

            } else {
                System.out.println("There is/are no animal(s) to delete.");
            }

        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}