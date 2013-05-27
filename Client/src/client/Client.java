/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.registry.*;
import rmi.*;

public class Client {
    private static String HOST = "193.10.237.105";
    private static int PORT = 32145;
    private static Registry registry;
    private static GraphicalInterface gui;
    
    public static void main(String[] args) throws Exception {
         gui = new GraphicalInterface();
         gui.setVisible(true);
    }
    
    public static long[] sendArray(long[] array){
        try{
            registry = LocateRegistry.getRegistry(HOST, PORT);
            Api remoteApi = (Api) registry.lookup(Api.class.getSimpleName());

            sortData send = new sortData(array);
            send = remoteApi.quickSort(send);

            if(send.error){
                System.out.println(send.showErrorMessage());
            } else {
                array = remoteApi.quickSort(send).getValue();
            }
        } catch (Exception E) {
            System.out.println("Could not send or receive array...\n"+E);
        }
        return array;
    }
    
    public static void setIp(String ip){
        HOST = ip;
    }
    
    public static void setPort(int p){
        PORT = p;
    }
}
