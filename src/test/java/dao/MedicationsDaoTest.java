package dao;

import enumerations.MedicationTypes;
import models.Medications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationsDaoTest {
    private static MedicationsDao medicationsDao;

    @BeforeAll
    static void beforeAll() {
        medicationsDao = new MedicationsDao();
        Medications medications1 = new Medications("Сeftriaxone", (short) 10, MedicationTypes.DROPPER);
        Medications medications2 = new Medications("Trombonete", (short) 30, MedicationTypes.PILLS);
        medicationsDao.saveMedication(medications1);
        medicationsDao.saveMedication(medications2);
    }

    @Test
    void saveMedication() {
        Medications medications3 = new Medications("Trombonete2", (short) 35, MedicationTypes.INJECTIONS);
        medicationsDao.saveMedication(medications3);
        Assertions.assertEquals(medications3.getId(), medicationsDao.getMedicationsById(medications3.getId()).getId());
    }

    @Test
    void updateMedications() {
        Medications medication = medicationsDao.getMedicationsById(1);
        medication.setName("Aspirin");
        medicationsDao.updateMedications(medication);
        Assertions.assertEquals(medication.getName(), medicationsDao.getMedicationsById(medication.getId()).getName());
    }

    @Test
    void getMedicationsById() {
        Medications medications = new Medications("Ceftriacson", (short) 40, MedicationTypes.INJECTIONS);
        medicationsDao.saveMedication(medications);
        Medications medications1 = medicationsDao.getMedicationsById(medications.getId());
        Assertions.assertEquals(medications1.getName(), medications.getName());
    }

    @Test
    void getAllMedications() {
        Assertions.assertEquals(3, medicationsDao.getAllMedications().size());
    }

    @Test
    void deleteMedications() {
        Medications medications = new Medications();
        medicationsDao.saveMedication(medications);
        medicationsDao.deleteMedications(medications.getId());
        assertNull(medicationsDao.getMedicationsById(medications.getId()));
    }
}