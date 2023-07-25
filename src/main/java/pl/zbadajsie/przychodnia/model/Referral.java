package pl.zbadajsie.przychodnia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "visit")
@EqualsAndHashCode(of = "id")
@Table(name = "referral")
public class Referral {

    @Id
    @Column(name = "referral_id")
    private int id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

}
