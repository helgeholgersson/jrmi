package com.littletutorials.rmi.server;

import java.rmi.*;
import java.rmi.registry.*;

import com.littletutorials.rmi.server.*;
import com.littletutorials.rmi.api.Api;

public class Server {
    private static final int PORT = 1099;
    private static Registry registry;

    public static void startRegistry() throws RemoteException {
        // create in server registry
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    public static void registerObject(String name, Remote remoteObj)
        throws RemoteException, AlreadyBoundException
    {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> " +
            remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    public static void main(String[] args) throws Exception {
        startRegistry();
        registerObject(Api.class.getSimpleName(), new ApiImpl());
        Thread.sleep(5 * 60 * 1000);
        //Thread.sleep(1000);
    }
}

