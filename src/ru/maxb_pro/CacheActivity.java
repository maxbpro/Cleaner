package ru.maxb_pro;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class CacheActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cache);

        final List<PackageInfo> packages =  getPackageManager().getInstalledPackages(0);
        ListView listView = (ListView)findViewById(R.id.list);
        ApplicationAdapter adapter = new ApplicationAdapter(packages, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                LabelView label  = (LabelView)view;
                CacheManager manager = new CacheManager(label.getPackageInfo(), getApplicationContext());
                manager.clearCache();
                Toast.makeText(getApplicationContext(),
                        "Кэш приложения " + label.getApplicationName() +" " + manager.getSize() + " был очищен",
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_clear_all = (Button)findViewById(R.id.btn_delete_all);
        btn_clear_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                long size = 0;
                for(PackageInfo pInfo: packages)
                {
                    CacheManager manager = new CacheManager(pInfo, getApplicationContext());
                    manager.clearCache();
                    size += Long.parseLong(manager.getSize());
                }
                Toast.makeText(getApplicationContext(),
                       "Очищено " + size,
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}