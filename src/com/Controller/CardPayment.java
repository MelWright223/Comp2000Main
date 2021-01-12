package com.Controller;

import com.Model.AbstractView;


import javax.swing.*;

public class CardPayment extends AbstractView {
    public boolean bankApproved;

    private JList stockedItemsLst;
    private JButton returnBackBtn;
    private JButton payCardBtn;
    private JButton printRecieptBtn;
    private JPanel cardPanel;
    private JTextField textField1;
    private JLabel basketLbl;

  public CardPayment(){
      setContentPane(cardPanel);
      displayJPanel();

  }
  public void loadBasket(){



  }

}
