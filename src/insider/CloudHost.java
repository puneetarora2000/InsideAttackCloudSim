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
public class CloudHost {
    
    public int NoOfCores  = 4 ;
    public int ImageSize = 1024 ;
    public int ram = 512 ;
    public int Os = 1; // 1 = Linux , 2 = Windows 
    public int NoofVMs  = 10;

    
    
    public int getNoofVMs() {
        return NoofVMs;
    }

    public void setNoofVMs(int NoofVMs) {
        this.NoofVMs = NoofVMs;
    }
    
    
    
    
    
    
    public boolean Virtualization = true;
    
    
    

    public boolean isVirtualization() {
        return Virtualization;
    }

    public void setVirtualization(boolean Virtualization) {
        this.Virtualization = Virtualization;
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
    
    ArrayList<CloudHost> objHost = new ArrayList<CloudHost>();

    public ArrayList<CloudHost> getObjHost() {
        return objHost;
    }

    public void setObjHost(ArrayList<CloudHost> objHost) {
        this.objHost = objHost;
    }

    
    VM obj = new VM(Virtualization, Os, ram, ImageSize, NoOfCores);

    public VM getObj() {
        return obj;
    }

    public void setObj(VM obj) {
        this.obj = obj;
    }
   
    
    
    
             
    
     CloudHost(boolean Vf,int OsType,int ram,int imgSize,int NoOfCore, VM vmObj){
    
        this.Virtualization = Vf;
        
        for(int i=0;i<NoofVMs;i++){
        
        if (Vf==true){
            
            this.ImageSize = imgSize;
            this.Os = OsType;
            this.ram = ram;
            this.NoOfCores = NoOfCore;
            this.obj = vmObj;
            
        }else {
        
            
        }
        
        
        }
    }
    
     public void BuidHostVM(CloudHost h, int NoOfHost){
     
         for(int i=0;i<NoOfHost;i++){ 
                objHost.add(h);
            }
     }
     
    
}
