/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.salesreport;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class SalesReport {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // One-dimensional array
        String[] productNames = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        // Two-dimensional array
        int[][] sales = new int[3][3];

        // Enter sales information
        for (int i = 0; i < productNames.length; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print("Enter number of sales for month "
                        + (j + 1) + " for " + productNames[i] + ": ");

                sales[i][j] = input.nextInt();
            }

            System.out.println();
        }

        // Create Product object
        Product p = new Product(productNames, sales);

        // Display sales report
        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");

        System.out.println("***************************************");

        System.out.println("TOTAL PRODUCT SALES: "
                + p.getTotalProductSold());

        System.out.println("AVERAGE PRODUCT SALES: "
                + p.getAverageProductSold());

        System.out.println("MOST POPULAR PRODUCT: "
                + p.getMostPopularProduct());

        input.close();
    }
}

