package com.heaven7.android.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class InstrumentationProxy extends Instrumentation {

    private Instrumentation mInstrumentation;
    private PackageManager mPackageManager;

    public InstrumentationProxy(Context context, Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
        mPackageManager = context.getPackageManager();
    }
    /*public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        List<ResolveInfo> infos = mPackageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (infos == null || infos.size() == 0) {
            intent.putExtra(HookHelper.TARGET_INTENsT_NAME, intent.getComponent().getClassName());//1
            intent.setClassName(who, "com.example.liuwangshu.pluginactivity.StubActivity");//2
        }
        try {
            Method execMethod = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return (ActivityResult) execMethod.invoke(mInstrumentation, who, contextThread, token,
                    target, intent, requestCode, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        int type = intent.getIntExtra(HookCons.KEY_TYPE, 0);

        if(type == HookCons.TYPE_REPLACE_ACTIVITY){
            Intent src = intent.getParcelableExtra(HookCons.KEY_SRC_INTENT);
            return (Activity) cl.loadClass(src.getComponent().getClassName()).newInstance();
        }
        return mInstrumentation.newActivity(cl, className, intent);
    }

}
