package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.lending.LendingDAO;
import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.api.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * @author Felix Welter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitWebConfig(locations = {"file:./src/main/resources/spring-config.xml"})
@Transactional
abstract public class BasicServiceTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CustomerService customerService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private KeywordService keywordService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationService publicationService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PublicationTypeService typeService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LendingService lendingService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LendingDAO lendingDAO;

    @Before
    public void clearData() {
        for (Lending item : lendingService.listLendings()) {
            lendingDAO.deleteLending(item.getId());
        }
        for (Publication item : publicationService.listPublications()) {
            publicationService.deletePublication(item.getId());
        }
        for (Customer item : customerService.listCustomers()) {
            customerService.deleteCustomer(item.getId());
        }
        for (Keyword item : keywordService.listKeywords()) {
            keywordService.deleteKeyword(item.getId());
        }
        for (PublicationType item : typeService.listPublicationTypes()) {
            typeService.deletePublicationType(item.getId());
        }
    }
}
