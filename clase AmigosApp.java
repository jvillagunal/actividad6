Clase AmigosApp.java

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package amigos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class AmigosApp {
    private Frame frame;
    private Label nameLabel, numberLabel;
    private TextField nameTextField, numberTextField;
    private Button crearButton, leerButton, actualizarButton, eliminarButton, limpiarButton, salirButton;
    private AmigosManager amigosManager;

    public AmigosApp() {
        amigosManager = new AmigosManager();
        prepareGUI();
    }

    private void addFriend() {
        String newName = nameTextField.getText();
        long newNumber;
        try {
            newNumber = Long.parseLong(numberTextField.getText());
            amigosManager.addFriend(newName, newNumber);
            displayMessage("Amigo creado exitosamente.");
            clearFields();
        } catch (NumberFormatException ex) {
            displayMessage("Número inválido. Ingresa un número válido.");
        }
    }

    private void readRecord() {
        String searchName = nameTextField.getText();
        amigosManager.searchFriend(searchName);
        displayFriends();
    }

    private void updateRecord() {
        String newName = nameTextField.getText();
        long newNumber;
        try {
            newNumber = Long.parseLong(numberTextField.getText());
            amigosManager.updateFriend(newName, newNumber);
            displayMessage("Amigo actualizado exitosamente.");
            clearFields();
        } catch (NumberFormatException ex) {
            displayMessage("Número inválido. Ingresa un número válido.");
        }
    }

    private void deleteRecord() {
        String deleteName = nameTextField.getText();
        long deleteNumber;
        try {
            deleteNumber = Long.parseLong(numberTextField.getText());
            amigosManager.deleteFriend(deleteName, deleteNumber);
            displayMessage("Amigo eliminado exitosamente.");
            clearFields();
        } catch (NumberFormatException ex) {
            displayMessage("Número inválido. Ingresa un número válido.");
        }
    }

    private void clearFields() {
        nameTextField.setText("");
        numberTextField.setText("");
    }

    private void displayFriends() {
        amigosManager.displayFriends();
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void prepareGUI() {
        frame = new Frame("Amigos App");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 2));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                closeFile();
                System.exit(0);
            }
        });

        nameLabel = new Label("Nombre:");
        frame.add(nameLabel);
        nameTextField = new TextField();
        frame.add(nameTextField);

        numberLabel = new Label("Número:");
        frame.add(numberLabel);
        numberTextField = new TextField();
        frame.add(numberTextField);

        crearButton = new Button("Crear");
        frame.add(crearButton);

        leerButton = new Button("Leer");
        frame.add(leerButton);

        actualizarButton = new Button("Actualizar");
        frame.add(actualizarButton);

        eliminarButton = new Button("Eliminar");
        frame.add(eliminarButton);

        limpiarButton = new Button("Limpiar");
        frame.add(limpiarButton);

        salirButton = new Button("Salir");
        frame.add(salirButton);

        frame.setVisible(true);

        // Acciones de los botones
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFriend();
            }
        });

        leerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readRecord();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFile();
                System.exit(0);
            }
        });
    }

    private void closeFile() {
        amigosManager.closeFile();
    }

    public static void main(String[] args) {
        new AmigosApp();
    }
}

