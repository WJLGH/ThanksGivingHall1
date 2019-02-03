package com.example.sxd.thanksgivinghall.bean;

import java.util.Date;
import java.util.List;

public class FinRecordListEntity {
    private String success;
    private String statusMessage;
    private List<Data> data;
    public class Data {
        private  String id;
        private  String rType;
        private Date createDate;
    }
}
