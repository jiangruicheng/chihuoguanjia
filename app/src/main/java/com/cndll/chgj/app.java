package com.cndll.chgj;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by kongqing on 2017/3/24.
 */

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
