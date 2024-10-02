import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private JTextField txtCrimeType, txtFIRNumber, txtCriminalName, txtSearch;
    private JTable criminalTable;
    private DefaultTableModel tableModel;
    private Controller controller = new Controller();  // Controller instance

    public MainFrame() {
        setTitle("Crime Records Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(28, 40, 51));  // Dark blue-gray
        JLabel headerTitle = new JLabel("Crime Records Management System");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 28));
        headerTitle.setForeground(Color.WHITE);
        headerPanel.add(headerTitle);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));  // Light gray background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding between components

        // Labels and Text Fields for Crime Records Input
        JLabel lblCrimeType = new JLabel("Crime Type:");
        lblCrimeType.setFont(new Font("Arial", Font.BOLD, 16));
        txtCrimeType = new JTextField(20);

        JLabel lblFIRNumber = new JLabel("FIR Number:");
        lblFIRNumber.setFont(new Font("Arial", Font.BOLD, 16));
        txtFIRNumber = new JTextField(20);

        JLabel lblCriminalName = new JLabel("Criminal Name:");
        lblCriminalName.setFont(new Font("Arial", Font.BOLD, 16));
        txtCriminalName = new JTextField(20);

        // Submit, Update, and Delete Buttons
        JButton btnSubmit = createButton("Submit", new Color(0, 122, 204));
        JButton btnUpdate = createButton("Update", new Color(0, 122, 204));
        JButton btnDelete = createButton("Delete", new Color(255, 69, 58));
        JButton btnClear = createButton("Clear Form", new Color(0, 122, 204));

        // Layout positioning for components
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCrimeType, gbc);

        gbc.gridx = 1;
        formPanel.add(txtCrimeType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblFIRNumber, gbc);

        gbc.gridx = 1;
        formPanel.add(txtFIRNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblCriminalName, gbc);

        gbc.gridx = 1;
        formPanel.add(txtCriminalName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(btnSubmit, gbc);

        gbc.gridx = 1;
        formPanel.add(btnUpdate, gbc);

        gbc.gridx = 2;
        formPanel.add(btnDelete, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(btnClear, gbc);

        add(formPanel, BorderLayout.WEST);

        // Search and Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout(10, 10));

        // Criminal Table
        tableModel = new DefaultTableModel(new Object[]{"ID", "Crime Type", "FIR Number", "Criminal Name"}, 0);
        criminalTable = new JTable(tableModel);
        criminalTable.setRowHeight(25); // Adjust row height for better visibility
        tablePanel.add(new JScrollPane(criminalTable), BorderLayout.CENTER);

        // Add margin to the bottom of the header
        JPanel marginPanel = new JPanel();
        marginPanel.setPreferredSize(new Dimension(0, 20));  // Set height for margin
        tablePanel.add(marginPanel, BorderLayout.SOUTH);  // Adds margin below the header

        add(tablePanel, BorderLayout.CENTER);

        // Load all criminals initially
        loadAllCriminals();

        // Submit button action listener to add a criminal
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCriminal();
            }
        });

        // Update button action listener to update a criminal
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCriminal();
            }
        });

        // Delete button action listener to delete a criminal
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCriminal();
            }
        });

        // Clear form fields
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Create and add search panel
        addSearchPanel();

        setVisible(true);
    }

    // Method to add search panel
    private void addSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout(10, 10));

        // Label above the search bar
        JLabel lblSearch = new JLabel("Enter Name or FIR Number to Search:");
        lblSearch.setFont(new Font("Arial", Font.BOLD, 16));

        // Search Bar
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        JPanel searchControlsPanel = new JPanel();
        searchControlsPanel.add(lblSearch);  // Add label above search bar
        searchControlsPanel.add(txtSearch);
        searchControlsPanel.add(btnSearch);
        searchPanel.add(searchControlsPanel, BorderLayout.NORTH);

        add(searchPanel, BorderLayout.SOUTH);  // Move search panel to bottom

        // Search button action listener to find a criminal
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCriminal();
            }
        });
    }

    // Helper method to create a button with consistent styling
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 40));
        return button;
    }

    // Method to load all criminals from the controller
    private void loadAllCriminals() {
        List<Criminal> criminals = controller.getAllCriminals();
        tableModel.setRowCount(0);  // Clear table
        for (Criminal criminal : criminals) {
            tableModel.addRow(new Object[]{criminal.getId(), criminal.getCrimeType(), criminal.getFirNumber(), criminal.getName()});
        }
    }

    // Method to add a criminal
    private void addCriminal() {
        String crimeType = txtCrimeType.getText();
        String firNumber = txtFIRNumber.getText();
        String criminalName = txtCriminalName.getText();

        // Validate inputs
        if (crimeType.isEmpty() || firNumber.isEmpty() || criminalName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controller.addCriminal(crimeType, firNumber, criminalName);
        loadAllCriminals();
        clearFields();
    }

    // Method to update a criminal
    private void updateCriminal() {
        int selectedRow = criminalTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String crimeType = txtCrimeType.getText();
            String firNumber = txtFIRNumber.getText();
            String criminalName = txtCriminalName.getText();

            controller.updateCriminal(id, crimeType, firNumber, criminalName);
            loadAllCriminals();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a record to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete a criminal
    private void deleteCriminal() {
        int selectedRow = criminalTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.deleteCriminal(id);
                loadAllCriminals();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a record to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to search for a criminal
    private void searchCriminal() {
        String keyword = txtSearch.getText();
        List<Criminal> criminals = controller.findCriminal(keyword);
        tableModel.setRowCount(0);  // Clear table
        for (Criminal criminal : criminals) {
            tableModel.addRow(new Object[]{criminal.getId(), criminal.getCrimeType(), criminal.getFirNumber(), criminal.getName()});
        }
    }

    // Method to clear all input fields
    private void clearFields() {
        txtCrimeType.setText("");
        txtFIRNumber.setText("");
        txtCriminalName.setText("");
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
