package com.example.sxd.thanksgivinghall.api.service;

import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordListEntity;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;
import com.example.sxd.thanksgivinghall.bean.FinTypeSumListEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface FinRecordService {

    /**
     * 新增明细记录
     * @param Body (json 序列化对象）
     * @return
     */
    @POST("infc/infcFinRecord/addFinRecord")
    Call<Base> addFinRecord(@Body RequestBody Body);

    /**
     * 获取 从开始时间到结束时间的明细信息列表
     * @param startDate
     * @param endDate
     * @return
     */
    @GET("infc/infcFinRecord/listFinRecord")
    Call<FinRecordListEntity> listFinRecord(@Query("startDate")String startDate,@Query("endDate")String endDate);

    /**
     * 根据ID获取特定的明细记录详情
     * @param recordId
     * @return
     */
    @GET("infc/infcFinRecord/detailFinRecord")
    Call<FinRecordDetailEntity> detailFinRecord(@Query("id")String recordId);

    /**
     * 根据记录类型获取明细汇总信息列表
     * @param reType
     * @return
     */
    @GET("infc/infcFinRecord/listSumBusType")
    Call<FinTypeSumListEntity> sumFinRecordByReType(@Query("reType") String reType);
    /**
     * 根据时间字符串获取明细汇总信息列表
     * @param dateStr
     * @return
     */
    @GET("infc/infcFinRecord/listSumDate")
    Call<FinDateSumListEntity> sumFinRecordByDate(@Query("dateStr") String dateStr);




}
