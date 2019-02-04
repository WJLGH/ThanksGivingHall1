package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinAccountService {

    @GET("infc/infcFinAccount/requestStartToEndList")
    Call<FinRecordListEntity> listFinRecord(@Query("id") String id ,@Query("acName") String acName);


}
