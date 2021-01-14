package com.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentTest {

    CardPayment cardTest = new CardPayment();

    CardPaymentTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After");
    }

    @Test //pass
    void loadBasket() throws IOException {
        System.out.println("During");
        cardTest.loadBasket();
    }
}