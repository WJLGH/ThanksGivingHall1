package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.api.service.FinRecordService;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinRecordDetailModelImpl implements FinRecordDetailContract.Model {
    String baseUrl = "";

    public FinRecordDetailModelImpl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String recordId, final ResultListener<FinRecordDetailEntity> result) {
        Call<FinRecordDetailEntity> call = AppMainService.getFinRecordService(baseUrl).detailFinRecord(recordId);
        result.onStart();
        call.enqueue(new Callback<FinRecordDetailEntity>() {
            @Override
            public void onResponse(Call<FinRecordDetailEntity> call, Response<FinRecordDetailEntity> response) {
                //请求成功
                result.onSuccess(response.body());
                //请求结束
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinRecordDetailEntity> call, Throwable t) {
                //请求失败
                result.onFailure(t.getMessage());
                //请求结束
                result.onEnd();

            }
        });
    }
}
