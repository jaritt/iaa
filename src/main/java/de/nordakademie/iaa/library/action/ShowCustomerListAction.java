package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.Action;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;

import java.util.List;

public class ShowCustomerListAction implements Action {

    public ShowCustomerListAction(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerService customerService;

    private List<Customer> customers;

    @Override
    public String execute() throws Exception {
        customers = customerService.listCustomers();
        return SUCCESS;
    }


}
