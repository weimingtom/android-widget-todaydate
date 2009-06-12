package com.obsidian.free.today;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.widget.RemoteViews;

public class TodaysDateProvider extends AppWidgetProvider {
   @Override
   public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
      RemoteViews updateView = buildUpdate(context);
      appWidgetManager.updateAppWidget(appWidgetIds, updateView);
      super.onUpdate(context, appWidgetManager, appWidgetIds);
   }
   
   private String[] months = {"January", "Februrary", "March", "April",
                              "May", "June", "July", "August",
                              "September", "October", "November", "December"};
   private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday",
                              "Thursday", "Friday", "Saturday"};
   
   private RemoteViews buildUpdate(Context context) {
      RemoteViews updateView = null;
      Time time = new Time();
      time.setToNow();
      String month = months[time.month] + " " + time.year;
      updateView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
      updateView.setTextViewText(R.id.Date, new Integer(time.monthDay).toString());
      updateView.setTextViewText(R.id.Month, month);
      updateView.setTextViewText(R.id.WeekDay, days[time.weekDay]);
      Intent launchIntent = new Intent();
      launchIntent.setComponent(new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity"));
      launchIntent.setAction(Intent.ACTION_MAIN);
      launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
      launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
      PendingIntent intent = PendingIntent.getActivity(context, 0, launchIntent, 0);
      updateView.setOnClickPendingIntent(R.id.Base, intent);
      return updateView;
   }
}