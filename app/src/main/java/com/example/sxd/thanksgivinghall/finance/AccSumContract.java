package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

public interface AccSumContract  {
    interface View extends BaseView<Presenter> {
        void showMessage(String msg);
        void requestSuccess(FinAccSumListEntity value);
    }
    interface  Presenter extends BasePresenter<View> {
        void request(String id ,String deptShortName);
    }
    interface  Model {
        void request(String id ,String dept, ResultListener<FinAccSumListEntity> result);
    }
}
