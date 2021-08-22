package com.example.projectmvp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import com.example.projectmvp.adapters.HomeFeedAdapter;
import com.example.projectmvp.helpers.StartSnapHelper;
import com.example.projectmvp.model.HomeFeedObjects;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewHomeFeed;
    ArrayList<HomeFeedObjects> homeFeedObjectsArrayList;
    HomeFeedAdapter homeFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewHomeFeed = (RecyclerView) findViewById(R.id.home_feed_recyclerview);
        DisplayMetrics metrics = getDisplayMetrics();

        homeFeedObjectsArrayList = new ArrayList<>();
        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "Reddit", R.mipmap.pic2, "this is Caption"));
        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "9GAG", R.mipmap.pic1, "this is 2nd Caption"));
        homeFeedObjectsArrayList.add(new HomeFeedObjects(R.mipmap.cat1, "Pewdiepie", R.mipmap.pic2, "this is 3rd Caption"));
getSupportActionBar().hide();
        homeFeedAdapter = new HomeFeedAdapter(metrics,homeFeedObjectsArrayList, MainActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewHomeFeed.setLayoutManager(linearLayoutManager);
        recyclerViewHomeFeed.setAdapter(homeFeedAdapter);
//        ItemTouchHelper.SimpleCallback simpleCallback = new SwipeRVTouchHelper(this::onSwiped, 0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
//        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerViewHomeFeed);
//        StartSnapHelper snapHelper=new StartSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerViewHomeFeed);


    }

    public void onSwiped( int direction, final int position) {
        // Temporary store the swiped off item
        final HomeFeedObjects contact = homeFeedObjectsArrayList.get(position);
        //Remove the item
        homeFeedAdapter.removeSwipeItem(position);
        // If swipe left - delete the item
        if (direction == ItemTouchHelper.LEFT) {
            Snackbar.make(recyclerViewHomeFeed, "Contact deleted", Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(getApplicationContext(), R.color.design_default_color_primary_dark))
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            homeFeedAdapter.addSwipeItem(position, contact);
                        }
                    }).show();
        } // If swipe left - archive the item
        else if (direction == ItemTouchHelper.RIGHT) {
            Snackbar.make(recyclerViewHomeFeed, "Contact archive", Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(getApplicationContext(), R.color.design_default_color_on_primary))
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            homeFeedAdapter.addSwipeItem(position, contact);
                        }
                    }).show();
        }
    }
        private DisplayMetrics getDisplayMetrics() {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);

            return metrics;
        }
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
