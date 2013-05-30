/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;
import java.io.*;

/**
 *
 * @author Andréas Forsbom, Helge Holgersson, Jacob Svedman
 * Denna klass innehåller en beskrivning av det objekt som kommer serialiseras och skickas via RMI
 * Denna klass, tillsammans med Api.java utgör själva komponenten. Det är viktigt att denna klass hålls uppdaterad mellan
 * bägge program för att undvika fel.
 * 
 * Av klassen kan ett objekt skapas, innehållande en heltalsarray(long) (sorterad eller osorterad)
 */
public class sortData implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long[] myarray;
    public boolean error = false;    
    private String errormessage = "";
    
    /**
     * Konstruktor, skapar ett serialiserbart objekt innehållande en osorterad eller sorterad heltalsarray(long)
     * @param value - En heltalsarray(long) som objektet ska innehålla
     */
    public sortData(long[] value)
    {
        myarray = value; 
    }
    
    /**
     * Används vid urplockning av den heltalsarray(long) som objektet innehåller
     * @return returnerar den heltalsarray(long) som objektet innehåller
     */
    public long[] getValue()
    {
        return myarray;
    }
    
    /**
     * Används för att sätta ett värde på arrayen i objektet (innehållande flera heltal av typen long)
     * @param value den heltalsarray(long) som objektet innehåller
     */
    public void setValue(long[] value)
    {
        this.myarray = value;
    }
    
    /**
     * Denna metod lägger till ett felmeddelande i objektet så att servern kan berätta för klienten om något fel inträffat
     * under kommunikationen.
     * @param message felmeddelande som servern placerat i objektet om något fel skett på serversidan 
     */
    public void appendErrorMessage(String message)
    {
        this.errormessage += message + " ";
    }
    
    /**
     * Denna metod används vid utskrift av felmeddelanden.
     * @return returnerar de felmeddelanden som samlats ihop under kommunikationen, som en enda lång sträng.
     */
    public String showErrorMessage()
    {
        return errormessage;
    }
}
