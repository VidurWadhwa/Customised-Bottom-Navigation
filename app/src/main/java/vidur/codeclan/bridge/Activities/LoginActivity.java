package vidur.codeclan.bridge.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vidur.codeclan.bridge.R;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    TextView tv_Register;
    ProgressBar loginProgressBar;
    EditText email;
    EditText password;
    Button bt_login;

    private Context mContext = LoginActivity.this;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);


        //mAuth = FirebaseAuth.getInstance();

        tv_Register = (TextView) findViewById(R.id.tv_register);
        loginProgressBar = (ProgressBar) findViewById(R.id.login_progress);


        loginProgressBar.setVisibility(GONE);

        setupFirebaseAuth();

        init();


        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }


    /*
    -----------------------------------------------------------checkNullString
     */

    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: checking string if null.");

        if(string.equals("")){
            return true;
        }
        else{
            return false;
        }
    }


    /*
    ------------------------------------------------------------ firebase stuff
     */

    private void init() {
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                if(isStringNull(userEmail) && isStringNull(userPassword)) {
                    Toast.makeText(getApplicationContext(), "Enter both", Toast.LENGTH_SHORT).toString();
                } else {
                    loginProgressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());

                                Toast.makeText(LoginActivity.this, "Failed Login",
                                        Toast.LENGTH_SHORT).show();
                                loginProgressBar.setVisibility(View.GONE);

                            }
                            else{
                                Log.d(TAG, "signInWithEmail: successful login");
                                Toast.makeText(LoginActivity.this, "Success" + mAuth.getCurrentUser().toString(),
                                        Toast.LENGTH_SHORT).show();
                                loginProgressBar.setVisibility(View.GONE);
                                if(mAuth.getCurrentUser() != null) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else{
                                    Toast.makeText(getApplicationContext(), "Some Error", Toast.LENGTH_LONG).show();
                                }
                            }

                            // ...
                        }
                    });
                }
            }
        });


    }


    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /*
    *-------------------------------------Method handles the login.....
     */

    private void handleLogin() {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        if(userEmail == null || userPassword == null) {
            Toast.makeText(getApplicationContext(), "Please enter both the fields", Toast.LENGTH_SHORT).show();
        } else {
            loginProgressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail:failed", task.getException());
                                loginProgressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d(TAG, "onComplete: Successful login");
                                loginProgressBar.setVisibility(View.GONE);
                                //Toast.makeText(LoginActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }

        if(mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }


}
