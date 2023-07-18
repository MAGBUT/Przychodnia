package pl.zbadajsie.przychodnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private int id;

    @NotNull
    @Size(min = 5,max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 1000)
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "specialization")
    private Set<Doctor> doctors;

}
