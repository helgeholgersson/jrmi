package server;

import java.rmi.*;
import java.rmi.registry.*;


import server.*;
import rmi.Api;

public class Server 
{
    //Default port: 32145
    //private static int PORT = 32145;
    //private static Registry registry;
    private static ServerGUI mygui;

    public static void main(String[] args) throws Exception
    {
        mygui = new ServerGUI();
    }
}

