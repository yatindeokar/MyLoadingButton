package com.example.myloadingbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by deokar on 12/11/17.
 */

public class MyLoadingButton extends RelativeLayout implements View.OnClickListener {

    /**
     * all the default colors to be used.
     */
    public int DEFAULT_LOADER_COLOR = getResources().getColor(R.color.colorAccent);
    public int DEFAULT_BUTTON_COLOR = getResources().getColor(R.color.colorPrimary);
    public int DEFAULT_BUTTON_LABEL_COLOR = getResources().getColor(R.color.white);

    /**
     * all the default values to be used
     */
    public float DEFAULT_BUTTON_LABEL_SIZE = 18f;
    public final int BUTTON_STATE_NORMAL = 1;
    public final int BUTTON_STATE_LOADING = 2;
    public final int BUTTON_STATE_DONE = 3;
    public final int BUTTON_STATE_ERROR = 4;

    String TAG = MyLoadingButton.class.getSimpleName();
    /**
     * the width for button. 1500 by default.
     */
    int buttonExpandedWidth = 1500;

    /**
     * the color for loading button at background. DEFAULT_BUTTON_COLOR by default.
     */
    int buttonBackgroundColor;

    /**
     * the color for loader circle. DEFAULT_LOADER_COLOR by default .
     */
    int loaderColor;

    /**
     * the size in float value for button label/text. DEFAULT_BUTTON_LABEL_SIZE by default.
     */
    float buttonLabelSize;

    /**
     * the color for button label/text. DEFAULT_BUTTON_LABEL_COLOR by default.
     */
    int buttonLabelColor;


    Drawable progressErrorDrawable;

    Drawable progressDoneDrawable;

    /**
     * Set button label. Button by default.
     */
    String buttonLabel = "Button";

    /**
     * When button in error state set it to normal automatically after 2 sec. true by default.
     */
    boolean normalAfterError = true;

    /**
     * Set button animation duration. 300 by default.
     */
    int animationTime = 300;

    /**
     * Set button currentState. by default in normal State.
     * State like normal, error, loading, done.
     */
    int currentState = BUTTON_STATE_NORMAL;

    /**
     * all views
     */
    RelativeLayout buttonLayout;
    TextView buttonLabelTextView;
    ProgressBar progressBar;
    ButtonAnimation buttonAnimation;
    LinearLayout progressDoneLayout;
    LinearLayout progressErrorLayout;


    public MyLoadingButton(Context context) {
        super(context);

        initView();
    }

