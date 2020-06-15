package com.bhardwaj.foodlovers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bhardwaj.foodlovers.R;
import com.bhardwaj.foodlovers.DB.SharedPreferenceConfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    Button bt_signIn;
    public FirebaseAuth mAuth;
    SharedPreferenceConfig preferenceConfig;
    EditText email, password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z0-9]+\\.+[a-z]+";
    private TextWatcher signInTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = email.getText().toString().trim();
            String passInput = password.getText().toString().trim();
            bt_signIn.setEnabled(!emailInput.isEmpty() && !passInput.isEmpty() && emailInput.matches(emailPattern));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String emailInput = email.getText().toString().trim();
            if (!emailInput.matches(emailPattern) && emailInput.length() > 0) {
                email.setTextColor(getResources().getColor(R.color.colorError));
            } else {
                email.setTextColor(getResources().getColor(R.color.colorTextField));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        bt_signIn = findViewById(R.id.bt_signup_signup);
        email = findViewById(R.id.username_editTxt_signup);
        password = findViewById(R.id.pass_editTxt_signup);
        email.addTextChangedListener(signInTextWatcher);
        password.addTextChangedListener(signInTextWatcher);
        preferenceConfig.writeOnboardingStatus(true);
    }

    public void signIn(View view) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        Toast.makeText(getBaseContext(), "Signed In.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), MainHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(getBaseContext(), "Incorrect Email Address", Toast.LENGTH_SHORT).show();
                    } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getBaseContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void forgotPass(View view) {
        String emailInput = email.getText().toString();
        if (emailInput != null && !emailInput.isEmpty()) {
            mAuth.sendPasswordResetEmail(emailInput).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "Password reset email sent", Toast.LENGTH_SHORT).show();
                    } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(getBaseContext(), "Email is not registered", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getBaseContext(), "Enter email first.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainHomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
