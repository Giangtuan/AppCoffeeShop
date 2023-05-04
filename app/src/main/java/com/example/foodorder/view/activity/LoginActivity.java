package com.example.foodorder.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorder.R;
import com.example.foodorder.view.BaseActivity;
import com.example.foodorder.view.MySharedPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button btnDangnhap,btnDangky;
    EditText edtEmail, edtMatkhau;
    MySharedPreferences mySharedPreferences;
    TextView txtQuenMk;

    private ProgressDialog progressDialog;

    String thongtinluu = "tk_mk";
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        edtEmail = findViewById(R.id.edtTendangnhap);
        edtMatkhau = findViewById(R.id.edtMatkhau);
        txtQuenMk = findViewById(R.id.txtQuenmk);
        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnDangky = findViewById(R.id.btnDangky);

        auth = FirebaseAuth.getInstance();
        mySharedPreferences = new MySharedPreferences(this);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = edtEmail.getText().toString();
                final String password = edtMatkhau.getText().toString();

                progressDialog.show();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập Email!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                auth.signInWithEmailAndPassword(Email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edtMatkhau.setError("Mật khẩu quá ngắn, tối thiểu phải 6 kí tự!");
                                    } else {
                                        Toast.makeText(LoginActivity.this,"Đăng nhập thất bại, vui lòng kiểm tra lại thông tin!" , Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    mySharedPreferences.putUserName("UserName",Email);
                                    mySharedPreferences.putPass("PassWord",password);
                                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }});
            }
        });
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        txtQuenMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotpasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtEmail.setText(mySharedPreferences.getUserName("UserName"));
        edtMatkhau.setText(mySharedPreferences.getPassWord("PassWord"));
    }
}
