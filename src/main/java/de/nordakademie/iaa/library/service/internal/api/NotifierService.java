package de.nordakademie.iaa.library.service.internal.api;

import de.nordakademie.iaa.library.model.Customer;

/**
 * This defines a possibility to inform a customer. While the
 * content of the message needs to be given as input, this service
 * takes care of delivering the message to the customer.
 *
 * @author Felix Welter
 */
public interface NotifierService {
    void sendMessage(Customer customer, String message);
}
