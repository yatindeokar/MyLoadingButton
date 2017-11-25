package com.example.deokar.myloadingbuttonexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myloadingbutton.MyLoadingButton;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, MyLoadingButton.MyLoadingButtonClick{

    MyLoadingButton myLoadingButton;

    Button normalButton, errorButton, loadingButton, doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLoadingButton = findViewById(R.id.my_loading_button);
        myLoadingButton.setMyButtonClickListener(this);

        normalButton = findViewById(R.id.normal_btn);
        errorButton = findViewById(R.id.error_btn);
        loadingButton = findViewById(R.id.loading_btn);
        doneButton = findViewById(R.id.done_btn);

        normalButton.setOnClickListener(this);
        errorButton.setOnClickListener(this);
        loadingButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);

        setLoadingButtonStyle();

    }

    /**
     * Customise MyLoadingButton
     */
    private void setLoadingButtonStyle(){

        myLoadingButton.setAnimationDuration(1000)
                .setButtonColor(R.color.colorAccent)
                .setButtonLabel("Login")
                .setButtonLabelSize(20)
                .setProgressLoaderColor(Color.WHITE)
                .setButtonLabelColor(R.color.white);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){

            case R.id.normal_btn:

                myLoadingButton.showNormalButton();

                break;

            case R.id.loading_btn:

                myLoadingButton.showLoadingButton();

                break;

            case R.id.done_btn:

                myLoadingButton.showDoneButton();

                break;

            case R.id.error_btn:

                myLoadingButton.showErrorButton();

                break;

        }

    }

    @Override
    public void onMyLoadingButtonClick() {
        Toast.makeText(this, "MyLoadingButton Click", Toast.LENGTH_SHORT).show();
    }
}
