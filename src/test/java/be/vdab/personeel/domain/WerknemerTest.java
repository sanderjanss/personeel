package be.vdab.personeel.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class WerknemerTest {
    private Werknemer werknemer;
    private Werknemer chef;
    private Jobtitel jobtitel;
    private Jobtitel jobtitelChef;


    @Before
    public void before(){
        jobtitel = new Jobtitel("test");
        jobtitelChef = new Jobtitel("chef");
        chef = new Werknemer("testChef", "testChef", "chef@email.com", null, jobtitelChef, BigDecimal.valueOf(3000), "test", LocalDate.of(1950, 01, 31), 50013100129L);
        werknemer = new Werknemer("testFam", "testVoor", "test@email.com", chef, jobtitel, BigDecimal.valueOf(2000), "test", LocalDate.of(1960, 01, 01), 60010100172L);
    }
    @Test
    public void opslag(){
        werknemer.opslag(BigDecimal.valueOf(20));
        assertThat(werknemer.getSalaris()).isEqualByComparingTo("2020");
    }

    @Test
    public void opslagMetNullMislukt(){
        assertThatNullPointerException().isThrownBy(()->werknemer.opslag(null));
    }

    @Test
    public void opslagMetNegatiefMislukt(){
        assertThatIllegalArgumentException().isThrownBy(()->werknemer.opslag(BigDecimal.valueOf(-20)));
    }
    @Test
    public void opslagMet0Mislukt(){
        assertThatIllegalArgumentException().isThrownBy(()->werknemer.opslag(BigDecimal.ZERO));
    }

}
