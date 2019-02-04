
package com.example.sxd.thanksgivinghall.bean;
import java.util.List;

public class FinDateSumListEntity {

    private String success;
    private String statusMessage;
    private Data mainData;
    private List<Data> data;
    public static class Data {

        private Double outAmount;
        private String amount;
        private String dateStr;
        private String month;
        private String year;
        private Double inAmount;
        private String day;
        public void setOutAmount(Double outAmount) {
            this.outAmount = outAmount;
        }
        public double getOutAmount() {
            return outAmount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
        public String getAmount() {
            return amount;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }
        public String getDateStr() {
            return dateStr;
        }

        public void setMonth(String month) {
            this.month = month;
        }
        public String getMonth() {
            return month;
        }

        public void setYear(String year) {
            this.year = year;
        }
        public String getYear() {
            return year;
        }

        public void setInAmount(Double inAmount) {
            this.inAmount = inAmount;
        }
        public Double getInAmount() {
            return inAmount;
        }

        public void setDay(String day) {
            this.day = day;
        }
        public String getDay() {
            return day;
        }

    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getSuccess() {
        return success;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setMainData(Data mainData) {
        this.mainData = mainData;
    }
    public Data getMainData() {
        return mainData;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

}