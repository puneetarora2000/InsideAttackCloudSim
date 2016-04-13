/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plot.results;

/**
 *
 * @author Ecologic
 */

import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;


public class AbnormalPatterns {
    
    
    
public static void main( String[ ] args )throws Exception 
   {

      final String oldWork = "Prefix Spam Algorithm ";
      final String newWork = "Proposed";
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

       dataset.addValue(6, newWork, oldWork);
       dataset.addValue(3, oldWork,newWork);
       
      JFreeChart barChart = ChartFactory.createBarChart(
         "Average Number of Abnormal Patterns Discovered   Performance Statistics", 
         "Number of Abnormal  Insider Acitivity", "Number of Abnormal  Insider Acitivity", 
         dataset,PlotOrientation.VERTICAL, 
         true, true, false);
      barChart.removeLegend();
         
      int width = 640; /* Width of the image */
      int height = 480; /* Height of the image */ 
      File BarChart = new File( "D:\\Data\\GroverChart2.jpeg" ); 
      ChartUtilities.saveChartAsJPEG( BarChart , barChart , width , height );
   }    
}