    public MyLoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
        setAttrs(attrs, context);
    }

    public MyLoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }


    /**
     * Inflate layout to view and initialize view
     */
    public void initView() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_loding_button_layout, this);

        buttonAnimation = new ButtonAnimation(buttonExpandedWidth, animationTime);

        buttonLayout = view.findViewById(R.id.button_layout);
        buttonLabelTextView = view.findViewById(R.id.button_label_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        progressDoneLayout = view.findViewById(R.id.progress_done_layout);
        progressErrorLayout = view.findViewById(R.id.progress_error_layout);

        buttonLabelTextView.setText(buttonLabel);

        Log.d(TAG, "initView: " + buttonLayout.getMeasuredWidthAndState());

        buttonLayout.setOnClickListener(this);


    }


    /**
     * Set custom attributes
     *
     * @param attrs
     * @param context
     */
    private void setAttrs(AttributeSet attrs, Context context) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyLoadingButton,
                0,
                0
        );

        buttonLabel = typedArray.getString(R.styleable.MyLoadingButton_mlb_label);
        animationTime = typedArray.getInteger(R.styleable.MyLoadingButton_mlb_animationDuration, animationTime);
        buttonBackgroundColor = typedArray.getColor(R.styleable.MyLoadingButton_mlb_backgroundColor, DEFAULT_BUTTON_COLOR);
        loaderColor = typedArray.getColor(R.styleable.MyLoadingButton_mlb_loaderColor, DEFAULT_LOADER_COLOR);
        buttonLabelSize = typedArray.getDimension(R.styleable.MyLoadingButton_mlb_labelSize, DEFAULT_BUTTON_LABEL_SIZE);
        buttonLabelColor = typedArray.getColor(R.styleable.MyLoadingButton_mlb_labelColor, DEFAULT_BUTTON_LABEL_COLOR);
        progressErrorDrawable = typedArray.getDrawable(R.styleable.MyLoadingButton_mlb_setErrorIcon);
        progressDoneDrawable = typedArray.getDrawable(R.styleable.MyLoadingButton_mlb_setDoneIcon);
        normalAfterError = typedArray.getBoolean(R.styleable.MyLoadingButton_mlb_setNormalAfterError, true);

        Log.d(TAG, "setAttrs: " + buttonLabel);


        //Set loading button label
        if (buttonLabel != null)
            buttonLabelTextView.setText(buttonLabel.toString());

        //Set loading button background color
        GradientDrawable bgShape = (GradientDrawable) buttonLayout.getBackground();
        bgShape.setColor(buttonBackgroundColor);

        //Set progressBar color
        setProgressLoaderColor(loaderColor);

        //Set button label text size
        buttonLabelTextView.setTextSize(buttonLabelSize);

        //Set button label text color
        buttonLabelTextView.setTextColor(buttonLabelColor);

        //Set progress error icon
        if (progressErrorDrawable != null)
        progressErrorLayout.setBackground(progressErrorDrawable);

        //Set progress done icon
        if (progressDoneDrawable != null)
        progressDoneLayout.setBackground(progressDoneDrawable);

        typedArray.recycle();

    }


    /**
     * Set label to button
     *
     * @param string
     * @return
     */
    public MyLoadingButton setButtonLabel(String string) {

        buttonLabelTextView.setText(string);

        return this;
    }


    /**
     * Set duration for button animation
     *
     * @param animDuration
     * @return
     */
    public MyLoadingButton setAnimationDuration(int animDuration) {

        buttonAnimation = new ButtonAnimation(buttonExpandedWidth, animDuration);

        return this;

    }


    /**
     * Set button background color
     *
     * @param buttonColor
     * @return
     */
    public MyLoadingButton setButtonColor(int buttonColor) {

        GradientDrawable bgShape = (GradientDrawable) buttonLayout.getBackground();
        bgShape.setColor(ContextCompat.getColor(getContext(), buttonColor));

        return this;
    }


    /**
     * Set loading button progress color
     *
     * @param mColor
     */
    public MyLoadingButton setProgressLoaderColor(int mColor) {

        progressBar.getIndeterminateDrawable()
                .setColorFilter(mColor, PorterDuff.Mode.SRC_IN);

        return this;
    }


    /**
     * Set loading button label/text size.
     * @param size
     * @return
     */
    public MyLoadingButton setButtonLabelSize(float size) {

        buttonLabelTextView.setTextSize(size);

        return this;
    }

    /**
     * Set loading button label/text colot
     * @param mColor
     * @return
     */
    public MyLoadingButton setButtonLabelColor(int mColor){

        buttonLabelTextView.setTextColor(getResources().getColor(mColor));

        return this;
    }

    /**
     * Set loading button progress error icon.
     * @param errorIcon
     * @return
     */
    public MyLoadingButton setProgressErrorIcon(Drawable errorIcon){

        if (errorIcon != null)
        progressErrorLayout.setBackground(errorIcon);

        return this;
    }


    /**
     * Set loading button progress done icon.
     * @param doneIcon
     * @return
     */
    public MyLoadingButton setProgressDoneIcon(Drawable doneIcon){

        if (doneIcon != null)
        progressDoneLayout.setBackground(doneIcon);

        return this;
    }

    /**
     * Set to normal state after error. true by default.
     * @param toNormal
     * @return
     */
    public MyLoadingButton setNormalAfterError(boolean toNormal){

        normalAfterError = toNormal;

        return this;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.button_layout){

                switch (currentState) {

                    case BUTTON_STATE_NORMAL:

                        showLoadingButton();
                        currentState = BUTTON_STATE_LOADING;

                        break;

                    case BUTTON_STATE_LOADING:

                        showNormalButton();

                        currentState = BUTTON_STATE_NORMAL;

                        break;

                }

        }

    }


    /**
     * Start animation to get back to normal button from loading view.
     */
    public void showProgressToNormalButton() {

        buttonAnimation.animateShapeExpand(buttonLayout);
        buttonAnimation.onFadeOutVisibleView(progressBar, buttonLabelTextView);
        buttonAnimation.fadeOutView(buttonLabelTextView);
        buttonLayout.setClickable(true);
        currentState = BUTTON_STATE_NORMAL;
    }


    /**
     * Start animation to convert button to loading button
     */
    public void showLoadingButton() {

        buttonAnimation.animateShapeShrink(buttonLayout);
        buttonAnimation.onFadeInVisibleView(buttonLabelTextView, progressBar);
        buttonAnimation.fadeOutView(progressBar);
        buttonAnimation.fadeInView(progressErrorLayout);
        buttonAnimation.fadeInView(progressDoneLayout);
        buttonLayout.setClickable(false);
        currentState = BUTTON_STATE_LOADING;
    }


    /**
     * Start animation to get back to normal button from done button.
     */
    public void showDoneToNormalButton() {

        buttonAnimation.animateShapeExpand(buttonLayout);
        buttonAnimation.onFadeOutVisibleView(progressDoneLayout, buttonLabelTextView);
        buttonAnimation.fadeOutView(buttonLabelTextView);
        buttonLayout.setClickable(true);
        currentState = BUTTON_STATE_NORMAL;
    }


    /**
     * Start animation to convert button to done view.
     */
    public void showDoneButton() {

        buttonAnimation.animateShapeShrink(buttonLayout);
        buttonAnimation.onFadeInVisibleView(buttonLabelTextView, progressDoneLayout);
        buttonAnimation.fadeOutView(progressDoneLayout);
        buttonAnimation.fadeInView(progressBar);
        buttonAnimation.fadeInView(progressErrorLayout);
        buttonLayout.setClickable(false);
        currentState = BUTTON_STATE_DONE;
    }


    /**
     * Start animation to get back to normal button from error view
     */
    public void showErrorToNormalButton() {

        buttonAnimation.animateShapeExpand(buttonLayout);
        buttonAnimation.onFadeOutVisibleView(progressErrorLayout, buttonLabelTextView);
        buttonAnimation.fadeOutView(buttonLabelTextView);
        buttonLayout.setClickable(true);
        currentState = BUTTON_STATE_NORMAL;
    }


    /**
     * Start animation to convert button to error view
     */
    public void showErrorButton(){

        buttonAnimation.animateShapeShrink(buttonLayout);
        buttonAnimation.onFadeInVisibleView(buttonLabelTextView, progressErrorLayout);
        buttonAnimation.fadeOutView(progressErrorLayout);
        buttonAnimation.fadeInView(progressDoneLayout);
        buttonAnimation.fadeInView(progressBar);
        buttonLayout.setClickable(false);
        currentState = BUTTON_STATE_ERROR;

        if (normalAfterError == true){

            gotoNormalAfterDelay();
        }

    }


    /**
     * Start animation to get normal button.
     */
    public void showNormalButton(){

        showProgressToNormalButton();
        showDoneToNormalButton();
        showErrorToNormalButton();
        buttonLayout.setClickable(true);
        currentState = BUTTON_STATE_NORMAL;

    }

    private void gotoNormalAfterDelay(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // add your code here
                Log.d(TAG, "run: 2345");
                showNormalButton();
            }
        }, 2000);

    }

}



