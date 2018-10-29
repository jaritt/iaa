package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Felix Welter
 */
public class PublicationServiceTest extends BasicServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationService service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationTypeService typeService;

    private void samplePublication() {
        Publication publication = new Publication();
        PublicationType type = new PublicationType("Lehrbuch");
        typeService.createPublicationType(type);
        publication.setKey("LS-2200");
        publication.setTitle("IT for Dummies");
        publication.setType(type);
        publication.setLendings(new ArrayList<Lending>());
        publication.setKeywords(new ArrayList<Keyword>());
        publication.setCopies(10L);
        service.createPublication(publication);
    }

    @Test
    public void testPublicationCreation() {
        Publication publication = new Publication();
        PublicationType type = new PublicationType("DummyType");
        typeService.createPublicationType(type);
        publication.setTitle("Elmshorn als strategischer Standort");
        publication.setKey("LS-4002");
        publication.setType(type);
        publication.setLendings(new ArrayList<Lending>());
        publication.setKeywords(new ArrayList<Keyword>());
        service.createPublication(publication);
        assertThat(service.listPublications().get(0).getTitle()).isEqualTo("Elmshorn als strategischer Standort");
    }

    @Test
    public void testPublicationDeletion() {
        samplePublication();
        Publication publication = service.listPublications().get(0);

        assertThat(service.listPublications().size()).isEqualTo(1);

        service.deletePublication(publication.getId());

        assertThat(service.listPublications().isEmpty()).isTrue();
    }

    @Test
    public void testUpdate() {
        samplePublication();
        Publication publication = service.listPublications().get(0);
        assertThat(service.listPublications().get(0).getTitle()).isEqualTo("IT for Dummies");

        PublicationType publicationType = new PublicationType("Buch");
        typeService.createPublicationType(publicationType);


        Keyword keyword1 = new Keyword("blue");
        Keyword keyword2 = new Keyword("red");

        keywordService.createKeyword(keyword1);
        keywordService.createKeyword(keyword2);

        service.updatePublication(publication.getId(),
                "Blue Ocean Strategy",
                "Renee Mauborgne",
                10L,
                10L,
                2018L,
                "Harvard Business Review",
                publicationType,
                "978-1-56619-909-4",
                Arrays.asList(keyword1, keyword2),
                10L);

        Publication pub1 = service.listPublications().get(0);

        assertThat(pub1.getTitle()).isEqualTo("Blue Ocean Strategy");
        assertThat(pub1.getAuthor()).isEqualTo("Renee Mauborgne");
        assertThat(pub1.getReleaseDate()).isEqualTo("2018-10-10");
        assertThat(pub1.getPublisher()).isEqualTo("Harvard Business Review");
        assertThat(pub1.getType().getTitle()).isEqualTo("Buch");
        assertThat(pub1.getIsbn()).isEqualTo("978-1-56619-909-4");
        assertThat(pub1.getKeywords().get(0).getWord()).isEqualTo("blue");
        assertThat(pub1.getKeywords().get(1).getWord()).isEqualTo("red");
    }

    @Test
    public void testSearch() {
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
        } catch (NoCopyAvailable noCopyAvailable) {
            noCopyAvailable.printStackTrace();
        }

        // Search for ISBN und LS -> direct hits
        assertThat(publicationService.search("978-0-20163-361-0").get(0)).isEqualTo(publication);
        assertThat(publicationService.search("978-3-87440-330-6").get(0)).isEqualTo(publication2);
        assertThat(publicationService.search("978-3-86490-555-1").get(0)).isEqualTo(publication3);
        assertThat(publicationService.search("LS-2110").get(0)).isEqualTo(publication);
        assertThat(publicationService.search("LS-2200").get(0)).isEqualTo(publication2);
        assertThat(publicationService.search("LS-2120").get(0)).isEqualTo(publication3);

        assertThat(publicationService.search("design").get(0)).isEqualTo(publication);
        assertThat(publicationService.search("adison").get(0)).isEqualTo(publication);

        assertThat(publicationService.search("Recht").get(0)).isEqualTo(publication2);
        assertThat(publicationService.search("Kudert").get(0)).isEqualTo(publication2);

        assertThat(publicationService.search("flexibel architektur").get(0)).isEqualTo(publication3);
        assertThat(publicationService.search("wollf").get(0)).isEqualTo(publication3);

        assertThat(publicationService.search("software").size()).isEqualTo(2);
    }
}
