/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.registry.*;
import rmi.*;

/*
 * @author Helge Holgersson, Jacob Svedman, Andreas Forsbom
 * Detta är main-klassen för klientsidan. Denna klass ritar upp ett GUI samt ger
 * möjligheten att skicka en array till servern med hjälp av API't.
 */
public class Client {
    private static String HOST = "193.10.237.105";
    private static int PORT = 32145;
    private static Registry registry;
    private static GraphicalInterface gui;
    
    /* Ritar upp ett GUI */
    public static void main(String[] args) throws Exception {
         gui = new GraphicalInterface();
         gui.setVisible(true);
    }
    
    /* Metod för att skicka iväg en array till RMI-servern */
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
