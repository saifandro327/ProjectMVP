package com.example.projectmvp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmvp.adapters.HomeFeedAdapter2;
import com.example.projectmvp.apis.ApiClient;
import com.example.projectmvp.apis.Apis;
import com.example.projectmvp.helpers.DetectConnection;
import com.example.projectmvp.helpers.TestCustomDialogClass;
import com.example.projectmvp.responses.FeedResponse;
import com.example.projectmvp.responses.Record;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import im.ene.toro.PlayerDispatcher;
import im.ene.toro.PlayerSelector;
import im.ene.toro.ToroPlayer;
import im.ene.toro.widget.Container;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestMainActivity extends AppCompatActivity {


    private Container container;
    private HomeFeedAdapter2 homeFeedAdapter;
    Apis apis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);

        container = findViewById(R.id.native_recycler_view);
        homeFeedAdapter = new HomeFeedAdapter2(container);
        container.setPlayerSelector(PlayerSelector.BY_AREA);

        container.setAdapter(homeFeedAdapter);
        container.setLayoutManager(new LinearLayoutManager(this));
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(container);
        getSupportActionBar().hide();
        getFeeddata();
    }

    private DisplayMetrics getDisplayMetrics() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics;
    }


    public class TryAgainListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // Code to undo the user's last action
            View contextView = findViewById(android.R.id.content);
            // Make and display Snackbar
            if (!DetectConnection.checkInternetConnection(TestMainActivity.this)) {
                Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", 30000);
                snackbar.setAction("Retry", new TestMainActivity.TryAgainListener());
                snackbar.show();
            } else {
                getFeeddata();
                Snackbar.make(contextView, "Internet Connected", Snackbar.LENGTH_SHORT)
                        .show();
            }

        }
    }

    public void getFeeddata() {
        DisplayMetrics metrics = getDisplayMetrics();

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(TestMainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        View contextView = findViewById(android.R.id.content);

        if (!DetectConnection.checkInternetConnection(this)) {
            progressDoalog.dismiss();
            Snackbar snackbar = Snackbar.make(contextView, "Sorry, No Internet", 30000);
            snackbar.setAction("Retry", new TestMainActivity.TryAgainListener());
            snackbar.show();

        } else {


            final Call<FeedResponse> feedResponse = ApiClient.getUserService().getfeedData();

            feedResponse.enqueue(new Callback<FeedResponse>() {
                @Override
                public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                    progressDoalog.dismiss();
                    FeedResponse googsheetResponse = response.body();
                    if (googsheetResponse != null) {
                        List<Record> feedResponseArrayList = new ArrayList(Arrays.asList(googsheetResponse.getRecords()));

//                        List<Uri> finatList = new ArrayList<>();
//                        for (Record record : feedResponseArrayList) {
//                            if(record != null && record.getFields() != null && record.getFields().getVideos() != null && !record.getFields().getVideos().isEmpty()){
//                                String videoUrl = String.valueOf(record.getFields().getVideos().get(0));
//                                Log.e("TAG",videoUrl);
//                                finatList.add(Uri.parse("https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8"));
//                            }
//
//                        }
                        homeFeedAdapter.addItems(feedResponseArrayList);
//                        homeFeedAdapter = new HomeFeedAdapter(metrics, feedResponseArrayList, TestMainActivity.this);
//                        recyclerViewHomeFeed.setLayoutManager(linearLayoutManager);
//                        recyclerViewHomeFeed.setAdapter(homeFeedAdapter);
                    }
                }

                @Override
                public void onFailure(Call<FeedResponse> call, Throwable t) {
                    Toast.makeText(TestMainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        TestCustomDialogClass cdd = new TestCustomDialogClass(TestMainActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
    }
}