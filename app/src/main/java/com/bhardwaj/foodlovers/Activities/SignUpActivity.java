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

import com.bhardwaj.foodlovers.DB.SharedPreferenceConfig;
import com.bhardwaj.foodlovers.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SignUpActivity extends AppCompatActivity {
    Button bt_signUp;
    EditText username, email, password;
    private FirebaseAuth mAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobPattern = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
    SharedPreferenceConfig preferenceConfig;
    private TextWatcher signUpTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = email.getText().toString().trim();
            String passInput = password.getText().toString().trim();
            String userInput = username.getText().toString().trim();
            bt_signUp.setEnabled(!emailInput.isEmpty() && !passInput.isEmpty() && !userInput.isEmpty() && emailInput.matches(emailPattern));
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        bt_signUp = findViewById(R.id.bt_signup_signup);
        email = findViewById(R.id.email_editTxt_signup);
        password = findViewById(R.id.pass_editTxt_signup);
        username = findViewById(R.id.username_editTxt_signup);
        email.addTextChangedListener(signUpTextWatcher);
        password.addTextChangedListener(signUpTextWatcher);
        username.addTextChangedListener(signUpTextWatcher);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void signUp(final View view) {
        String emailInput = email.getText().toString().trim();
        String passInput = password.getText().toString().trim();
        final String userInput = username.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(emailInput, passInput).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        Intent i = new Intent(getBaseContext(), SignInActivity.class);
                        startActivity(i);
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(userInput)
                                .build();
                        user.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                email.setText("");
                                password.setText("");
                                username.setText("");
                                Toast.makeText(getBaseContext(), "Signed Up.", Toast.LENGTH_SHORT).show();
                                view.setEnabled(false);
                                finish();
                            }
                        });

                    }
                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getBaseContext(), "Email already in use", Toast.LENGTH_SHORT).show();
                        view.setEnabled(true);
                    }
                }
            }
        });
    }

}
