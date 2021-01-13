package com.Model;

import com.Controller.CardPayment;
import com.Controller.Cash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kiosk extends AbstractView {
    private JPanel KioskMain;
    private JButton btnLogin;
    private JList<String> currentStockLst;
    public JList<String> addedStockLst;
    private JTextField totalTxt;
    private JLabel totalLbl;
    private JButton cashBtn;
    private JButton addItemBtn;
    private JTextField quantityTxt;
    private JButton cardBtn;
    private JLabel quantityLbl;
    private BlockingQueue<Kiosk> messages;

    private String[] values = new String[0];
    private ArrayList<Stock> stockData = new ArrayList<Stock>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    DefaultListModel<String> addModel = new DefaultListModel<>();
    private File fileIn = new File("Resources\\Stock");
    public static float total;
    public ArrayList<String> things;
    public static int quant;
   // private List<ArrayList> hope;


    public Kiosk() throws IOException {

        setTitle("Kiosk");
        setContentPane(KioskMain);
        displayJPanel();
        loadAllStock();


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Kiosk.this.dispose();
                    AdminLogin adminLogon = new AdminLogin();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = currentStockLst.getSelectedIndex();
                //String getItem = currentStockLst.getSelectedValue().toString();

                //addModel.addElement(getItem);

                if (index != -1) {
                    model.get(index);
                    addedStockLst.setModel(addModel);
                    addToBasket();
                }
            }
        });
        cashBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kiosk.this.setVisible(false);
                try {
                    Cash cash = new Cash();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CardPayment card = new CardPayment();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }


    public void loadAllStock() throws IOException {


        //Scanner fileRead = new Scanner(fileIn);
        List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));


        //int i = 0;

        //String linesFile = line.toString();


        for (String lines : line) {

            values = lines.split("\\|");
            //Stock item = new Stock();

            String itemName = values[0];

            int itemCode = Integer.parseInt(values[1]);
            //item.setStockCode(itemCode);

            //item.setStockName(itemName);

            int itemQuant = Integer.parseInt(values[2]);
            //item.setStockQuantity(itemQuant);

            float itemPrice = Float.parseFloat(values[3]);
            // item.setStockPrice(itemPrice);

            stockData.add(new Stock(itemName, itemCode, itemQuant, itemPrice));
            model.addElement(values[0]);
            currentStockLst.setModel(model);

        }
    }

    public void addToBasket() {
        things = new ArrayList<String>(addedStockLst.getModel().getSize());

        String getItem = currentStockLst.getSelectedValue();

        Stock item = new Stock();
        Iterator<Stock> iterator = stockData.iterator();
        float itemPrice;

        int quant;
        int itemBarcode;


        for (model.elements().asIterator(); iterator.hasNext(); ) { //model is my current stock

            item = iterator.next();
            if (getItem == item.getStockName()) {

                itemPrice = item.getStockPrice();
                quant = Integer.valueOf(quantityTxt.getText());
                itemBarcode = item.getStockCode();


                total = quant * itemPrice;

                if (!totalTxt.getText().isEmpty()) {

                    total = total + Float.parseFloat(totalTxt.getText());
                    totalTxt.setText(String.valueOf(total));
                } else {
                    totalTxt.setText(String.valueOf(total));
                }
                addModel.addElement(itemBarcode+"|"+ getItem +"|" + quant+ "|" + itemPrice);
            } else {

            }

        }
        quantityTxt.setText("");

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter("Resources\\BasketData.txt"));

            for (int i=0; i<addedStockLst.getModel().getSize(); i++){
                bw.write(addedStockLst.getModel().getElementAt(i));
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


}

















