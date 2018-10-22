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

import java.util.List;

public class LendingAction extends ActionSupport {

    public LendingAction(LendingService lendingService,
                         PublicationService publicationService,
                         CustomerService customerService) {
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

    private Lending lending;
    private Long lendingId;

    private List<Customer> customerList;
    private Long selectedCustomerId;

    public List<Customer> getCustomerList() {
        System.out.println("action.getCustomerList " + customerService.listCustomers().getClass());
        return customerService.listCustomers();
    }

    public void setCustomerList(List<Customer> customerList) {
        System.out.println("action.setCustomerList");
        this.customerList = customerList;
    }

    public String load() throws LendingNotFoundException {
        System.out.println("method.load");
        if (lendingId != null) {
            System.out.println("LendingID != null");
            lending = lendingService.loadLending(lendingId);
            System.out.println("Lending: " + lending);
            publication = publicationService.loadPublication(lending.getPublicationId());
            System.out.println("Publication: " + publication);
            customer = customerService.loadCustomer(lending.getCustomerId());
            System.out.println("Customer: " + customer);
        }
        System.out.println("LendingID == null");
        System.out.println("Publication: " + publication);

        return SUCCESS;
    }

    public String save() throws LendingAlreadyExistsException, NoCopyAvailable {
        if (lending.getId() != null) {

        } else {
            lendingService.lendPublication(publication, customer);
        }
        return SUCCESS;
    }

    public Long getSelectedCustomerId() {
        System.out.println("action.getSelectedCustomerId");
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        System.out.println("action.setSelectedCustomerId");
        this.selectedCustomerId = selectedCustomerId;
        lending.setCustomer(customerService.loadCustomer(selectedCustomerId));
    }

    public Long getLendingId() {
        System.out.println("action.getLendingId");
        return lendingId;
    }

    public void setLendingId(Long id) {
        System.out.println("action.setLendingId");
        this.lendingId = id;
    }

    public Lending getLending() {
        return lending;
    }

    public void setLending(Lending lending) {
        System.out.println("action.setLending");
        this.lending = lending;
    }

    public Publication getPublication() {
        System.out.println("action.getPublication");
        System.out.println("PublicationId: " + publicationId);
        return publicationService.loadPublication(publicationId);
    }

    public void setPublication(Publication publication) {
        System.out.println("action.setPublication");
        this.publication = publication;
    }

    public Customer getCustomer() {
        System.out.println("action.getCustomer");
        return customer;
    }

    public void setCustomer(Customer customer) {
        System.out.println("action.setCustomer");
        this.customer = customer;
    }

    public Long getPublicationId() {
        System.out.println("action.getPublicationId");
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        System.out.println("action.setPublicationId");
        this.publicationId = publicationId;
    }
}
