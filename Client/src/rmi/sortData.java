/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;
import java.io.*;

/**
 *
 * @author seho0002
 */
public class sortData implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int[] myarray;
    public boolean error = false;    
    private String errormessage = "";
    
    public sortData(int[] value)
    {
        myarray = value; 
    }
    
    public int[] getValue()
    {
        return myarray;
    }

    public void setValue(int[] value)
    {
        this.myarray = value;
    }
    
    public void appendErrorMessage(String message)
    {
        this.errormessage += message + " ";
    }
    
    public String showErrorMessage()
    {
        return errormessage;
    }
}
