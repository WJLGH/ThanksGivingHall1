
package com.example.sxd.thanksgivinghall.bean;
import java.util.List;

public class FinDateSumListEntity {

    private String success;
    private String statusMessage;
    private Data mainData;
    private List<Data> data;
    public  class Data {

        private Double inAmount;
        private Double outAmount;
        private Double amount;
        private String dateStr;
        private String month;
        private String year;
        private String day;

        /**
         *  总的时间信息 获得的获得列表的时间信息
         * @return
         * @param mainDate
         */
        public String getTime(String mainDate) {
            if(mainDate == null) {
                return dateStr;
            }
            if(mainDate.equals(year)) {
                return month;
            }
            if(mainDate.equals(month)) {
                return day;
            }
            return dateStr;
        }
        public void setOutAmount(Double outAmount) {
            this.outAmount = outAmount;
        }
        public double getOutAmount() {
            return outAmount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
        public Double getAmount() {
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