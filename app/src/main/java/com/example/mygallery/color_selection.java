package com.example.mygallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;
import com.skydoves.colorpickerpreference.ColorPickerView;
import com.skydoves.colorpickerpreference.FlagView;

public class color_selection extends AppCompatActivity {
    TextView textcolor,textcolorView,backgroundcolor,backgroundcolorview,primarycolor,primarycolorview;
    ColorPickerView pickerView;
    TextView selected_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);
        allocatememory();
        events();
    }

    private void events() {
        textcolorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(false,true,false);
            }
        });
        primarycolorview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(true,false,false);

            }
        });

        backgroundcolorview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(false,false,true);

            }
        });
    }

    private void allocatememory() {

        textcolor=findViewById(R.id.textcolor);
        textcolorView=findViewById(R.id.textcolorview);
        backgroundcolor=findViewById(R.id.backgroundcolor);
        backgroundcolorview=findViewById(R.id.backgroundcolorview);
        primarycolor=findViewById(R.id.primarycolor);
        primarycolorview=findViewById(R.id.primarycolorview);

    }
    private void showCustomDialog(final boolean primary, final boolean textcolor1 , final boolean background ) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.color_picker, viewGroup, false);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        pickerView=alertDialog.findViewById(R.id.colorPickerView);
        selected_color=alertDialog.findViewById(R.id.color_selector);
        pickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(final ColorEnvelope colorEnvelope) {

                colorEnvelope.getColor();
                selected_color.setBackgroundColor(colorEnvelope.getColor());
                colorEnvelope.getColorHtml();
                colorEnvelope.getColorRGB();


                Button okay=alertDialog.findViewById(R.id.btn_okay);
                Button cancel=alertDialog.findViewById(R.id.ntm_cancel);

                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (primary){
                            primarycolor.setBackgroundColor(colorEnvelope.getColor());
                           // int color= getResources().getColor(R.color.colorPrimary);
                         //   setFeatureDrawableResource(colorEnvelope.getColor(),R.color.colorPrimary);
                        }
                        if (background){
                            backgroundcolor.setBackgroundColor(colorEnvelope.getColor());
                        }
                        if (textcolor1){
                            textcolor.setBackgroundColor(colorEnvelope.getColor());
                        }
                    }
                });

            }
        });

        //Button cancel=alertDialog.findViewById(R.id.ntm_cancel);
        pickerView.setFlagView(new color_selection.CustomFlag(color_selection.this, R.layout.flag_view));
        ColorPickerDialog.Builder builder1 = new ColorPickerDialog.Builder(color_selection.this,R.style.Theme_AppCompat_Light_NoActionBar );
        builder1.setFlagView(new color_selection.CustomFlag(color_selection.this, R.layout.flag_view));





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

}