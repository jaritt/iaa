package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.model.Keyword;
import de.nordakademie.iaa.library.service.api.KeywordService;
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
public class KeywordServiceTest extends BasicServiceTest{

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private KeywordService service;

    private void sampleKeyword() {
        service.createKeyword(new Keyword("nano"));
    }

    @Test
    public void testKeywordCreation() {
        Keyword keyword = new Keyword("nano");
        service.createKeyword(keyword);
        assertThat(service.listKeywords().get(0).getWord()).isEqualTo("nano");
    }

    @Test
    public void testKeywordDeletion() {
        sampleKeyword();
        Keyword keyword = service.listKeywords().get(0);

        assertThat(service.listKeywords().size()).isEqualTo(1);

        service.deleteKeyword(keyword.getId());

        assertThat(service.listKeywords().isEmpty()).isTrue();
    }

    @Test
    public void testUpdate() {
        sampleKeyword();
        Keyword keyword = service.listKeywords().get(0);
        assertThat(service.listKeywords().get(0).getWord()).isEqualTo("nano");
        service.updateKeyword(keyword.getId(), "world");
        assertThat(service.listKeywords().get(0).getWord()).isEqualTo("world");
    }
}
