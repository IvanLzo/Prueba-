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
