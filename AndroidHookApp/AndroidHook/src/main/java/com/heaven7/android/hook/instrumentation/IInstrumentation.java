package com.heaven7.android.hook.instrumentation;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by heaven7 on 2019/6/12.
 */
public interface IInstrumentation {
    
    android.os.TestLooperManager acquireLooperManager(android.os.Looper looper);

    Instrumentation.ActivityResult onStartActivity(Intent intent);
    Activity waitForActivityWithTimeout(long timeOut);

    void removeMonitor(Instrumentation.ActivityMonitor monitor);
    Activity waitForMonitorWithTimeout(Instrumentation.ActivityMonitor monitor, long timeOut);
    Activity waitForMonitor(Instrumentation.ActivityMonitor monitor);
    boolean checkMonitorHit(Instrumentation.ActivityMonitor monitor, int minHits);
    Instrumentation.ActivityMonitor addMonitor(
            String cls, Instrumentation.ActivityResult result, boolean block);
    Instrumentation.ActivityMonitor addMonitor(IntentFilter filter, Instrumentation.ActivityResult result, boolean b);
    void addMonitor(Instrumentation.ActivityMonitor monitor);
    void addResults(android.os.Bundle b);

    void callActivityOnCreate(Activity activity,android.os.Bundle b);
    void callActivityOnCreate(Activity activity,android.os.Bundle b, android.os.PersistableBundle pb);
    void callActivityOnDestroy(Activity activity);
    void callActivityOnNewIntent(Activity activity,android.content.Intent intent);

    //void callActivityOnNewIntent(Activity activity,com.android.internal.content.ReferrerIntent)
    void callActivityOnNewIntent(Activity activity, Object referIntent);

    void callActivityOnPause(Activity activity);
    void callActivityOnPostCreate(Activity activity,android.os.Bundle b);
    void callActivityOnPostCreate(Activity activity,android.os.Bundle b,android.os.PersistableBundle pb);
    void callActivityOnRestart(Activity activity);
    void callActivityOnRestoreInstanceState(Activity activity, android.os.Bundle b);
    void callActivityOnRestoreInstanceState(Activity activity,android.os.Bundle b,android.os.PersistableBundle pb);
    void callActivityOnResume(Activity activity);
    void callActivityOnSaveInstanceState(Activity activity,android.os.Bundle b);
    void callActivityOnSaveInstanceState(Activity activity,android.os.Bundle b,android.os.PersistableBundle pb);
    void callActivityOnStart(Activity activity);
    void callActivityOnStop(Activity activity);
    void callActivityOnUserLeaving(Activity activity);
    void callApplicationOnCreate(Application application);

    void endPerformanceSnapshot();
    void startPerformanceSnapshot();

    void execStartActivities(Context context, IBinder b1, IBinder b2, Activity activity, android.content.Intent[] intents, android.os.Bundle b);
    void execStartActivitiesAsUser(Context context,IBinder b1,IBinder b2,Activity activity,android.content.Intent[] intents,android.os.Bundle b,int code);

    Instrumentation.ActivityResult execStartActivity(Context context, IBinder b1, IBinder b2, Activity activity,
                                                     android.content.Intent intent, int code, android.os.Bundle b);
    Instrumentation.ActivityResult execStartActivity(Context context,IBinder b1 ,IBinder b2,String str,
                                                     android.content.Intent intent,int code ,android.os.Bundle b);
    Instrumentation.ActivityResult execStartActivity(Context context,IBinder b1,IBinder b2,String str,
                                                     android.content.Intent intent,int code,android.os.Bundle b,android.os.UserHandle uh);
    Instrumentation.ActivityResult execStartActivityAsCaller(Context context,IBinder b1 ,IBinder b2,
                                                             Activity activity,android.content.Intent intent,
                                                             int c1,android.os.Bundle bun,boolean b, int c2);
    //IAppTask appTask
    void execStartActivityFromAppTask(Context who, IBinder contextThread, Object appTask, Intent intent, Bundle options);

    Activity newActivity(Class<?> clazz, Context context,
                         IBinder token, Application application, Intent intent, ActivityInfo info,
                         CharSequence title, Activity parent, String id,
                         Object lastNonConfigurationInstance) throws InstantiationException,
            IllegalAccessException;

    Activity newActivity(ClassLoader cl, String className,
                         Intent intent);


    void sendTrackballEventSync(MotionEvent event);
    void sendPointerSync(MotionEvent event);
    void sendCharacterSync(int keyCode);
    void sendKeyDownUpSync(int key);
    void sendKeySync(KeyEvent event);
    void sendStringSync(String text);
    boolean invokeContextMenuAction(Activity targetActivity, int id, int flag);
    boolean invokeMenuActionSync(Activity targetActivity,
                                 int id, int flag);

    boolean match(Context who, Activity activity, Intent intent);
    void finish(int code, Bundle b);
    Bundle getAllocCounts();
    Bundle getBinderCounts();
    ComponentName getComponentName();
    Context getContext();
    String getProcessName();
    Context getTargetContext();
}
