package de.nordakademie.iaa.library.service.initialization;

import de.nordakademie.iaa.library.dao.lending.LendingDAO;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.NoCopyAvailable;
import de.nordakademie.iaa.library.service.api.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Adds sample data after application start up
 *
 * @author Felix Welter
 */
@Component
public class ApplicationInitializer implements ApplicationListener<ContextRefreshedEvent> {

    public ApplicationInitializer(
            CustomerService customerService,
            KeywordService keywordService,
            PublicationTypeService typeService,
            PublicationService publicationService,
            LendingService lendingService,
            LendingDAO lendingDAO) {
        this.customerService = customerService;
        this.keywordService = keywordService;
        this.typeService = typeService;
        this.publicationService = publicationService;
        this.lendingService = lendingService;
        this.lendingDAO = lendingDAO;
    }

    private CustomerService customerService;

    private KeywordService keywordService;

    private PublicationTypeService typeService;

    private PublicationService publicationService;

    private LendingService lendingService;

    private LendingDAO lendingDAO;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setupData();
    }

    public void setupData() {
        Customer customer = new Customer("Peter", "Parker");
        customerService.createCustomer(customer);

        PublicationType type = new PublicationType("Hausarbeit");
        typeService.createPublicationType(type);
        typeService.createPublicationType(new PublicationType("Fachbuch"));

        Keyword keyword = new Keyword("Ökonomie");
        keywordService.createKeyword(keyword);
        keywordService.createKeyword(new Keyword("Agiles Arbeiten"));

        Publication publication = new Publication("LS-2339", "Europäische Märkte", type, 3L);
        publication.getKeywords().add(keyword);
        publicationService.createPublication(publication);

        try {
            lendingService.lendPublication(publication, customer);
        } catch (NoCopyAvailable noCopyAvailable) {
            noCopyAvailable.printStackTrace();
        }
    }
}
