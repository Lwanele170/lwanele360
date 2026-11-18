/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaapplication;

/**
 *
 * @author Student
 */
    public class ProductCalculations implements IProductCalculations {

    double price;

    // Constructor
    public ProductCalculations(double price) {
        this.price = price;
    }

    // Calculate VAT
    @Override
    public double GetVat() {
        return price * 0.15;
    }

    // Calculate Commission
    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    // Calculate Total
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
    

