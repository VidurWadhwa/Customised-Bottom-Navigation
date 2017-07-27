package vidur.codeclan.bridge.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import vidur.codeclan.bridge.R;

import static android.view.View.GONE;

public class LoginActivity extends AppCompatActivity {

    TextView tv_Register;
    ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_Register = (TextView) findViewById(R.id.tv_register);
        loginProgressBar = (ProgressBar) findViewById(R.id.login_progress);
        loginProgressBar.setVisibility(GONE);
        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}
