package com.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CashTest {
    Cash testCash = new Cash();

    CashTest() throws IOException, InterruptedException {
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
    void payment() throws IOException, InterruptedException {
        System.out.println("During");
        testCash.payment();


    }
}