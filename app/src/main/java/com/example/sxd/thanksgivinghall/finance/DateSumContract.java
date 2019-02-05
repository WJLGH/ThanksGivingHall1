package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

public interface DateSumContract  {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(FinDateSumListEntity value);
    }
    interface  Presenter extends BasePresenter<View> {
        void request(String dateStr);
    }
    interface Model {
        void request(String dateStr, ResultListener<FinDateSumListEntity> result);
    }

}
