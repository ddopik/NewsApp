package com.codecourt.ddopikmain.seedapplication.app.Presenter.AppConfig;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.AppComponent;

import com.codecourt.ddopikmain.seedapplication.app.Dagger.Componentes.DaggerAppComponent;
import com.codecourt.ddopikmain.seedapplication.app.Dagger.Modules.MainAppModule;
import com.codecourt.ddopikmain.seedapplication.app.Model.SourceListModel;
import com.example.networkmodule.VolleySingleton;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by ddopikMain on 2/28/2017.
 */

///this is (Application) class will provide Dagger Depandaces
// for all activites as our application Running
public class MainApp extends Application  {
//
    private AppComponent appComponent;
    public static MainApp app;
    public static Realm realm;
    public static final String SittingActivity_sharedPreferance = "MyPrefsFile";
    private SourceListModel sourceListModel;
    public static RequestQueue mRequestQueue;
    public static ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        initializeDepInj(); ///intializing Dagger Dependancy
        intializeRealmInstance(); //intializing Realm Config Instance
//        deleteCache(app);   ///for developing
        SharedPreferences.Editor editor2 = getApplicationContext().getSharedPreferences(SittingActivity_sharedPreferance, MODE_PRIVATE).edit();
        editor2.putString("notification_switch","true");
        editor2.putString("source_switch","true");
        editor2.commit();

        VolleySingleton.appContext=app;
        mRequestQueue=VolleySingleton.getInstance().getRequestQueue();
        mImageLoader=VolleySingleton.getInstance().getImageLoader();

    }

    private void initializeDepInj() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .mainAppModule(new MainAppModule(this)).build();
//            appComponent.inject(this);  //this App don't Need Any Dependancyes

            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                            .build());

        }
    }


    public static MainApp getMainAppApplication() {

        return app;
    }

    public AppComponent getAppComponent() {

        return appComponent;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void intializeRealmInstance() {
        // Initialize Realm
        Realm.init(app); // Initialize Realm. Should only be done once when the application starts.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();

//        Realm.deleteRealm(realmConfig); // Delete Realm between app restarts.


        Realm.setDefaultConfiguration(realmConfig);
        this.realm = Realm.getDefaultInstance();

        sourceListModel=new SourceListModel();
        sourceListModel.intializeList();
        sourceListModel.intializeCatList();

    }

    public static  boolean isInternetAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if(activeNetworkInfo == null) {
            connected = false;
        }
        else
        {
            connected=true;
        }
        Log.e("MainApp","ConnectionState---->"+connected+activeNetworkInfo);
        return connected;
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}

