package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinRecordListModelImpl implements FinRecordListContract.Model {
    private String baseUrl;


    class MyCallBack implements  Callback<FinRecordListEntity> {
        private  ResultListener<FinRecordListEntity> resultTmp;
        MyCallBack( ResultListener<FinRecordListEntity> resultTmp) { this.resultTmp = resultTmp;}

        @Override
        public void onResponse(Call<FinRecordListEntity> call, Response<FinRecordListEntity> response) {
            resultTmp.onSuccess(response.body());
            resultTmp.onEnd();
        }
        @Override
        public void onFailure(Call<FinRecordListEntity> call, Throwable t) {
            resultTmp.onFailure(t.getMessage());
            resultTmp.onEnd();
        }
    }

    public FinRecordListModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void requestBusTypeList(String busType, ResultListener<FinRecordListEntity> result) {
        Call<FinRecordListEntity> call = AppMainService.getFinRecordService(baseUrl).requestBusTypeList(busType);
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }

    @Override
    public void requestStartToEndList(String startDate, String endDate, final ResultListener<FinRecordListEntity> result) {
        Call<FinRecordListEntity> call = AppMainService.getFinRecordService(baseUrl).requestStartToEndList(startDate,endDate);
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }

    @Override
    public void requestAccList(String id, String acName, ResultListener<FinRecordListEntity> result) {
        Call<FinRecordListEntity> call = AppMainService.getFinRecordService(baseUrl).requestAccList(id,acName);
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }

    @Override
    public void requestDateList(String dateStr, ResultListener<FinRecordListEntity> result) {
        Call<FinRecordListEntity> call = AppMainService.getFinRecordService(baseUrl).requestDateList(dateStr);
        result.onStart();
        call.enqueue(new MyCallBack(result));
    }

    @Override
    public void requestDeleteRecord(String id, final ResultListener<Base> result) {
        Call<Base> call = AppMainService.getFinRecordService(baseUrl).requestDeleteRecord(id);
        result.onStart();
        call.enqueue(new Callback<Base>() {
            @Override
            public void onResponse(Call<Base> call, Response<Base> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<Base> call, Throwable t) {
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });
    }
}
