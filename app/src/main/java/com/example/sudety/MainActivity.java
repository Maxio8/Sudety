package com.example.sudety;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int likesCount = 0;
    private TextView likesCounter;
    private EditText emailInput, passwordInput, repeatPasswordInput;
    private TextView messageArea;
    private String lastRegisteredEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        likesCounter = findViewById(R.id.likesCounter);
        Button likeButton = findViewById(R.id.likeButton);
        Button removeButton = findViewById(R.id.removeButton);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        repeatPasswordInput = findViewById(R.id.repeatPasswordInput);
        Button saveButton = findViewById(R.id.saveButton);
        Button showParticipantButton = findViewById(R.id.showParticipantButton);
        messageArea = findViewById(R.id.messageArea);


        likeButton.setOnClickListener(view -> {
            likesCount++;
            updateLikesCounter();
        });


        removeButton.setOnClickListener(view -> {
            if (likesCount > 0) {
                likesCount--;
                updateLikesCounter();
            }
        });


        saveButton.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String repeatPassword = repeatPasswordInput.getText().toString();

            if (!isValidEmail(email)) {
                messageArea.setText("Nieprawidłowy adres e-mail");
                return;
            }

            if (!password.equals(repeatPassword)) {
                messageArea.setText("Hasła się różnią");
                return;
            }

            lastRegisteredEmail = email;
            messageArea.setText("Zarejestrowano: " + email);
        });


        showParticipantButton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(lastRegisteredEmail)) {
                messageArea.setText("Brak zarejestrowanego uczestnika");
            } else {
                messageArea.setText(lastRegisteredEmail);
            }
        });
    }


    private void updateLikesCounter() {
        likesCounter.setText(likesCount + " polubień");
    }


    private boolean isValidEmail(String email) {
        return email.contains("@") && !TextUtils.isEmpty(email);
    }
}
