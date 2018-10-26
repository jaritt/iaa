package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.customer.CustomerDAO;
import de.nordakademie.iaa.library.dao.keyword.KeywordDAO;
import de.nordakademie.iaa.library.dao.lending.LendingDAO;
import de.nordakademie.iaa.library.dao.publication.PublicationDAO;
import de.nordakademie.iaa.library.dao.publicationtype.PublicationTypeDAO;
import de.nordakademie.iaa.library.dao.reminder.ReminderDAO;
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
    protected CustomerDAO customerDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected KeywordDAO keywordDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected PublicationDAO publicationDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected PublicationTypeDAO typeDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected LendingDAO lendingDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected ReminderDAO reminderDAO;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected CustomerService customerService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected KeywordService keywordService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected PublicationService publicationService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected PublicationTypeService typeService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected LendingService lendingService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected ReminderService reminderService;

    @Before
    public void clearData() {
        for (Reminder item : reminderDAO.listReminders()) {
            reminderDAO.deleteReminder(item.getId());
        }
        for (Lending item : lendingDAO.listLendings()) {
            lendingDAO.deleteLending(item.getId());
        }
        for (Publication item : publicationDAO.listPublications()) {
            publicationDAO.deletePublication(item.getId());
        }
        for (Customer item : customerDAO.listCustomers()) {
            customerDAO.deleteCustomer(item.getId());
        }
        for (Keyword item : keywordDAO.listKeywords()) {
            keywordDAO.deleteKeyword(item.getId());
        }
        for (PublicationType item : typeDAO.listPublicationTypes()) {
            typeDAO.deletePublicationType(item.getId());
        }
    }
}
