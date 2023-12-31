package co.mathgenie.AdaptersPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.mathgenie.DataModelsPackage.youTubeVideos;
import co.mathgenie.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
private List<youTubeVideos> youtubeVideoList;
        public VideoAdapter(List<youTubeVideos> youtubeVideoList) {
        this.youtubeVideoList = youtubeVideoList;
        }
@Override
public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.custom_video_view_layout, parent, false);
        return new VideoViewHolder(view);
        }
@Override
public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.videoWeb.loadData( youtubeVideoList.get(position).getVideoUrl(), "text/html" , "utf-8");
        }
@Override
public int getItemCount() {
        return youtubeVideoList.size();
        }
class VideoViewHolder extends RecyclerView.ViewHolder{
    WebView videoWeb;
    VideoViewHolder(View itemView) {
        super(itemView);
        videoWeb = itemView.findViewById(R.id.webView);
        videoWeb.getSettings().setJavaScriptEnabled(true);
        videoWeb.setWebChromeClient(new WebChromeClient() {
        } );
    }
}

}
