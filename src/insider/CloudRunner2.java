/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insider;

import ca.pfv.spmf.test.TransactionDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 

public class CloudRunner2 {

    private final int NUM_OF_TASKS = 100;

    public ArrayList<Object> createVMList(int NoofVMs) {

        ArrayList<Object> objVMs = new ArrayList();

        Random rnd = new Random();

        for (int i = 0; i < NoofVMs; i++) {

            VM obj = new VM(true, 1, 512, 1024, rnd.nextInt(3));
            System.out.println("Adding VM(s)");
            
            objVMs.add(obj);
        }

        return objVMs;

    }

    public ArrayList<Object> createHostList(int NoofHost, int NoofVM) {
        ArrayList<Object> objHost = new ArrayList();
        ArrayList<Object> VMObjects = createVMList(NoofVM);
        VM vmO = (VM) VMObjects.get(1);

        for (int i = 0; i < NoofHost; i++) {
            CloudHost obj = new CloudHost(true, 2, 512 * NoofVM, 1024 * NoofVM, 8, vmO);
            System.out.println("Adding Host");
            
            objHost.add(obj);
        }

        return objHost;
    }

    public void createDataCenter(int NoofHost, int NoofVM) {
        ArrayList<Object> Hosts = createHostList(NoofHost, NoofVM);
        Random rnd = new Random();
        for (Object item : Hosts) {

            DataCenter dc = new DataCenter((CloudHost) item, rnd.nextInt(), rnd.nextInt());

            System.out.println("Adding Host(s),VM(s) to Data Center :" + "DataCenter ID:"+dc.getDataCenterID() + ",Data Center ZoneID:" + dc.getDataCenterZone());
        }

    }

    public void run() throws IOException {

        long begTest = new java.util.Date().getTime();

        List< Future> futuresList = new ArrayList< Future>();

        int NoofVMs = 4;
        int NoofHost = 10;
        ExecutorService eservice = null;

        createDataCenter(NoofVMs, NoofHost);
        //ActivateUserBase 
        CloudBroker cd = new CloudBroker();
        cd.BrokerID = 10;
        cd.workStatus = 10;
        if (cd.getWorkStatus() > 1) {
            eservice = Executors.newFixedThreadPool(NoofVMs);
        } else {

            System.out.println("No Work Submitted Broker");
        }

        String[] arg = {"", "", ""};

        for (int index = 0; index < NUM_OF_TASKS; index++) {
            CloudTasks obj = new CloudTasks();
            boolean add = futuresList.add(eservice.submit(obj));
            System.out.println("Cloud Job Submition Status:"+add);

        }

        Object taskResult;
        for (Future future : futuresList) {
            try {
                taskResult = future.get();
                System.out.println("Cloud Task Results: " + taskResult);
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }
        Double secs = new Double((new java.util.Date().getTime() - begTest) * 0.001);
        System.out.println("Run time " + secs + " secs");
    }

    public static void main(String args[]) {
        try {
            new CloudRunner2().run();
            TransactionDatabase.MeraMain(args);
            
            PreviousAlgoExecution.Execute(args);
            
            System.exit(0);

        } catch (IOException ex) {
            Logger.getLogger(CloudRunner2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
