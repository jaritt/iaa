package de.nordakademie.iaa.library.service.internal;

import de.nordakademie.iaa.library.model.Customer;
import de.nordakademie.iaa.library.service.internal.api.NotifierService;

import javax.inject.Named;

/**
 * @author Felix Welter
 */
@Named
public class StdOutNotifierService implements NotifierService {
    @Override
    public void sendMessage(Customer customer, String message) {
        System.out.println("Message for " + customer.fullRepresentation());
        System.out.println(message);
    }
}
