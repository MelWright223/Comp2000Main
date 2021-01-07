package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public StockAdmin(String title) {
        super(title);
        setContentPane(StockPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        this.setVisible(true);
        pack();

        AddStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }

        });
    }

    public void loadData() throws FileNotFoundException {
        File fileIn = new File("Resources\\FileStock");
        Scanner fileRead = new Scanner(fileIn);

        ArrayList<Stock> stockData = new ArrayList<>();


        while (fileRead.hasNext()) {
            String line = fileRead.nextLine();
            String[] values = line.split(",");
            Stock item = new Stock();

            int itemCode = Integer.parseInt(values[0]);
            item.setStockCode(itemCode);

            item.setStockName(values[1]);

            int itemQuant = Integer.parseInt(values[2]);
            item.setStockQuantity(itemQuant);

            double itemPrice = Double.parseDouble(values[3]);
            item.setStockPrice(itemPrice);

            stockData.add(item);

        }

        for(int i =0; i<stockData.size(); i++){
            DefaultListModel model = new DefaultListModel();
            StockList = new JList(model);
            model.add(i, stockData.get(1));




        }

    }




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
