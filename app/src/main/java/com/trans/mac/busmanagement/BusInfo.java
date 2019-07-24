package com.trans.mac.busmanagement;

public class BusInfo {
    public String from;
    public String to;
    public String time;
    public String price;
    public String date;
    public String month;
    public String year;

    public BusInfo(String from,String to,String time,String price,String date,String month,String year) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.price = price;
        this.date = date;
        this.month = month;
        this.year = year;
    }
}
