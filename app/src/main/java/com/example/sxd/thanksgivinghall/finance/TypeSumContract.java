package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

public class TypeSumContract {
    interface View extends BaseView<Presenter>{
        void showMessage(String message);
        void requestSuccess(FinTypeSumListEntity value);
    }
    interface  Presenter extends BasePresenter<View> {
        void request(String reType);
    }
    interface Model {
        void request(String reType, final  ResultListener<FinTypeSumListEntity> result);
    }
}
