package com.example.sxd.thanksgivinghall.bean;

public class FinAccount {

    private String success;

    private String statusMessage;
    private Data data;

    public class Data {
      private  String id;
      private  String aType;
      private  String cardNum;
      private  String name;
      private  Double amount;
      private  String monType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getaType() {
            return aType;
        }

        public void setaType(String aType) {
            this.aType = aType;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getMonType() {
            return monType;
        }

        public void setMonType(String monType) {
            this.monType = monType;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", aType='" + aType + '\'' +
                    ", cardNum='" + cardNum + '\'' +
                    ", name='" + name + '\'' +
                    ", amount=" + amount +
                    ", monType='" + monType + '\'' +
                    '}';
        }
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
