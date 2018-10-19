package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Felix Welter
 */
public class CustomerTest {

    @Test
    void testFullRepresentation() {
        Customer customer = new Customer("Franz", "Hermann", 13300L);
        assertThat(customer.fullRepresentation()).isEqualTo("Franz Hermann (13300)");
    }
}
