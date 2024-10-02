// src/LoginFrame.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginFrame() {
        // Frame settings
        setTitle("Login - Crime Records Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame
        setLayout(new BorderLayout());

        // Background panel with GridBagLayout
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(60, 63, 65));  // Dark gray color
        add(backgroundPanel, BorderLayout.CENTER);

        // Title Label
        JLabel lblTitle = new JLabel("Crime Records Management");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);

        // Username Label and Text Field
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        txtUsername = new JTextField(15);

        // Password Label and Password Field
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        txtPassword = new JPasswordField(15);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 122, 204));  // Blue button
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));

        // Form Panel to hold labels and text fields
        JPanel formPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        formPanel.setBackground(new Color(60, 63, 65));  // Same dark gray
        formPanel.add(lblTitle);
        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(btnLogin);

        // Add form panel to background panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(formPanel, gbc);

        // Action Listener for Login Button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });

        setVisible(true);
    }

    // Authentication Method
    private void authenticate() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Simple authentication logic - replace with real authentication as needed
        if (username.equals("admin") && password.equals("password")) { // Replace with secure authentication
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // Open MainFrame and dispose LoginFrame
            new MainFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Launch the login frame
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame();
            }
        });
    }
}
