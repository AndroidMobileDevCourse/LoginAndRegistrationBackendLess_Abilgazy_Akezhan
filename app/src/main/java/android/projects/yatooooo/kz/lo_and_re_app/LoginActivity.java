package android.projects.yatooooo.kz.lo_and_re_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private TextView lo_info;
    private EditText lo_username;
    private EditText lo_password;
    private Button lo_sign_in_button;
    private Button lo_sign_up_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lo_info = (TextView) findViewById(R.id.lo_info) ;
        lo_username = (EditText)findViewById(R.id.lo_username);
        lo_password = (EditText)findViewById(R.id.lo_password);
        lo_sign_in_button = (Button) findViewById(R.id.lo_sign_in_button);
        lo_sign_up_button = (Button)findViewById(R.id.lo_sign_up_button);

        lo_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lo_username_str = lo_username.getText().toString();
                String lo_password_str = lo_password.getText().toString();
                signIn(lo_username_str, lo_password_str);
            }
        });

        lo_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistrationActivity();
            }
        });
    }

    private void signIn(String username, String password){
        String whereClause = "username = '"+username+"' and password = '"+password+"'";
        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        dataQueryBuilder.setWhereClause(whereClause);

        Backendless.Persistence.of(UserAccount.class).find(dataQueryBuilder, new AsyncCallback<List<UserAccount>>() {
            @Override
            public void handleResponse(List<UserAccount> response) {
                if(response.size()>0){
                    BackendlessDefaults.CURRENT_USER_ACCOUNT = response.get(0);
                    startMainActivity();
                    updateInfoMessage("");
                }else{
                    updateInfoMessage("Username or Password is invalid!!!");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                updateInfoMessage(fault.getMessage());
            }
        });
    }

    private void startRegistrationActivity(){
        Intent registration = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registration);
    }

    private void startMainActivity(){
        Intent main = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(main);
    }

    private void updateInfoMessage(String message){
        lo_info.setText(message);
    }
}
