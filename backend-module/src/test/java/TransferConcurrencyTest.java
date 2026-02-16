import com.example.backend.BackendApplication;
import com.example.ejb.BeneficioEjbService;
import com.example.ejb.adapter.persistence.BeneficioRepository;
import com.example.ejb.domain.models.Beneficio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@Transactional()
@ActiveProfiles("test")
public class TransferConcurrencyTest {

    @Autowired
    private BeneficioEjbService service;

    @Autowired
    private BeneficioRepository repository;

    @Test
    void shouldThrowOptimisticLockExceptionWhenConcurrentUpdate() throws Exception {

        Beneficio from  = new Beneficio();
        from.setId(1L);
        from.setValor(BigDecimal.valueOf(100));

        Beneficio to  = new Beneficio();
        to.setId(2L);
        to.setValor(BigDecimal.valueOf(0));

        from = repository.save(from);
        to = repository.save(to);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Beneficio finalFrom = from;
        Beneficio finalTo = to;
        Callable<Void> task = () -> {
            service.transfer(finalFrom.getId(), finalTo.getId(), new BigDecimal("50"));
            return null;
        };

        Future<Void> f1 = executor.submit(task);
        Future<Void> f2 = executor.submit(task);

        int failures = 0;

        try { f1.get(); } catch (Exception e) { failures++; }
        try { f2.get(); } catch (Exception e) { failures++; }

        System.out.println("failures: " + failures);

        assertTrue(failures >= 1);
    }
}
