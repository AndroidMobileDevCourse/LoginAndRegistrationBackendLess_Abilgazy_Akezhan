package android.projects.yatooooo.kz.lo_and_re_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private Button ho_sign_out;
    private TextView ho_fullname;
    private TextView ho_email;
    private TextView ho_username;
    private TextView ho_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViewElements();
        updateViewElementValues();
    }

    private void initViewElements() {
        ho_fullname = (TextView) findViewById(R.id.ho_fullname);
        ho_email = (TextView) findViewById(R.id.ho_email);
        ho_username = (TextView) findViewById(R.id.ho_username);
        ho_password = (TextView) findViewById(R.id.ho_password);
        ho_sign_out = (Button) findViewById(R.id.ho_sign_out_button);

        ho_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singOut();
            }
        });
    }

    private void updateViewElementValues(){
        UserAccount currentUA = BackendlessDefaults.CURRENT_USER_ACCOUNT;
        ho_fullname.setText(currentUA.getFullname());
        ho_email.setText(currentUA.getEmail());
        ho_username.setText(currentUA.getUsername());
        ho_password.setText(currentUA.getPassword());
    }

    private void singOut(){
        BackendlessDefaults.CURRENT_USER_ACCOUNT = null;
        startLoginActivity();
    }

    private void startLoginActivity(){
        Intent login = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(login);
    }
}
