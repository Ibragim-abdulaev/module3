package models;

import enumerations.MedicationTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "medication")
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private short count;
    private String name;
    @Enumerated(EnumType.STRING)
    private MedicationTypes medication_type = MedicationTypes.UNKNOWN;

    public Medications(String name, short count, MedicationTypes medications_type) {
        this.name = name;
        this.count = count;
        this.medication_type = medications_type;
    }
}
