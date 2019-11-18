package be.vdab.personeel.domain;

import be.vdab.personeel.constraints.Rijksregister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "werknemers")
@NamedEntityGraph(name = Werknemer.MET_JOBTITEL,
        attributeNodes = @NamedAttributeNode("jobtitel"))

public class Werknemer implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String MET_JOBTITEL="Werknemer.metJobtitel";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String familienaam;
    @NotBlank
    private String voornaam;
    @NotNull
    @Email
    private String email;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chefid")
    private Werknemer chef;

    @OneToMany(mappedBy = "chef")
    private Set<Werknemer> subordinates = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;
    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal salaris;
    //-----------------------------------------------------------
    private String paswoord;
    @DateTimeFormat(pattern = "d-M-yy")
    private LocalDate geboorte;
    @Column(unique = true)

    private long rijksregisternr;
    @Version
    private long versie;

    protected Werknemer() {
    }

    public Werknemer(@NotBlank String familienaam, @NotBlank String voornaam, @NotNull @Email String email, Werknemer chef, Jobtitel jobtitel, @NotNull @PositiveOrZero @Digits(integer = 10, fraction = 2) BigDecimal salaris, String paswoord, LocalDate geboorte, long rijksregisternr) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        this.chef = chef;
        this.jobtitel = jobtitel;
        this.salaris = salaris;
        this.paswoord = paswoord;
        this.geboorte = geboorte;
        this.rijksregisternr = rijksregisternr;
    }

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public long getRijksregisternr() {
        return rijksregisternr;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Set<Werknemer> getSubordinates() {
        return Collections.unmodifiableSet(subordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Werknemer)) return false;
        Werknemer werknemer = (Werknemer) o;
        return rijksregisternr == werknemer.rijksregisternr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rijksregisternr);
    }

    public void opslag(BigDecimal bedrag){
        if(bedrag.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException();
        }
        salaris = salaris.add(bedrag);
    }


    public void setRijksregisternr(long nummer){
        long geboortejaar = nummer/1000000000;
        long geboortemaand = (nummer/10000000)%100;
        long geboortedag = (nummer/100000)%100;
        if(geboorte.getDayOfMonth() == geboortedag && geboorte.getMonthValue() == geboortemaand && (geboorte.getYear()%100) == geboortejaar) {
            rijksregisternr = nummer;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
