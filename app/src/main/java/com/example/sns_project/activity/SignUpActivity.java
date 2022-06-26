package com.example.sns_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.sns_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.sns_project.Util.showToast;

public class SignUpActivity extends BasicActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setToolbarTitle();

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoLoginButton).setOnClickListener(onClickListener);

        findViewById(R.id.testButton).setOnClickListener(onClickListener);
        findViewById(R.id.codeButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUpButton:
                    signUp(); //회원가입
                    break;
                case R.id.gotoLoginButton:
                    myStartActivity(LoginActivity.class); //로그인화면으로
                    break;
                case R.id.testButton:
                    myStartActivity(ReviewActivity.class); //후기
                    break;
                case R.id.codeButton:
                    myStartActivity(CodeActivity.class); //코드 생성
                    break;
            }
        }
    };

    private void signUp() { //회원가입
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString(); //이메일 테스틉에
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordCheckEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if(email.contains("ac.kr")){
                if(password.equals(passwordCheck)){
                    final RelativeLayout loaderLayout = findViewById(R.id.loaderLyaout);
                    loaderLayout.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    loaderLayout.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        user = mAuth.getCurrentUser();
                                        send();
                                        myStartActivity(LoginActivity.class);
                                    } else {
                                        if(task.getException() != null){
                                            showToast(SignUpActivity.this, task.getException().toString());
                                        }
                                    }
                                }
                            });
                }else{
                    showToast(SignUpActivity.this, "비밀번호가 일치하지 않습니다.");
                }

            }
            else {
                showToast(SignUpActivity.this, "학교 이메일을 입력해주세요.");
            }
        }else {
            showToast(SignUpActivity.this, "이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void send(){ //이메일 보내는 함수
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            showToast(SignUpActivity.this, "이메일이 전송되었습니다.");
                        }
                    }
                });
    }
}
