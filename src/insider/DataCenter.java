/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insider;

import java.util.Random;

/**
 *
 * @author Ecologic
 */
public class DataCenter {
    
    Random rnd = new Random();
    
    int DataCenterID = 1;
    int DataCenterZone = 1;
    VM vmObj = new VM();
    CloudHost h = new CloudHost(true, 1, 1, 1, 1, vmObj);

    public int WorkSubmission = 1;

    public int getWorkSubmission() {
        return WorkSubmission;
    }

    public void setWorkSubmission(int WorkSubmission) {
        
        if (WorkSubmission>1){
        
            System.out.println("Work Submissions");
            
        }else {
        
            System.out.println("No Work Submissions");
            
        
        }
        
        this.WorkSubmission = WorkSubmission;
    }
    
    
    
    
    
    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public int getDataCenterID() {
        return DataCenterID;
    }

    public void setDataCenterID(int DataCenterID) {
        this.DataCenterID = DataCenterID;
    }

    public int getDataCenterZone() {
        return DataCenterZone;
    }

    public void setDataCenterZone(int DataCenterZone) {
        this.DataCenterZone = DataCenterZone;
    }

    public VM getVmObj() {
        return vmObj;
    }

    public void setVmObj(VM vmObj) {
        this.vmObj = vmObj;
    }

    public CloudHost getH() {
        return h;
    }

    public void setH(CloudHost h) {
        this.h = h;
    }

    
    
    
    public DataCenter(CloudHost h,int  DataCenterID ,int DataCenterZone) {
        
        this.DataCenterID = DataCenterID;
        this.DataCenterZone = DataCenterZone;
        this.h = h;
       System.out.println("Buidling DataCenter"+DataCenterID+" "+ DataCenterZone+" ");
    }
    
    
    
    
}
