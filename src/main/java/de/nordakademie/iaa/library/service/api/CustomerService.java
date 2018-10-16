/**
 * Author: Felix Welter
 */
package de.nordakademie.iaa.library.service.api;

import de.nordakademie.iaa.library.model.Customer;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CustomerService {
    /**
     * Takes a new customer and stores it in the database.
     *
     * @param customer The customer to be persisted.
     */
    void createCustomer(Customer customer);

    /**
     * List all customers currently stored in the database.
     *
     * @return a list of Customer entities. If no customer was found an empty list is
     * returned.
     */
    @SuppressWarnings("unchecked")
    List<Customer> listCustomers();

    /**
     * Deletes the customer with the given id.
     *
     * @param id The identifier.
     * @throws EntityNotFoundException if no customer could be found for the given id.
     */
    void deleteCustomer(Long id) throws EntityNotFoundException;

    /**
     * Returns the customer identified by the given id.
     *
     * @param id The identifier.
     * @return the found entity or {@code null} if no entity was found with given identifier.
     */
    Customer loadCustomer(Long id);

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
    void updateCustomer(Long id,
                        String title,
                        String name,
                        String firstName,
                        String city,
                        String street,
                        Long matnr) throws EntityNotFoundException;

}
