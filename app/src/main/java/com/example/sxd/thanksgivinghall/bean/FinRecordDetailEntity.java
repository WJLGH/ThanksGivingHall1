package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;

public class FinRecordDetailEntity {

    private String success;
    private String statusMessage;
    private Data data;
    public static class Data{
        private  String id;
        private String dept;
        private  String reType;
        private  String busType;
        private  Double amount;
        private  String description;
        private  String inId;
        private  String outId;
        private String noteDate;//时间字符串
        private String dateStr;

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }
        public String getDept() {
            return dept;
        }

        public void setDept(String dept) {
            this.dept = dept;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReType() {
            return reType;
        }

        public void setReType(String reType) {
            this.reType = reType;
        }

        public String getBusType() {
            return busType;
        }

        public void setBusType(String busType) {
            this.busType = busType;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInId() {
            return inId;
        }

        public void setInId(String inId) {
            this.inId = inId;
        }

        public String getOutId() {
            return outId;
        }

        public void setOutId(String outId) {
            this.outId = outId;
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
