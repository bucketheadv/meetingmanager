package com.thoughtworks.meeting.io;

import com.thoughtworks.meeting.utils.PathUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by sven on 14-12-24.
 */
public class EventReader {
    public static ArrayList<String> getInput(String filePath){
        filePath = PathUtils.getPath(filePath);
        ArrayList<String> retList = new ArrayList<String>();
        File file = new File(filePath);
        if (!file.exists()){
            return null;
        }
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null){
                retList.add(line);
                //System.out.println(line);
                line = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                br.close();
                fr.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return retList;
    }
}
