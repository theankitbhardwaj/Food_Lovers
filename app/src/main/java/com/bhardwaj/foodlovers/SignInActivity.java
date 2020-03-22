package com.bhardwaj.foodlovers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    Button bt_signIn;
    SharedPreferenceConfig preferenceConfig;
    DatabaseHelper db;
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
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        db = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        bt_signIn = findViewById(R.id.bt_signup_signup);
        email = findViewById(R.id.username_editTxt_signup);
        password = findViewById(R.id.pass_editTxt_signup);
        email.addTextChangedListener(signInTextWatcher);
        password.addTextChangedListener(signInTextWatcher);

    }

    public void signIn(View view) {
        if (db.checkUser(email.getText().toString().trim(), password.getText().toString().trim())) {
            Intent intent = new Intent(this, MainHomeActivity.class);
            startActivity(intent);
            preferenceConfig.writeLoginStatus(true);
            view.setEnabled(false);
        } else {
            view.setEnabled(false);
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            view.setEnabled(true);
        }
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void forgotPass(View view) {
        Toast.makeText(this, "Forgot password opened...", Toast.LENGTH_SHORT).show();
    }

    public void googleSignIn(View view) {
        Toast.makeText(this, "Google signin opened...", Toast.LENGTH_SHORT).show();
    }

    public void fbSignIn(View view) {
        Toast.makeText(this, "Facebook signin opened...", Toast.LENGTH_SHORT).show();
    }

}
