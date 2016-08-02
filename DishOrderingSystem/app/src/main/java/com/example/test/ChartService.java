package com.example.test;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class ChartService {

    private GraphicalView mGraphicalView;
    private XYMultipleSeriesDataset multipleSeriesDataset;//
    private XYMultipleSeriesRenderer multipleSeriesRenderer;//
    private XYSeries mSeries;//
    private XYSeriesRenderer mRenderer;//
    private Context context;

    public ChartService(Context context) {
        this.context = context;
    }


    public GraphicalView getGraphicalView() {
        mGraphicalView = ChartFactory.getCubeLineChartView(context,
                multipleSeriesDataset, multipleSeriesRenderer, 0.1f);
        return mGraphicalView;
    }


    public void setXYMultipleSeriesDataset(String curveTitle) {
        multipleSeriesDataset = new XYMultipleSeriesDataset();
        mSeries = new XYSeries(curveTitle);
        multipleSeriesDataset.addSeries(mSeries);
    }

    /**
     * »ñÈ¡äÖÈ¾Æ÷
     *
     * @param maxX
     *            xÖá×î´óÖµ
     * @param maxY
     *            yÖá×î´óÖµ
     * @param chartTitle
     *            ÇúÏßµÄ±êÌâ
     * @param xTitle
     *            xÖá±êÌâ
     * @param yTitle
     *            yÖá±êÌâ
     * @param axeColor
     *            ×ø±êÖáÑÕÉ«
     * @param labelColor
     *            ±êÌâÑÕÉ«
     * @param curveColor
     *            ÇúÏßÑÕÉ«
     * @param gridColor
     *            Íø¸ñÑÕÉ«
     */
    public void setXYMultipleSeriesRenderer(double maxX, double maxY,
                                            String chartTitle, String xTitle, String yTitle, int axeColor,
                                            int labelColor, int curveColor, int gridColor) {
        multipleSeriesRenderer = new XYMultipleSeriesRenderer();
        if (chartTitle != null) {
            multipleSeriesRenderer.setChartTitle(chartTitle);
        }
        multipleSeriesRenderer.setXTitle(xTitle);
        multipleSeriesRenderer.setYTitle(yTitle);
        multipleSeriesRenderer.setRange(new double[] { 1, maxX, 0, maxY });
        multipleSeriesRenderer.setLabelsColor(labelColor);
        multipleSeriesRenderer.setXLabels(20);
        multipleSeriesRenderer.setYLabels(10);
        multipleSeriesRenderer.setXLabelsAlign(Align.RIGHT);
        multipleSeriesRenderer.setYLabelsAlign(Align.RIGHT);
        multipleSeriesRenderer.setAxisTitleTextSize(20);
        multipleSeriesRenderer.setChartTitleTextSize(20);
        multipleSeriesRenderer.setLabelsTextSize(15);
        multipleSeriesRenderer.setLegendTextSize(15);
        multipleSeriesRenderer.setPointSize(2f);
        multipleSeriesRenderer.setFitLegend(true);
        multipleSeriesRenderer.setMargins(new int[] { 20, 30, 15, 20 });
        multipleSeriesRenderer.setShowGrid(true);
        multipleSeriesRenderer.setZoomEnabled(true, false);
        multipleSeriesRenderer.setAxesColor(axeColor);
        multipleSeriesRenderer.setGridColor(gridColor);
        multipleSeriesRenderer.setBackgroundColor(Color.WHITE);
        multipleSeriesRenderer.setMarginsColor(Color.WHITE);
        mRenderer = new XYSeriesRenderer();
        mRenderer.setColor(curveColor);
        mRenderer.setPointStyle(PointStyle.CIRCLE);
        multipleSeriesRenderer.addSeriesRenderer(mRenderer);
    }

    /**
     * ¸ù¾ÝÐÂ¼ÓµÄÊý¾Ý£¬¸üÐÂÇúÏß£¬Ö»ÄÜÔËÐÐÔÚÖ÷Ïß³Ì
     *
     * @param x
     *            ÐÂ¼ÓµãµÄx×ø±ê
     * @param y
     *            ÐÂ¼ÓµãµÄy×ø±ê
     */
    public void updateChart(double x, double y) {
        mSeries.add(x, y);
        mGraphicalView.repaint();//´Ë´¦Ò²¿ÉÒÔµ÷ÓÃinvalidate()
    }

    /**
     * Ìí¼ÓÐÂµÄÊý¾Ý£¬¶à×é£¬¸üÐÂÇúÏß£¬Ö»ÄÜÔËÐÐÔÚÖ÷Ïß³Ì
     * @param xList
     * @param yList
     */
    public void updateChart(List<Double> xList, List<Double> yList) {
        for (int i = 0; i < xList.size(); i++) {
            mSeries.add(xList.get(i), yList.get(i));
        }
        mGraphicalView.repaint();//´Ë´¦Ò²¿ÉÒÔµ÷ÓÃinvalidate()
    }
}