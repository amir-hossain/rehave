package com.example.amir.rehave.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amir.rehave.R;
import com.example.amir.rehave.fragments.ArchiveFragment;
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
import butterknife.OnClick;
import butterknife.Optional;

public class ArchiveListAdapter extends RecyclerView.Adapter<ArchiveListAdapter.MyViewHolder> {
    private Fragment fragment;
    private List<DataModel> dataSet;
    private ArrayList<String> videoIds;
    private String API_KEY = "AIzaSyCBlhPQAYs4UxO3A-4-G-ljeQpZp0UsyZc";
    private static final String REGEXP_ID_PATTERN = "(?i)https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube(?:-nocookie)?\\.com" +
            "\\S*?[^\\w\\s-])([\\w-]{11})(?=[^\\w-]|$)(?![?=&+%\\w.-]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w.-]*";

    private static int SLIDER_HEADER = 0;
    private static int VIDEO_ITEM = 1;
    private static int IMAGE_ITEM = 2;
    private static int OTHER_ITEM = 3;
    private static int OPINION_ITEM = 4;


    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };
    private Context context;
    private onCategoryClickListener listener;

    public ArchiveListAdapter(Context context,Fragment fragment, List<DataModel> data,onCategoryClickListener listener) {
        this.context=context;
        this.dataSet = data;
        this.fragment = fragment;
        this.listener=listener;
        if(data.get(1).getSection()==ArchiveFragment.VIDEO){
            this.videoIds = extractVideoIds(data);
        }

    }

    private ArrayList<String> extractVideoIds(List<DataModel> datas) {
        ArrayList<String> vidioIds = new ArrayList<>();
        for (DataModel data : datas) {
            if (data == null) {
                vidioIds.add(null);
            } else {
                try {
                    vidioIds.add(extractVideoId(data));
                }catch (Exception e){
                    Toast.makeText(context,"invalid url not shown",Toast.LENGTH_SHORT).show();
                }

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
        View view = null;
        if (viewType == SLIDER_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.slider_header_layout, parent, false);

        } else if(viewType==VIDEO_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.archive_vidio_view, parent, false);

        }else if(viewType==IMAGE_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.archive_image_view, parent, false);

        }else if(viewType==OPINION_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.expert_layout, parent, false);

        }else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.other_archive_view, parent, false);
        }


        return new MyViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(final ArchiveListAdapter.MyViewHolder holder, final int listPosition) {
        if (getItemViewType(listPosition) == VIDEO_ITEM) {
            TextView textViewName = holder.titleView;
            textViewName.setText(dataSet.get(listPosition).getTitle());
            YouTubePlayerView playerView = holder.playerView;

            playerView.initPlayer(API_KEY, videoIds.get(listPosition), "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html", YouTubePlayerType.STRICT_NATIVE, null, fragment, imageLoader);
        }else if(getItemViewType(listPosition) == IMAGE_ITEM){
            holder.titleView.setText(dataSet.get(listPosition).getTitle());

            Glide
                    .with(context)
                    .load(dataSet.get(listPosition).getPost())
                    .into(holder.imageView);

        }else if(getItemViewType(listPosition) == OTHER_ITEM){
            holder.titleView.setText(dataSet.get(listPosition).getTitle());
            holder.linkView.setText(dataSet.get(listPosition).getPost());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataSet.get(listPosition).getPost()));
                        context.startActivity(browserIntent);
                    }catch (Exception e){
                        Toast.makeText(context,"invalid url",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SLIDER_HEADER;
        } else {
            if(dataSet.get(position).getSection()== ArchiveFragment.VIDEO){
                return VIDEO_ITEM;
            }else if(dataSet.get(position).getSection()== ArchiveFragment.AUDIO){
                return OTHER_ITEM;
            }else if(dataSet.get(position).getSection()== ArchiveFragment.BOOK){
                return OTHER_ITEM;
            }else if(dataSet.get(position).getSection()== ArchiveFragment.IMAGE){
                return IMAGE_ITEM;
            }else if(dataSet.get(position).getSection()== ArchiveFragment.SHARING){
                return OTHER_ITEM;
            }else if(dataSet.get(position).getSection()== ArchiveFragment.OPNION){
                return OPINION_ITEM;
            }else {
                return OTHER_ITEM;
            }

        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(ArrayList<DataModel> dataSet){
        this.dataSet=dataSet;
        notifyDataSetChanged();
    }

    CardView activatedcardView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.item_tools)
        CardView toolsBtn;

        @Nullable
        @BindView(R.id.item_opnion)
        CardView opnionBtn;

        @Nullable
        @BindView(R.id.textViewName)
        TextView titleView;

        @Nullable
        @BindView(R.id.youtube_player_view)
        YouTubePlayerView playerView;

        @Nullable
        @BindView(R.id.img)
        ImageView imageView;

        @Nullable
        @BindView(R.id.link)
        TextView linkView;

        @Nullable
        @BindView(R.id.card_view)
        CardView cardView;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (viewType == SLIDER_HEADER) {
                videoBtn.setActivated(true);
                activatedcardView = videoBtn;

            }
        }

        @Optional
        @OnClick(R.id.item_video)
        public void videoClick() {
            activatedcardView.setActivated(false);
            videoBtn.setActivated(true);
            activatedcardView = videoBtn;
            listener.onCategoryClick(ArchiveFragment.VIDEO);

        }

        @Optional
        @OnClick(R.id.item_audio)
        public void audioClick() {

            activatedcardView.setActivated(false);
            audioBtn.setActivated(true);
            activatedcardView = audioBtn;
            listener.onCategoryClick(ArchiveFragment.AUDIO);
        }

        @Optional
        @OnClick(R.id.item_book)
        public void bookClick() {

            activatedcardView.setActivated(false);
            bookBtn.setActivated(true);
            activatedcardView = bookBtn;
            listener.onCategoryClick(ArchiveFragment.BOOK);
        }

        @Optional
        @OnClick(R.id.item_image)
        public void imageClick() {

            activatedcardView.setActivated(false);
            imgBtn.setActivated(true);
            activatedcardView = imgBtn;
            listener.onCategoryClick(ArchiveFragment.IMAGE);
        }


        @Optional
        @OnClick(R.id.item_tools)
        public void toolsClick() {

            activatedcardView.setActivated(false);
            toolsBtn.setActivated(true);
            activatedcardView = toolsBtn;
            listener.onCategoryClick(ArchiveFragment.TOOLS);
        }

        @Optional
        @OnClick(R.id.item_opnion)
        public void opinionClick() {

            activatedcardView.setActivated(false);
            opnionBtn.setActivated(true);
            activatedcardView = opnionBtn;
            ArrayList<DataModel> models=new ArrayList<>();
            models.add(null);
            DataModel dataModel=new DataModel();
            dataModel.setSection(9);
            models.add(dataModel);
           setData(models);

        }
    }



    public interface onCategoryClickListener{
        void onCategoryClick(int dataType);
    }
}
