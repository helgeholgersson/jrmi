package rmi;

import java.rmi.*;

public interface Api extends Remote
{
    //public Data incrementCounter(Data value) throws RemoteException;
    public sortData quickSort(sortData value) throws RemoteException;
}

