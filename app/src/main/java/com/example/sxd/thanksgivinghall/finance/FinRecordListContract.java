package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

import retrofit2.http.Query;

public class FinRecordListContract {
    interface  View extends BaseView<Presenter> {
        void showMessage(String message);

        void requestSuccess( FinRecordListEntity value);
    }
    interface  Presenter extends  BaseView<View> {
        void request(String startDate,String endDate);
    }
    interface Model{
        void request(String startDate,String endDate, ResultListener<FinRecordListEntity> result);
    }
}
