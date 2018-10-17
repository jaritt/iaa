package de.nordakademie.iaa.library.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerAction extends ActionSupport implements Preparable {

    public CustomerAction(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerService customerService;

    private Long customerId;
    private List<String> title;
    private Customer customer;

    public String load() throws Exception{
        customer = customerService.loadCustomer(customerId);
        return SUCCESS;
    }

    public String save() throws Exception{
        if (customer.getId() != null){
            customerService.updateCustomer(customer.getId(),customer.getTitle(),customer.getName(),customer.getFirstName(),customer.getCity(),customer.getStreet(),customer.getMatnr());
        }else{
            customerService.createCustomer(customer);
        }
        return SUCCESS;
    }

    public String delete() throws Exception{
        customerService.deleteCustomer(customerId);
        return SUCCESS;
    }


    public void prepare() throws Exception {
        title=new ArrayList<String>();
        title.add("Herr");
        title.add("Frau");
    }

    public String titleList() throws Exception{
        return SUCCESS;
    }
}
