package com.example.wordfindertwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;

public class StandartDictionary {

	static public ArrayList<String> getEnglishList() {
		Context context = MyApp.getContext();
        
        InputStream inputStream = context.getResources().openRawResource(R.raw.english);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        ArrayList<String> list = new ArrayList<String>();
        
        try {
            while (( line = buffreader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            return null;
        }
        return list;
	}
	
}
