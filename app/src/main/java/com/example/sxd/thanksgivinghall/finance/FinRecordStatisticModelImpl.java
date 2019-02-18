package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class FinRecordStatisticModelImpl implements FinRecordStatisticContract.Model {
    private String baseUrl;

    public FinRecordStatisticModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void request(String dateStr, final ResultListener<FinDateSumListEntity> result) {
        Call<FinDateSumListEntity> call = AppMainService.getFinRecordService(baseUrl).sumFinRecordByDate(dateStr);
        result.onStart();

        call.enqueue(new Callback<FinDateSumListEntity>() {
            @Override
            public void onResponse(Call<FinDateSumListEntity> call, Response<FinDateSumListEntity> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinDateSumListEntity> call, Throwable t) {
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });
    }
}
