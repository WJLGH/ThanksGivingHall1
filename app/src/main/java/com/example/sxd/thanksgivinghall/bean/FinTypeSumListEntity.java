package com.example.sxd.thanksgivinghall.bean;


import java.util.List;


public class FinTypeSumListEntity {

    private String success;
    private String statusMessage;
    private Data mainData;
    private List<Data> data;
    public static class Data {

        private String reType;
        private Double amount;
        private String busType;
        public void setReType(String reType) {
            this.reType = reType;
        }
        public String getReType() {
            return reType;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
        public Double getAmount() {
            return amount;
        }

        public void setBusType(String busType) {
            this.busType = busType;
        }
        public String getBusType() {
            return busType;
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