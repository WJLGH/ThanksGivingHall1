package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

public class FinAccSumListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public static class Data {
        String id;
        String acName;
        Double amount;
        Double inAmount;
        Double outAmount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAcName() {
            return acName;
        }

        public void setAcName(String acName) {
            this.acName = acName;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getInAmount() {
            return inAmount;
        }

        public void setInAmount(Double inAmount) {
            this.inAmount = inAmount;
        }

        public Double getOutAmount() {
            return outAmount;
        }

        public void setOutAmount(Double outAmount) {
            this.outAmount = outAmount;
        }
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
