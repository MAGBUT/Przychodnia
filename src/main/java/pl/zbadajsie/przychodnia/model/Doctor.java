package pl.zbadajsie.przychodnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"specialization","person"})
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int id;

    @Size(max = 500, min = 10)
    @NotNull
    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "doctor")
    private Person person;

    @NotNull
    @Column(name = "specialization_id")
    @ManyToMany
    @JoinTable(
            name = "clinic_doctor_specialization",
            joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specialization;
}

