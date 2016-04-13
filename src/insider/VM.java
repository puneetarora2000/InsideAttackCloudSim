/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insider;

import java.util.ArrayList;

/**
 *
 * @author Ecologic
 */
public class VM {
    
    public int NoOfCores  = 4 ;
    public int ImageSize = 1024 ;
    public int ram = 512 ;
    public int Os = 1; // 1 = Linux , 2 = Windows 
    public int NoofVMs  = 10;

    VM() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    System.out.println("Creating Default Clean VM");
    
    }

    

    
    
    public int getNoofVMs() {
        return NoofVMs;
    }

    public void setNoofVMs(int NoofVMs) {
        this.NoofVMs = NoofVMs;
    }
    
    
    
    
    
    
    
    
    
    
    public int getNoOfCores() {
        return NoOfCores;
    }

    public void setNoOfCores(int NoOfCores) {
        this.NoOfCores = NoOfCores;
    }

    public int getImageSize() {
        return ImageSize;
    }

    public void setImageSize(int ImageSize) {
        this.ImageSize = ImageSize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getOs() {
        return Os;
    }

    public void setOs(int Os) {
        this.Os = Os;
    }
    
    ArrayList<VM> objHost = new ArrayList<VM>();

    public ArrayList<VM> getObjHost() {
        return objHost;
    }

    public void setObjHost(ArrayList<VM> objHost) {
        this.objHost = objHost;
    }

             
    
     VM(boolean Vf,int OsType,int ram,int imgSize,int NoOfCore){
    
         
         
             this.ImageSize = imgSize;
            this.Os = OsType;
            this.ram = ram;
            this.NoOfCores = NoOfCore;
            
            
         
        
        
        }
 


}
    
    
     
    
 
