package com.bhardwaj.foodlovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {
    Button bt_signUp;
    EditText username, email, password, mob;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobPattern = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
    private TextWatcher signUpTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String emailInput = email.getText().toString().trim();
            String passInput = password.getText().toString().trim();
            String userInput = username.getText().toString().trim();
            String mobInput = mob.getText().toString().trim();
            bt_signUp.setEnabled(!emailInput.isEmpty() && !passInput.isEmpty() && !userInput.isEmpty() && !mobInput.isEmpty() && emailInput.matches(emailPattern) && mobInput.matches(mobPattern));
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String emailInput = email.getText().toString().trim();
            String mobInput = mob.getText().toString().trim();
            if (!emailInput.matches(emailPattern) && emailInput.length() > 0) {
                email.setTextColor(getResources().getColor(R.color.colorError));
            } else {
                email.setTextColor(getResources().getColor(R.color.colorTextField));
            }
            if (!mobInput.matches(mobPattern) && mobInput.length() > 0) {
                mob.setTextColor(getResources().getColor(R.color.colorError));
            } else {
                mob.setTextColor(getResources().getColor(R.color.colorTextField));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        bt_signUp = findViewById(R.id.bt_signup_signup);
        email = findViewById(R.id.email_editTxt_signup);
        password = findViewById(R.id.pass_editTxt_signup);
        username = findViewById(R.id.username_editTxt_signup);
        mob = findViewById(R.id.mobile_editTxt_signup);
        email.addTextChangedListener(signUpTextWatcher);
        password.addTextChangedListener(signUpTextWatcher);
        username.addTextChangedListener(signUpTextWatcher);
        mob.addTextChangedListener(signUpTextWatcher);
    }

    public void signIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        String emailInput = email.getText().toString().trim();
        String passInput = password.getText().toString().trim();
        String userInput = username.getText().toString().trim();
        String mobInput = mob.getText().toString().trim();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        databaseHelper.addUser(userInput, passInput, emailInput, mobInput, database);
        databaseHelper.close();
        email.setText("");
        password.setText("");
        username.setText("");
        mob.setText("");

        Toast.makeText(this, "Signed Up", Toast.LENGTH_SHORT).show();
        view.setEnabled(false);
    }

}
