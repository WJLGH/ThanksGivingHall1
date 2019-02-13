package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.FinAccSumListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AccSumModelImpl implements AccSumContract.Model {
    String baseUrl;
    public AccSumModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void request(String id, final ResultListener<FinAccSumListEntity> result) {
        Call<FinAccSumListEntity> call = AppMainService.getFinAccountService(baseUrl).listSumAccount(id);
        result.onStart();
        call.enqueue(new Callback<FinAccSumListEntity>() {
            @Override
            public void onResponse(Call<FinAccSumListEntity> call, Response<FinAccSumListEntity> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinAccSumListEntity> call, Throwable t) {
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });
    }
}
