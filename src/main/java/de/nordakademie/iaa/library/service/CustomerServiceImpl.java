package de.nordakademie.iaa.library.service;

import de.nordakademie.iaa.library.dao.customer.CustomerDAO;
import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.api.CustomerService;

import javax.inject.Named;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Implements functions defined by
 * the CustomerService interface
 *
 * @author Felix Welter
 * @see CustomerService
 */
@Named
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO dao;

    public CustomerServiceImpl(CustomerDAO dao) {
        this.dao = dao;
    }

    /**
     * @param customer The customer to be persisted.
     */
    @Override
    public void createCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }

    /**
     * Returns available customers
     *
     * @return List of customers
     */
    @Override
    public List<Customer> listCustomers() {
        return dao.listCustomers();
    }

    /**
     * Deletes a customer
     *
     * @param id The identifier.
     * @throws EntityNotFoundException
     */
    @Override
    public void deleteCustomer(Long id) throws EntityNotFoundException {
        Customer customer = loadCustomer(id);
        if (customer == null) {
            throw new EntityNotFoundException();
        }
        dao.deleteCustomer(customer.getId());
    }

    /**
     * Load a customer
     *
     * @param id The identifier.
     * @return The requested customer
     */
    @Override
    public Customer loadCustomer(Long id) {
        return dao.loadCustomer(id);
    }

    /**
     * Updates a customer entity and stores the changes into database.
     *
     * @param id
     * @param title
     * @param name
     * @param firstName
     * @param city
     * @param street
     * @param matnr
     * @throws EntityNotFoundException
     */
    @Override
    public void updateCustomer(Long id, String title, String name, String firstName, String city, String street, Long matnr, Long plz) throws EntityNotFoundException {
        Customer customer = loadCustomer(id);
        if (customer == null) {
            throw new EntityNotFoundException();
        }
        customer.setId(id);
        customer.setTitle(title);
        customer.setName(name);
        customer.setFirstName(firstName);
        customer.setCity(city);
        customer.setStreet(street);
        customer.setMatnr(matnr);
        customer.setPlz(plz);
    }

    public Customer findCustomerByMatnr(Long matnr){ return dao.findCustomerByMatnr(matnr);}
}
