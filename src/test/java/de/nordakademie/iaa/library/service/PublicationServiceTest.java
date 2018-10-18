package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.model.PublicationType;
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
public class PublicationServiceTest extends BasicServiceTest{

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
        publication.setTitle("IT for Dummies");
        publication.setType(type);
        publication.setLendings(new ArrayList<Lending>());
        publication.setKeywords(new ArrayList<Keyword>());
        service.createPublication(publication);
    }

    @Test
    public void testPublicationCreation() {
        Publication publication = new Publication();
        PublicationType type = new PublicationType("DummyType");
        typeService.createPublicationType(type);
        publication.setTitle("Elmshorn als strategischer Standort");
        publication.setType(type);
        publication.setLendings(new ArrayList<Lending>());
        publication.setKeywords(new ArrayList<Keyword>());
        service.createPublication(publication);
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

        service.updatePublication(publication.getId(),
                "Blue Ocean Strategy",
                "Renee Mauborgne",
                LocalDate.of(2004, 05, 05),
                "Harvard Business Review",
                publicationType,
                "978-1-56619-909-4",
                Arrays.asList(new Keyword("blue"), new Keyword("red")),
                10L);

        Publication pub1 = service.listPublications().get(0);

        assertThat(pub1.getTitle()).isEqualTo("Blue Ocean Strategy");
        assertThat(pub1.getAuthor()).isEqualTo("Renee Mauborgne");
        assertThat(pub1.getReleaseDate()).isEqualTo(LocalDate.of(2004, 05, 05));
        assertThat(pub1.getPublisher()).isEqualTo("Harvard Business Review");
        assertThat(pub1.getType().getTitle()).isEqualTo("Buch");
        assertThat(pub1.getIsbn()).isEqualTo("978-1-56619-909-4");
        assertThat(pub1.getKeywords().get(0).getWord()).isEqualTo("blue");
        assertThat(pub1.getKeywords().get(1).getWord()).isEqualTo("red");
    }
}
