package com.example.projectmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import com.example.projectmvp.adapters.HomeFeedAdapter;
import com.example.projectmvp.apis.ApiClient;
import com.example.projectmvp.apis.Apis;
import com.example.projectmvp.helpers.StartSnapHelper;
import com.example.projectmvp.model.HomeFeedObjects;
import com.example.projectmvp.responses.FeedResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewHomeFeed;
    List<FeedResponse> feedResponseArrayList;
    HomeFeedAdapter homeFeedAdapter;

    Apis apis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewHomeFeed = (RecyclerView) findViewById(R.id.home_feed_recyclerview);
        DisplayMetrics metrics = getDisplayMetrics();

//        homeFeedObjectsArrayList = new ArrayList<>();
//        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "Reddit", R.mipmap.pic2, "this is Caption"));
//        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "9GAG", R.mipmap.pic1, "this is 2nd Caption"));
//        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "Pewdiepie", R.mipmap.pic2, "this is 3rd Caption"));
getSupportActionBar().hide();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
//        recyclerViewHomeFeed.setLayoutManager(linearLayoutManager);
//        recyclerViewHomeFeed.setAdapter(homeFeedAdapter);

//        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            private final ColorDrawable background = new ColorDrawable(getResources().getColor(R.color.design_default_color_primary_dark));
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                homeFeedAdapter.showMenu(viewHolder.getAdapterPosition());
//            }
//
//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//                View itemView = viewHolder.itemView;
//
//                if (dX > 0) {
//                    background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
//                } else if (dX < 0) {
//                    background.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
//                } else {
//                    background.setBounds(0, 0, 0, 0);
//                }
//
//                background.draw(c);
//            }
//        };
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerViewHomeFeed);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            recyclerViewHomeFeed.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    recyclerViewHomeFeed.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            homeFeedAdapter.closeMenu();
//                        }
//                    });
//                }
//            });
//        }
    }

        private DisplayMetrics getDisplayMetrics() {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);

            return metrics;
        }


//    public class SnapHelperOneByOne extends PagerSnapHelper {
//
//        @Override
//        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY){
//
//            if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
//                return RecyclerView.NO_POSITION;
//            }
//
//            final View currentView = findSnapView(layoutManager);
//
//            if( currentView == null ){
//                return RecyclerView.NO_POSITION;
//            }
//
//            final int currentPosition = layoutManager.getPosition(currentView);
//
//            if (currentPosition == RecyclerView.NO_POSITION) {
//                return RecyclerView.NO_POSITION;
//            }
//
//            return currentPosition;
//        }
//    }
public void getFeeddata(){
    final Call<List<FeedResponse>> feedResponse = ApiClient.getUserService().getfeedData();

feedResponse.enqueue(new Callback<List<FeedResponse>>() {
    @Override
    public void onResponse(Call<List<FeedResponse>> call, Response<List<FeedResponse>> response) {
        feedResponseArrayList=response.body();

    }

    @Override
    public void onFailure(Call<List<FeedResponse>> call, Throwable t) {

    }
});
}
}