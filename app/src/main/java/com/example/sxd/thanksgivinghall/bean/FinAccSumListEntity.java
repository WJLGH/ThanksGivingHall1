package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

public class FinAccSumListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public static class Data {
        String id;
        String acName;
        String amount;
        String inAmount;
        String outAmount;

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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInAmount() {
            return inAmount;
        }

        public void setInAmount(String inAmount) {
            this.inAmount = inAmount;
        }

        public String getOutAmount() {
            return outAmount;
        }

        public void setOutAmount(String outAmount) {
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
