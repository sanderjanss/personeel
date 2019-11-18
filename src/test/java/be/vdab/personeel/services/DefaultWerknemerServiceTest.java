package be.vdab.personeel.services;

import be.vdab.personeel.domain.Jobtitel;
import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class DefaultWerknemerServiceTest {
    private DefaultWerknemerService service;
    @Mock
    private WerknemerRepository repository;
    private Werknemer werknemer;
    private Werknemer chef;
    private Jobtitel jobtitel;
    private Jobtitel jobtitelChef;

    @Before
    public void before() {
        jobtitel = new Jobtitel("test");
        werknemer = new Werknemer("testFam", "testVoor", "test@email.com", null, jobtitel, BigDecimal.valueOf(2000), "test", LocalDate.of(1960, 01, 01), 60010100172L);
        when(repository.findById(1L)).thenReturn(Optional.of(werknemer));
        service = new DefaultWerknemerService(repository);
    }

    @Test
    public void opslagBedragVerhoogtSalaris() {
        service.opslagBedrag(1, BigDecimal.TEN);
        assertThat(werknemer.getSalaris()).isEqualByComparingTo("2010");
        verify(repository).findById(1L);
    }

    @Test
    public void setRijksregisterGeeftNieuwRijksregisternummer() {
        service.setRijksregister(1, "60010100172");
        assertThat(werknemer.getRijksregisternr()).isEqualByComparingTo(60010100172L);
        verify(repository).findById(1L);
    }
}
