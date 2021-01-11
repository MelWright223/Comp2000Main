package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AdminLogon extends Kiosk {
    private JPanel AdminMain;
    private JButton btnLogin;
    private JButton btnBack;
    private JTextField txtUser;
    private JTextField txtPass;

    public AdminLogon(String title) throws IOException {
        super(title);
        setContentPane(AdminMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        this.setVisible(true);
        pack();

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Kiosk kiosk = new Kiosk("Home");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                AdminLogon.this.setVisible(false);
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                login();
            }
        });

    }

    public boolean login(){
        boolean isAuthenticated = false;


        String Username = txtUser.getText();
        String Password = txtPass.getText();

        File file = new File("Resources\\FileAdmin");
        try {
            Scanner inputBuffer = new Scanner(file);

            while(inputBuffer.hasNext()){
                String line = inputBuffer.nextLine();
                String[] values = line.split("\\|");

                if (values[0].equals(Username) && values[1].equals(Password)){
                        isAuthenticated = true;
                        StockAdmin stock = new StockAdmin("Stock");
                        AdminLogon.this.setVisible(false);


                        break;

                }
            }


        } catch (FileNotFoundException fe){
            fe.getMessage();

            txtUser.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }
}
