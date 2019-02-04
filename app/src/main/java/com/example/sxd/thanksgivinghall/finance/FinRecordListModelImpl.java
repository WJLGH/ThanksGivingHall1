package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinRecordListModelImpl implements FinRecordListContract.Model {
    private String baseUrl;

    public FinRecordListModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    @Override
    public void request(String startDate, String endDate, final ResultListener<FinRecordListEntity> result) {
        Call<FinRecordListEntity> call = AppMainService.getFinRecordService(baseUrl).listFinRecord(startDate,endDate);
        result.onStart();
        call.enqueue(new Callback<FinRecordListEntity>() {
            @Override
            public void onResponse(Call<FinRecordListEntity> call, Response<FinRecordListEntity> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }
            @Override
            public void onFailure(Call<FinRecordListEntity> call, Throwable t) {
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });
    }
}
