package server;

import java.rmi.*;
import java.rmi.server.*;
import rmi.*;

public class ApiImpl extends UnicastRemoteObject implements Api
{
    private static final long serialVersionUID = 1L;
    private int counter = 0;
    private String msg = "";
    private ServerGUI myparent;

    public ApiImpl(ServerGUI parent) throws RemoteException
    {
        super();
        myparent = parent;
    }
    
    @Override
    public synchronized sortData quickSort(sortData unsorted) throws RemoteException
    {
        //int[] sorted = new int[unsorted.length];
        //int[] j = {3,2,1};
        long[] j = (long[])unsorted.getValue().clone();
        
        //Print in console
        if(myparent.myconsole != null)
        {
            try
            {
                msg = "" + UnicastRemoteObject.getClientHost() + " Requested to sort: ";
                for(int i=0; i<j.length; i++)
                {
                    msg += ""+j[i]+" ";
                }
            }
            catch (ServerNotActiveException e)
            {
                msg = "Server not active";
            }
            myparent.myconsole.appendOutput(msg);
            msg = "";
        }
        
        Quicksort sorter = new Quicksort();
        
        try
        {
            sorter.sort(j);
            j = sorter.getNumbers();
        }
        catch(Exception e)
        {
            unsorted.error = true;
            unsorted.appendErrorMessage("Quicksort failed");
        }
        
        unsorted.setValue(j);
        return unsorted;
    }
}


