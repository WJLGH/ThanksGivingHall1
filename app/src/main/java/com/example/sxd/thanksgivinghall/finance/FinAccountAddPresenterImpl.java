package com.example.sxd.thanksgivinghall.finance;

import android.content.Context;

import com.example.sxd.thanksgivinghall.api.ResultListener;
import com.example.sxd.thanksgivinghall.base.BasePresenterImpl;
import com.example.sxd.thanksgivinghall.bean.Base;
import com.example.sxd.thanksgivinghall.bean.FinAccountDetailEntity;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;
import com.example.sxd.thanksgivinghall.utils.StringUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FinAccountAddPresenterImpl extends BasePresenterImpl implements FinAccountAddContract.Presenter {
    private FinAccountAddContract.View mView;
    private  FinAccountAddContract.Model mModel;
    private Context context;

    public FinAccountAddPresenterImpl(Context context , FinAccountAddContract.View view) {
        this.mView = view;
        this.context = context;
        mModel = new FinAccountAddModelImpl(getBaseUrl(context));
    }

    @Override
    public void request(FinAccountDetailEntity.Data entity) {
        //先没有做验证
//        if (!checkParams(entity)) return;

        Map<String ,Object> params = new LinkedHashMap<>();
        params.put("acType",entity.getAcType());
        params.put("cardNum",entity.getCardNum());
        params.put("acName",entity.getAcName());
        params.put("amount",entity.getAmount());

        JSONObject jsonObj = new JSONObject(params);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
        this.mModel.request(requestBody, new ResultListener<Base>() {
            @Override
            public void onEnd() {

            }

            @Override
            public void onFailure(String paramString) {
                mView.showMessage("访问服务器异常");
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Base data) {
                if(null == data) {
                    mView.showMessage("数据获取失败");
                    return;
                }
                if(!"ok".equals(data.getStatusMessage())) {
                    mView.showMessage(data.getStatusMessage());
                    return;
                }
                //数据获取成功
                mView.requestSuccess(data);
            }
        });
    }

//    private boolean checkParams(FinAccountDetailEntity.Data entity) {
//        if(null == entity) {
//            this.mView.showMessage("请填写明细信息");
//            return false;
//        }
//        if(!StringUtils.notIsBlankAndEmpty(entity.getReType())) {
//            this.mView.showMessage("明细类型不能为空");
//            return false;
//        }
//        if(!StringUtils.notIsBlankAndEmpty(entity.getBusType())) {
//            this.mView.showMessage("交易类型不能为空");
//            return false;
//        }
//        if(!StringUtils.notIsBlankAndEmpty(entity.getDescription())) {
//            this.mView.showMessage("摘要不能为空");
//            return false;
//        }
//        if(!StringUtils.notIsBlankAndEmpty(entity.getAmount().toString())) {
//            this.mView.showMessage("金额不能为空");
//            return false;
//        }
//        if(!StringUtils.notIsBlankAndEmpty(entity.getNoteDate())) {
//            this.mView.showMessage("日期不能为空");
//            return false;
//        }
//        if(entity.getReType().equals("支出")) {
//            if(!StringUtils.notIsBlankAndEmpty(entity.getOutId())) {
//                this.mView.showMessage("出账账户不能为空");
//                return false;
//            }
//        } else if(entity.getReType().equals("收入")) {
//            if(!StringUtils.notIsBlankAndEmpty(entity.getInId())) {
//                this.mView.showMessage("入账账户不能为空");
//                return false;
//            }
//        } else if(entity.getReType().equals("转账")){
//            if(!StringUtils.notIsBlankAndEmpty(entity.getOutId()) || !StringUtils.notIsBlankAndEmpty(entity.getInId())) {
//                this.mView.showMessage("出入账户均不能为空");
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    public void attachView(FinAccountAddContract.View paramT) {

    }

    @Override
    public void detachView() {

    }

}
