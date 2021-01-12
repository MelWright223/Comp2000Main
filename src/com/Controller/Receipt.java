package com.Controller;

import com.Model.AbstractView;

import java.util.List;

public class Receipt extends AbstractView {
    public String typePayment;

    public CardPayment cardPayment;

    public Cash cashPayment;

    public void purchasedItems(final List<String> ItemName) {
    }

}
