package ru.maxb_pro;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.*;
import java.security.Provider;


public class HomeActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button btn_open_settings = (Button)findViewById(R.id.btn_open_settings);
        btn_open_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                startActivity(intent);
            }
        });

        TextView txt_proc = (TextView)findViewById(R.id.txt_proc);
        TextView txt_memory = (TextView)findViewById(R.id.txt_memory);
        txt_proc.setText(ReadCPUinfo().trim());
        txt_memory.setText(readMemoryInfo());
    }


    private String ReadCPUinfo()
    {
        ProcessBuilder cmd;
        String result="";

        try{
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public String readMemoryInfo()
        {
            String result = "";
            String str1 = "/proc/meminfo";
            String str2;
            String[] arrayOfString;
            long initial_memory = 0;
            try
            {
                FileReader localFileReader = new FileReader(str1);
                BufferedReader localBufferedReader = new BufferedReader(    localFileReader, 8192);
                str2 = localBufferedReader.readLine();//meminfo
                arrayOfString = str2.split("\\s+");
                for (String num : arrayOfString)
                {
                    result = result + str2 + "\n";

                    //Log.i(str2, num + "\t");
                }
                //total Memory
                initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
                localBufferedReader.close();
            }
            catch (IOException e)
            {
            }
            return result;
        }

    }