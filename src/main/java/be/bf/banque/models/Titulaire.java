package be.bf.banque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Titulaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String firstname;

    public Titulaire(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
