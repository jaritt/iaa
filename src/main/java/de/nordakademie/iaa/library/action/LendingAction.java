package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.lending.LendingAlreadyExistsException;
import de.nordakademie.iaa.library.dao.lending.LendingNotFoundException;
import de.nordakademie.iaa.library.model.*;
import de.nordakademie.iaa.library.service.NoCopyAvailable;
import de.nordakademie.iaa.library.service.api.CustomerService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.api.ReminderService;
import de.nordakademie.iaa.library.service.internal.api.ReturnDateCalculatorService;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Jannis Bär & Vikash Sharma
 */

public class LendingAction extends ActionSupport implements Action {

    public LendingAction(LendingService lendingService,
                         PublicationService publicationService,
                         CustomerService customerService,
                         ReturnDateCalculatorService returnDateCalculator,
                         ReminderService reminderService) {

        this.returnDateCalculator = returnDateCalculator;
        this.lendingService = lendingService;
        this.publicationService = publicationService;
        this.customerService = customerService;
        this.reminderService = reminderService;

        customerList = this.customerService.listCustomers();
    }

    private LendingService lendingService;
    private PublicationService publicationService;
    private CustomerService customerService;
    private ReminderService reminderService;

    private Publication publication;
    private Long publicationId;

    private Customer customer;
    private Long customerId;

    private Lending lending;
    private Long lendingId;

    private ReturnDateCalculatorService returnDateCalculator;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<Customer> customerList;
    private Long selectedCustomerId;

    private List<Reminder> reminders;

    private Lending newLending;

    public List<Customer> getCustomerList() {
        return customerService.listCustomers();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String start() throws LendingNotFoundException {
        publication = publicationService.loadPublication(publicationId);
        setStartDate(LocalDate.now());
        setEndDate(returnDateCalculator.reset().getReturnDate());

        return SUCCESS;
    }

    public String load() throws LendingNotFoundException {
        lending = lendingService.loadLending(lendingId);
        publication = publicationService.loadPublication(lending.getPublicationId());
        customer = customerService.loadCustomer(lending.getCustomerId());
        reminders = reminderService.findRemindersByLending(lending);
        return SUCCESS;
    }

    public String save() throws LendingAlreadyExistsException, NoCopyAvailable {
        lending.setCustomer(customerService.loadCustomer(selectedCustomerId));
        lending.setPublication(publicationService.loadPublication(publicationId));
        newLending = lendingService.lendPublication(lending.getPublication(), lending.getCustomer());

        return SUCCESS;
    }

    public String prolongLending() {
        lending = lendingService.loadLending(lendingId);
        try {
            lendingService.prolongLending(lending);
        } catch (ProlongationNotPossible prolongationNotPossible) {
            addActionError(getText("error.prolongationNotPossible"));
            return ERROR;
        }
        return SUCCESS;
    }

    public String receivedLending() {
        lending = lendingService.loadLending(lendingId);
        lendingService.markLendingAsReturned(lending);
        return SUCCESS;
    }

    public String lostLending() {
        lendingService.markLendingAsLost(lending);
        return SUCCESS;
    }

    public void validateLostLending() {
        if (lending == null && lendingId == null) {
            addActionError(getText("error.selectLending"));
        }
        if (lendingId != null){
            lending = lendingService.loadLending(lendingId);
            if (!lending.isLossPossible()) {
                addActionError(getText("error.lossNotPossible"));
            }
        }
    }

    public void validateSave() {
        if (selectedCustomerId == 0) {
            addActionError(getText("error.selectLending"));
        }
    }

    public void validateLoad() {
        if (lending == null && lendingId == null) {
            addActionError(getText("error.selectLending"));
        }
    }

    public void validateProlongLending() {
        if (lending == null && lendingId == null) {
            addActionError(getText("error.selectLending"));
        }
        if (lendingId != null) {
            lending = lendingService.loadLending(lendingId);
            if (!lending.canBeProlonged()) {
                addActionError(getText("error.prolongationNotPossible"));
            }
        }
    }

    public void validateReceivedLending() {
        if (lending == null && lendingId == null) {
            addActionError(getText("error.selectLending"));
        }
    }

    public Long getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
        lending.setCustomer(customerService.loadCustomer(selectedCustomerId));
    }

    public Long getLendingId() {
        return lendingId;
    }

    public void setLendingId(Long id) {
        this.lendingId = id;
    }

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        this.lending = lending;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {

        this.customerId = customerId;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
        lending.setPublication(publicationService.loadPublication(publicationId));
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReturnDateCalculatorService getReturnDateCalculator() {
        return returnDateCalculator;
    }

    public void setReturnDateCalculator(ReturnDateCalculatorService returnDateCalculator) {
        this.returnDateCalculator = returnDateCalculator;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }
}