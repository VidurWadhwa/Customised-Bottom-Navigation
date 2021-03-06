package vidur.codeclan.bridge.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vidur.codeclan.bridge.POJO.User;
import vidur.codeclan.bridge.POJO.UserAccountSettings;
import vidur.codeclan.bridge.R;

/**
 * Created by vidur on 7/28/2017.
 */

public class FireBaseMethods {

    private static final String TAG = "FireBaseMethods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String UserID;
    Context c;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FireBaseMethods(Context c) {
        this.c = c;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            UserID = mAuth.getCurrentUser().getUid();
        }
    }


    /**
    ----------------------------------------------------------- Check UserName
    */

    public boolean checkName(String username, DataSnapshot dataSnapshot) {
        Log.d(TAG, "checkName: Checking name");
        User user = new User();
        for(DataSnapshot dsp: dataSnapshot.child(UserID).getChildren()){

            Log.d(TAG, "checkName: Looping through data " + dsp.toString());
            //user.setUsername(dsp.getCgetValue(User.class).getUsername());
            user.setUsername(dsp.getValue(User.class).getUsername());
            Log.d(TAG, "checkName: Username is" + user.getUsername());
            if(StringManipulator.expandUsername(user.getUsername()).equals(username)){
                Log.d(TAG, "checkName: Found a match for "+ username);
                return true;
            }
            
        }
        return false;
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------------------------------
     */


    /**
    ---------------------------------------------------------------------------------------------------------------------------Register a new User
    */

    public void registerUser(String email, String password, String username) {

        //oncomplete does not let the context to be added over here
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(c, "Failed to register",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(c, "Registered Successfully",
                                    Toast.LENGTH_SHORT).show();
                            UserID = mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "User signed up Properly");

                        }


                    }
                });
    }

    /**
     * -----------------------------------------------------------------------------------------------------------------------------------------
     */

    /**
     * ---------------------------------------------------------------------------------------------------------------- Add a new User to the database
     */

    public void addNewUser(String description, String username, String profile_photo, String website, String email) {

        User user = new User(email, UserID, StringManipulator.condenseUsername(username), 1);

        myRef.child("user_edit").child(UserID).setValue(user);

        UserAccountSettings userAccountSettings = new UserAccountSettings(description, username,
                                                    0, 0, 0, "",
                                                    username, website);

        myRef.child("user_account_settings").child(UserID).setValue(userAccountSettings);


    }


}
