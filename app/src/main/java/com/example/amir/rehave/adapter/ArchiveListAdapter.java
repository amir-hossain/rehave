package com.example.amir.rehave.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchiveListAdapter extends RecyclerView.Adapter<ArchiveListAdapter.MyViewHolder> {
    private Fragment fragment;
    private List<DataModel> dataSet;
    private ArrayList<String> videoIds;
    private String API_KEY="AIzaSyCBlhPQAYs4UxO3A-4-G-ljeQpZp0UsyZc";
    private static final String REGEXP_ID_PATTERN = "(?i)https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube(?:-nocookie)?\\.com" +
            "\\S*?[^\\w\\s-])([\\w-]{11})(?=[^\\w-]|$)(?![?=&+%\\w.-]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w.-]*";

    private static int SLIDER_HEADER=0;
    private static int ITEM=1;

    @Nullable
    @BindView(R.id.item_video)
    CardView videoBtn;

    @Nullable
    @BindView(R.id.item_audio)
    CardView audioBtn;

    @Nullable
    @BindView(R.id.item_book)
    CardView bookBtn;

    @Nullable
    @BindView(R.id.item_image)
    CardView imgBtn;

    @Nullable
    @BindView(R.id.item_sharing)
    CardView shereBtn;

    @Nullable
    @BindView(R.id.item_tools)
    CardView toolsBtn;

    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };
    private Context context;


    public ArchiveListAdapter(Fragment fragment, List<DataModel> data) {
        this.dataSet = data;
        this.dataSet.add(0,null);
        this.fragment=fragment;
        this.videoIds=extractVideoIds(data);

    }

    private ArrayList<String> extractVideoIds(List<DataModel> datas) {
        ArrayList<String> vidioIds=new ArrayList<>();
        for (DataModel data : datas){
            if(data==null){
                vidioIds.add(null);
            }else {
                vidioIds.add(extractVideoId(data));
            }
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
        this.context=parent.getContext();
        View view=null;
        if (viewType==SLIDER_HEADER){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.slider_header_layout, parent, false);
            ButterKnife.bind(this,view);
            videoBtn.setActivated(true);
        }else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.archive_item, parent, false);
            ButterKnife.bind(this,view);
        }



        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ArchiveListAdapter.MyViewHolder holder, final int listPosition) {
        if(getItemViewType(listPosition)==ITEM){
            TextView textViewName = holder.textViewName;


            textViewName.setText(dataSet.get(listPosition).getTitle());
            YouTubePlayerView playerView = holder.playerView;

            playerView.initPlayer(API_KEY, videoIds.get(listPosition), "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html", YouTubePlayerType.STRICT_NATIVE, null, fragment, imageLoader);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return SLIDER_HEADER;
        }else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        YouTubePlayerView playerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName =itemView.findViewById(R.id.textViewName);
            playerView=itemView.findViewById(R.id.youtube_player_view);

        }
    }
}
