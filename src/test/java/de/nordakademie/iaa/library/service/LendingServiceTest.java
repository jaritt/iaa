package de.nordakademie.iaa.library.service;


import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.api.CustomerService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Felix Welter
 */
public class LendingServiceTest extends BasicServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LendingService service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CustomerService customerService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationService publicationService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationTypeService publicationTypeService;

    @Before
    public void setupData() {
        customerService.createCustomer(new Customer("Harald", "Bauer"));
        PublicationType type = new PublicationType("Buch");
        publicationTypeService.createPublicationType(type);
        publicationService.createPublication(new Publication("BG-233", "Sun Oil", type, 10L));
    }

    private Customer customer() {
        return customerService.listCustomers().get(0);
    }

    private Publication publication() {
        return publicationService.listPublications().get(0);
    }

    LocalDate date(int month) {
        return LocalDate.of(2018, month, 01);
    }

    @Test
    public void testLendingCreation() throws NoCopyAvailable {
        Lending lending = service.lendPublication(publication(), customer());
        assertThat(lending).isEqualTo(service.listLendings().get(0));
        assertThat(lending).isEqualTo(publication().getLendings().get(0));
        assertThat(publication().copiesAvailable()).isEqualTo(9L);
    }

    @Test
    public void testLendingCreationFailWhenNoCopiesAvailable() throws NoCopyAvailable {
        PublicationType type = new PublicationType("Anleitung");
        publicationTypeService.createPublicationType(type);
        Publication pub1 = new Publication("BG-112", "Sun Screen", type, 2L);
        publicationService.createPublication(pub1);
        Long pubId = pub1.getId();

        Publication publication = publicationService.loadPublication(pubId);

        assertThat(publication.isCopyAvailable()).isTrue();

        Lending lending = service.lendPublication(publication, customer());

        assertThat(publication.isCopyAvailable()).isTrue();

        Lending lending2 = service.lendPublication(publication, customer());

        assertThat(publication.isCopyAvailable()).isFalse();

        assertThat(service.listLendings().size()).isEqualTo(2);

        assertThatThrownBy(() -> service.lendPublication(publication, customer())).isInstanceOf(NoCopyAvailable.class);
    }

    @Test
    public void testProlongLending() throws NoCopyAvailable {
        Lending lending = service.lendPublication(publication(), customer());
        assertThat(lending.canBeProlonged()).isTrue();

        service.prolongLending(lending);

        assertThat(service.listLendings().get(0).canBeProlonged()).isTrue();

        service.prolongLending(lending);

        assertThat(service.listLendings().get(0).canBeProlonged()).isFalse();

        assertThatThrownBy(() -> service.prolongLending(lending)).isInstanceOf(ProlongationNotPossible.class);
    }

    @Test
    public void testMarkLendingAsReturned() throws NoCopyAvailable {
        Lending lending = service.lendPublication(publication(), customer());
        assertThat(service.listLendings().get(0).isCompleted()).isFalse();
        service.markLendingAsReturned(lending);
        assertThat(service.listLendings().get(0).isCompleted()).isTrue();
        assertThat(publication().getCopies()).isEqualTo(10);
    }

    @Test
    public void testMarkLendingAsLost() throws NoCopyAvailable {
        Lending lending = service.lendPublication(publication(), customer());
        assertThat(service.listLendings().get(0).isCompleted()).isFalse();
        service.markLendingAsLost(lending);
        assertThat(service.listLendings().get(0).isCompleted()).isTrue();
        assertThat(publication().getCopies()).isEqualTo(9);
    }
}
