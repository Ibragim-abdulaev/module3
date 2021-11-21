package dao;

import enumerations.DoctorProfessions;
import models.Doctor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDaoTest {
    private static DoctorDao doctorDao;

    @BeforeAll
    static void beforeAll() {
        doctorDao = new DoctorDao();
        Doctor doctor1 = new Doctor("Doctor1", DoctorProfessions.CARDIOLOGIST);
        Doctor doctor2 = new Doctor("Doctor2", DoctorProfessions.INTERN);
        doctorDao.saveDoctor(doctor1);
        doctorDao.saveDoctor(doctor2);
    }

    @Test
    void save() {
        Doctor doctor3 = new Doctor("Doctor3", DoctorProfessions.THERAPIST);
        doctorDao.saveDoctor(doctor3);
        Assertions.assertEquals(doctor3.getId(), doctorDao.getDoctorById(doctor3.getId()).getId());
    }

    @Test
    void update() {
        Doctor doctor = doctorDao.getDoctorById(1);
        doctor.setLast_name("Doctor4");
        doctorDao.updateDoctor(doctor);
        Assertions.assertEquals(doctor.getLast_name(), doctorDao.getDoctorById(doctor.getId()).getLast_name());
    }

    @Test
    void delete() {
        Doctor doctor = new Doctor();
        doctorDao.saveDoctor(doctor);
        doctorDao.deleteDoctor(doctor.getId());
        assertNull(doctorDao.getDoctorById(doctor.getId()));
    }

    @Test
    void getById() {
        Doctor doctor = new Doctor("Doctor5", DoctorProfessions.THERAPIST);
        doctorDao.saveDoctor(doctor);
        Doctor doctors = doctorDao.getDoctorById(doctor.getId());
        Assertions.assertEquals(doctors.getLast_name(), doctor.getLast_name());
    }

    @Test
    void getAll() {
        Assertions.assertEquals(2, doctorDao.getAllDoctor().size());
    }
}