package de.nordakademie.iaa.library.service.internal;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.service.internal.api.ReturnDateCalculatorService;

import javax.inject.Named;
import javax.inject.Scope;
import java.time.LocalDate;

/**
 * @author Felix Welter
 */
@Named
public class ReturnDateCalculatorServiceImpl implements ReturnDateCalculatorService {

    private static final int DEFAULT_LENDING_TIME = 14;
    private Lending lending;

    @Override
    public ReturnDateCalculatorServiceImpl reset() {
        this.lending = null;
        return this;
    }

    @Override
    public ReturnDateCalculatorServiceImpl setLending(Lending lending) {
        this.lending = lending;
        return this;
    }

    @Override
    public ReturnDateCalculatorServiceImpl setCustomer(Customer customer) {
        // This calculator does not use the customer
        // during calculation
        return this;
    }

    @Override
    public ReturnDateCalculatorServiceImpl setPublication(Publication publication) {
        // This calculation does not use the publication
        // during calculation
        return this;
    }

    @Override
    public LocalDate getReturnDate() {
        if (lending == null) {
            return LocalDate.now().plusDays(DEFAULT_LENDING_TIME);
        }
        return lending.getEndDate().plusDays(DEFAULT_LENDING_TIME);
    }
}
