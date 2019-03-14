package com.example.amir.rehave.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amir.rehave.R;
import com.example.amir.rehave.model.DataModel;
import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.flipkart.youtubeview.models.YouTubePlayerType;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArchiveListAdapter extends RecyclerView.Adapter<ArchiveListAdapter.MyViewHolder> {
    private Fragment fragment;
    private ArrayList<DataModel> dataSet;
    private ArrayList<String> videoIds;
    private String API_KEY="AIzaSyCBlhPQAYs4UxO3A-4-G-ljeQpZp0UsyZc";
    private static final String REGEXP_ID_PATTERN = "(?i)https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube(?:-nocookie)?\\.com" +
            "\\S*?[^\\w\\s-])([\\w-]{11})(?=[^\\w-]|$)(?![?=&+%\\w.-]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w.-]*";

    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };




    public ArchiveListAdapter(Fragment fragment, ArrayList<DataModel> data) {
        this.dataSet = data;
        this.fragment=fragment;
        this.videoIds=extractVideoIds(data);

    }

    private ArrayList<String> extractVideoIds(ArrayList<DataModel> datas) {
        ArrayList<String> vidioIds=new ArrayList<>();
        for (DataModel data : datas){

            vidioIds.add(extractVideoId(data));
        }
        return vidioIds;
    }

    private String extractVideoId(DataModel data) {
        final Pattern pattern = Pattern.compile(REGEXP_ID_PATTERN);
        final Matcher matcher = pattern.matcher(data.getPost());
        if (matcher.matches()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("Cannot extract video id from url");
    }

    @Override
    public ArchiveListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_item, parent, false);


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ArchiveListAdapter.MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;


        textViewName.setText(dataSet.get(listPosition).getTitle());
        YouTubePlayerView playerView = holder.playerView;

        playerView.initPlayer(API_KEY, videoIds.get(listPosition), null, YouTubePlayerType.STRICT_NATIVE, null, fragment, imageLoader);

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        YouTubePlayerView playerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName =itemView.findViewById(R.id.textViewName);
            playerView=itemView.findViewById(R.id.youtube_player_view);
        }
    }
}
