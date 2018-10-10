package de.nordakademie.iaa.library.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DummyMessageTest {
    @Test
    void testCorrectMessage() {
        DummyMessage message = new DummyMessage();
        assertThat("Hello World".equals(message.getInformation())).isTrue();

        assertThat("Dont like world".equals(message.getInformation())).isFalse();
    }
}
