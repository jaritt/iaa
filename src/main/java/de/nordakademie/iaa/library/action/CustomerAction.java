package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.model.Lending;
import de.nordakademie.iaa.library.service.api.CustomerService;
import de.nordakademie.iaa.library.service.api.LendingService;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Jannis Bär & Vikash Sharma
 */

public class CustomerAction extends ActionSupport implements Action {
    public CustomerAction(CustomerService customerService, LendingService lendingService) {

        this.customerService = customerService;
        this.lendingService = lendingService;
    }

    private CustomerService customerService;
    private LendingService lendingService;
    private Long customerId;
    private Customer customer;

    public String load() throws Exception {
        customer = customerService.loadCustomer(customerId);

        return SUCCESS;
    }

    public String save() throws Exception {
        if (customer.getId() != null) {
            customerService.updateCustomer(customer.getId(), customer.getTitle(), customer.getName(), customer.getFirstName(), customer.getCity(), customer.getStreet(), customer.getMatnr(), customer.getPlz());
        } else {
            customerService.createCustomer(customer);
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        customerService.deleteCustomer(customerId);
        return SUCCESS;
    }

    public void validateLoad() {
        if (customerId == null && customer == null) {
            addActionError(getText("error.selectCustomer"));
        }
    }

    public void validateSave() {
        Customer presentCustomer = customerService.findCustomerByMatnr(customer.getMatnr());
        if (customer.getMatnr() != null && presentCustomer != null) {
            if (customer.getId() != presentCustomer.getId()) {
                addActionError(getText("error.customerAlreadyExists"));
            }
        }
    }

    public void validateDelete() {
        if (customerId == null) {
            addActionError(getText("error.selectCustomer"));
        }
        List<Lending> lendingList = lendingService.listLendings().stream()
                .filter(lending -> lending.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
        if (!lendingList.isEmpty()) {
            addActionError(getText("error.customerHadLendings"));
        }
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
