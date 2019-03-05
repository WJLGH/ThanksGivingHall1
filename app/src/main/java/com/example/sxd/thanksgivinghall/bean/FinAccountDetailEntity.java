package com.example.sxd.thanksgivinghall.bean;

public class FinAccountDetailEntity {

    private String success;
    private String statusMessage;
    private Data data;
    public static class Data{
        private  String id;
        private  String acType;
        private  String cardNum;
        private  String acName;
        private  Double amount;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAcType() {
            return acType;
        }

        public void setAcType(String acType) {
            this.acType = acType;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
