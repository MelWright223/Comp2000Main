package com.Controller;

import com.Model.AbstractView;
import com.Model.Kiosk;
import com.Model.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import  java.util.List;
import java.util.concurrent.BlockingQueue;


public class Cash extends AbstractView {
    private JPanel cashPanel;
    private JList itemsSelectedLst;
    private JLabel finalTotalLbl;
    private JLabel basketLbl;
    private JTextField finalTotalTxt;
    private JTextField cashGivenTxt;
    private JButton returnBtn;
    private JButton payBtn;
    private JButton printRecBtn;
    private JTextField leftToPayTxt;
    private JLabel amountLeftLbl;
    private JLabel amountEnteredLbl;
    private BlockingQueue<Kiosk> messages;
    private DefaultListModel<String> payModel = new DefaultListModel<>();
    private String[] values = new String[0];
    private String[] stockData = new String[0];
    private File fileIn = new File("Resources\\BasketData.txt");
    private File stockFile = new File("Resources\\Stock");
    public float total = Kiosk.total;
    public int quant = Kiosk.quant;
    Float amountGiven;


    public Cash() throws IOException, InterruptedException {
        setContentPane(cashPanel);
        displayJPanel();
        payment();

        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeGiven();
            }
        });

        printRecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptPrint();
            }
        });

    }


    public void payment() throws IOException, InterruptedException {
        //Scanner fileRead = new Scanner(fileIn);
        printRecBtn.setEnabled(false);
        List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));

        String linesFile = line.toString();


        for (String lines : line) {

            values = lines.split("\\|");
            int itemBarcode = Integer.parseInt(values[1]);
            String itemName = values[0];
            int itemQuant = Integer.parseInt(values[2]);
            float itemPrice = Float.parseFloat(values[3]);
            payModel.addElement(values[0] + "," + values[1] + "," + values[2]+ ",£" + values[3]);
            itemsSelectedLst.setModel(payModel);
            //stockData.add(new Stock(itemName, itemBarcode, itemQuant, itemPrice));
            quant = itemQuant;

        }

        finalTotalTxt.setText(String.valueOf(total));

    }

    public void changeGiven() {

        amountGiven = Float.parseFloat(cashGivenTxt.getText());


        if (amountGiven < total) {
            leftToPayTxt.setText(String.valueOf(total - amountGiven));
            finalTotalTxt.setText(String.valueOf(total - amountGiven));
            amountLeftLbl.setText("Please provide sufficient funds");
            amountLeftLbl.setForeground(Color.RED);
            cashGivenTxt.setText(" ");
            total = Float.valueOf(finalTotalTxt.getText());


//            if (amountGiven > total){
//                amountLeftLbl.setText("Change:");
//                leftToPayTxt.setText(String.valueOf(amountGiven-total));
//            }
        } else {
            amountLeftLbl.setText("Change:");
            amountLeftLbl.setForeground(Color.green);
            finalTotalTxt.setText("0.00");
            leftToPayTxt.setText(String.valueOf(amountGiven - total));
            payBtn.setEnabled(false);
            try {
                List<String> line = Files.readAllLines(Path.of(String.valueOf(fileIn)));

                for (String lines : line) {
                    values = lines.split("\\|");
                    reduceQuantity("Resources\\Stock");

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            printRecBtn.setEnabled(true);


        }


    }

    public void receiptPrint() {
        JOptionPane.showMessageDialog(null, "SHOPPING SPREES" + System.lineSeparator() + "Sprees that last a week" + System.lineSeparator() +
                "13/01/2022" + System.lineSeparator() + "Items bought: " + payModel
                + System.lineSeparator() + "Total Price: " + total + System.lineSeparator() + "Amount given:" + amountGiven + System.lineSeparator() +
                "Change given: " + leftToPayTxt.getText(), "Receipt", JOptionPane.DEFAULT_OPTION);




    }

    public void reduceQuantity(String filePath) throws IOException {
        printRecBtn.setEnabled(false);
        int position = 0;
        String tempFile = "Resources\\Dump";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);
        String name = "";

        try {
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
        catch (Exception ignore){

        }

    }

}













