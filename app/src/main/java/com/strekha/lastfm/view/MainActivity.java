package com.strekha.lastfm.view;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.content.info.ArtistInfo;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    LastFM lastFM = new LastFM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);


        final TextView textView = (TextView) findViewById(R.id.text);
        final SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.image);
        image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());

        Observable<ArtistInfo> list = lastFM.getArtistInfo("Nirvana");
        list.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArtistInfo>() {
                    @Override
                    public void onCompleted() {
                        Log.d("myLog", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("myLog", "error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(ArtistInfo artist) {
                        image.setImageURI(Uri.parse(artist.getArtist().getImage().get(2).getText()));
                        textView.setText(artist.getArtist().getBio().getContent());
                    }
                });
    }
}
