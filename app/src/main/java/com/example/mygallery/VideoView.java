package com.example.mygallery;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygallery.Pojo.PictureFacer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VideoView extends AppCompatActivity {
     android.widget.VideoView videoView;
     ImageView leftimage,rightimage,seekprevious,seeknext,play;
     LinearLayout layout_hide,layout_hide2;
    MediaController mediaController;
    AppCompatSeekBar seekbar;
    TextView time;
    Uri url;
    int position;
    ArrayList<PictureFacer> list=new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_view);
        allocatememory();
        playvideo();
        initview();
        events();
    }

    private void initview() {
        // mediaController = new MediaController(this);
        //mediaController.setMediaPlayer(videoView);
        url=Uri.parse(getIntent().getStringExtra("url"));
        list=getIntent().getParcelableArrayListExtra("list");
        position=getIntent().getIntExtra("position",0);
        // videoView.setMediaController(mediaController);
        videoView.setVideoURI(url);
        videoView.requestFocus();
        videoView.start();
        videoView.canSeekBackward();
        videoView.canSeekBackward();
        seekbar.setMax(100);
        seekbar.setProgress(0);

    }

    private void timecount() {
//        int duration=videoView.getDuration()/1000;
//        int hours = duration / 3600;
//        int minutes = (duration / 60) - (hours * 60);
//        int seconds = duration - (hours * 3600) - (minutes * 60) ;
//        String formatted = String.format("%d:%02d:%02d", hours, minutes, seconds);
//        // Toast.makeText(getApplicationContext(), "duration is " + formatted ,  Toast.LENGTH_LONG).show();
//        time.setText(formatted);

    }

    private void playvideo() {

    }

    private void allocatememory() {
        leftimage=findViewById(R.id.leftvideo);
        rightimage=findViewById(R.id.rightvideo);
        videoView=findViewById(R.id.vdVw);
        seeknext=findViewById(R.id.seeknext);
        seekprevious=findViewById(R.id.seekpreviuos);
        seekbar=findViewById(R.id.seek);
        time=findViewById(R.id.time);
        play=findViewById(R.id.play);
        layout_hide=findViewById(R.id.layout_hide);
    }

    private void events() {
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (Prefrence.getInstance(getApplicationContext()).getLoop()){
                    initview();
                }
                else {
                    position++;
                    videoView.pause();
                    PictureFacer pictureFacer = list.get(position);
                    url = Uri.parse(pictureFacer.getPicturePath());
                    Log.d("error", url.toString());
                    videoView.setVideoURI(url);
                    videoView.requestFocus();
                    videoView.start();
                    videoView.canSeekBackward();
                    videoView.canSeekBackward();
                }
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                final boolean  running = true;
                final int duration = videoView.getDuration();

                new Thread(new Runnable() {
                    public void run() {
                        do{
                            time.post(new Runnable() {
                                public void run() {
                                    int time1=(videoView.getDuration()-videoView.getCurrentPosition())/1000;
                                    int hours = time1 / 3600;
                                    int minutes = (time1 / 60) - (hours * 60);
                                    int seconds = time1 - (hours * 3600) - (minutes * 60) ;
                                    String formatted = String.format("%d:%02d:%02d", hours, minutes, seconds);
                                    if (Prefrence.getInstance(getApplication()).getDuration()){
                                        time.setVisibility(View.INVISIBLE);
                                    }
                                    else {
                                        time.setVisibility(View.VISIBLE);
                                        time.setText(formatted);

                                    }
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();

                timerCounter();
                setDuration();
            }
        });

        videoView.setClickable(false);
        videoView.setOnTouchListener(new View.OnTouchListener()
        {
            int count=0;
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent)
            {

                count++;
                if (count%2!=0){
                    layout_hide.setVisibility(View.VISIBLE);
                  //  layout_hide2.setVisibility(View.VISIBLE);
       //             mediaController.show(0);

                }
                else {
                   layout_hide.setVisibility(View.INVISIBLE);
               //    layout_hide2.setVisibility(View.INVISIBLE);
         //           mediaController.hide();
                }

                return false;
            }
        });
        rightimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == list.size()) {
                    Toast.makeText(VideoView.this, "This is the last video", Toast.LENGTH_LONG).show();
                }
                else {
                    position++;
                    videoView.pause();
                    PictureFacer pictureFacer = list.get(position);
                    url = Uri.parse(pictureFacer.getPicturePath());
                    Log.d("error", url.toString());
                    videoView.setVideoURI(url);
                    videoView.requestFocus();
                    videoView.start();
                    videoView.canSeekBackward();
                    videoView.canSeekBackward();
                }
            }
        });
        leftimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    Toast.makeText(VideoView.this, "This is the First video", Toast.LENGTH_LONG).show();


                }
                else {
                    position--;
                    videoView.pause();

                    PictureFacer pictureFacer = list.get(position);

                    url = Uri.parse(pictureFacer.getPicturePath());
                    Log.d("error", url.toString());
                    videoView.setVideoURI(url);
                    videoView.requestFocus();
                    videoView.start();
                    videoView.canSeekBackward();
                    videoView.canSeekBackward();
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override

            public void onClick(View v) {
                if (videoView.isPlaying()){
                    play.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    videoView.pause();
                    videoView.requestFocus();



                }
                else if (!videoView.isPlaying()){
                    videoView.start();
                    play.setImageResource(R.drawable.ic_baseline_pause_24);

                }
            }
        });
        seeknext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seek=5000;
                videoView.seekTo(videoView.getCurrentPosition()+seek);

            }
        });
        seekprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backword=5000;
                videoView.seekTo(videoView.getCurrentPosition()+backword);
            }
        });
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onStop() {
        videoView.pause();
        super.onStop();
    }

    @Override
    protected void onResume() {
        videoView.start();
        super.onResume();
    }

    private Timer timer;
    private void timerCounter(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI();
                    }
                });
            }
        };
        timer.schedule(task, 0, 1000);
    }
    private int duration = 0;
    private void setDuration(){
        duration = videoView.getDuration();
    }

    private void updateUI(){
        if (seekbar.getProgress() >= 100) {
            timer.cancel();
        }
        int current = videoView.getCurrentPosition();
        int progress = current * 100 / duration;
        seekbar.setProgress(progress);
    }


}