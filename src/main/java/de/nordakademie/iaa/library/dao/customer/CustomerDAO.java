package de.nordakademie.iaa.library.dao.customer;

import de.nordakademie.iaa.library.model.Customer;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class gives access to customers.
 *
 * @author Felix Welter
 * @see Customer
 */
@Named
public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * List all customers
     *
     * @return list of customers
     */
    public List<Customer> listCustomers() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    /**
     * Load a specific customer
     *
     * @param customerId customer identifier
     * @return requested customer
     */
    public Customer loadCustomer(Long customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    /**
     * Delete a specific customer
     *
     * @param customerId customer identifier
     */
    public void deleteCustomer(Long customerId) {
        entityManager.remove(loadCustomer(customerId));
    }

    /**
     * Persist a given customer to the database
     *
     * @param customer a new customer
     * @return the persisted customer
     */
    public Customer saveCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }
}
