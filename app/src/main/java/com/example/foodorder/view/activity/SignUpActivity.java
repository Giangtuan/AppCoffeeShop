package com.example.foodorder.view.activity;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmaildangky,edtMatkhaudk;
    Button btnDangky;
    FirebaseAuth auth;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        edtEmaildangky = findViewById(R.id.edtEmaildangky);
        edtMatkhaudk= findViewById(R.id.edtMatkhaudk);

        btnDangky =(Button) findViewById(R.id.btndangky);


        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmaildangky.getText().toString().trim();
                String mk  = edtMatkhaudk.getText().toString().trim();

                progressDialog.show();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(mk)){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập mật khẩu",Toast.LENGTH_LONG).show();
                    return;
                }
                if(mk.length() < 6){
                    Toast.makeText(getApplicationContext(), "Mật khẩu quá ngắn, Tối thiểu phải 6 kí tự!", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.createUserWithEmailAndPassword(email,mk)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại!" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
