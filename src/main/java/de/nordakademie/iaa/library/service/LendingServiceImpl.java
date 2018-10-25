package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.customer.CustomerDAO;
import de.nordakademie.iaa.library.dao.lending.LendingDAO;
import de.nordakademie.iaa.library.dao.publication.PublicationDAO;
import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.internal.api.ReturnDateCalculatorService;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Felix Welter
 */
@Named
public class LendingServiceImpl implements LendingService {

    private LendingDAO dao;

    private PublicationDAO publicationDao;

    private CustomerDAO customerDAO;

    private ReturnDateCalculatorService returnDateCalculator;

    public LendingServiceImpl(LendingDAO dao,
                              ReturnDateCalculatorService returnDateCalculator,
                              PublicationDAO publicationDao,
                              CustomerDAO customerDAO) {
        this.dao = dao;
        this.publicationDao = publicationDao;
        this.returnDateCalculator = returnDateCalculator;
        this.customerDAO = customerDAO;
    }

    /**
     * Returns all lendings
     *
     * @return List of lendings
     */
    @Override
    public List<Lending> listLendings() {
        return dao.listLendings();
    }

    /**
     * Load a lending
     *
     * @param id The identifier.
     * @return The requested lending
     */
    @Override
    public Lending loadLending(Long id) {
        return dao.loadLending(id);
    }

    /**
     * @param publication Publication that
     * @return list of lendings
     */
    @Override
    public List<Lending> findLendingsByPublication(Publication publication) {
        return dao.findLendingsByPublication(publication);
    }

    /**
     * Create a new lending for given publication and customer
     *
     * @param publication Publication that is borrowed
     * @param customer    Customer hat receives the publication
     * @return the created lending
     */
    @Override
    public Lending lendPublication(Publication publication, Customer customer) throws NoCopyAvailable {

        Publication currentPublication = publicationDao.loadPublication(publication.getId());

        Customer currentCustomer = customerDAO.loadCustomer(customer.getId());

        if (!publication.isCopyAvailable()) {
            throw new NoCopyAvailable();
        }

        Lending lending = new Lending();
        lending.setReturned(false);
        lending.setLost(false);
        lending.setStartDate(LocalDate.now());
        lending.setEndDate(returnDateCalculator.reset().setCustomer(currentCustomer).setPublication(currentPublication).getReturnDate());
        lending.setTimesProlonged(0L);
        lending.setReminders(new ArrayList<>());
        lending.setCustomer(currentCustomer);
        currentPublication.addLending(lending);
        dao.saveLending(lending);

        return lending;
    }

    /**
     * This prolongs a lending. New duration is calculated automatically.
     * Check with lending.canBeProlonged() if the lending can be prolonged
     * before using this method or catch ProlongationNotPossible
     *
     * @param lending The lending to be prolonged
     * @throws ProlongationNotPossible Thrown if lending cant be prolonged
     */
    @Override
    public void prolongLending(Lending lending) throws ProlongationNotPossible {
        LocalDate newReturnDate = returnDateCalculator.reset().setLending(lending).getReturnDate();
        lending.prolongUntil(newReturnDate);
    }

    @Override
    public void markLendingAsReturned(Lending lending) {
        lending.setReturned(true);
    }

    @Override
    public void markLendingAsLost(Lending lending) {
        lending.setLost(true);
        lending.getPublication().decreaseCopyCount();
    }
}
