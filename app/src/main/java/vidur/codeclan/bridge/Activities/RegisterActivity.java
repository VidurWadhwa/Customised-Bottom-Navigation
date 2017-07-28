package vidur.codeclan.bridge.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vidur.codeclan.bridge.R;
import vidur.codeclan.bridge.Utils.FireBaseMethods;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private EditText registername, registeremail, registerpassword;
    private Button bt_register;
    private ProgressBar progressBar;
    Context mContext;
    FireBaseMethods methods;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRef;
    String email, password, username;
    String append="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registeremail = (EditText) findViewById(R.id.register_email);
        registername = (EditText) findViewById(R.id.register_name);
        registerpassword = (EditText) findViewById(R.id.register_password);
        progressBar = (ProgressBar)findViewById(R.id.register_progress);
        bt_register = (Button) findViewById(R.id.bt_register);

        progressBar.setVisibility(View.GONE);

        methods = new FireBaseMethods(RegisterActivity.this);

        setupFirebaseAuth();

        init();



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
        Log.d(TAG, "init: In init");

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = registeremail.getText().toString();
                password = registerpassword.getText().toString();
                username = registername.getText().toString();
                if (isStringNull(email) || isStringNull(password) || isStringNull(username)){
                    Toast.makeText(getApplicationContext(), "Enter the fields", Toast.LENGTH_SHORT).toString();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    //As soon as this method is called the the AuthState would become signed in if it is true
                    methods.registerUser(email, password, username);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }


    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {


            //This method will be called whenever there is addition or change in the authentication of the user
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                firebaseDatabase = FirebaseDatabase.getInstance();
                mRef = firebaseDatabase.getReference();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            /*
                                Make sure the username is not already in use.......
                             */
                            if(methods.checkName(username, dataSnapshot)){
                                append = mRef.push().getKey().substring(3,10);
                                Log.d(TAG, "onDataChange: username changed to "+" "+append);
                                username = username + append;
                            }

                            //Add new User to the database or add new account settings to the database


                            /**
                             * Method call to add a new user to the database........................
                             */

                            methods.addNewUser(username, username, "", "www.get.com", email);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

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

}
