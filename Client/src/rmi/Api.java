/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.*;
/**
 *
 * @author jasv0004
 */
public interface Api extends Remote {
    public Data incrementCounter(Data value) throws RemoteException;
    public Data testapi(Data value) throws RemoteException;
    public sortData quickSort(sortData value)throws RemoteException;
}
