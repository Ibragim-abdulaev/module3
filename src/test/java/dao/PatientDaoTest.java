package dao;

import enumerations.DoctorProfessions;
import models.Doctor;
import models.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientDaoTest {
    private static PatientDao patientDao;

    @BeforeAll
    static void beforeAll() {
        patientDao = new PatientDao();
        Patient patient1 = new Patient("Ibrahim", "Abdulaiev");
        Patient patient2 = new Patient("Ibrahim1", "Abdulaiev1");
        patientDao.savePatient(patient1);
        patientDao.savePatient(patient2);
    }

    @Test
    void savePatient() {
        Patient patient3 = new Patient("Ibrahim3", "Abdulaiev3");
        patientDao.savePatient(patient3);
        Assertions.assertEquals(patient3.getId(), patientDao.getPatientById(patient3.getId()).getId());
    }

    @Test
    void updatePatient() {
        Patient patient = patientDao.getPatientById(1);
        patient.setFirst_name("Ibrahim4");
        patientDao.updatePatient(patient);
        Assertions.assertEquals(patient.getFirst_name(), patientDao.getPatientById(patient.getId()).getFirst_name());
    }

    @Test
    void getPatientById() {
        Patient patient = new Patient("Ibrahim5", "Abdulaiev5");
        patientDao.savePatient(patient);
        Patient patients = patientDao.getPatientById(patient.getId());
        Assertions.assertEquals(patients.getLast_name(), patient.getLast_name());
    }

    @Test
    void getAllPatient() {
        Assertions.assertEquals(4, patientDao.getAllPatient().size());
    }

    @Test
    void deletePatient() {
        Patient patient = new Patient();
        patientDao.savePatient(patient);
        patientDao.deletePatient(patient.getId());
        assertNull(patientDao.getPatientById(patient.getId()));
    }
}