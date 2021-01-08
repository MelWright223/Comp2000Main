package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class StockAdmin extends AdminLogon {
    private JPanel StockPanel;
    private JButton AddStockBtn;
    private JButton EditStockBtn;
    private JButton DeleteStockBtn;
    private JTextField StockIdTxt;
    private JTextField StockNameTxt;
    private JTextField StockPriceTxt;
    private JTextField StockQuantTxt;
    private JLabel StockIDLbl;
    private JLabel StockNameLbl;
    private JLabel StockQuantLbl;
    private JLabel StockPriceLbl;
    private JList StockList;

    public StockAdmin(String title) throws FileNotFoundException {
        super(title);
        setContentPane(StockPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        this.setVisible(true);
        pack();
        loadData();
        AddStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadData();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }




        });
    }

    public void loadData() throws FileNotFoundException {
        File fileIn = new File("Resources\\FileStock");
        Scanner fileRead = new Scanner(fileIn);

        ArrayList<Stock> stockData = new ArrayList<Stock>();

        String[] values = new String[0];
        int i = 0;
        DefaultListModel<String> model = new DefaultListModel();


        while (fileRead.hasNext()) {

            String line = fileRead.nextLine();
            values = line.split(",");
            Stock item = new Stock();


            int itemCode = Integer.parseInt(values[0]);
            item.setStockCode(itemCode);

            String itemName = String.valueOf(values[1]);
            item.setStockName(itemName);

            int itemQuant = Integer.parseInt(values[2]);
            item.setStockQuantity(itemQuant);

            double itemPrice = Double.parseDouble(values[3]);
            item.setStockPrice(itemPrice);
            for (int s = 0; s<stockData.size(); s++){
                Stock tempItem = new Stock(itemCode, itemName, itemQuant, itemPrice);


                stockData.add(tempItem);

            }
            model.addElement(values[1]);
            StockList.setModel(model);








        }
        fileRead.close();
            //for (Stock items : stockData) {






            //}
            //i++;
        }

        //for(int i =0; i<stockData.size(); i++) {

            //}










    public void addStock()throws IOException {
        FileWriter file = new FileWriter("Resources\\FileStock");
        /*try {
            BufferedWriter outputBuffer = new BufferedWriter(file);
            outputBuffer.append("\n" + StockIdTxt.getText() + "," + StockNameTxt.getText() + "," + StockQuantTxt.getText() + "," + StockPriceTxt.getText());


        } catch (
                FileNotFoundException fe) {
            fe.getMessage();


        }*/
    }
}
