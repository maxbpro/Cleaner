package ru.maxb_pro;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;

public class CacheManager
{
    private PackageInfo pInfo = null;
    private Context mContext = null;
    private File dirCache = null;
    private long size = 0;

    public CacheManager(PackageInfo pInfo, Context context)
    {
        try
        {
            this.pInfo = pInfo;
            mContext = context.createPackageContext(pInfo.packageName, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
            dirCache = mContext.getCacheDir();
        }
        catch(PackageManager.NameNotFoundException ex)
        {
            // exception
        }
    }

    public void clearCache()
    {
        if(!pInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString().contains("Платформа Android"))
        {
           if(dirCache != null)
           {
              File appDir = new File(dirCache.getParent());
              if(appDir.exists())
              {
                  String[] children = appDir.list();
                  if(children != null)
                  {
                     for(String s:children)
                     {
                         if(!s.equals("lib"))
                         {
                             deleteDir(new File(appDir, s));
                             Log.i("TAG", "**************************  FILE " + s + " DELETED ******************");
                         }
                     }
                  }
              }
           }
        }
    }


    private boolean deleteDir(File dir)
    {
        if(dir != null && dir.isDirectory())
        {
            String[] children = dir.list();
            for(int i=0; i<children.length; i++)
            {
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success)
                    return false;
            }
        }
        size += dir.length();
        if(size>0)
            size = 0;
        return dir.delete();

    }

    public String getSize()
    {
        return String.valueOf(size);
    }
}
