package models;

import enumerations.DoctorProfessions;
import enumerations.DoctorStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String last_name;

    @Enumerated(EnumType.STRING)
    private DoctorProfessions profession;

    @Enumerated(EnumType.STRING)
    private DoctorStatus status = DoctorStatus.FREE;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Patient> patients = new ArrayList<>();

    public Doctor(String last_name, DoctorProfessions profession) {
        this.last_name = last_name;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Doctor " +
                "last name is'" + last_name + '\'' +
                ", profession=" + profession +
                ", status=" + status +
                '}';
    }
}