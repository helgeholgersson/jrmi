/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.registry.*;
import rmi.*;

public class Client {
    private static final String HOST = "193.10.237.105";
    private static int PORT = 32145;
    private static Registry registry;

    public static void main(String[] args) throws Exception {
        GraphicalInterface gui = new GraphicalInterface();
        registry = LocateRegistry.getRegistry(HOST, PORT);
        Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());
        
        int[] test = {3,1,2,10,67,22,88,4,90,5,1001}; 
        sortData send = new sortData(test);
        send = remoteApi.quickSort(send);
        
        if(send.error){
            System.out.println(send.showErrorMessage());
        }else{
            int[] m = remoteApi.quickSort(send).getValue();
            for(int l=0;l<m.length;l++){
                System.out.println("Rad " + l + ": " + m[l]);
                Thread.sleep(100);
            }
        }        
        /*for (int i = 1; i <= 100; i++) {
            System.out.println("counter = ");
                    //remoteApi.incrementCounter(new Data(1)).getValue() + " " +
            //remoteApi.testapi(new Data(1)).getValue());
            Thread.sleep(100);
        }*/
    }
}
