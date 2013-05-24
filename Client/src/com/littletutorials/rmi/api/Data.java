/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.littletutorials.rmi.api;

import java.io.*;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private int value;

    public Data(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
