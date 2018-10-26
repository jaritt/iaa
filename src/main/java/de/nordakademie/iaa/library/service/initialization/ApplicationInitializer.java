package de.nordakademie.iaa.library.service.initialization;

import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.NoCopyAvailable;
import de.nordakademie.iaa.library.service.api.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
            LendingService lendingService) {
        this.customerService = customerService;
        this.keywordService = keywordService;
        this.typeService = typeService;
        this.publicationService = publicationService;
        this.lendingService = lendingService;
    }

    private CustomerService customerService;

    private KeywordService keywordService;

    private PublicationTypeService typeService;

    private PublicationService publicationService;

    private LendingService lendingService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        setupData();
    }

    public void setupData() {
        Customer customer = new Customer("Peter", "Parker");
        customer.setCity("SpiderTown");
        customer.setStreet("SpiderStreet");
        customer.setMatnr(6299L);
        customerService.createCustomer(customer);

        PublicationType type = new PublicationType("Fachbuch");
        typeService.createPublicationType(type);
        typeService.createPublicationType(new PublicationType("Hausarbeit"));
        PublicationType type2 = new PublicationType("Lehrbuch");
        typeService.createPublicationType(type2);

        Keyword keyword = new Keyword("Softwareentwicklung");
        keywordService.createKeyword(keyword);
        Keyword keyword1 = new Keyword("Steuern");
        keywordService.createKeyword(keyword1);
        Keyword keyword3 = new Keyword("Bildung");
        keywordService.createKeyword(keyword3);
        Keyword keyword2 = new Keyword("Software");
        keywordService.createKeyword(keyword2);

        Publication publication = new Publication("LS-2110", "Design Patterns. Elements of Reusable Object-Oriented Software.", type, 2L);
        publication.getKeywords().add(keyword);
        publication.setReleaseDate(LocalDate.of(1994, 10, 31));
        publication.setAuthor("Erich Gamma");
        publication.setIsbn("978-0-20163-361-0");
        publication.setPublisher("Addison-Wesley");
        publicationService.createPublication(publication);

        Publication publication2 = new Publication("LS-2200", "Steuerrecht leicht gemacht", type2, 10L);
        publication2.getKeywords().add(keyword1);
        publication2.getKeywords().add(keyword3);
        publication2.setReleaseDate(LocalDate.of(2007, 10, 10));
        publication2.setAuthor("Stephan Kudert");
        publication2.setIsbn("978-3-87440-330-6");
        publication2.setPublisher("Kleist");
        publicationService.createPublication(publication2);

        Publication publication3 = new Publication("LS-2120", "Microservices. Grundlagen flexibler Softwarearchitekturen.", type, 5L);
        publication3.getKeywords().add(keyword);
        publication3.getKeywords().add(keyword2);
        publication3.setReleaseDate(LocalDate.of(2018, 07, 30));
        publication3.setAuthor("Eberhard Wolff");
        publication3.setIsbn("978-3-86490-555-1");
        publication3.setPublisher("Dpunkt.verlag GmbH");
        publicationService.createPublication(publication3);

        try {
            lendingService.lendPublication(publication, customer);
            Lending lending2 = lendingService.lendPublication(publication2, customer);
            Lending lending3 = lendingService.lendPublication(publication3, customer);
            lending2.setStartDate(LocalDate.of(2018,10,1));
            lending2.setEndDate(LocalDate.of(2018,10,24));
            lending3.setStartDate(LocalDate.of(2018,10,1));
            lending3.setEndDate(LocalDate.of(2018,10,20));
        } catch (NoCopyAvailable noCopyAvailable) {
            noCopyAvailable.printStackTrace();
        }

    }
}
