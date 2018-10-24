package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.dao.lending.LendingAlreadyExistsException;
import de.nordakademie.iaa.library.dao.lending.LendingNotFoundException;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.model.Publication;
import de.nordakademie.iaa.library.service.NoCopyAvailable;
import de.nordakademie.iaa.library.service.api.CustomerService;
import de.nordakademie.iaa.library.service.api.LendingService;
import de.nordakademie.iaa.library.service.api.PublicationService;
import de.nordakademie.iaa.library.service.internal.api.ReturnDateCalculatorService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LendingAction extends ActionSupport {

    public LendingAction(LendingService lendingService,
                         PublicationService publicationService,
                         CustomerService customerService,
                         ReturnDateCalculatorService returnDateCalculator) {

        this.returnDateCalculator = returnDateCalculator;
        this.lendingService = lendingService;
        this.publicationService = publicationService;
        this.customerService = customerService;

        customerList = this.customerService.listCustomers();
        System.out.println("LendingAction constructor");
    }

    private LendingService lendingService;
    private PublicationService publicationService;
    private CustomerService customerService;

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


    public List<Customer> getCustomerList() {
        return customerService.listCustomers();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String load() throws LendingNotFoundException {
        if (lendingId != null) {
            lending = lendingService.loadLending(lendingId);
            publication = publicationService.loadPublication(lending.getPublicationId());
            customer = customerService.loadCustomer(lending.getCustomerId());
        }
        setStartDate(LocalDate.now());
        setEndDate(returnDateCalculator.reset().getReturnDate());

        return SUCCESS;
    }

    public String save() throws LendingAlreadyExistsException, NoCopyAvailable {
        System.out.println("selectedCustomerId: " + selectedCustomerId);
        lending.setCustomer(customerService.loadCustomer(selectedCustomerId));
        lending.setPublication(publicationService.loadPublication(publicationId));
        lendingService.lendPublication(lending.getPublication(), lending.getCustomer());

        return SUCCESS;
    }

    /*
    public void validateSave() {
        if (selectedCustomerId == 0) {
            addActionError(getText("error.selectCustomerId"));
        }
    }
    */

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
        return publicationService.loadPublication(publicationId);
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

    public void setCustomerId(Long Id) {
        this.customerId = Id;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
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
}
