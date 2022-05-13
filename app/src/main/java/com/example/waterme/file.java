package com.example.waterme;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class file {
    public static final String fName = "listData.dat";


    public static void saving(ArrayList<String> arrList, Context context){
       try{
           FileOutputStream fOutput = context.openFileOutput(fName, Context.MODE_PRIVATE);
           ObjectOutputStream oOutput = new ObjectOutputStream(fOutput);
           oOutput.writeObject(arrList);
           oOutput.close();
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
    }


    public static ArrayList<String> readData(Context context){
        ArrayList<String> list = null;
        try{
            FileInputStream fInput = context.openFileInput(fName);
            ObjectInputStream oInput = new ObjectInputStream(fInput);
            list = (ArrayList<String>) oInput.readObject();
        }
        catch (FileNotFoundException e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
