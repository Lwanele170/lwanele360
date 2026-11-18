/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salesreport;

/**
 *
 * @author Student
 */
    public class Product implements IProducts {

    String[] productNames;
    int[][] sales;

    // Constructor
    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    // Calculate total products sold
    @Override
    public int getTotalProductSold() {

        int total = 0;

        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }

        return total;
    }

    // Calculate average products sold
    @Override
    public double getAverageProductSold() {

        int total = getTotalProductSold();

        return (double) total / 9;
    }

    // Find the most popular product
    @Override
    public String getMostPopularProduct() {

        int highest = 0;
        int position = 0;

        for (int i = 0; i < sales.length; i++) {

            int productTotal = 0;

            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
            }

            if (productTotal > highest) {
                highest = productTotal;
                position = i;
            }
        }

        return productNames[position] + " (" + highest + ")";
    }

}
    

