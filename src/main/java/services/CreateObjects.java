package services;

import enumerations.DoctorProfessions;
import enumerations.MedicationTypes;
import models.Doctor;
import models.Medications;
import models.Patient;
import models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class CreateObjects {
    DoctorService doctorServices = new DoctorService();
    RecipeService recipeServices = new RecipeService();
    PatientService patientServices = new PatientService();

    public void createObjects() throws InterruptedException {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Morales", DoctorProfessions.THERAPIST));
        doctors.add(new Doctor("Welch", DoctorProfessions.CARDIOLOGIST));
        doctors.add(new Doctor("Wilson", DoctorProfessions.PATHOLOGIST));
        doctors.forEach(x -> doctorServices.saveDoctor(x));
        System.out.println("Create the Doctor...");

        Thread.sleep(1000);
        System.out.println("Create the Medications...");

        List<Medications> medications = new ArrayList<>();
        medications.add(new Medications("Сeftriaxone", (short) 10, MedicationTypes.DROPPER));
        medications.add(new Medications("Siofor", (short) 10, MedicationTypes.PILLS));
        medications.add(new Medications("Sodium_Chloride", (short) 20, MedicationTypes.INJECTIONS));
        medications.add(new Medications("Trombonete", (short) 20, MedicationTypes.PILLS));
        medications.add(new Medications("Amoxil", (short) 10, MedicationTypes.DROPPER));
        medications.add(new Medications("Aspirin", (short) 10, MedicationTypes.PILLS));
        medications.add(new Medications("Penicillin", (short) 10, MedicationTypes.INJECTIONS));
        medications.add(new Medications("Erius", (short) 100, MedicationTypes.PILLS));
        medications.add(new Medications("Moflax", (short) 10, MedicationTypes.INJECTIONS));
        medications.add(new Medications("Hepacef", (short) 10, MedicationTypes.DROPPER));

        Thread.sleep(1000);
        System.out.println("Create the Recipe...");

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new Recipe("Take Aspirin 4 tablets daily," + System.lineSeparator() +
                "Penicillin two injections per day"));
        recipeList.get(0).getMedications().add(medications.get(0));
        recipeList.get(0).getMedications().add(medications.get(3));
        recipeList.add(new Recipe("Take Trombonete 1 tablets daily," + System.lineSeparator() +
                "Сeftriaxone one dropper per day"));
        recipeList.get(1).getMedications().add(medications.get(1));
        recipeList.get(1).getMedications().add(medications.get(6));
        recipeList.add(new Recipe("Take Erius 2 tablets daily," + System.lineSeparator() +
                "Amoxil one dropper per day"));
        recipeList.get(2).getMedications().add(medications.get(2));
        recipeList.get(2).getMedications().add(medications.get(7));
        recipeList.add(new Recipe("Take Hepacef one dropper daily," + System.lineSeparator() +
                "Siofor one tablets per day"));
        recipeList.get(3).getMedications().add(medications.get(4));
        recipeList.get(3).getMedications().add(medications.get(9));
        recipeList.add(new Recipe("Take Sodium_Chloride one injections daily," + System.lineSeparator() +
                "Moflax one injections per day"));
        recipeList.get(4).getMedications().add(medications.get(5));
        recipeList.get(4).getMedications().add(medications.get(8));
        recipeList.forEach(recipe -> recipeServices.saveRecipe(recipe));

        Thread.sleep(1000);
        System.out.println("Create the Patient...");

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Ibrahim", "Abdulaiev"));
        patients.get(0).setRecipe(recipeList.get(0));
        patients.get(0).getDoctors().add(doctors.get(0));
        patients.add(new Patient("Dmitriy", "Zhukov"));
        patients.get(1).setRecipe(recipeList.get(1));
        patients.get(1).getDoctors().add(doctors.get(1));
        patients.add(new Patient("Victoria", "Studneva"));
        patients.get(2).setRecipe(recipeList.get(2));
        patients.get(2).getDoctors().add(doctors.get(2));
        patients.add(new Patient("Ekaterina", "Danilenko"));
        patients.get(3).setRecipe(recipeList.get(3));
        patients.get(3).getDoctors().add(doctors.get(2));

        patients.forEach(patient -> patientServices.savePatient(patient));
        Thread.sleep(1000);
    }
}
