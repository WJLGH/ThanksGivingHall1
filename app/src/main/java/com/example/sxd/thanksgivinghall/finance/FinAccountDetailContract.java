package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;

public interface FinAccountDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(FinAccountDetailEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String AccountId);
    }

    public interface Model {
        public void request(String AccountId, ResultListener<FinAccountDetailEntity> result);

    }
}
