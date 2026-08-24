/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog6112;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HospitalSystemTest {

    private HospitalSystem hospital;

    @BeforeEach
    public void setUp() {
        hospital = new HospitalSystem();
    }

    @Test
    public void testRegisterPatient() {

        Patient patient = new Patient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        assertTrue(hospital.registerPatient(patient));
    }

    @Test
    public void testDuplicatePatientId() {

        Patient patient1 = new Patient(
                "P001",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P001",
                "Peter",
                "Jones",
                30,
                "Male",
                "Fever",
                PatientCategory.EMERGENCY
        );

        hospital.registerPatient(patient1);

        assertFalse(hospital.registerPatient(patient2));
    }

    @Test
    public void testSearchPatient() {

        Patient patient = new Patient(
                "P002",
                "Mary",
                "Jones",
                30,
                "Female",
                "Headache",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        Patient result = hospital.searchPatient("P002");

        assertNotNull(result);
        assertEquals("Mary", result.getFirstName());
    }

    @Test
    public void testSearchPatientNotFound() {

        Patient result = hospital.searchPatient("P999");

        assertNull(result);
    }

    @Test
    public void testUpdatePatient() {

        Patient patient = new Patient(
                "P003",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        boolean result = hospital.updatePatient(
                "P003",
                "James",
                "Smith",
                26,
                "Male",
                "Cold"
        );

        assertTrue(result);

        Patient updated = hospital.searchPatient("P003");

        assertEquals("James", updated.getFirstName());
        assertEquals(26, updated.getAge());
    }

    @Test
    public void testDeletePatient() {

        Patient patient = new Patient(
                "P004",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        assertTrue(hospital.deletePatient("P004"));

        assertNull(hospital.searchPatient("P004"));
    }

    @Test
    public void testAllocateBed() {

        Inpatient patient = new Inpatient(
                "P005",
                "David",
                "Brown",
                40,
                "Male",
                "Injury",
                "Ward 1"
        );

        hospital.registerPatient(patient);

        assertTrue(hospital.allocateBed("P005"));

        assertEquals(1, hospital.getTotalOccupiedBeds());
        assertEquals("B01", patient.getBedNumber());
    }

    @Test
    public void testOutpatientCannotGetBed() {

        Patient patient = new Patient(
                "P006",
                "Sarah",
                "Brown",
                20,
                "Female",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        assertFalse(hospital.allocateBed("P006"));
    }

    @Test
    public void testReleaseBed() {

        Inpatient patient = new Inpatient(
                "P007",
                "Mike",
                "Brown",
                35,
                "Male",
                "Injury",
                "Ward 1"
        );

        hospital.registerPatient(patient);

        hospital.allocateBed("P007");

        assertEquals(1, hospital.getTotalOccupiedBeds());

        assertTrue(hospital.releaseBed("P007"));

        assertEquals(0, hospital.getTotalOccupiedBeds());
        assertNull(patient.getBedNumber());
    }

    @Test
    public void testAllTwentyBedsCanBeOccupied() {

        for (int i = 1; i <= 20; i++) {

            String id = "P" + i;

            Inpatient patient = new Inpatient(
                    id,
                    "First",
                    "Patient",
                    30,
                    "Male",
                    "Condition",
                    "Ward 1"
            );

            hospital.registerPatient(patient);

            assertTrue(hospital.allocateBed(id));
        }

        assertEquals(20, hospital.getTotalOccupiedBeds());
    }

    @Test
    public void testNoBedWhenWardIsFull() {

        for (int i = 1; i <= 20; i++) {

            String id = "P" + i;

            Inpatient patient = new Inpatient(
                    id,
                    "First",
                    "Patient",
                    30,
                    "Male",
                    "Condition",
                    "Ward 1"
            );

            hospital.registerPatient(patient);

            hospital.allocateBed(id);
        }

        Inpatient extraPatient = new Inpatient(
                "P21",
                "Extra",
                "Patient",
                30,
                "Male",
                "Condition",
                "Ward 1"
        );

        hospital.registerPatient(extraPatient);

        assertFalse(hospital.allocateBed("P21"));
    }

    @Test
    public void testOccupancyPercentage() {

        Inpatient patient = new Inpatient(
                "P008",
                "Tom",
                "Brown",
                30,
                "Male",
                "Injury",
                "Ward 1"
        );

        hospital.registerPatient(patient);

        hospital.allocateBed("P008");

        assertEquals(5.0, hospital.getOccupancyPercentage());
    }

    @Test
    public void testSortBySurname() {

        Patient patient1 = new Patient(
                "P009",
                "John",
                "Zulu",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P010",
                "Peter",
                "Adams",
                30,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient1);
        hospital.registerPatient(patient2);

        hospital.sortBySurname();

        assertEquals("Adams",
                hospital.searchPatient("P010").getLastName());
    }

    @Test
    public void testSortByPatientId() {

        Patient patient1 = new Patient(
                "P002",
                "John",
                "Smith",
                25,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P001",
                "Peter",
                "Jones",
                30,
                "Male",
                "Cold",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient1);
        hospital.registerPatient(patient2);

        hospital.sortByPatientId();

        assertEquals("P001",
                hospital.searchPatient("P001").getPatientId());
    }
}