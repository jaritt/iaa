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

    /**
     * Load a customer by matnr
     *
     * @param matnr
     * @return requested customer
     */
    public Customer findCustomerByMatnr(Long matnr) {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer l where l.matnr = :matnr", Customer.class);
        query.setParameter("matnr", matnr);
        List<Customer> customers = query.getResultList();
        if (customers.isEmpty()) {
            return null;
        }
        return customers.get(0);
    }
}
