package be.bf.banque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@ToString(includeFieldNames = true)
//@NoArgsConstructor
@AllArgsConstructor
public class AccountOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String ssin;

    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String firstname;

    public AccountOwner() {}

    public AccountOwner(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public AccountOwner setLastname(String lastname) {
        if(lastname.isEmpty()) return null;
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AccountOwner setFirstname(String firstname) {
        if(firstname.isEmpty()) return null;
        this.firstname = firstname;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Titulaire{");
        sb.append("id=").append(id);
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
