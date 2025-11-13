
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class prueba {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}

class LoginWindow extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginWindow() {
        setTitle("Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Usuario:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Contraseña:"));
        passField = new JPasswordField();
        panel.add(passField);

        JButton loginBtn = new JButton("Ingresar");
        loginBtn.addActionListener(e -> login());
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }

    private void login() {
        String user = userField.getText().trim();
        if (!user.isEmpty()) {
            dispose();
            new MainWindow(user);
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un usuario");
        }
    }
}

class MainWindow extends JFrame {

    private ArrayList<String> lista = new ArrayList<>();
    private DefaultListModel<String> model;
    private JList<String> ProductoJList = new JList<>();
    private ArrayList<String> cantidadProductos = new ArrayList<>();

    public MainWindow(String usuario) {
        setTitle("Gestor - Usuario: " + usuario);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultListModel<>();
        ProductoJList = new JList<>(model);

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelBotones.add(crearBoton("Agregar", e -> agregar()));
        panelBotones.add(crearBoton("Mostrar", e -> mostrar()));
        panelBotones.add(crearBoton("Eliminar", e -> eliminar()));
        panelBotones.add(crearBoton("Limpiar", e -> limpiar()));

        add(new JScrollPane(ProductoJList), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton crearBoton(String texto, ActionListener listener) {
        JButton btn = new JButton(texto);
        btn.addActionListener(listener);
        return btn;
    }

    private void agregar() {
        Scanner texto = new Scanner(System.in);
        System.out.println("Agregar item: ");
        String item = texto.nextLine();

        if (item != null && !item.trim().isEmpty()) {
            lista.add(item);
            model.addElement(item);
            Scanner cantidad = new Scanner(System.in);
            while (true) {
                System.out.println("Agregar cantidad: ");
                String stock = cantidad.nextLine();
                try {
                    int valor = Integer.parseInt(stock);

                    if (stock != null && !stock.trim().isEmpty()) {
                        System.out.println("Cantidad agregado: " + stock);
                        cantidadProductos.add(stock);
                        break;

                    } else {
                        System.err.println("Cantidad no válida");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Cantidad no válida");
                }
            }

        } else {
            System.err.println("Item vacío");

        }

    }

    private void mostrar() {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La lista está vacía");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("Item: " + lista.get(i) + " - Cantidad: " + cantidadProductos.get(i));
            }

        }
    }

    private void eliminar() {
        Scanner texto = new Scanner(System.in);
        Scanner cantidad = new Scanner(System.in);
        System.out.println("Item a eliminar: ");
        String item = texto.nextLine();
        if (item != null && !item.trim().isEmpty()) {
            while (true) {

                System.out.println("Cantidad a eliminar: ");
                String stock = cantidad.nextLine();

                try {
                    int numero = Integer.parseInt(stock);

                    if (stock != null && !stock.trim().isEmpty() && numero >= 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            if (lista.get(i).equals(item)) {
                                if (Integer.parseInt(cantidadProductos.get(i)) >= numero) {
                                    int nuevaCantidad = Integer.parseInt(cantidadProductos.get(i)) - numero;
                                    if (nuevaCantidad == 0) {
                                        lista.remove(i);
                                        cantidadProductos.remove(i);
                                        model.remove(i);
                                        System.out.println("Item eliminado completamente: " + item);
                                    } else {
                                        cantidadProductos.set(i, String.valueOf(nuevaCantidad));
                                        System.out.println("Cantidad actualizada para " + item + ": " + nuevaCantidad);
                                    }
                                } else {
                                    System.err.println("No hay suficiente cantidad para eliminar");

                                }
                                return;
                            }
                                
                                
                            

                        }
                    } else {
                        System.out.println("Item no encontrado: " + item);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Cantidad no válida");
                }
            }
        } else {
            System.err.println("Item vacío");
            return;
        }

    }

    private void limpiar() {
        lista.clear();
        model.clear();
    }
}
