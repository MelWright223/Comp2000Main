package com.Controller;

import com.Model.AbstractView;
import com.Model.Kiosk;
import com.Model.Stock;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardPayment extends AbstractView {
    public boolean bankApproved;

    private JList stockedItemsLst;
    private JButton returnBackBtn;
    private JButton payCardBtn;
    private JButton printRecieptBtn;
    private JPanel cardPanel;
    private JTextField cardTotalTxt;
    private JLabel basketLbl;
    private DefaultListModel<String> payModel = new DefaultListModel<>();
    private String[] values = new String[0];
    private ArrayList<Stock> stockData = new ArrayList<Stock>();
    private File fileIn = new File("Resources\\BasketData.txt");
    public float total = Kiosk.total;

  public CardPayment() throws IOException {
      setContentPane(cardPanel);
      displayJPanel();
      loadBasket();

      payCardBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              verifyCard();
          }
      });
      printRecieptBtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              printReceipt();
          }
      });

  }
  public void loadBasket() throws IOException {
      //Scanner fileRead = new Scanner(fileIn);
      List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));

      String linesFile = line.toString();


      for (String lines : line) {

          values = lines.split("\\|");
          String itemName = values[0];
          int itemQuant = Integer.parseInt(values[1]);
          float itemPrice = Float.parseFloat(values[2]);
          payModel.addElement(values[0] + ", " + values[1] + ", £" + values[2]);
          stockedItemsLst.setModel(payModel);



      }
       cardTotalTxt.setText(String.valueOf(total));

  }
  public void verifyCard(){
      Random num = new Random();
      switch (num.nextInt(2)){
          case 1:
              JOptionPane.showMessageDialog(null, "Your card has been verified. \n Please take your receipt.",
                      "Card Payment",JOptionPane.DEFAULT_OPTION);
              cardTotalTxt.setText("0.00");
              payCardBtn.setEnabled(false);
              break;
          case 2:
              JOptionPane.showMessageDialog(null, "Your card has not been verified. \n returning you back to card payment.",
                      "card payment",JOptionPane.DEFAULT_OPTION );
              break;
      }


  }

  public void printReceipt(){


      JOptionPane.showMessageDialog(null, "SHOPPING SPREES"+System.lineSeparator()+"Sprees that last a week" + System.lineSeparator()+
              "13/01/2022"+ System.lineSeparator() + "Items bought: "+ payModel
              + System.lineSeparator() + "Total Price: " + total, "Receipt",JOptionPane.DEFAULT_OPTION);



  }

}
