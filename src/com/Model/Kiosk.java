package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Kiosk extends JFrame {
    private JPanel KioskMain;
    private JButton btnLogin;
    private JList CurrentStockLst;
    private JList AddedStockLst;
    private JTextField textField1;
    private JLabel TotalLbl;
    private JButton Cash;
    private JButton ADDITEMButton;
    private JTextField quantityTxt;
    private JButton cardBtn;

    private String[] values = new String[0];
    private ArrayList<Stock> stockData = new ArrayList<Stock>();
    private List<Stock> det;
    private File fileIn = new File("Resources\\Stock");


    public Kiosk(String title) throws IOException {
        super(title);
        setContentPane(KioskMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));
        this.setVisible(true);
        pack();
        loadData();


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminLogon adminLogon = new AdminLogon("test");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Kiosk.this.setVisible(false);
            }
        });
    }

    ;

    public void loadData() throws IOException {


        //Scanner fileRead = new Scanner(fileIn);
        List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));
        det = new ArrayList<>();


        //int i = 0;

        //String linesFile = line.toString();
        DefaultListModel<String> model = new DefaultListModel();


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

            det.add(new Stock(itemName, itemCode, itemQuant, itemPrice));
            model.addElement(values[0]);
            CurrentStockLst.setModel(model);

        }
    }

    public void addToBasket(){

    }
}
