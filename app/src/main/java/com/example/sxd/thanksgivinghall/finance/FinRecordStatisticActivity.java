package com.example.sxd.thanksgivinghall.finance;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sxd.thanksgivinghall.R;
import com.example.sxd.thanksgivinghall.bean.FinDateSumListEntity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinRecordStatisticActivity extends AppCompatActivity implements FinRecordStatisticContract.View{

    @BindView(R.id.lineChart)
    LineChart lineChart;
    private XAxis xAxis;                //X轴
    private YAxis leftYAxis;            //左侧Y轴
    private YAxis rightYaxis;           //右侧Y轴
    private Legend legend;              //图例
    private LimitLine limitLine;        //限制线
    FinRecordStatisticContract.Presenter mPresenter;
    //  private MyMarkerView markerView;    //标记视图 即点击xy轴交点时弹出展示信息的View 需自定义
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_record_statistic);
        ButterKnife.bind(this);
        mPresenter = new FinRecordStatisticPresenterImpl(FinRecordStatisticActivity.this,this);
        initChart(lineChart);
        initValue();
    }

    private void initValue() {
        mPresenter.request("2019");
    }
    public void onConfigurationChanged(Configuration newConfig) {
// TODO Auto-generated method stubsuper.onConfigurationChanged(newConfig);
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

        } else {
// Nothing need to be done here
        }
    }

    /**
     * 初始化图表
     */
    private void initChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //是否显示边界
        lineChart.setDrawBorders(true);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        //去掉背景
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否展示网格线
        lineChart.setDrawGridBackground(false);

        /***XY轴的设置***/
        xAxis = lineChart.getXAxis();
        leftYAxis = lineChart.getAxisLeft();
        rightYaxis = lineChart.getAxisRight();
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(1f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftYAxis.setAxisMinimum(-100f);
        rightYaxis.setAxisMinimum(0f);
        xAxis.setDrawGridLines(false);
        rightYaxis.setDrawGridLines(false);
        leftYAxis.setDrawGridLines(true);
        leftYAxis.enableGridDashedLine(10f, 10f, 60f);
        rightYaxis.setEnabled(false);

        Description description = new Description();
//        description.setText("需要展示的内容");
        description.setEnabled(false);
        lineChart.setDescription(description);
        /***折线图例 标签 设置***/
        legend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        //显示位置 左下方
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
    }
    /**
     * 曲线初始化设置 一个LineDataSet 代表一条曲线
     *
     * @param lineDataSet 线条
     * @param color       线条颜色
     * @param mode
     */
    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }
    /**
     * 添加曲线
     */
    private void addLine(List<FinDateSumListEntity.Data> dataList, String name, int color,int type) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            FinDateSumListEntity.Data data = dataList.get(i);
            double d = 0;
            switch (type) {
                case 0:
                    d = data.getAmount();
                    break;
                case 1:
                    d = data.getInAmount();
                    break;
                case -1 :
                    d = data.getOutAmount();
                    break;
            }

            int month = 0;
            try {
                month = new SimpleDateFormat("yyyy-MM").parse(data.getMonth()).getMonth() + 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Entry entry = new Entry(month, (float) d);
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = lineChart.getLineData();
        lineData.addDataSet(lineDataSet);
        lineChart.notifyDataSetChanged();
    }
    /**
     * 展示曲线
     *  @param dataList 数据集合
     * @param name     曲线名称
     * @param color    曲线颜色
     */
    public void showLineChart(List<FinDateSumListEntity.Data> dataList, String name, int color) {

        List<Entry> entries = new ArrayList<>();

        xAxis.setLabelCount( dataList.size(),true);
        for (int i = 0; i < dataList.size(); i++) {
            FinDateSumListEntity.Data data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            double d = data.getAmount();
            int month = 0;
            try {
                month = new SimpleDateFormat("yyyy-MM").parse(data.getMonth()).getMonth() + 1;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Entry entry = new Entry(month, (float) d);
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void requestSuccess(FinDateSumListEntity value) {
        showLineChart(value.getData(), "我的收益", Color.BLUE);
        addLine(value.getData(),"我的支出",Color.RED,-1);
        addLine(value.getData(),"我的收入",Color.GREEN,1);
    }

    @Override
    public void setPresenter(FinRecordStatisticContract.Presenter paramT) {

    }
}
