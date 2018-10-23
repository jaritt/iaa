package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;

import java.util.List;


public class CustomerAction extends ActionSupport {
    public CustomerAction(CustomerService customerService) {

        this.customerService = customerService;

    }

    private List<String> titleList;
    private CustomerService customerService;
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

    public void validateDelete() {
        if (customerId == null && customer == null) {
            addActionError(getText("error.selectCustomer"));
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

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }
}
