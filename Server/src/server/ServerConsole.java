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
import java.text.DateFormat;

/**
 *
 * @author Helge Holgersson
 */
public class ServerConsole extends JFrame implements ActionListener
{
    private JLabel lbl_title = new JLabel("Console");
    private JTextArea ta_output = new JTextArea(2, 30);
    private JScrollPane scrollPane = new JScrollPane(ta_output);
    private JButton btn_clr = new JButton("Clear Console");
    private String ctext = "";
    int port = -1;
    private ServerGUI myparent;
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    
    //Konstruktor
    public ServerConsole(int newport, ServerGUI parent)
    {
        //Parameters
        port = newport;
        myparent = parent;
        
        //Component stylings
        lbl_title.setFont(new Font("Helvetica", Font.BOLD, 32));
        ta_output.setLineWrap(true);
        ta_output.setEditable(false);
        
        Container cont = getContentPane();
        
        cont.setLayout(new BorderLayout());
        
        cont.add(BorderLayout.NORTH, lbl_title);
        cont.add(BorderLayout.CENTER, ta_output);
        cont.add(BorderLayout.SOUTH, btn_clr);
        
        //Setup Menu bar
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu mb_file = new JMenu("File");
        mb.add(mb_file);
        
        //File menu contents
        mb_file.add(opt_savelog);
        mb_file.addSeparator();
        mb_file.add(opt_exit);
        
        //Add actionlistener to buttons
        btn_clr.addActionListener(this);
        
        //Add listener to when the window is closing
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        
        setSize(780, 480);
        setResizable(false);
        setTitle("Server listening on port: " + port);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn_clr)
        {
            //JOptionPane.showMessageDialog(this, "Width: " + this.getWidth());
            if(JOptionPane.showConfirmDialog(this, "This clears all text in the console, continue?") == JOptionPane.YES_OPTION)
            {
                ta_output.setText("");
            }
        }
    }
    
   Action opt_savelog = new AbstractAction("Save log to file...")
   {
     public void actionPerformed(ActionEvent e)
     {
       if (dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
       {
            saveLog(dialog.getSelectedFile().getAbsolutePath());
       }
      
     }
   };
   
   Action opt_exit = new AbstractAction("Stop Server")
   {
     public void actionPerformed(ActionEvent e)
     {
         myparent.runServer();
         dispose();
     }
   };
   
   public void saveLog(String fname)
   {
       try
       {
           FileWriter w = new FileWriter(fname); 
           ta_output.write(w); 
           w.close();
       }
       catch(IOException err)
       {
           JOptionPane.showMessageDialog(this, "ERROR! Could not save file");
       }
   }
    
    public void formWindowClosing(WindowEvent evt)
    {
        myparent.runServer();
    }
    
    public void appendOutput(String newtext)
    {
        String oldtext = ta_output.getText();
        if(!(oldtext.isEmpty()))
        {
            newtext = oldtext + "\n\n" + newtext;
            ta_output.setText(newtext);
        }
        else
        {
            ta_output.setText(newtext);
        }
    }
    
}
