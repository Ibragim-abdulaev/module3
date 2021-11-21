package services;

import dao.MedicationsDao;
import models.Medications;

import java.util.List;

public class MedicationService {
    MedicationsDao dao = new MedicationsDao();

    public void saveMedication(Medications medication) {
        dao.saveMedication(medication);
    }

    public void readAllMedication() {
        System.out.println();
        List<Medications> allMedications = dao.getAllMedications();
        for (Medications medications : allMedications) {
            System.out.println(medications);
        }
    }
}
