package ru.maxb_pro;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ApplicationAdapter extends BaseAdapter
{
    private List<PackageInfo> packages = null;
    private Context mContext = null;

    public ApplicationAdapter(List<PackageInfo> packages, Context mContext)
    {
        this.packages = packages;
        this.mContext = mContext;
    }

    @Override
    public int getCount()
    {
        return packages.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LabelView label = new LabelView(packages.get(i), mContext);
        return label;

    }
}
