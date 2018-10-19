package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;


public class CustomerAction extends ActionSupport {

    public CustomerAction(CustomerService customerService) {
        this.customerService = customerService;
    }
    private CustomerService customerService;

    private Long customerId;
    private Customer customer;

    public String load() throws Exception{
        customer = customerService.loadCustomer(customerId);
        return SUCCESS;
    }

    public String save() throws Exception{
        if (customer.getId() != null){
            customerService.updateCustomer(customer.getId(),customer.getTitle(),customer.getName(),customer.getFirstName(),customer.getCity(),customer.getStreet(),customer.getMatnr());
        }
        else{
            customerService.createCustomer(customer);
        }
        return SUCCESS;
    }

    public String delete() throws Exception{
        customerService.deleteCustomer(customerId);
        return SUCCESS;
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
