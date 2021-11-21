package services;

import dao.PatientDao;
import models.Patient;

import java.util.List;

public class PatientService {
    PatientDao patientDao = new PatientDao();

    public Patient savePatient(Patient patient) {
        List<Patient> patients = patientDao.getAllPatient();

        if (!patients.isEmpty()) {
            int i = 0, patientsSize = patients.size();
            while (i < patientsSize) {
                Patient p = patients.get(i);
                if (p.getFirst_name().equals(patient.getFirst_name()) && p.getLast_name().equals(patient.getLast_name())) {
                    patient = p;
                }
                i++;
            }
        } else {
            patientDao.savePatient(patient);
        }

        if (patient.getId() == 0) {
            patientDao.savePatient(patient);
        }
        return patient;
    }

    public void readAllPatients() {
        List<Patient> patients = patientDao.getAllPatient();
        System.out.println();
        System.out.println("All patients in hospital");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        System.out.println();
    }

    public void updatePatient(Patient patient) {
        patientDao.updatePatient(patient);
    }

    public Patient getPatientById(int id){
        return patientDao.getPatientById(id);
    }
}
