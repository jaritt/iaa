package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.api.ReminderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
public class ReminderServiceTest extends BasicServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ReminderService reminderService;

    @Before
    public void setupData() {
        Customer customer = new Customer("Peter", "Parker");
        customerService.createCustomer(customer);
        PublicationType type = new PublicationType("Anleitung");
        typeService.createPublicationType(type);
        Keyword keyword = new Keyword("Meeresbiologie");
        keywordService.createKeyword(keyword);
        Publication publication = new Publication("LS-2339", "Pazifik vs. Atlantik", type, 3L);
        publication.getKeywords().add(keyword);
        publicationService.createPublication(publication);
        lendingService.lendPublication(publication, customer);
    }

    @Test
    public void testSendReminder() {
        Lending lending = lendingService.listLendings().get(0);
        reminderService.sendReminder(lending);

        assertThat(reminderService.listReminders().get(0).getLending()).isEqualTo(lending);
    }
}
