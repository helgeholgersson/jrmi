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

/**
 *
 * @author seho0002
 */
public class Quicksort  {
  private long[] numbers;
  private int number;

  public void sort(long[] values)
  {
    // Check for empty or null array
    if (values ==null || values.length==0)
    {
      return;
    }
    this.numbers = values;
    number = values.length;
    quicksort(0, number - 1);
  }

  private void quicksort(int low, int high) {
    int i = low, j = high;
    // Get the pivot element from the middle of the list
    long pivot = numbers[low + (high-low)/2];

    // Divide into two lists
    while (i <= j) {
      // If the current value from the left list is smaller then the pivot
      // element then get the next element from the left list
      while (numbers[i] < pivot) {
        i++;
      }
      // If the current value from the right list is larger then the pivot
      // element then get the next element from the right list
      while (numbers[j] > pivot) {
        j--;
      }

      // If we have found a values in the left list which is larger then
      // the pivot element and if we have found a value in the right list
      // which is smaller then the pivot element then we exchange the
      // values.
      // As we are done we can increase i and j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    // Recursion
    if (low < j)
      quicksort(low, j);
    if (i < high)
      quicksort(i, high);
  }

  private void exchange(int i, int j)
  {
    long temp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = temp;
  }
  
  public long[] getNumbers()
  {
      return numbers;
  }
}
