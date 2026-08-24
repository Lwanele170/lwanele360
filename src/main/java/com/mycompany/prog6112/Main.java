/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog6112;

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HospitalSystem hospital = new HospitalSystem();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("     HOSPITAL PATIENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Register Patient");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Display All Patients");
            System.out.println("6. Allocate Bed");
            System.out.println("7. Release Bed");
            System.out.println("8. Display Bed Layout");
            System.out.println("9. Display Available Beds");
            System.out.println("10. Display Occupied Beds");
            System.out.println("11. Display Reports");
            System.out.println("12. Sort Patients by Surname");
            System.out.println("13. Sort Patients by Patient ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n--- REGISTER PATIENT ---");

                    System.out.print("Patient ID: ");
                    String id = scanner.nextLine();

                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Medical Condition: ");
                    String condition = scanner.nextLine();

                    System.out.println("\n1. Inpatient");
                    System.out.println("2. Outpatient");
                    System.out.println("3. Emergency");

                    System.out.print("Choose category: ");
                    int category = scanner.nextInt();
                    scanner.nextLine();

                    Patient patient;

                    if (category == 1) {

                        System.out.print("Ward Number: ");
                        String ward = scanner.nextLine();

                        patient = new Inpatient(
                                id,
                                firstName,
                                lastName,
                                age,
                                gender,
                                condition,
                                ward
                        );

                    } else if (category == 2) {

                        patient = new Patient(
                                id,
                                firstName,
                                lastName,
                                age,
                                gender,
                                condition,
                                PatientCategory.OUTPATIENT
                        );

                    } else if (category == 3) {

                        patient = new Patient(
                                id,
                                firstName,
                                lastName,
                                age,
                                gender,
                                condition,
                                PatientCategory.EMERGENCY
                        );

                    } else {

                        System.out.println("Invalid category.");
                        break;
                    }

                    if (hospital.registerPatient(patient)) {
                        System.out.println(
                                "Patient registered successfully."
                        );
                    } else {
                        System.out.println(
                                "Patient ID already exists."
                        );
                    }

                    break;

                case 2:

                    System.out.print("Enter Patient ID: ");
                    String searchId = scanner.nextLine();

                    Patient found = hospital.searchPatient(searchId);

                    if (found != null) {

                        System.out.println("\nPatient found:");
                        found.displayDetails();

                    } else {

                        System.out.println(
                                "Patient not found."
                        );
                    }

                    break;

                case 3:

                    System.out.print("Enter Patient ID: ");
                    String updateId = scanner.nextLine();

                    if (hospital.searchPatient(updateId) == null) {

                        System.out.println(
                                "Patient not found."
                        );

                        break;
                    }

                    System.out.print("New First Name: ");
                    String newFirstName = scanner.nextLine();

                    System.out.print("New Last Name: ");
                    String newLastName = scanner.nextLine();

                    System.out.print("New Age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("New Gender: ");
                    String newGender = scanner.nextLine();

                    System.out.print(
                            "New Medical Condition: "
                    );

                    String newCondition = scanner.nextLine();

                    hospital.updatePatient(
                            updateId,
                            newFirstName,
                            newLastName,
                            newAge,
                            newGender,
                            newCondition
                    );

                    System.out.println(
                            "Patient updated successfully."
                    );

                    break;

                case 4:

                    System.out.print("Enter Patient ID: ");
                    String deleteId = scanner.nextLine();

                    if (hospital.deletePatient(deleteId)) {

                        System.out.println(
                                "Patient deleted successfully."
                        );

                    } else {

                        System.out.println(
                                "Patient not found."
                        );
                    }

                    break;

                case 5:

                    hospital.displayAllPatients();

                    break;

                case 6:

                    System.out.print(
                            "Enter Inpatient ID: "
                    );

                    String allocateId = scanner.nextLine();

                    if (hospital.allocateBed(allocateId)) {

                        System.out.println(
                                "Bed allocated successfully."
                        );

                    } else {

                        System.out.println(
                                "Bed could not be allocated."
                        );
                    }

                    break;

                case 7:

                    System.out.print("Enter Patient ID: ");
                    String releaseId = scanner.nextLine();

                    if (hospital.releaseBed(releaseId)) {

                        System.out.println(
                                "Bed released successfully."
                        );

                    } else {

                        System.out.println(
                                "Bed could not be released."
                        );
                    }

                    break;

                case 8:

                    hospital.displayBedLayout();

                    break;

                case 9:

                    hospital.displayAvailableBeds();

                    break;

                case 10:

                    hospital.displayOccupiedBeds();

                    break;

                case 11:

                    System.out.println(
                            "\n--- HOSPITAL REPORT ---"
                    );

                    System.out.println(
                            "Total registered patients: "
                            + hospital.getTotalPatients()
                    );

                    System.out.println(
                            "Total occupied beds: "
                            + hospital.getTotalOccupiedBeds()
                    );

                    System.out.println(
                            "Ward occupancy: "
                            + hospital.getOccupancyPercentage()
                            + "%"
                    );

                    System.out.println("\nAll Patients:");

                    hospital.displayAllPatients();

                    System.out.println("\nAvailable Beds:");

                    hospital.displayAvailableBeds();

                    System.out.println("\nOccupied Beds:");

                    hospital.displayOccupiedBeds();

                    break;

                case 12:

                    hospital.sortBySurname();

                    System.out.println(
                            "Patients sorted by surname."
                    );

                    hospital.displayAllPatients();

                    break;

                case 13:

                    hospital.sortByPatientId();

                    System.out.println(
                            "Patients sorted by Patient ID."
                    );

                    hospital.displayAllPatients();

                    break;

                case 0:

                    System.out.println(
                            "Thank you for using the system."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);

        scanner.close();
    }
}
 