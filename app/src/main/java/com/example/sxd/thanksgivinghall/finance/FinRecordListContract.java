package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyListEntity;

import retrofit2.http.Query;

public class FinRecordListContract {
    interface  View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess( FinRecordListEntity value);
    }
    /**
     *  busType
     *  startDate endDate
     *  account  or acName
     *  dateStr
     */
    interface  Presenter extends  BaseView<View> {
        void requestBusTypeList(String busType);
        void requestStartToEndList(String startDate,String endDate);
        void requestAccList(String id,String acName );
        void requestDateList(String dateStr);
        void requestDeleteRecord(String id);
    }
    interface Model{
        void requestBusTypeList(String busType, ResultListener<FinRecordListEntity> result);
        void requestStartToEndList(String startDate,String endDate, ResultListener<FinRecordListEntity> result);
        void requestAccList(String id,String acName, ResultListener<FinRecordListEntity> result);
        void requestDateList(String dateStr, ResultListener<FinRecordListEntity> result);

        void requestDeleteRecord(String id, ResultListener<Base> resultListener);
    }
}
