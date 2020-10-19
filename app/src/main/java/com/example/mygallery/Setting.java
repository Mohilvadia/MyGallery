package com.example.mygallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;
import com.skydoves.colorpickerpreference.ColorPickerView;
import com.skydoves.colorpickerpreference.FlagView;

public class Setting extends AppCompatActivity {
    SwitchCompat hidefolder,automatic_play_swtch,loop_swtch,cropthumbnail_swtch,duration_swtch,horizontalscroll_swtch;
   //boolean hide;
    ColorPickerView pickerView;
    TextView customze_color,selected_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        allocatememory();
        if (Prefrence.getInstance(getApplication()).getHide_folder()){
            hidefolder.setChecked(true);
        }
        if (Prefrence.getInstance(getApplication()).getAutoPlay()){
            automatic_play_swtch.setChecked(true);
        }
        if (Prefrence.getInstance(getApplication()).getCropThumb()){
            cropthumbnail_swtch.setChecked(true);
        }
        if (Prefrence.getInstance(getApplication()).getDuration()){
            duration_swtch.setChecked(true);
        }
        if (Prefrence.getInstance(getApplication()).getHorizontalScroll()){
            horizontalscroll_swtch.setChecked(true);
        }
        if (Prefrence.getInstance(getApplication()).getLoop()){
            loop_swtch.setChecked(true);
        }


        initview();
        events();
    }

    private void events() {
        duration_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Prefrence.getInstance(Setting.this).setDuration(true);

                }
                else {
                    Prefrence.getInstance(Setting.this).setDuration(false);

                }
            }
        });
        hidefolder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Prefrence.getInstance(Setting.this).hidefolder(true);
                }
                else{
                    Prefrence.getInstance(Setting.this).hidefolder(false);
                    Toast.makeText(Setting.this,"off",Toast.LENGTH_LONG).show();

                }
            }
        });
        customze_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Setting.this,color_selection.class));
                finish();

            }
        });
        loop_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Prefrence.getInstance(Setting.this).setLoop(true);

                }
                else {
                    Prefrence.getInstance(Setting.this).setLoop(false);

                }
            }
        });

        automatic_play_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Prefrence.getInstance(Setting.this).setAutoPlay(true);

                }
                else {
                    Prefrence.getInstance(Setting.this).setAutoPlay(false);

                }
            }
        });

        cropthumbnail_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Prefrence.getInstance(Setting.this).setCropThumb(true);

                }
                else {
                    Prefrence.getInstance(Setting.this).setCropThumb(false);

                }
            }
        });
        horizontalscroll_swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Prefrence.getInstance(Setting.this).setHorizontalScroll(true);

                }
                else {
                    Prefrence.getInstance(Setting.this).setHorizontalScroll(false);

                }
            }
        });



//        pickerView.setPreferenceName("MyColorPickerView");

    }

    private void initview() {

    }

    private void allocatememory() {
        hidefolder=findViewById(R.id.hide);
        customze_color=findViewById(R.id.customize_color);
        automatic_play_swtch=findViewById(R.id.automatic_play_swtch);
        loop_swtch=findViewById(R.id.loop_swtch);
        cropthumbnail_swtch=findViewById(R.id.cropthumbnail_swtch);
        duration_swtch=findViewById(R.id.duration_swtch);
        horizontalscroll_swtch=findViewById(R.id.horizontalscroll_swtch);


    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.color_picker, viewGroup, false);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        pickerView=alertDialog.findViewById(R.id.colorPickerView);
        selected_color=alertDialog.findViewById(R.id.color_selector);


        Button okay=alertDialog.findViewById(R.id.btn_okay);
        Button cancel=alertDialog.findViewById(R.id.ntm_cancel);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();
                pickerView.setColorListener(new ColorListener() {
                    @Override
                    public void onColorSelected(ColorEnvelope colorEnvelope) {
                        // alertDialog.hide();
                        LinearLayout linearLayout = findViewById(R.id.linearlayout);
                        // linearLayout.setBackgroundColor(colorEnvelope.getColor());
                        colorEnvelope.getColor();
                        selected_color.setBackgroundColor(colorEnvelope.getColor());
                        colorEnvelope.getColorHtml();
                        colorEnvelope.getColorRGB();
                    }
                });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.hide();

            }
        });
        //Button cancel=alertDialog.findViewById(R.id.ntm_cancel);
        // setting of a flagview
        pickerView.setFlagView(new CustomFlag(Setting.this, R.layout.flag_view));
        ColorPickerDialog.Builder builder1 = new ColorPickerDialog.Builder(Setting.this,R.style.Theme_AppCompat_Light_NoActionBar );
        builder1.setFlagView(new CustomFlag(Setting.this, R.layout.flag_view));

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
   //     pickerView.saveData();
    }
    @SuppressLint("ViewConstructor")
    public static class CustomFlag extends FlagView {

        private TextView textView;
        private View view;

        public CustomFlag(Context context, int layout) {
            super(context, layout);
            textView = findViewById(R.id.flag_color_code);
            view = findViewById(R.id.flag_color_layout);
        }

        @Override
        public void onRefresh(ColorEnvelope colorEnvelope) {
            textView.setText("#" + String.format("%06X", (0xFFFFFF & colorEnvelope.getColor())));
            view.setBackgroundColor(colorEnvelope.getColor());
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplication(),Gallery.class);

       startActivity(intent);
        super.onBackPressed();
    }
}