package services;

import dao.DoctorDao;
import models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    DoctorDao doctorDao = new DoctorDao();

    public void saveDoctor(Doctor doctor) {
        List<Doctor> doctors = doctorDao.getAllDoctor();
        List<String> names = new ArrayList<>();

        if (doctors.isEmpty()) {
            doctorDao.saveDoctor(doctor);
        } else {
            for (Doctor x : doctors) {
                names.add(x.getLast_name());
            }
            if (!names.contains(doctor.getLast_name())) {
                doctorDao.saveDoctor(doctor);
            } else {
                System.out.println("This doctor already yet");
            }
        }
    }

    public Doctor getDoctorById(int id) {
        return doctorDao.getDoctorById(id);
    }

    public void readAllDoctors() {
        List<Doctor> doctors = doctorDao.getAllDoctor();
        System.out.println();
        for (Doctor d : doctors) {
            String s = "ID: #" + d.getId() + " | Last_name: " + d.getLast_name() + " | Profession: "
                    + d.getProfession() + " | Status is " + d.getStatus();
            System.out.println(s);
        }
    }

    public void updateDoctor(Doctor doctor) {
        doctorDao.updateDoctor(doctor);
    }
}
