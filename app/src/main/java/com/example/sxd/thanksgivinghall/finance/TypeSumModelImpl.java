package com.example.sxd.thanksgivinghall.finance;

import com.example.sxd.thanksgivinghall.api.AppMainService;
import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class TypeSumModelImpl implements TypeSumContract.Model {
    private String baseUrl;
    public TypeSumModelImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void request(String reType,final ResultListener<FinTypeSumListEntity> result) {
        Call<FinTypeSumListEntity> call = AppMainService.getFinRecordService(baseUrl).sumFinRecordByReType(reType);
        result.onStart();
        call.enqueue(new Callback<FinTypeSumListEntity>() {
            @Override
            public void onResponse(Call<FinTypeSumListEntity> call, Response<FinTypeSumListEntity> response) {
                result.onSuccess(response.body());
                result.onEnd();
            }

            @Override
            public void onFailure(Call<FinTypeSumListEntity> call, Throwable t) {
                result.onFailure(t.getMessage());
                result.onEnd();
            }
        });

    }
}
