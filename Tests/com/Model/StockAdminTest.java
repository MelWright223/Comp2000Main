package com.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StockAdminTest {
    StockAdmin testStock = new StockAdmin();

    StockAdminTest() throws IOException {
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before class");
    }

    @AfterEach
    void tearDown() {
    }

    @Test //passed
    void loadData() throws IOException {
        testStock.loadData();

    }
}