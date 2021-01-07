package com.Model;

public class Stock {

    public int stockCode;


    public String stockName;

    public int stockQuantity;
    public double stockPrice;



    public int getStockCode(){
        return stockCode;
    }

    public String GetStockName(){
        return stockName;

    }
    public int getStockQuantity(){
        return stockQuantity;

    }
    public double getStockPrice(){
        return stockPrice;
    }

    public void setStockCode(int stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }
}

