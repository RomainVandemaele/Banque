package be.bf.banque.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.Length;


import javax.annotation.MatchesPattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//import javax.validation.constraints.*;

/**
 * Bank class repsonsible to represents a bank that holds accounts from several owners.
 * It is also an entity of the database
 * @attributes id of the bank
 * @attributes name of the bank
 *
 * @invariant
 */
@Entity
@Table(name = "bank")
@Getter @Setter
@NoArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY IS AUTOINCREMEHNT
    @Column(name = "id", nullable = false )
    private Long id;

    @Column(name = "name", nullable = false , length = 32)
    @NotBlank
    @Pattern(regexp ="[a-z A-Z 0-9.]*")
    private String name;

    public String getName() {
        return this.name;
    }

    public Bank setName(String name) {
        if(name==null) return null;
        if (name.isBlank()) return null;
        this.name = name;
        return this;
    }
}