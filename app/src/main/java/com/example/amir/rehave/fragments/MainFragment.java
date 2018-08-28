package com.example.amir.rehave.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.amir.rehave.ForumDetails;
import com.example.amir.rehave.R;
import com.example.amir.rehave.adapter.MainFragmentAdapter;
import com.example.amir.rehave.factory.IntentFactory;
import com.example.amir.rehave.link.LinkListeners;
import com.example.amir.rehave.link.LinkMethods;
import com.example.amir.rehave.model.HomeProductResponse;
import com.example.amir.rehave.model.MainFragmentData;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements MainFragmentAdapter.Listener,LinkListeners.DataTableListener{
    private RecyclerView recyclerView;
    private MainFragmentAdapter adapter;

    private ProgressBar progressBar;
    private View rootView;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);

        progressBar = rootView.findViewById(R.id.progress_bar);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        LinkMethods linkMethods=new LinkMethods();

        linkMethods.setDataTableListener(this);

        return rootView;
    }
    @Override
    public void itemClick(String id,String title) {

        try {
            Intent intent = IntentFactory.getIntent(getContext(),title);

            intent.putExtra("key",id );
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }



    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



    @Override
    public void listenDatable(List<MainFragmentData> datas) {

        adapter = new MainFragmentAdapter( datas, MainFragment.this);

        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);

    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}