package co.mathgenie.AllActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.Vector;

import co.mathgenie.AdaptersPackage.VideoAdapter;
import co.mathgenie.BaseActivity;
import co.mathgenie.DataModelsPackage.youTubeVideos;
import co.mathgenie.R;
import co.mathgenie.databinding.ActivityYoutTubeBinding;

public class YouTubeActivity extends BaseActivity<ActivityYoutTubeBinding>
{
    private ActivityYoutTubeBinding binding;
    Vector<youTubeVideos> youtubeVideos = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.youTubeRecyclerView.setHasFixedSize(true);
        binding.youTubeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" + ".youtube.com/embed/mjlsSYLLOSE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" + ".youtube.com/embed/ug0gs8kLE48\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" + ".youtube.com/embed/0cG_jL39XZE\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" + ".youtube.com/embed/LD4zp8ruvaI\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +".youtube.com/embed/71SsIxghf5Y\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +".youtube.com/embed/6Q4WZVrznQ4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new youTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +".youtube.com/embed/iF91axh0LeY\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        binding.youTubeRecyclerView.setAdapter(videoAdapter);
    }

    @Override
    protected ActivityYoutTubeBinding getViewBinding() {

        binding = ActivityYoutTubeBinding.inflate(getLayoutInflater());
        return binding;
    }


}