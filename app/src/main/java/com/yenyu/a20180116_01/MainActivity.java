package com.yenyu.a20180116_01;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        String str=getFilesDir().getAbsolutePath();
        Log.d("FILE",str);
        String str2= getCacheDir().getAbsolutePath();
        Log.d("Cache",str2);
    }

    public void click2(View v) //點擊後在file資料夾寫入檔案及內容
    {
        File f = new File(getFilesDir(),"myfile.txt");
        //new File(路徑,檔案名稱類型)
        //getFilesDir() 跑出可儲存的File路徑
        try {
            FileWriter fw=new FileWriter(f); //filewriter 寫入檔案
            fw.write("what the fffffff"); //輸入內容
            fw.close(); //close後才更新內容
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click3(View v) //點擊寫入檔案及內容到File內部資料夾(直接輸入內容)
    {
        ArrayList<String> mylist = new ArrayList<>();
        File  f = new File (getFilesDir(),"myfile2.txt");
        mylist.add("Peter");
        mylist.add("APPle");
        mylist.add("Louisa");

        try {
            FileWriter fw= new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void click4(View v) //點擊寫入檔案及內容至File內部資料夾(new class)
    {
        ArrayList<Student> mydata = new ArrayList<>();
        mydata.add(new Student(1,"Peter",87));
        mydata.add(new Student(2,"Apple",80));
        mydata.add(new Student(3,"Louisa",97));
        mydata.add(new Student(4,"Any",87));

        File f = new File(getFilesDir(),"mydata.txt");
        try {
            FileWriter fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mydata);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void click5(View v) //點擊輸出內部資料夾的檔案及內容
    {
        File f = new File(getFilesDir(),"myfile2.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str= br.readLine(); //沒有分行寫的程式可以用readline一次性抓出
            Log.d("str",str);
            Gson gson = new Gson();
            ArrayList<String> mydata = gson.fromJson(str, new TypeToken<ArrayList<String>>(){}.getType());
            for (String o:mydata)
            {
                Log.d("FILE","myfile2:"+o);
            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void click6(View v) //點擊輸出內部資料夾的檔案及內容
    {
        File f = new File(getFilesDir(),"mydata.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            ArrayList<Student> myData = gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            for(Student a:myData)
            {
                Log.d("FILE","a" + a.id+","+a.name+","+a.score);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click7(View v) //在外部資料夾建一個data資料夾在app檔案
    {
        File f= getExternalFilesDir("data");
        Log.d("FILE",f.getAbsolutePath());

    }

    public void click8(View v) //指向外部資料夾的外圍
    {
        File f = Environment.getExternalStorageDirectory();
        Log.d("FILE",f.getAbsolutePath());
    }













    class Student
    {
        public int id;
        public String name;
        public int score;

        public Student(int id,String name, int score)
        {
            this.id= id;
            this.name=name;
            this.score=score;
        }
    }


}
