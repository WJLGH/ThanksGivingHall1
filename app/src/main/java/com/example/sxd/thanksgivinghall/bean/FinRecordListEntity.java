package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;
import java.util.List;

public class FinRecordListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data {
        private  String id;
        private  Double amount;
        private  String reType;
        private  String noteDate;
        private  String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getReType() {
            return reType;
        }

        public void setReType(String reType) {
            this.reType = reType;
        }

        public String getNoteDate() {
            return noteDate;
        }

        public void setNoteDate(String noteDate) {
            this.noteDate = noteDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
