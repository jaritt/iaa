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

    public LendingAction(PublicationService publicationService, CustomerService customerService) {
        this.publicationService = publicationService;
        this.customerService = customerService;

        customerList = this.customerService.listCustomers();
    }

    private LendingService lendingService;
    private PublicationService publicationService;
    private CustomerService customerService;

    private Publication publication;
    private Customer customer;

    private Lending lending;
    private Long lendingId;

    private List<Customer> customerList;
    private Long selectedCustomerId;

    public List<Customer> getCustomerList() {
        return customerService.listCustomers();
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


    public String load() throws LendingNotFoundException {
        lending = lendingService.loadLending(lendingId);
        customerList = customerService.listCustomers();
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
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
        lending.setCustomer(customerService.loadCustomer(selectedCustomerId));
    }
}
