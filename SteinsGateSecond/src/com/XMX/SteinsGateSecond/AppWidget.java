package com.XMX.SteinsGateSecond;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider
{
    Context context;
    
    private int[] num = {R.drawable._0, R.drawable._1, R.drawable._2, R.drawable._3, R.drawable._4, 
    		R.drawable._5, R.drawable._6, R.drawable._7, R.drawable._8, R.drawable._9};
    
    public void Update()
    {
    	RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context,AppWidget.class);
    	Time t=new Time();
    	t.setToNow();
    	int mon = t.month + 1;
    	int day = t.monthDay;
    	int hour = t.hour;
    	int min = t.minute;
    	int sec = t.second;
    	remoteViews.setImageViewResource(R.id.mon1, num[mon/10]);
    	remoteViews.setImageViewResource(R.id.mon2, num[mon%10]);
    	remoteViews.setImageViewResource(R.id.day1, num[day/10]);
    	remoteViews.setImageViewResource(R.id.day2, num[day%10]);
    	remoteViews.setImageViewResource(R.id.hour1, num[hour/10]);
    	remoteViews.setImageViewResource(R.id.hour2, num[hour%10]);
    	remoteViews.setImageViewResource(R.id.min1, num[min/10]);
    	remoteViews.setImageViewResource(R.id.min2, num[min%10]);
    	remoteViews.setImageViewResource(R.id.sec1, num[sec/10]);
    	remoteViews.setImageViewResource(R.id.sec2, num[sec%10]);
    	
    	appWidgetManager.updateAppWidget(componentName, remoteViews);
    }
    
    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        super.onDeleted(context, appWidgetIds);
    }
    
    @Override
    public void onDisabled(Context context)
    {
        super.onDisabled(context);
    }
 
    @Override
    public void onEnabled(Context context)
    {
        super.onEnabled(context);
    }
    
    @Override
    public void onReceive(Context context, Intent intent)
    {
    	super.onReceive(context, intent);
    	if (intent.getAction().equals("Click"))
    	{
    		this.context = context;
    		Update();
    	}
    	else if (intent.getAction().equals("Timer"))
    	{
    		this.context = context;
    		Update();
    	}
    }
 
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
    	super.onUpdate(context, appWidgetManager, appWidgetIds);
        this.context=context;
    	Update();
    	
    	Intent intent1 = new Intent("Click");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
        RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.appwidgetlayout);
        remoteViews.setOnClickPendingIntent(R.id.bg, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    	Intent intent2 = new Intent("Timer");
    	PendingIntent pendIntent = PendingIntent.getBroadcast(context, 0, intent2, 0);
    	int interval = 1 * 1000;
    	alarmMgr.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendIntent);
    }
}