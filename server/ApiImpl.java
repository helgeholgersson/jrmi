package com.littletutorials.rmi.server;

import java.rmi.*;
import java.rmi.server.*;
import com.littletutorials.rmi.api.*;

public class ApiImpl extends UnicastRemoteObject implements Api
{
    private static final long serialVersionUID = 1L;
    private int counter = 0;

    public ApiImpl() throws RemoteException
    {
        super();
    }

    @Override
    public synchronized Data incrementCounter(Data value) throws RemoteException
    {
        counter += value.getValue();
        return new Data(counter);
    }
    
    //@Override
    public synchronized Data testapi(Data value) throws RemoteException
    {
     //counter = 3; //value.getValue();
     return new Data(3);
    }
    
    @Override
    public synchronized sortData quickSort(sortData unsorted) throws RemoteException
    {
        //int[] sorted = new int[unsorted.length];
        //int[] j = {3,2,1};
        int[] j = (int[])unsorted.getValue().clone();
        Quicksort sorter = new Quicksort();
        
        try
        {
            sorter.sort(j);
            j = sorter.getNumbers();
            //j[1] = 500;
        }
        catch(Exception e)
        {
            unsorted.error = true;
            unsorted.appendErrorMessage("Quicksort failed");
        }
        
        /*
        for(int i=0; i<j.length; i++)
        {
             j[i] += 1;
        }
         */
        
        unsorted.setValue(j);
        //return sorted;
        return unsorted;
        //System.out.println(j[2]);
    }
}


