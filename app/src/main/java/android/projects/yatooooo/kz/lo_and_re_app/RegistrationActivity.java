package android.projects.yatooooo.kz.lo_and_re_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    private TextView re_info;
    private EditText re_fullname;
    private EditText re_email;
    private EditText re_username;
    private EditText re_password;
    private EditText re_confirm_password;
    private Button re_sign_in_button;
    private Button re_sign_up_button;

    private String re_info_str;
    private String re_fullname_str;
    private String re_email_str;
    private String re_username_str;
    private String re_password_str;
    private String re_confirm_password_str;

    private boolean isUAAlreadyExist = false;
    private ArrayList<String> ua_usernames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        clearUserAccountParams();
        initViewElements();
    }

    private void clearViewElements(){
        re_info.setText("");
        re_fullname.setText("");
        re_email.setText("");
        re_username.setText("");
        re_password.setText("");
        re_confirm_password.setText("");
    }

    private void clearUserAccountParams(){
        re_fullname_str = null;
        re_email_str = null;
        re_username_str = null;
        re_password_str = null;
        re_confirm_password_str = null;
    }

    private void updateInfoMessage(){
        re_info.setText(re_info_str);
    }

    private void initViewElements(){
        re_info = (TextView) findViewById(R.id.re_info);
        re_fullname = (EditText) findViewById(R.id.re_fullname);
        re_email = (EditText) findViewById(R.id.re_email);
        re_username = (EditText) findViewById(R.id.re_username);
        re_password = (EditText) findViewById(R.id.re_password);
        re_confirm_password = (EditText) findViewById(R.id.re_confirm_password);
        re_sign_in_button = (Button) findViewById(R.id.re_sign_in_button);
        re_sign_up_button = (Button) findViewById(R.id.re_sign_up_button);

        re_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });

        re_sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initUserAccountParams();
                uploadUsername();
            }
        });
    }

    private void initUserAccountParams(){
        re_fullname_str = re_fullname.getText().toString();
        re_email_str = re_email.getText().toString();
        re_username_str = re_username.getText().toString();
        re_password_str = re_password.getText().toString();
        re_confirm_password_str = re_confirm_password.getText().toString();
    }

    private void validate(){
        if(isValid()){
            registerNewUserAccount();
        }else{
            updateInfoMessage();
        }
    }

    private boolean isValid(){
        if(re_username_str==null || re_username_str.equals("")){
            re_info_str = "username can not empty";
            return false;
        }

        if(ua_usernames.contains(re_username_str)){
            re_info_str = "username '"+re_username_str+"' already exist";
            return false;
        }

        if(re_password_str==null || re_password_str.equals("")){
            re_info_str = "password can not empty";
            return false;
        }

        if(re_confirm_password_str==null || re_confirm_password_str.equals("")){
            re_info_str = "confirm password can not empty";
            return false;
        }

        if(!re_password_str.equals(re_confirm_password_str)){
            re_info_str = "password and confirm password is not equals";
            return false;
        }

        return true;
    }



    private void uploadUsername(){
        ua_usernames = new ArrayList<>();
        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        Backendless.Persistence.of(UserAccount.class).find(dataQueryBuilder, new BackendlessCallback<List<UserAccount>>() {
            @Override
            public void handleResponse(List<UserAccount> response) {
                try{
                    for(UserAccount ua:response){
                        ua_usernames.add(ua.getUsername());
                    }
                    validate();
                }catch (Exception e){
                }

            }
        });
    }

    private void startLoginActivity(){
        Intent login = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(login);
    }

    private void registerNewUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setFullname(re_fullname_str);
        userAccount.setEmail(re_email_str);
        userAccount.setUsername(re_username_str);
        userAccount.setPassword(re_password_str);
        Backendless.Data.of(UserAccount.class).save(userAccount, new AsyncCallback<UserAccount>() {
            @Override
            public void handleResponse(UserAccount response) {
                startLoginActivity();
                Toast.makeText(getApplicationContext(), "You are register successfully!",Toast.LENGTH_LONG).show();
                clearViewElements();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                re_info_str = fault.getMessage();
                updateInfoMessage();
            }
        });

//        BackendlessUser backendlessUser = new BackendlessUser();
//        backendlessUser.setEmail(BackendlessDefaults.MY_EMAIL_ADDRESS);
//        backendlessUser.setPassword(BackendlessDefaults.MY_PASSWORD);
//        Backendless.UserService.register(backendlessUser, new BackendlessCallback<BackendlessUser>() {
//            @Override
//            public void handleResponse(BackendlessUser response) {
//                System.out.println(response);
//            }
//        });
    }
}
