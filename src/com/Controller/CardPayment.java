package com.Controller;

import com.Model.AbstractView;
import com.Model.Kiosk;
import com.Model.Stock;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
    private File stockFile = new File("Resources\\Stock");
    public float total = Kiosk.total;
    public int quant = Kiosk.quant;

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
          int itemBarcode = Integer.valueOf(values[1]);
          int itemQuant = Integer.parseInt(values[2]);
          float itemPrice = Float.parseFloat(values[3]);
          payModel.addElement(values[0] + ", " + values[1] +"," + values[2] +", £" + values[2]);
          stockedItemsLst.setModel(payModel);
          quant = itemQuant;



      }
       cardTotalTxt.setText(String.valueOf(total));

  }
  public void verifyCard(){
      Random num = new Random();
      switch (num.nextInt(2)){
          case 1:
              JOptionPane.showMessageDialog(null, "Your card has been verified. \n Please print your receipt.",
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
      try {
          List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));

          for (String lines : line) {
              values = lines.split("\\|");
              reduceQuantity("Resources\\Stock");

          }

      } catch (IOException e) {
          e.printStackTrace();
      }



  }
    public void reduceQuantity(String filePath) throws IOException {
        int position = 0;
        String tempFile = "Resources\\Dump";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        // String name = "";


        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        String currentLine;

        while ((currentLine = br.readLine()) != null) {

            String[] data = currentLine.split("\\|");
            int itemQuanty = Integer.parseInt(data[2]);

            itemQuanty = itemQuanty - Integer.parseInt(values[2]);



            if (!(data[position].equals(values[0]))) {
                pw.println(currentLine);

            } else {

                pw.println(data[0] + "|" + data[1] + "|" + itemQuanty + "|" + data[3]);
            }
        }


        pw.flush();
        pw.close();
        fr.close();
        br.close();
        bw.close();
        fw.close();
        oldFile.delete();
        File dump = new File(filePath);
        newFile.renameTo(dump);

    }

}
