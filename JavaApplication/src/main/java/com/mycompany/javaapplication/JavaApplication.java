/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javaapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Student
 */
        


public class JavaApplication extends JFrame {

    JLabel lblCustomer, lblProduct, lblPrice;
    JTextField txtCustomer, txtPrice;
    JComboBox<String> cmbProduct;
    JButton btnProcess;

    public JavaApplication() {

        setTitle("Product App");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 15));

        // Customer name
        lblCustomer = new JLabel("Customer Name:");
        txtCustomer = new JTextField();

        // Product selection
        lblProduct = new JLabel("Select Product:");

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        cmbProduct = new JComboBox<>(products);

        // Product price
        lblPrice = new JLabel("Enter Price:");
        txtPrice = new JTextField();

        // Process button
        btnProcess = new JButton("PROCESS");

        add(lblCustomer);
        add(txtCustomer);

        add(lblProduct);
        add(cmbProduct);

        add(lblPrice);
        add(txtPrice);

        add(new JLabel(""));
        add(btnProcess);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

        // Process button action
        btnProcess.addActionListener(e -> processSale());

        // Process menu action
        processItem.addActionListener(e -> processSale());

        // Clear menu action
        clearItem.addActionListener(e -> clearFields());

        // Exit menu action
        exitItem.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Process sales information
    public void processSale() {

        try {

            String customer = txtCustomer.getText();
            String product = cmbProduct.getSelectedItem().toString();

            if (customer.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter customer name.");
                return;
            }

            double price = Double.parseDouble(txtPrice.getText());

            if (price <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid price.");
                return;
            }

            // Create calculation object
            ProductCalculations calc =
                    new ProductCalculations(price);

            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Display results
            String result = "Customer Name: " + customer
                    + "\nProduct: " + product
                    + "\nVAT: R " + vat
                    + "\nCOMMISSION: R " + commission
                    + "\nTOTAL: R " + total;

            JOptionPane.showMessageDialog(this, result);

            // Save information to data.txt
            FileWriter writer = new FileWriter("data.txt", true);

            writer.write("DATA LOG\n");
            writer.write("****************************\n");
            writer.write("Customer Name: " + customer + "\n");
            writer.write("Product: " + product + "\n");
            writer.write("VAT: R " + vat + "\n");
            writer.write("COMMISSION: R " + commission + "\n");
            writer.write("TOTAL: R " + total + "\n");
            writer.write("****************************\n");

            writer.close();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid product price.");

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this,
                    "Error saving data to file.");
        }
    }

    // Clear text boxes
    public void clearFields() {

        txtCustomer.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        new JavaApplication();
    }
}

    
