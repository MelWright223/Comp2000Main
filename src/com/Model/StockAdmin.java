package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StockAdmin extends AbstractView {
    private JPanel StockPanel;
    private JButton AddStockBtn;
    private JButton DeleteStockBtn;
    private JButton EditStockBtn;
    private JTextField StockIdTxt;
    private JTextField StockNameTxt;
    private JTextField StockPriceTxt;
    private JTextField StockQuantTxt;
    private JLabel StockIDLbl;
    private JLabel StockNameLbl;
    private JLabel StockQuantLbl;
    private JLabel StockPriceLbl;
    private JList StockList;
    private JButton ClearStock;
    private JList ReorderLSt;

    private String[] values = new String[0];
    private ArrayList<Stock> stockData = new ArrayList<Stock>();


   private   File fileIn = new File("Resources\\Stock");

    public StockAdmin() throws IOException {

        setContentPane(StockPanel);
        displayJPanel();
        loadData();


        AddStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addStock();
                    StockAdmin.this.dispose();
                    StockAdmin stockAdmin = new StockAdmin();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }




        });
        DeleteStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStock("Resources\\Stock", String.valueOf(StockList.getSelectedValue()), 1);
                StockAdmin.this.dispose();
                try {
                    StockAdmin stockAdmin = new StockAdmin();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });

        EditStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStock("Resources\\Stock", String.valueOf(StockList.getSelectedValue()),1, String.valueOf(StockNameTxt.getText()),
                        Integer.valueOf(StockIdTxt.getText()), Integer.valueOf(StockQuantTxt.getText()), Float.valueOf(StockPriceTxt.getText()));

                StockAdmin.this.dispose();
                try {
                    StockAdmin stockAdmin = new StockAdmin();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        ClearStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    clearItems();

            }
        });
    }

    public void loadData() throws IOException {
        //Scanner fileRead = new Scanner(fileIn);
        List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));

        //int i = 0;

        //String linesFile = line.toString();
        DefaultListModel<String> model = new DefaultListModel();
        DefaultListModel<String> reorder = new DefaultListModel<>();


        for (String lines:line) {

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
            StockList.setModel(model);


            StockList.addListSelectionListener(e -> {

                String selected = (String.valueOf(StockList.getSelectedValue()));
                StockNameTxt.setText(selected);


                if (selected.equals(itemName)) {

                    StockIdTxt.setText(String.valueOf(itemCode));
                    StockPriceTxt.setText(String.valueOf(itemPrice));
                    StockQuantTxt.setText(String.valueOf(itemQuant));

                }

            });
            if (itemQuant < 5) {
                reorder.addElement(itemName);
                ReorderLSt.setModel(reorder);

            }




        }




    }

    public void clearItems(){

        StockIdTxt.setText("");
        StockNameTxt.setText("");
        StockQuantTxt.setText("");
        StockPriceTxt.setText("");
    }


    public void addStock()throws IOException {


        //  List<String> inputString = Files.readAllLines(Path.of(String.valueOf(fileIn)));

        var sw = new BufferedWriter(new FileWriter(fileIn, true));
        int i =0;
        if (i < stockData.size()){
            String data = "";
            if (i < 0) {
                data += "\n";

            }
            data += StockNameTxt.getText();
            String itemCode = StockIdTxt.getText();
            data += "|" + itemCode;
            String itemQuant = StockQuantTxt.getText();
            data += "|" + itemQuant;
            String itemPrice = StockPriceTxt.getText();
            data += "|" + itemPrice;
            System.out.println(data);
           // sw.append("\n");
            sw.append(data);

        }

        sw.close();

    }

    public void deleteStock(String filePath, String removeItem, int positionOfItem){
        int position = positionOfItem -1;
        String tempFile = "Resources\\PLOP";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        String currentLine;

        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine())!=null){
                String[] data = currentLine.split("\\|");
                if (!(data[position].equalsIgnoreCase(removeItem))){
                    pw.println(currentLine);
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
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void editStock(String filePath, String editTerm, int positionOfTerm, String newName, int newCode, int newQuan, float newPrice) {
        int position = positionOfTerm-1;
        String tempFile = "Resources\\Dump";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String name = "";


        String currentLine;

        try{
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);


            while((currentLine = br.readLine()) != null) {


                String[] data = currentLine.split("\\|");

                if(!(data[position].equals(editTerm))){
                    pw.println(currentLine);
                } else {
                    pw.println(newName + "|" + newCode + "|" + newQuan + "|" + newPrice);
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
        catch (Exception ignored){ }

    }

}