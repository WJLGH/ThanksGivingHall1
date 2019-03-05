package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.github.mikephil.charting.data.BaseEntry;


public class FinAccountListContract {
    interface  View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(FinAccountListEntity value);
    }
    interface  Presenter extends  BaseView<View> {
        void requestAccountList();
        void deleteById(String id);
    }
    interface Model{
        void requestAccountList(ResultListener<FinAccountListEntity> result);
        void deleteById(String id, ResultListener<FinAccountListEntity> result);
    }
}
