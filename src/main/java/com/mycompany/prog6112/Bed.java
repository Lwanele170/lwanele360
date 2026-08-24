/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog6112;

/**
 *
 * @author Student
 */
public class Bed {

    private String bedNumber;
    private boolean occupied;
    private String patientId;

    // Constructor
    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        this.occupied = false;
        this.patientId = null;
    }

    // Get bed number
    public String getBedNumber() {
        return bedNumber;
    }

    // Check if bed is occupied
    public boolean isOccupied() {
        return occupied;
    }

    // Get patient ID
    public String getPatientId() {
        return patientId;
    }

    // Occupy the bed
    public void occupy(String patientId) {
        this.occupied = true;
        this.patientId = patientId;
    }

    // Release the bed
    public void release() {
        this.occupied = false;
        this.patientId = null;
    }
}