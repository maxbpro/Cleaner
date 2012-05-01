package ru.maxb_pro;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity
{
    private static final String TAB_TAG_PROCESS = "processes";
    private static final String TAB_TAG_CACHE = "cache";
    private static final String TAB_TAG_CLEANER = "cleaner";
    private static final String TAB_TAG_ABOUT = "about";

    private Resources res = null;
    private TabHost tabHost = null;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        tabHost = getTabHost();

        addTab(HomeActivity.class, TAB_TAG_PROCESS, res.getString(R.string.process), res.getDrawable(R.drawable.process));
        addTab(CacheActivity.class, TAB_TAG_CACHE, res.getString(R.string.cache), res.getDrawable(R.drawable.cache));
        addTab(CleanerActivity.class, TAB_TAG_CLEANER, res.getString(R.string.cleaner), res.getDrawable(R.drawable.clear));
        addTab(AboutActivity.class, TAB_TAG_ABOUT, res.getString(R.string.about), res.getDrawable(R.drawable.about));

        tabHost.setCurrentTab(0);
    }

    private void addTab(Class activity, String tag, String text, Drawable icon)
    {
        Intent intent = new Intent().setClass(this, activity);
        TabHost.TabSpec spec = tabHost.newTabSpec(tag).setIndicator(text, icon).setContent(intent);
        tabHost.addTab(spec);
    }
}
