Clase AmigosManager.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amigos;

import java.io.*;

public class AmigosManager {
    private RandomAccessFile amigosFile;

    public AmigosManager() {
        initializeFile();
    }

    public void initializeFile() {
        try {
            amigosFile = new RandomAccessFile("amigos.dat", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            if (amigosFile != null) {
                amigosFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFriend(String newName, long newNumber) {
        try {
            amigosFile.seek(amigosFile.length());
            amigosFile.writeBytes(newName + "!" + newNumber + System.lineSeparator());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void displayFriends() {
        try {
            amigosFile.seek(0);

            while (amigosFile.getFilePointer() < amigosFile.length()) {
                String nameNumberString = amigosFile.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                System.out.println("Friend Name: " + name + "\n" + "Contact Number: " + number + "\n");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void updateFriend(String newName, long newNumber) {
        try {
            File tmpFile = new File("temp.dat");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

            amigosFile.seek(0);

            while (amigosFile.getFilePointer() < amigosFile.length()) {
                String nameNumberString = amigosFile.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName)) {
                    nameNumberString = newName + "!" + newNumber;
                }

                tmpraf.writeBytes(nameNumberString);
                tmpraf.writeBytes(System.lineSeparator());
            }

            amigosFile.seek(0);
            tmpraf.seek(0);

            while (tmpraf.getFilePointer() < tmpraf.length()) {
                amigosFile.writeBytes(tmpraf.readLine());
                amigosFile.writeBytes(System.lineSeparator());
            }

            amigosFile.setLength(tmpraf.length());

            tmpraf.close();
            tmpFile.delete();

            System.out.println("Amigo actualizado exitosamente.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void deleteFriend(String nameToDelete, long numberToDelete) {
        try {
            File tmpFile = new File("temp.dat");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

            amigosFile.seek(0);

            while (amigosFile.getFilePointer() < amigosFile.length()) {
                String nameNumberString = amigosFile.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];

                if (!name.equals(nameToDelete)) {
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                }
            }

            amigosFile.seek(0);
            tmpraf.seek(0);

            while (tmpraf.getFilePointer() < tmpraf.length()) {
                amigosFile.writeBytes(tmpraf.readLine());
                amigosFile.writeBytes(System.lineSeparator());
            }

            amigosFile.setLength(tmpraf.length());

            tmpraf.close();
            tmpFile.delete();

            System.out.println("Amigo eliminado exitosamente.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void searchFriend(String searchName) {
        try {
            amigosFile.seek(0);

            while (amigosFile.getFilePointer() < amigosFile.length()) {
                String nameNumberString = amigosFile.readLine();
                String[] lineSplit = nameNumberString.split("!");
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                if (name.equals(searchName)) {
                    System.out.println("Friend Name: " + name + "\n" + "Contact Number: " + number + "\n");
                    return; // Salimos del método si encontramos el amigo
                }
            }

            // Si llegamos aquí, no se encontró el amigo
            System.out.println("Amigo no encontrado.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
