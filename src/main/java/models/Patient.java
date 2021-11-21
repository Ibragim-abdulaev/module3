package models;

import enumerations.PatientStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private String last_name;
    @Enumerated(EnumType.STRING)
    private PatientStatus status = PatientStatus.SICK;
    private LocalDate arrival_date = LocalDate.now();

    @ManyToOne(cascade = CascadeType.ALL)
    private Recipe recipe;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Doctor> doctors = new ArrayList<>();

    public Patient(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "PatientID №" + id +
                " | First name='" + first_name + '\'' +
                " | last name='" + last_name + '\'' +
                " | arrival date=" + arrival_date +
                " | status=" + status +
                " | doctors=" + doctors +
                '}';
    }
}
