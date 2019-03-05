package com.example.sxd.thanksgivinghall.bean;

import java.util.List;

public class FinAccountListEntity {
    private String success;
    private String statusMessage;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private List<Data> data;

    public class Data{
        private String id;
        private String acName;
        private String acType;
        public String getAcType() {
            return acType;
        }

        public void setAcType(String acType) {
            this.acType = acType;
        }


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
}
