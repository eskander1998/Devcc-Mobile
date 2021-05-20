/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceDate;
import com.mycompany.myapp.utils.UserSession;



import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author asus
 */
public class chartsForm extends Form {

    Form current;

    protected CategorySeries buildCategoryDataset(String title) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
         Map<String, String> stats = new HashMap<>();
        stats = ServiceDate.getInstance().getConsultation(UserSession.instance.getUserName());
       for (Map.Entry<String, String> entry : stats.entrySet()) {
            series.add("Date: "+entry.getValue(),Double.parseDouble(entry.getKey()));
        }

        return series;
    }

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    public chartsForm(Form res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseX(
                FlowLayout.encloseIn(menuButton), FlowLayout.encloseIn(
                new Label("Statistics", "Title")
        )
        );

        tb.setTitleComponent(titleCmp);
        // Generate the values
        double[] values = new double[]{12, 14, 11, 10, 19};

        // Set up the renderer
        int[] colors = new int[]{ ColorUtil.rgb(255, 0, 127), ColorUtil.rgb(255, 204, 153), ColorUtil.rgb(255, 20, 103),  ColorUtil.rgb(255, 204, 153),  ColorUtil.rgb(255, 153, 255)};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(50);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart;

        chart = new PieChart(buildCategoryDataset("Partcipation Events"), renderer);
        
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);
        add(c);
        // Create a form and show it. 
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> res.showBack());
    }


}