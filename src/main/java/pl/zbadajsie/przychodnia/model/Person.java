package pl.zbadajsie.przychodnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"doctor","visit","address"})
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Size(max = 30, min = 2)
    @NotNull
    @Column(name = "name")
    private String name;

    @Size(max = 30, min = 2)
    @NotNull
    @Column(name = "surname")
    private String surname;

    @Size(max = 10, min = 10)
    @NotNull
    @Column(name = "pesel")
    private String pesel;

    @ManyToMany
    @JoinTable(
            name = "clinic_visit",
            joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "visit_id")
    )
    private Set<Visit> visit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
