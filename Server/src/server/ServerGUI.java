/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.*;
import java.rmi.registry.*;
import rmi.Api;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Sebastian Holgersson
 */
public class ServerGUI extends JFrame implements ActionListener
{
    
    String msg_serveroff = "(Idle)";
    String msg_serveron = "(Active)";
    
    private boolean activated = false;
    private JButton btn_start = new JButton("Start Server");
    private JLabel lbl_title = new JLabel("QSort RMI Server");
    private JLabel lbl_status = new JLabel(msg_serveroff);
    private JLabel lbl_footer = new JLabel("Created by Andreas Forsbom, Jacob Svedman & Sebastian Holgersson");
    private JTextField tf_port = new JTextField("32145");
    private JLabel lbl_lgport = new JLabel("Listen for client on port");
    private static Registry registry = null;
    private boolean active = false;
    int port = 32145;
    Server myparent;
    ServerConsole myconsole;
    ApiImpl myapi;
    
    public ServerGUI() throws InterruptedException
    {
        //set Parent
        //myparent = parent;
        
        //Fonts
        lbl_title.setFont(new Font("Helvetica", Font.BOLD, 32));
        lbl_status.setFont(new Font("Helvetica", Font.BOLD, 32));
        lbl_status.setForeground(Color.red);
        
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu mb_file = new JMenu("Server"); //Server menu
        mb.add(mb_file);
        
        mb_file.add(opt_start);
        mb_file.add(opt_stop);
        mb_file.add(opt_hidecon);
        mb_file.addSeparator();mb_file.add(opt_quit);
        
        Container cont = getContentPane();
        Container header = new Container();
        Container section = new Container();
        Container footer = new Container();
        
        cont.setLayout(new GridLayout(3,1));
        header.setLayout(new FlowLayout());
        section.setLayout(new GridLayout(2,2)); //2r 2c
        footer.setLayout(new GridLayout(3,1)); //3r 1c
        
        //Complete Header layout
        header.add(lbl_title);
        header.add(lbl_status);
        
        //Complete Section layout
        section.add(lbl_lgport);
        section.add(new JLabel(""));
        section.add(tf_port);
        section.add(btn_start);
        
        //Complete Footer layout
        ///////footer.add(lbl_status);
        footer.add(new JLabel(""));
        footer.add(lbl_footer);
        
        //Complete cont layout
        cont.setBackground(Color.lightGray);
        cont.add(header);
        cont.add(section);
        cont.add(footer);
        
        //Add Actionlistener to buttons
        btn_start.addActionListener(this);
        
        opt_stop.setEnabled(false);
        opt_hidecon.setEnabled(false);
        setLocationRelativeTo(null);
        setSize(640, 320);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("RMI Server");
        setVisible(true);        
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn_start)
        {
            runServer();
       }
    }
    
   //START SERVER ACTION (Menu bar)
   Action opt_start = new AbstractAction("Start Server")
   {
     public void actionPerformed(ActionEvent e)
     {
       runServer();
     }
   };
   
   //STOP SERVER ACTION (Menu bar)
   Action opt_stop = new AbstractAction("Stop Server")
   {
     public void actionPerformed(ActionEvent e)
     {
       runServer();
     }
   };
   
   //STOP SERVER ACTION (Menu bar)
   Action opt_hidecon = new AbstractAction("Show/Hide Console")
   {
     public void actionPerformed(ActionEvent e)
     {
       try
       {
           if(myconsole.isVisible())
           {
               myconsole.setVisible(false);
           }
           else
           {
               myconsole.setVisible(true);
           }
       }
       catch(Exception err)
       {
           //nothing
       }
     }
   };
   
   Action opt_quit = new AbstractAction("Exit")
   {
     public void actionPerformed(ActionEvent e)
     {
       try
       {
         UnicastRemoteObject.unexportObject(registry, true);  
       }
       catch(Exception error)
       {
           //nothing
       }
       System.exit(0);
     }
   };
    
    public String registerObject(String name, Remote remoteObj) throws RemoteException, AlreadyBoundException
    {
        String answer = "";
        registry.bind(name, remoteObj);
        answer = "Registered: " + name + "\n-> " + remoteObj.getClass().getName() + "\n[" + remoteObj + "]";
        
        return answer;
    }
    
    public void startRegistry() throws RemoteException
    {
        // create in server registry
        registry = java.rmi.registry.LocateRegistry.createRegistry(port);
    }
    
    public void runServer()
    {
        
        if(!active)
        {
            try
            {
                port = Integer.parseInt(tf_port.getText().substring(0, 5));
            }
            catch(Exception inputerr)
            {
                JOptionPane.showMessageDialog(this, "Invalid portnumber selected, using default port: 32145");
                port = 32145;
            }
            lbl_status.setText("Server is starting...");
            myconsole = new ServerConsole(port, this);
            opt_hidecon.setEnabled(true);
            try
            {
                startRegistry();
            }
            catch(Exception ee)
            {
                JOptionPane.showMessageDialog(this, "Error when starting registry:\n" + ee);
            }

            try
            {
                myapi = new ApiImpl(this);
                String message = registerObject(Api.class.getSimpleName(), myapi);
                myconsole.appendOutput(message);
            }
            catch(Exception ee)
            {
                JOptionPane.showMessageDialog(this, "Error when registered Api:\n" + ee); 
            }

            active = true;
            lbl_status.setText(msg_serveron);
            lbl_status.setForeground(Color.green);
            btn_start.setText("Stop Server");
            opt_start.setEnabled(false);
            opt_stop.setEnabled(true);
            
        }
        else
        {
            try
            {
                UnicastRemoteObject.unexportObject(registry, true);
                registry = null;
                active = false;
                lbl_status.setText(msg_serveroff);
                lbl_status.setForeground(Color.red);
                btn_start.setText("Start Server");
                myconsole.dispose();
                opt_start.setEnabled(true);
                opt_stop.setEnabled(false);
                opt_hidecon.setEnabled(false);
            }
            catch(Exception ee)
            {
                JOptionPane.showMessageDialog(this, "Error when closing registry:\n" + ee); 
            }        
        } 
    }
}
