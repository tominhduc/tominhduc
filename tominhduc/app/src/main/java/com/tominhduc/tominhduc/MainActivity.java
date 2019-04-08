package com.tominhduc.tominhduc;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tominhduc.tominhduc.Adapter.MyAdapterViewpager;
import com.tominhduc.tominhduc.Fragment.FragmentHome;
import com.tominhduc.tominhduc.Fragment.FragmentLyric;
import com.tominhduc.tominhduc.Fragment.FramentListmusic;
import com.tominhduc.tominhduc.Interface.Datasend;
import com.tominhduc.tominhduc.Model.ListMusic;
import com.tominhduc.tominhduc.Model.Test;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import static com.tominhduc.tominhduc.R.raw.aaa;

public class MainActivity extends AppCompatActivity implements Datasend {
    SharedPreferences ginho;
    MediaPlayer mediaPlayer;
    ViewPager viewpager;
    MyAdapterViewpager myAdapterViewpager;

    ImageView imgrandom, imgskipprevious, imgskipnext, imgplay, imgrepeat;
    public static ArrayList<ListMusic> listMusicArrayList = new ArrayList<>();

    private FragmentHome fragmentHome;
    private FramentListmusic framentListmusic;
    private FragmentLyric fragmentLyric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewpager = findViewById(R.id.viewpager);
        myAdapterViewpager = new MyAdapterViewpager(getSupportFragmentManager());

        myAdapterViewpager.addFragment(framentListmusic = FramentListmusic.newInstance());
        myAdapterViewpager.addFragment(fragmentHome = FragmentHome.newInstance());
        myAdapterViewpager.addFragment(fragmentLyric = FragmentLyric.newInstance());

        viewpager.setAdapter(myAdapterViewpager);
        imgplay = findViewById(R.id.imgplay);
        imgrandom = findViewById(R.id.imgrandom);
        imgskipprevious = findViewById(R.id.imgskipprevious);
        imgskipnext = findViewById(R.id.imgskipnext);
        imgrepeat = findViewById(R.id.imgrepeat);

    }

    @Override
    public void send(final List<ListMusic> listMusic, final int vitri) {
        listMusicArrayList.addAll(listMusic);
        even(vitri);
    }

    private void play(int hihi) {

        mediaPlayer = MediaPlayer.create(this, listMusicArrayList.get(hihi).getLink());
        Test.listMusics = listMusicArrayList.get(hihi);
    }

    private void even(final int vitr) {
        Log.e("Check even", vitr + "");
        if (Test.vitri == -1) {
            mediaPlayer.stop();
        }
        if (listMusicArrayList != null) {
            Log.e("Check event", "go list");
            Bundle bundle = new Bundle();
            if (fragmentHome != null) {
                Log.e("Check event", "send to home");
                fragmentHome.play(listMusicArrayList.get(vitr));
            }

            play(vitr);
            mediaPlayer.start();
            imgplay.setImageResource(R.drawable.ic_pause);
            imgplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        imgplay.setImageResource(R.drawable.ic_play);
                    } else {
                        mediaPlayer.start();
                        imgplay.setImageResource(R.drawable.ic_pause);
                    }
                }
            });
        }

        imgskipnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play(++Test.vitri);
                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "hihi", Toast.LENGTH_SHORT).show();
            }
        });
        imgskipprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play(--Test.vitri);
                mediaPlayer.start();
            }
        });

    }
}
