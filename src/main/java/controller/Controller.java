package controller;

import enumerations.DoctorStatus;
import enumerations.MedicationTypes;
import enumerations.PatientStatus;
import models.Doctor;
import models.Medications;
import models.Patient;
import services.CreateObjects;
import services.DoctorService;
import services.MedicationService;
import services.PatientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Controller {
    DoctorService doctorServices = new DoctorService();
    MedicationService medicationsServices = new MedicationService();
    PatientService patientServices = new PatientService();
    CreateObjects create = new CreateObjects();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws InterruptedException {
        create.createObjects();

        String action = "";
        do {
            System.out.println(System.lineSeparator() +
                    "----------------Menu----------------" + System.lineSeparator() +
                    "Press 1: To see a list of all Doctors" + System.lineSeparator() +
                    "Press 2: To see a list of all Medications" + System.lineSeparator() +
                    "Press 3: To see a list of all Patients" + System.lineSeparator() +
                    "Press 4: To change the Doctor status" + System.lineSeparator() +
                    "Press 5: Add new medications" + System.lineSeparator() +
                    "Press 6: Discharge the patient" + System.lineSeparator() +
                    "Press 0: To finish" + System.lineSeparator()
            );
            System.out.print("Write number: ");
            try {
                action = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (action) {
                case "1" -> doctorServices.readAllDoctors();
                case "2" -> medicationsServices.readAllMedication();
                case "3" -> patientServices.readAllPatients();
                case "4" -> changeStatusOfTheDoctor();
                case "5" -> createMedication();
                case "6" -> dischargeAPatient();
                case "0" -> System.out.println("Application finished, good bye");
                default -> System.out.println("Wrong, enter a number between 0 and 6");
            }
        } while (!action.equals("0"));
    }

    private void changeStatusOfTheDoctor() {
        System.out.println(System.lineSeparator() + "Select ID Doctor, who status is changed:");
        doctorServices.readAllDoctors();
        int idDoc = 0;
        String title = "";
        try {
            System.out.print(System.lineSeparator() + "Write ID: ");
            idDoc = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Doctor doctor = doctorServices.getDoctorById(idDoc);
        if (doctor == null) {
            System.out.println("Sorry, but doctor with this ID is not found...");
        } else {
            System.out.println(System.lineSeparator() + "With status of this doctor now? Choose number from the list" +
                    System.lineSeparator());
            System.out.println(
                    "-----Doctor status-----" + System.lineSeparator() +
                            "1: SICK" + System.lineSeparator() +
                            "2: HOME" + System.lineSeparator() +
                            "3: ON_OPERATION" + System.lineSeparator() +
                            "default = FREE" + System.lineSeparator()
            );
            try {
                System.out.print("Write number: ");
                title = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (title) {
                case "1" -> doctor.setStatus(DoctorStatus.SICK);
                case "2" -> doctor.setStatus(DoctorStatus.HOME);
                case "3" -> doctor.setStatus(DoctorStatus.ON_OPERATION);
                default -> doctor.setStatus(DoctorStatus.FREE);
            }
            System.out.println("Doctor status is changed for " + doctor.getStatus());
            doctorServices.updateDoctor(doctor);
        }
    }

    private void dischargeAPatient() {
        System.out.println(System.lineSeparator() + "Please, enter ID patient, who is healthy.");
        patientServices.readAllPatients();
        int idPat = 0;
        try {
            System.out.print("Write id: #");
            idPat = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Patient patient = patientServices.getPatientById(idPat);
        if (patient == null) {
            System.out.println("Patient with this ID is not found");
        } else {
            if (patient.getStatus() != PatientStatus.DEAD) {
                patient.setStatus(PatientStatus.HEALTHY);
                patient.setRecipe(null);
                patient.getDoctors().clear();
                System.out.println("Patient with ID #" + idPat + " is healthy");
                patientServices.updatePatient(patient);
            } else {
                System.out.println("Patient is dead");
            }
        }

    }

    private void createMedication() {
        String title = null;
        System.out.println(System.lineSeparator() + "Please, choose type of medication");
        System.out.println(System.lineSeparator() +
                "----Type medication----" + System.lineSeparator() +
                "1: PILLS" + System.lineSeparator() +
                "2: INJECTIONS" + System.lineSeparator() +
                "3: DROPPER" + System.lineSeparator()
        );
        try {
            System.out.print("Write number: ");
            title = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Medications medication = new Medications();
        switch (Objects.requireNonNull(title)) {
            case "1" -> medication.setMedication_type(MedicationTypes.PILLS);
            case "2" -> medication.setMedication_type(MedicationTypes.INJECTIONS);
            case "3" -> medication.setMedication_type(MedicationTypes.DROPPER);
            default -> medication.setMedication_type(MedicationTypes.UNKNOWN);
        }
        System.out.println(System.lineSeparator() + "Write name for your Medication");
        try {
            System.out.print("Write name: ");
            title = bufferedReader.readLine();
            medication.setName(title);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(System.lineSeparator() + "Write count for your Medication (e.g: '1', '10', etc)");
        try {
            System.out.print("Write count: ");
            title = bufferedReader.readLine();
            short count = Short.parseShort(title);
            medication.setCount(count);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        medicationsServices.saveMedication(medication);
        System.out.println("Medication is saved" + medication);
    }
}
