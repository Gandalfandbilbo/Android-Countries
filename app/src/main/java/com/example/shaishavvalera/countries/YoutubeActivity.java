package com.example.shaishavvalera.countries;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Shaishav Valera on 07-Nov-17.
 */

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    private static final String developer_key = "AIzaSyDjMdz050doycPrt8igdhDXHUfUrbs4Myw";
    private static final String video_id = "xUuoFch3ArM";
    private static final int recovery_dialog_request = 1;
    public YouTubePlayerFragment yf;
    @Override
    protected void onCreate(Bundle onSavedInstance)
    {
        super.onCreate(onSavedInstance);
        setContentView(R.layout.youtube_fragment);
        yf = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtubeplayer);
        yf.initialize(developer_key,this);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason)
    {
        if (errorReason.isUserRecoverableError())
        {
            errorReason.getErrorDialog(this, recovery_dialog_request).show();
        }
        else
        {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
    {
        if (!wasRestored)
        {
            player.cueVideo(video_id);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == recovery_dialog_request)
        {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(developer_key, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider()
    {
        return (YouTubePlayerView)findViewById(R.id.youtubeplayer);
    }
}
