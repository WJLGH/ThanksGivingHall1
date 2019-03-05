package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

import okhttp3.RequestBody;

public interface FinAccountAddContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(Base value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(FinAccountDetailEntity.Data entity);
    }

    public interface Model {
        public void request(RequestBody body, ResultListener<Base> result);
    }
}
