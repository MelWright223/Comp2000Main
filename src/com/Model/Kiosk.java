package com.Model;

import com.sun.source.tree.WhileLoopTree;

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

public class Kiosk extends JFrame {
    private JPanel KioskMain;
    private JButton btnLogin;
    private JList currentStockLst;
    private JList addedStockLst;
    private JTextField totalTxt;
    private JLabel totalLbl;
    private JButton cashBtn;
    private JButton addItemBtn;
    private JTextField quantityTxt;
    private JButton cardBtn;
    private JLabel quantityLbl;

    private String[] values = new String[0];
    private ArrayList<Stock> stockData = new ArrayList<Stock>();
    private DefaultListModel<String> model = new DefaultListModel();
    DefaultListModel<String> addModel = new DefaultListModel<>();
    private File fileIn = new File("Resources\\Stock");


    public Kiosk(String title) throws IOException {
        super(title);
        setContentPane(KioskMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));
        this.setVisible(true);
        pack();
        loadAllStock();


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Kiosk.this.dispose();
                    AdminLogon adminLogon = new AdminLogon("test");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        addItemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = currentStockLst.getSelectedIndex();
                String getItem = currentStockLst.getSelectedValue().toString();

                addModel.addElement(quantityTxt.getText() + " " + getItem);

                if (index != -1) {
                    model.get(index);
                    addedStockLst.setModel(addModel);
                    addToBasket();
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
        String getItem = currentStockLst.getSelectedValue().toString();

        Stock item = new Stock();
        Iterator<Stock> iterator = stockData.iterator();
        float itemPrice;
        float total;
        int quant;
        float price;


        for (model.elements().asIterator(); iterator.hasNext(); ) { //model is my other listbox

            item = iterator.next();
            if (getItem == item.stockName) {

                itemPrice = item.getStockPrice();
                quant = Integer.valueOf(quantityTxt.getText());

                total = quant * itemPrice;

                if (!totalTxt.getText().isEmpty()) {

                    total = total + Float.parseFloat(totalTxt.getText());
                    totalTxt.setText(String.valueOf(total));
                } else {
                    totalTxt.setText("£" + String.valueOf(total));
                }
                addModel.addElement("£" + itemPrice);
            } else {
                System.out.println("No");
            }
        }

        quantityTxt.setText("");

    }

    public void Cash() {




    }
}











