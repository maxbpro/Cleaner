package ru.maxb_pro;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class LabelView extends FrameLayout
{
    private PackageInfo pInfo = null;
    private String applicationName = null;

    public LabelView(PackageInfo pInfo, Context mContext)
    {
        super(mContext);
        this.pInfo = pInfo;
        Drawable icon = pInfo.applicationInfo.loadIcon(mContext.getPackageManager());
        applicationName = pInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString();

        LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.label,this,true);

        ((ImageView)findViewById(R.id.application_icon)).setImageDrawable(icon);
        ((TextView)findViewById(R.id.application_txt)).setText(applicationName);
    }

    public PackageInfo getPackageInfo()
    {
        return pInfo;
    }

    public String getApplicationName()
    {
        return applicationName;
    }


}
