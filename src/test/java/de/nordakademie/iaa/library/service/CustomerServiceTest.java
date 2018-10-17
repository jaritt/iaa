package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(locations = {"file:./src/main/resources/spring-config.xml"})
@Transactional
public class CustomerServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CustomerService service;

    private void sampleCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Hans");
        customer.setName("Blaumeier");
        service.createCustomer(customer);
    }

    @Test
    public void testCustomerCreation() {
        Customer customer = new Customer();
        customer.setFirstName("Peter");
        customer.setName("Mustermann");
        service.createCustomer(customer);

        Customer c2 = service.listCustomers().get(0);
        assertThat(c2.getFirstName()).isEqualTo("Peter");
        assertThat(c2.getName()).isEqualTo("Mustermann");
    }

    @Test
    public void testCustomerDeletion() {
        sampleCustomer();
        Customer customer = service.listCustomers().get(0);

        assertThat(service.listCustomers().size()).isEqualTo(1);

        service.deleteCustomer(customer.getId());

        assertThat(service.listCustomers().isEmpty()).isTrue();
    }

    @Test
    public void testUpdate() {
        sampleCustomer();
        Customer customer = service.listCustomers().get(0);
        assertThat(service.listCustomers().get(0).getName()).isEqualTo("Blaumeier");
        service.updateCustomer(customer.getId(),
                "Herr",
                "Franzenstein",
                "Freddy",
                "Hamburg",
                "Mittelweg",
                14002L);

        Customer customer1 = service.listCustomers().get(0);

        assertThat(customer1.getTitle()).isEqualTo("Herr");
        assertThat(customer1.getName()).isEqualTo("Franzenstein");
        assertThat(customer1.getFirstName()).isEqualTo("Freddy");
        assertThat(customer1.getCity()).isEqualTo("Hamburg");
        assertThat(customer1.getStreet()).isEqualTo("Mittelweg");
        assertThat(customer1.getMatnr()).isEqualTo(14002L);
    }
}
