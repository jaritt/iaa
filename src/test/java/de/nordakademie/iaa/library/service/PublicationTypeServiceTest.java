package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.PublicationType;
import de.nordakademie.iaa.library.service.api.PublicationTypeService;
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
public class PublicationTypeServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationTypeService service;

    private void samplePublicationType() {
        service.createPublicationType(new PublicationType("Bachelorarbeit"));
    }

    @Test
    public void testPublicationTypeCreation() {
        PublicationType type = new PublicationType("Bachelorarbeit");
        service.createPublicationType(type);
        assertThat(service.listPublicationTypes().get(0).getTitle()).isEqualTo("Bachelorarbeit");
    }

    @Test
    public void testPublicationTypeDeletion() {
        samplePublicationType();
        PublicationType type = service.listPublicationTypes().get(0);

        assertThat(service.listPublicationTypes().size()).isEqualTo(1);

        service.deletePublicationType(type.getId());

        assertThat(service.listPublicationTypes().isEmpty()).isTrue();
    }

    @Test
    public void testUpdate() {
        samplePublicationType();
        PublicationType type = service.listPublicationTypes().get(0);
        assertThat(service.listPublicationTypes().get(0).getTitle()).isEqualTo("Bachelorarbeit");
        service.updatePublicationType(type.getId(), "world");
        assertThat(service.listPublicationTypes().get(0).getTitle()).isEqualTo("world");
    }
}
