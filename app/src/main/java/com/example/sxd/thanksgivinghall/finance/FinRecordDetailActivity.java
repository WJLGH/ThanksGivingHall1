package com.example.sxd.thanksgivinghall.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.base.BaseActivity;
import com.example.sxd.thanksgivinghall.bean.FinGood;
import com.example.sxd.thanksgivinghall.bean.FinRecordDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinRecordDetailActivity extends BaseActivity implements FinRecordDetailContract.View {

    @BindView(R.id.good_detail)
    LinearLayout goodDetail;
    @BindView(R.id.tv_fin_record_detail_bustype)
    TextView tvBusType;
    @BindView(R.id.tv_fin_record_detail_in)
    TextView tvIn;
    @BindView(R.id.tv_fin_record_detail_out)
    TextView tvOut;
    @BindView(R.id.tv_fin_record_detail_desc)
    TextView tvDesc;
    @BindView(R.id.tv_fin_record_detail_amount)
    TextView tvAmount;
    @BindView(R.id.tv_fin_record_detail_note_date)
    TextView tvNoteDate;
    @BindView(R.id.tv_fin_record_detail_dept)
    TextView tvDept;
    @BindView(R.id.et_fin_record_detail_id)
    EditText etId;

    @BindView(R.id.bt__fin_record_detail_find)
    Button btFind;

    FinRecordDetailPresenterImpl mPresenter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_record_detail);
        ButterKnife.bind(this);
        mPresenter = new FinRecordDetailPresenterImpl(FinRecordDetailActivity.this, this);
        initData();
    }

    @OnClick(R.id.bt__fin_record_detail_find)
    public void findByID() {
        clearView();
        id = etId.getText().toString().trim();
        mPresenter.request(id);
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        id = id != null ? id : "3c455601-26af-11e9-afd2-00ffc45c1233" ;
        mPresenter.request(id);
    }
    private void clearView() {
        tvNoteDate.setText("");
        tvIn.setText("");;
        tvOut.setText("");
        tvDesc.setText("");
        tvBusType.setText("");
        tvAmount.setText("");
    }
    @Override
    protected void setRightTitleOnClick(View v) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param value
     */
    @Override
    public void requestSuccess(FinRecordDetailEntity value) {
        if(value == null || value.getData() == null) {
            Toast.makeText(this,"记录不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        FinRecordDetailEntity.Data data = value.getData();

        tvAmount.setText(data.getAmount().toString());
        tvBusType.setText(data.getBusType());
        tvDesc.setText(data.getDescription());
        tvNoteDate.setText(data.getNoteDate());
        Toast.makeText(this,data.getReType(), Toast.LENGTH_LONG).show();
        tvOut.setText(data.getOutId() != null ? data.getOutId() : "");
        tvIn.setText(data.getInId() != null ? data.getInId() : "");
        tvDept.setText(data.getDept() != null ? data.getDept():"");
        FinGood good = data.getGood();
        if (good != null) {
            goodDetail.setVisibility(View.VISIBLE);
            final EditText etGoodName = this.findViewById(R.id.et_good_name);
            final EditText etPrice = this.findViewById(R.id.et_price);
            final EditText etQuantity = this.findViewById(R.id.et_quantity);
            final EditText etUnit = this.findViewById(R.id.et_unit);
            final EditText etSupplier = this.findViewById(R.id.et_supplier);
            final EditText etSpaAddress = this.findViewById(R.id.et_sp_address);
            final EditText etBuyer = this.findViewById(R.id.et_buyer);
            etGoodName.setText(good.getGoodName());
            etPrice.setText(String.format("%.2f",good.getPrice()));
            etQuantity.setText(String.format("%.2f",good.getQuantity()));
            etUnit.setText(good.getUnit());
            etSpaAddress.setText(good.getSpAddress());
            etSupplier.setText(good.getSupplier());
            etBuyer.setText(good.getBuyer());
        }
    }

    @Override
    public void setPresenter(FinRecordDetailContract.Presenter paramT) {

    }
}
