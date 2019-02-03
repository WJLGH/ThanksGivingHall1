package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.bean.ToDoNotifyDetailEntity;
import com.example.sxd.thanksgivinghall.notice.NoticeDetailContract;

public interface FinRecordDetailContract {
    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(FinRecordDetailEntity value);

    }

    interface Presenter extends BasePresenter<View> {
        void request(String recordId);

    }

    public interface Model {
        public void request(String recordId, ResultListener<FinRecordDetailEntity> result);

    }
}
