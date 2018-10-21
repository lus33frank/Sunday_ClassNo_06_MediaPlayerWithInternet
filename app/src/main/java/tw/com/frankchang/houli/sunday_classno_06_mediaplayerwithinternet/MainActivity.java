package tw.com.frankchang.houli.sunday_classno_06_mediaplayerwithinternet;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    TextView tvShow;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewer();
    }

    private void findviewer() {
        etInput = (EditText) findViewById(R.id.editText);
        tvShow = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        etInput.setText("http://promodj.com/download/4877776/Fabo%2CSolomina-%20Where%20i%20stand%28Dj%20Amice%20Remix%29%20%28promodj.com%29.mp3");
    }

    public void playOnClick(View view) {
        tvShow.setText(R.string.manin_start);

        if (mediaPlayer == null){
            try {
                mediaPlayer = new MediaPlayer();
                //設定資料串流型式
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //設定資料來源
                mediaPlayer.setDataSource(etInput.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //準備播放器進行播放，異步播放。
        mediaPlayer.prepareAsync();
        tvShow.setText(R.string.main_prepareasync);

        //設定準備好要播放的監聽器
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                tvShow.setText(R.string.main_play);
            }
        });
    }

    public void stopOnClick(View view) {
        if (mediaPlayer != null){
            mediaPlayer.stop();
            tvShow.setText(R.string.main_stop);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
