package rmi;

import java.rmi.*;

/**
 * 
 * @author Helge Holgersson, Jacob Svedman, Andreas Forsbom
 * Detta är det gemensamma API som delas med Serverapplikationen och klientapplikationen
 * Detta interface beskriver vilka meotder som klienten kan använda på Serverapplikationen.
 * Det är viktigt att denna klass finns i samma version i bägge program.
 * 
 * Ett objekt av denna klass kommer skapas av Servern och en referens till det kommer att läggas till i RMI-registret.
 */
public interface Api extends Remote
{
    //public Data incrementCounter(Data value) throws RemoteException;
    public sortData quickSort(sortData value) throws RemoteException;
}

