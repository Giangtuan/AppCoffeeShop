package com.example.foodorder.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorder.R;
import com.example.foodorder.view.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity{
    private EditText edtEmailQMK;
    private Button btnReset;
    private FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        auth = FirebaseAuth.getInstance();

        edtEmailQMK = findViewById(R.id.edtEmailQ);
        btnReset = findViewById(R.id.btnQMK);



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailQMK.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Nhập Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(ForgotpasswordActivity.this,new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Chúng tôi đã gửi cho bạn hướng dẫn để thiết lập lại mật khẩu của bạn!", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ForgotpasswordActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Không thể gửi email!", Toast.LENGTH_SHORT).show();
                                }}});
            }
        });
    }
}
