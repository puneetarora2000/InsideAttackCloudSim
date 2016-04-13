/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insider;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author Ecologic
 */
public class CloudTasks implements Callable<Object>{

    
    Random rnd = new Random();
    public  double CloudTaskSize   = 10;
    public  double CloudTaskID   = 10;
    public  double CloudTaskUserBrokerID   = 10;
     

    public double getCloudTaskSize() {
        return CloudTaskSize;
    }

    public void setCloudTaskSize(double CloudTaskSize) {
        this.CloudTaskSize = CloudTaskSize;
    }

    public double getCloudTaskID() {
        return CloudTaskID;
    }

    public void setCloudTaskID(double CloudTaskID) {
        this.CloudTaskID = CloudTaskID;
    }

    public double getCloudTaskUserBrokerID() {
        return CloudTaskUserBrokerID;
    }

    public void setCloudTaskUserBrokerID(double CloudTaskUserBrokerID) {
        this.CloudTaskUserBrokerID = CloudTaskUserBrokerID;
    }

    
    public int  NoofBrokers   = 10;
   public int  NoofSubTasks   = 10;

    public int getNoofBrokers() {
        return NoofBrokers;
    }

    public void setNoofBrokers(int NoofBrokers) {
        this.NoofBrokers = NoofBrokers;
    }

    public int getNoofSubTasks() {
        return NoofSubTasks;
    }

    public void setNoofSubTasks(int NoofSubTasks) {
        this.NoofSubTasks = NoofSubTasks;
    }
    
    
    
    
    
    
    
    
     public Object call() {
      
      String str = ""+rnd.nextInt();
      String bstr = ""+ rnd.nextInt(NoofBrokers);
      
      System.out.println("Start - Task ID"+str);
      System.out.println("BrokerID"+bstr);
     
      long begTest = new java.util.Date().getTime();
      
      String DummyTaskWork = "WorkFlow Data Work Processing";
      try {
          
          for(int i = 0; i < NoofSubTasks; i++){
              Random rnd = new Random();
              int d = rnd.nextInt(3);
              Thread.sleep(1000*d);
             DummyTaskWork = DummyTaskWork + ", " + bstr + ", " + str;   
          }
            
         
      } catch (Exception e) {System.out.println("Error:"+e.getMessage());}
      Double secs = new Double((new java.util.Date().getTime() - begTest)*0.001);
      System.out.println("run time " + secs + " secs");
      return DummyTaskWork;
    }
    
}
