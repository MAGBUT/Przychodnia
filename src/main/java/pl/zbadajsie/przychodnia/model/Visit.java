package pl.zbadajsie.przychodnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"referrals","note","prescriptions"})
@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private int id;

    @NotNull
    @Column(name = "visit_data")
    private OffsetDateTime dateTime;

    @ManyToMany(mappedBy = "visit")
    private Set<Person> person;

    @Column(name = "description")
    private String description;

    @Column(name = "accept")
    private Boolean accept;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Note note;

    @OneToMany(mappedBy = "visit",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Referral> referrals;

    @OneToMany(mappedBy = "visit",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions;

    public LocalDate getDate(){
       return dateTime.toLocalDate();
    }

    public LocalTime getTime(){
        return dateTime.toLocalTime();
    }
}
