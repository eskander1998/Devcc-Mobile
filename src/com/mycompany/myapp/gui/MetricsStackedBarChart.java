/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Reservationconsultation;
import com.mycompany.myapp.services.ReservationService;







/**
 * Sales demo bar chart.
 */
public class MetricsStackedBarChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Metrics stacked bar chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  public String getDesc() {
    return "System Health and Compliance";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute()  {
       
    String[] titles = new String[] { "Total reservations", "Nombre reservations"};
   int k=0,j=0,ii=0,a=0;
   System.out.println(ReservationService.getInstance().getAllReservations("yassine.benouaghrem@esprit.tn"));
      for (Reservationconsultation b : ReservationService.getInstance().getAllReservations("yassine.benouaghrem@esprit.tn")) {
          //titles=new String[]{b.getCategoriebook()};
          if(b.getEtat().compareTo("En attente de confirmation")==0){
              k++;}
else
j++;
    }
      System.out.println("k="+k + "j="+j); 
    List<double[]> values = new ArrayList<double[]>();
    values.add(new double[] { k, j}); // Gnrl
  //  values.add(new double[] { 9, 32, 10, 35}); // Gnr Pvt
   // values.add(new double[] { 12, 21, 20, 10}); // Conc.
  // values.add(new double[] { 21, 12, 22, 23}); // VA
    //values.add(new double[] { 11, 12, 20, 21}); // S3
    int[] colors = new int[]{ColorUtil.rgb(0, 76, 153), ColorUtil.rgb(0, 102, 204), ColorUtil.rgb(0, 128, 255), ColorUtil.rgb(51, 153, 255)
                            , ColorUtil.rgb(102, 178, 255), ColorUtil.rgb(153, 204, 255)};
    XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
    setChartSettings(renderer, "Books Selon Category", "", "", 0.5,
        4.5, 0, 50, ColorUtil.GRAY, ColorUtil.LTGRAY);

    renderer.setXLabels(0);
    renderer.setYLabels(0);
    renderer.setXLabelsAlign(Component.LEFT);
    renderer.setYLabelsAlign(Component.LEFT);
    renderer.setPanEnabled(true, false);
    // renderer.setZoomEnabled(false);
    renderer.setZoomRate(1.1f);
    renderer.setBarSpacing(0.5f);
      renderer.setApplyBackgroundColor(true);
      renderer.setBackgroundColor(ColorUtil.WHITE);
      renderer.setMarginsColor(ColorUtil.WHITE);
      renderer.setBarWidth(70);
      renderer.addXTextLabel(1, "en attente");
      renderer.addXTextLabel(2, "a domicile");

//      renderer.setChartTitleTextSize(50);
//      renderer.setChartTitleTextSize(50);
//      renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
      int length = renderer.getSeriesRendererCount();
     // System.out.println("Series len "+length);
      for (int i = 0; i < length; i++) {
          XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
          seriesRenderer.setDisplayChartValues(true);
          seriesRenderer.setChartValuesTextFont(largeFont);
//            seriesRenderer.setChartValuesFormat(new ICRNumerFormat());
      }
      
      BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
        Type.STACKED);
      return wrap("", new ChartComponent(chart));
   
  }

}
    