import com.example.ejb.BeneficioEjbService;
import com.example.ejb.adapter.persistence.BeneficioRepository;
import com.example.ejb.domain.exceptions.InsufficientBalanceException;
import com.example.ejb.domain.exceptions.NotFountException;
import com.example.ejb.domain.models.Beneficio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class TransferServiceTest {

    private BeneficioRepository repository;
    private BeneficioEjbService service;

    @BeforeEach
    void give() {
        repository = Mockito.mock(BeneficioRepository.class);
        service = new BeneficioEjbService(repository);
    }

    @Test
    void shouldTransferSuccessfully() {

        Beneficio from = new Beneficio();
        from.setId(1L);
        from.setValor(new BigDecimal(100));

        Beneficio to = new Beneficio();
        to.setId(2L);
        to.setValor(new BigDecimal(50));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(from));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(to));

        service.transfer(1L, 2L, BigDecimal.valueOf(30));

        assertEquals(new BigDecimal("70"), from.getValor());
        assertEquals(new BigDecimal("80"), to.getValor());
    }

    @Test
    void shouldThrowWhenSaldoInsuficiente() {

        Beneficio from = new Beneficio();
        from.setId(1L);
        from.setValor(new BigDecimal("10"));

        Beneficio to = new Beneficio();
        to.setId(2L);
        to.setValor(new BigDecimal("50"));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(from));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(to));

        assertThrows(InsufficientBalanceException.class, () ->
                service.transfer(1L, 2L, new BigDecimal("30"))
        );
    }

    @Test
    void shouldThrowWhenContaNaoExiste() {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFountException.class, () ->
                service.transfer(1L, 2L, new BigDecimal("10"))
        );
    }
}
