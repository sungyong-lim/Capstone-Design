package com.example.sns_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.sns_project.R;

public class ReviewActivity extends BasicActivity {
    RatingBar ratingBar;
    EditText contentsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        findViewById(R.id.reviewSaveButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.reviewSaveButton:
                    save();
                    break;

            }
        }
    };
    private void save(){
        String contentsInput = ((EditText)findViewById(R.id.InPutText)).getText().toString();
        if(contentsInput.length()>0){ //내용이 있으면

        }
    }
    private void processIntent(Intent intent){
        if(intent != null){
            float rating = intent.getFloatExtra("rating",0.0f);
                    ratingBar.setRating(rating);
        }
    }
    public void returnToMain(){
        String contents = contentsInput.getText().toString();
        float ratingbarupdate = ratingBar.getRating();
        Intent intent = new Intent();
        intent.putExtra("content",contents);
        intent.putExtra("ratingbarupdate",ratingbarupdate);

        setResult(RESULT_OK,intent);
        finish();
    }
}
