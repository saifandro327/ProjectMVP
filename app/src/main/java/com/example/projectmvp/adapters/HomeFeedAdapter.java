package com.example.projectmvp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectmvp.R;
import com.example.projectmvp.helpers.MetalRecyclerViewPager;
import com.example.projectmvp.responses.FeedResponse;
import com.example.projectmvp.responses.GoogsheetResponse;
import com.example.projectmvp.responses.Images;
import com.example.projectmvp.responses.JsonModel;
import com.example.projectmvp.responses.Record;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import fr.tvbarthel.intentshare.IntentShare;

public class HomeFeedAdapter extends MetalRecyclerViewPager.MetalAdapter<HomeFeedAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private Context context;
    private String base_URL = "https://api.airtable.com/";
    private List<Record> homeFeedObjects;

    public HomeFeedAdapter(@NonNull DisplayMetrics metrics, List<Record> homeFeedObjects, Context context) {
        super(metrics);
        this.homeFeedObjects = homeFeedObjects;
        this.context = context;
    }


    @NonNull
    @Override
    public HomeFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_home, parent, false);

        return new HomeFeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeFeedAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String imageUrl;
        String videoUrl = String.valueOf(homeFeedObjects.get(position).getFields().getVideos());
        try {
            if (homeFeedObjects.get(position).getFields().getImages() != null) {
                holder.videoViewPost.setVisibility(View.GONE);
                List<Images> enrloment = homeFeedObjects.get(position).getFields().getImages();
                ArrayList<String> enrolmentData = new ArrayList();
                for (int i = 0; i < enrloment.size(); i++) {
                    enrolmentData.add(enrloment.get(i).getUrl());
                }

            } else {
                Uri videoUri = Uri.parse(videoUrl);
                try {
                    holder.videoViewPost.setVideoURI(videoUri);
                    holder.videoViewPost.setTag(videoUrl);
                    String hasVideo_string = (String) holder.videoViewPost.getTag();
                    boolean hasVideo = Boolean.parseBoolean(hasVideo_string);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//        String image = base_URL + homeFeedObjects.get(position).getFields().getProfilePicture().get(1).getUrl();

            holder.textViewProfileName.setText(homeFeedObjects.get(position).getFields().getName());
//        holder.textViewCaption.setText(homeFeedObjects.get(position).getFields().getCaption());
            Glide.with(context).load(homeFeedObjects.get(position).getFields().getProfilePicture().get(0).getUrl()).
//                apply(new RequestOptions().override(75, 75)).


        into(holder.imageViewProfile);
//                   .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
            Glide.with(context)
                    .load(homeFeedObjects.get(position).getFields().getImages().get(0).getUrl())
                    .into(holder.imageViewPost);
//        Glide.with(context)
//                .load(enrolmentData)
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .dontAnimate()
//                .apply(new RequestOptions().override(500, 500))
//                .into(holder.imageViewPost);
//Picasso.get().load(homeFeedObjects.get(position).getFields().getImages()).into(holder.imageViewPost);
//Picasso.get().load(homeFeedObjects.get(position).getFields().getImages()).into(holder.imageViewPost);
        } catch (Exception e) {

        }
//        holder.imageViewPopupMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(context, holder.imageViewPopupMenu);
//
//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        // Toast message on menu item clicked
//                        Toast.makeText(context, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//            }
//        });
        holder.imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri bmpUri = getLocalBitmapUri(holder.imageViewPost);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "For Countless memes Download the App at" + "https://jaffsay.weebly.com/download.html");

                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    context.startActivity(Intent.createChooser(shareIntent, "Share Meme"));
                } else {
                    Toast.makeText(context, "Sharing failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TEXT, "Let's go for a trip to "
//                + placeNames[holder.getAdapterPosition()] +
//                "\nHere is the link to the full review: " + placeGuide[holder.
//                getAdapterPosition()]);
//        intent.setType("text/plain");
//        context.startActivity(Intent.createChooser(intent, "Send To"));
    }


    @Override
    public int getItemCount() {
        return homeFeedObjects.size();
    }

//    public void showMenu(int position) {
//        for(int i=0; i<homeFeedObjects.size(); i++){
//            homeFeedObjects.get(i).setShowMenu(false);
//        }
//        homeFeedObjects.get(position).setShowMenu(true);
//        notifyDataSetChanged();
//    }


//    public boolean isMenuShown() {
//        for(int i=0; i<homeFeedObjects.size(); i++){
//            if(homeFeedObjects.get(i).isShowMenu()){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void closeMenu() {
//        for(int i=0; i<homeFeedObjects.size(); i++){
//            homeFeedObjects.get(i).setShowMenu(false);
//        }
//        notifyDataSetChanged();
//    }


    public class ViewHolder extends MetalRecyclerViewPager.MetalViewHolder {


        TextView textViewProfileName;
        ImageView imageViewProfile, imageViewPost;
        VideoView videoViewPost;
        //        ImageView imageViewPopupMenu;
        ImageView imageViewShare, imageViewDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textViewCaption = (TextView) itemView.findViewById(R.id.caption_textview);
            textViewProfileName = (TextView) itemView.findViewById(R.id.name_textview);
            imageViewProfile = (ImageView) itemView.findViewById(R.id.prifle_imageview);
            imageViewPost = (ImageView) itemView.findViewById(R.id.feed_imageview);
//            imageViewPopupMenu = (ImageView) itemView.findViewById(R.id.action_popup_menu_imageview);
            imageViewShare = (ImageView) itemView.findViewById(R.id.share_imageview);
            videoViewPost = (VideoView) itemView.findViewById(R.id.feed_videoview);

//            imageViewDownload = (ImageView) itemView.findViewById(R.id.download_imageview);

        }


    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


}