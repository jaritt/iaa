/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.dao;

import de.nordakademie.iaa.library.model.Customer;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
public class CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> listCustomers() {
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    public Customer loadCustomer(Long customerId) {
        Customer customer = (Customer) entityManager.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }

    public Customer findCustomerByWord(String word) {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer l where l.word = :word", Customer.class);
        query.setParameter("word", word);
        List<Customer> customers = query.getResultList();
        if (customers.isEmpty()) {
            return null;
        }
        return customers.get(0);
    }

    public void deleteCustomer(Long customerId) {
        entityManager.remove(loadCustomer(customerId));
    }

    public Customer saveCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }
}
