/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog6112;

/**
 *
 * @author Student
 */
import java.util.ArrayList;
import java.util.Comparator;

public class HospitalSystem {

    private ArrayList<Patient> patients;
    private ArrayList<Bed> beds;

    public HospitalSystem() {

        patients = new ArrayList<>();
        beds = new ArrayList<>();

        // Create 20 beds
        for (int i = 1; i <= 20; i++) {

            String bedNumber;

            if (i < 10) {
                bedNumber = "B0" + i;
            } else {
                bedNumber = "B" + i;
            }

            beds.add(new Bed(bedNumber));
        }
    }

    // Register patient
    public boolean registerPatient(Patient patient) {

        if (searchPatient(patient.getPatientId()) != null) {
            return false;
        }

        patients.add(patient);
        return true;
    }

    // Search patient
    public Patient searchPatient(String patientId) {

        for (Patient patient : patients) {

            if (patient.getPatientId().equalsIgnoreCase(patientId)) {
                return patient;
            }
        }

        return null;
    }

    // Delete patient
    public boolean deletePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        if (patient instanceof Inpatient) {

            Inpatient inpatient = (Inpatient) patient;

            if (inpatient.getBedNumber() != null) {
                releaseBed(patientId);
            }
        }

        patients.remove(patient);

        return true;
    }

    // Update patient
    public boolean updatePatient(String patientId,
                                 String firstName,
                                 String lastName,
                                 int age,
                                 String gender,
                                 String medicalCondition) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);

        return true;
    }

    // Display all patients
    public void displayAllPatients() {

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient patient : patients) {

            System.out.println("----------------------------");

            patient.displayDetails();
        }
    }

    // Allocate bed
    public boolean allocateBed(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        // Only inpatients can get beds
        if (!(patient instanceof Inpatient)) {
            return false;
        }

        Inpatient inpatient = (Inpatient) patient;

        // Check if patient already has a bed
        if (inpatient.getBedNumber() != null) {
            return false;
        }

        // Find available bed
        for (Bed bed : beds) {

            if (!bed.isOccupied()) {

                bed.occupy(patientId);

                inpatient.setBedNumber(
                        bed.getBedNumber()
                );

                return true;
            }
        }

        // All beds are occupied
        return false;
    }

    // Release bed
    public boolean releaseBed(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        if (!(patient instanceof Inpatient)) {
            return false;
        }

        Inpatient inpatient = (Inpatient) patient;

        String bedNumber = inpatient.getBedNumber();

        if (bedNumber == null) {
            return false;
        }

        for (Bed bed : beds) {

            if (bed.getBedNumber().equals(bedNumber)) {

                bed.release();

                inpatient.setBedNumber(null);

                return true;
            }
        }

        return false;
    }

    // Display bed layout
    public void displayBedLayout() {

        System.out.println("\nBED LAYOUT");

        for (int i = 0; i < beds.size(); i++) {

            Bed bed = beds.get(i);

            if (bed.isOccupied()) {

                System.out.print(
                        "[" + bed.getBedNumber()
                        + ": OCCUPIED] "
                );

            } else {

                System.out.print(
                        "[" + bed.getBedNumber()
                        + ": AVAILABLE] "
                );
            }

            // Put 5 beds on each row
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    // Display available beds
    public void displayAvailableBeds() {

        System.out.println("\nAVAILABLE BEDS:");

        boolean found = false;

        for (Bed bed : beds) {

            if (!bed.isOccupied()) {

                System.out.println(
                        bed.getBedNumber()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No beds available.");
        }
    }

    // Display occupied beds
    public void displayOccupiedBeds() {

        System.out.println("\nOCCUPIED BEDS:");

        boolean found = false;

        for (Bed bed : beds) {

            if (bed.isOccupied()) {

                System.out.println(
                        bed.getBedNumber()
                        + " - Patient: "
                        + bed.getPatientId()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No beds are occupied.");
        }
    }

    // Total patients
    public int getTotalPatients() {

        return patients.size();
    }

    // Total occupied beds
    public int getTotalOccupiedBeds() {

        int count = 0;

        for (Bed bed : beds) {

            if (bed.isOccupied()) {
                count++;
            }
        }

        return count;
    }

    // Occupancy percentage
    public double getOccupancyPercentage() {

        return (getTotalOccupiedBeds() / 20.0) * 100;
    }

    // Sort by surname
    public void sortBySurname() {

        patients.sort(
                Comparator.comparing(
                        Patient::getLastName
                )
        );
    }

    // Sort by Patient ID
    public void sortByPatientId() {

        patients.sort(
                Comparator.comparing(
                        Patient::getPatientId
                )
        );
    }
}