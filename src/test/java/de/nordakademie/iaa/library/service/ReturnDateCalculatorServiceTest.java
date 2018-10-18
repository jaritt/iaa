package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.service.api.CustomerService;
import de.nordakademie.iaa.library.service.internal.api.ReturnDateCalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.cglib.core.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(locations = {"file:./src/main/resources/spring-config.xml"})
@Transactional
public class ReturnDateCalculatorServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ReturnDateCalculatorService service;

    @Before
    public void setUp() throws Exception {
        service.reset();
    }

    @Test
    public void testSetter() {
        service.setCustomer(new Customer());
        service.setLending(new Lending());
        service.setPublication(new Publication());
    }

    @Test
    public void testSimpleReturnDate() {
        LocalDate returnDate = service.getReturnDate();
        LocalDate inTwoWeeks = LocalDate.now().plusDays(14);
        assertThat(returnDate).isEqualTo(inTwoWeeks);
    }

    @Test
    public void testProlongation() {
        Lending lending = new Lending();
        lending.setEndDate(LocalDate.of(2018, 02, 02));

        LocalDate newReturnDate = service.setLending(lending).getReturnDate();

        assertThat(newReturnDate).isEqualTo(LocalDate.of(2018, 02, 02).plusDays(14));
    }
}
