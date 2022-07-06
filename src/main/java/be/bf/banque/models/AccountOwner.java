package be.bf.banque.models;

import com.google.common.base.Objects;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.time.LocalDate;

/**
 * Mutable Class that represents the owner of account(s) in a bank
 * FA AccountOwner{firstname,lastname}
 *
 * @attribute id Long
 * @attribute ssin String
 * @attribute firstname Strting
 * @attribute lastname String
 * @attrivute birthday LocalDate
 *
 * @invariant ssin not null and ssin.length = 12 FORMAT YYMMDD-XXXXX
 * @invariant firstname not null and firstname.length > 0
 * @invariant lastname not null and lastname.length > 0
 */
@Entity

//@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "AccountOwner.findById", query = "SELECT a FROM AccountOwner a WHERE a.id = :id")
@NamedQuery(name = "AccountOwner.findAll", query = "SELECT a FROM AccountOwner a")
@NamedQuery(name = "AccountOwner.findBySSIN", query = "SELECT a FROM AccountOwner a WHERE a.SSIN = :ssin")
public class AccountOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = SSIN_LENGTH, max = SSIN_LENGTH)
    @Column(name = "ssin", nullable = false,unique = true, length = SSIN_LENGTH)
    private final String SSIN;

    //columnDefinition = "varchar(32) default 'John' "
    @NotNull
    @Column(nullable = false, length = 32)
    private String lastname = "Snow";
    @NotNull
    @Column(nullable = false, length = 32 )
    private String firstname = "John";

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @Transient
    static final int SSIN_LENGTH = 12;

    public AccountOwner() {
        this.SSIN = this.generateSSIN();
    }

    public static String generateSSIN() {
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i=0 ;i < SSIN_LENGTH - 3 ;++i) {
            sb.append(sr.nextInt(1,10));
        }
        int n = Integer.parseInt(sb.toString());
        int control = 97 - n%97;
        sb.append(control);
        ;       sb.insert(6,'-');
        return sb.toString();
    }

    public AccountOwner(AccountOwner accountOwner) {
        this(accountOwner.SSIN,accountOwner.firstname,accountOwner.lastname,accountOwner.birthday);
        this.id = accountOwner.id;
    }

    public AccountOwner(String lastname, String firstname) {
        this.SSIN = generateSSIN();
        setLastname(lastname);
        setFirstname(firstname);
    }
    public AccountOwner(String ssin,String lastname, String firstname) {
        this.SSIN = ssin;
        setLastname(lastname);
        setFirstname(firstname);
    }

    public AccountOwner(String ssin, String lastname, String firstname, LocalDate birthday) {
        this(ssin,firstname,lastname);
        this.birthday = birthday;
    }


    public String getSsin() {
        return SSIN;
    }



    public LocalDate getBirthday() {
        return birthday;
    }

    public AccountOwner setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public AccountOwner setBirthday(int year, int month, int day) {
        this.birthday = LocalDate.of(year,month,day);
        return this;
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
        if(lastname == null) return this;
        if(lastname.isBlank()) return this;
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AccountOwner setFirstname(String firstname) {
        if(firstname == null) return null;
        if(firstname.isBlank()) return null;
        this.firstname = firstname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOwner that = (AccountOwner) o;
        return Objects.equal(SSIN, that.SSIN) && Objects.equal(lastname, that.lastname) && Objects.equal(firstname, that.firstname) && Objects.equal(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(SSIN, lastname, firstname, birthday);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountOwner{");
        sb.append("SSIN='").append(SSIN).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
