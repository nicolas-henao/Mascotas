package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import paqueteMascota.Mascota;
import java.awt.Font;
import javax.swing.SwingConstants;

public class registroMascotas extends JFrame {
	
	Mascota miMascota;

    private HashMap<Integer, Mascota> mascotas = new HashMap<>();
    private JTextField idTextField = new JTextField(10);
    private JTextField nombreTextField = new JTextField(10);
    private JTextField colorTextField = new JTextField(10);
    private JTextField edadTextField = new JTextField(10);
    private JTextField tipoTextField = new JTextField(10);
    private JLabel sumaEdadesLabel = new JLabel();

	public registroMascotas() {
		super("Registro de Mascotas");

        // Crear elementos de la interfaz gráfica
        JPanel registroPanel = new JPanel(new GridLayout(6, 2));
        JLabel label_1 = new JLabel("ID:");
        label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        registroPanel.add(label_1);
        registroPanel.add(idTextField);
        JLabel label_2 = new JLabel("Nombre:");
        label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        registroPanel.add(label_2);
        registroPanel.add(nombreTextField);
        JLabel label_3 = new JLabel("Color:");
        label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        registroPanel.add(label_3);
        registroPanel.add(colorTextField);
        JLabel label_4 = new JLabel("Edad:");
        label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_4.setHorizontalAlignment(SwingConstants.CENTER);
        registroPanel.add(label_4);
        registroPanel.add(edadTextField);
        JLabel label_5 = new JLabel("Tipo:");
        label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
        label_5.setHorizontalAlignment(SwingConstants.CENTER);
        registroPanel.add(label_5);
        registroPanel.add(tipoTextField);
        JButton registrarButton = new JButton("Registrar");
        JButton buscarIdButton = new JButton("Buscar por ID");
        JButton buscarNombreButton = new JButton("Buscar por Nombre");
        JButton eliminarButton = new JButton("Eliminar");
        JButton imprimirButton = new JButton("Imprimir lista completa");
        JPanel botoneraPanel = new JPanel(new FlowLayout());
        botoneraPanel.add(registrarButton);
        botoneraPanel.add(buscarIdButton);
        botoneraPanel.add(buscarNombreButton);
        botoneraPanel.add(eliminarButton);
        botoneraPanel.add(imprimirButton);
        JPanel resultadoPanel = new JPanel(new GridLayout(2, 2));
        JLabel label = new JLabel("Suma de edades:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 15));
        resultadoPanel.add(label);
        sumaEdadesLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        sumaEdadesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultadoPanel.add(sumaEdadesLabel);

        // Agregar elementos a la ventana
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(registroPanel, BorderLayout.CENTER);
        getContentPane().add(botoneraPanel, BorderLayout.SOUTH);
        getContentPane().add(resultadoPanel, BorderLayout.NORTH);
        
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMascota();
            }
        });

        buscarIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMascotaPorId();
            }
        });

        buscarNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMascotaPorNombre();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMascota();
            }
        });

        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirListaMascotas();
            }
        });

        // Configurar ventana
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void registrarMascota() {
        // Obtener datos de la mascota
        int id = Integer.parseInt(idTextField.getText());
        String nombre = nombreTextField.getText();
        String color = colorTextField.getText();
        int edad = Integer.parseInt(edadTextField.getText());
        String tipo = tipoTextField.getText();

        // Crear objeto Mascota y agregarlo al HashMap
        Mascota mascota = new Mascota(id, nombre, color, edad, tipo);
        mascotas.put(id, mascota);

        // Limpiar campos de texto
        idTextField.setText("");
        nombreTextField.setText("");
        colorTextField.setText("");
        edadTextField.setText("");
        tipoTextField.setText("");

        // Actualizar suma de edades
        int sumaEdades = 0;
        for (Mascota m : mascotas.values()) {
            sumaEdades += m.getEdad();
        }
        sumaEdadesLabel.setText(Integer.toString(sumaEdades));
    }

    private void buscarMascotaPorId() {
        // Obtener ID de la mascota a buscar
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota:"));

        // Buscar mascota en el HashMap
        Mascota mascota = mascotas.get(id);

        // Mostrar resultado de la búsqueda
        if (mascota != null) {
            JOptionPane.showMessageDialog(null,
                    "ID: " + mascota.getId() +
                            "\nNombre: " + mascota.getNombre() +
                            "\nColor: " + mascota.getColor() +
                            "\nEdad: " + mascota.getEdad() +
                            "\nTipo: " + mascota.getTipo(),
                    "Mascota encontrada",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se encontró una mascota con el ID ingresado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMascotaPorNombre() {
        // Obtener nombre de la mascota a buscar
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la mascota:");

        // Buscar mascota en el HashMap
        for (Mascota mascota : mascotas.values()) {
            if (mascota.getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(null,
                        "ID: " + mascota.getId() +
                                "\nNombre: " + mascota.getNombre() +
                                "\nColor: " + mascota.getColor() +
                                "\nEdad: " + mascota.getEdad() +
                                "\nTipo: " + mascota.getTipo(),
                        "Mascota encontrada",
                        JOptionPane.INFORMATION_MESSAGE);
                        return;
                        }
                       }
	}
    
    private void eliminarMascota() {
        // Obtener ID de la mascota a eliminar
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la mascota a eliminar:"));

        // Eliminar mascota del HashMap
        Mascota mascota = mascotas.remove(id);

        // Mostrar resultado de la eliminación
        if (mascota != null) {
            JOptionPane.showMessageDialog(null,
                    "La mascota con ID " + id + " fue eliminada correctamente.",
                    "Mascota eliminada",
                    JOptionPane.INFORMATION_MESSAGE);

            // Actualizar suma de edades
            int sumaEdades = 0;
            for (Mascota m : mascotas.values()) {
                sumaEdades += m.getEdad();
            }
            sumaEdadesLabel.setText(Integer.toString(sumaEdades));
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se encontró una mascota con el ID ingresado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void imprimirListaMascotas() {
        // Crear StringBuilder para almacenar la lista de mascotas
        StringBuilder sb = new StringBuilder();

        // Agregar encabezado de la lista
        sb.append(String.format("%-5s %-15s %-10s %-5s %-10s\n", "ID", "Nombre", "Color", "Edad", "Tipo"));

        // Agregar cada mascota a la lista
        for (Mascota mascota : mascotas.values()) {
            sb.append(String.format("%-5d %-15s %-10s %-5d %-10s\n",
                    mascota.getId(),
                    mascota.getNombre(),
                    mascota.getColor(),
                    mascota.getEdad(),
                    mascota.getTipo()));
        }

        // Mostrar lista de mascotas en un cuadro de diálogo
        JOptionPane.showMessageDialog(null,
                sb.toString(),
                "Lista de mascotas",
                JOptionPane.INFORMATION_MESSAGE);
    }
            
}
