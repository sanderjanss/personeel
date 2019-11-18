package be.vdab.personeel.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobtitels")

public class Jobtitel implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @Version
    private long versie;

    protected Jobtitel() {
    }

    public Jobtitel(@NotBlank String naam) {
        this.naam = naam;
    }

    @OneToMany(mappedBy = "jobtitel")
    private Set<Werknemer> werknemers = new HashSet<>();;

    public String getNaam() {
        return naam;
    }

    public Set<Werknemer> getWerknemers() {
        return Collections.unmodifiableSet(werknemers);
    }

    public long getId() {
        return id;
    }

}
