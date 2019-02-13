package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import java.util.List;

public interface AccSumContract  {
    interface View extends BaseView<Presenter> {
        void showMessage(String msg);
        void requestSuccess(List<FinAccSumListEntity.Data> data);
    }
    interface  Presenter extends BasePresenter<View> {
        void request(String id );
    }
    interface  Model {
        void request(String id , ResultListener<FinAccSumListEntity> result);
    }
}
