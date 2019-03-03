package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenter;
import com.example.sxd.thanksgivinghall.base.BaseView;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountListEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.bean.UpLoadFile;
import com.example.sxd.thanksgivinghall.notice.NoticeAddContract;

import okhttp3.RequestBody;

public interface FinRecordAddContract  {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void requestSuccess(Base value);
        void fillInOutAcSpinner(FinAccountListEntity finAccountListEntity,String type);
        void fillDefault(FinRecordDetailEntity value);
    }

    interface Presenter extends BasePresenter<View> {
        void request(FinRecordDetailEntity.Data entity);
        void requestAcList(String type);
        void requestDefault(String recordId);
    }

    interface Model {
         void request(RequestBody body,ResultListener<Base> result);
         void requestAcList(ResultListener<FinAccountListEntity> result);
         void request(String recordId, ResultListener<FinRecordDetailEntity> result);
    }
}
