package android.projects.yatooooo.kz.lo_and_re_app;

import android.app.Application;
import com.backendless.Backendless;

public class BackendlessApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(BackendlessDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), BackendlessDefaults.MY_APP_ID, BackendlessDefaults.API_KEY );
    }
}
