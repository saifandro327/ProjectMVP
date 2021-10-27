/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.projectmvp.adapters;

import static java.util.Objects.requireNonNull;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmvp.R;
import com.example.projectmvp.helpers.MetalRecyclerViewPager;
import com.example.projectmvp.responses.Images;
import com.example.projectmvp.responses.Record;
import com.google.android.exoplayer2.ui.PlayerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import im.ene.toro.ToroPlayer;
import im.ene.toro.ToroUtil;
import im.ene.toro.exoplayer.ExoPlayerViewHelper;
import im.ene.toro.helper.ToroPlayerHelper;
import im.ene.toro.media.PlaybackInfo;
import im.ene.toro.widget.Container;

/**
 * @author Dharmesh Dhameliya (2021/10/20).
 */
public class HomeFeedAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_COUNT = 150;
    private List<Record> list = new ArrayList<>();
    private Container container;
    private final int VIDEO_ITEM = 1;
    private final int IMAGE_ITEM = 2;

    public HomeFeedAdapter2(Container container) {
        this.container = container;
    }

    @Override
    public int getItemViewType(int position) {
        Record record = list.get(position);
        if (record.getFields() != null
                && record.getFields().getVideos() != null
                && !record.getFields().getVideos().isEmpty()
                && record.getFields().getVideos().get(0) != null) {
            return VIDEO_ITEM;
        } else {
            if (record.getFields() != null
                    && record.getFields().getImages() != null
                    && !record.getFields().getImages().isEmpty()
                    && record.getFields().getImages().get(0) != null) {
                return IMAGE_ITEM;
            }
        }
        return IMAGE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        if (viewType == VIDEO_ITEM) {
            final View itemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video_layout_home, parent, false);
            return new VideoViewHolder(itemView);
        } else {
            final View itemView =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image_layout_home, parent, false);
            return new ImageViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.onBind(list.get(holder.getAdapterPosition()));
        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.onBind(list.get(holder.getAdapterPosition()));
        }
    }

    @Override
    public long getItemId(final int position) {
        return (long) position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Record> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {


        TextView textViewProfileName;
        ImageView imageViewProfile, imageViewPost;
        //        VideoView videoViewPost;
        //        ImageView imageViewPopupMenu;
        ImageView imageViewShare, imageViewDownload;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProfileName = (TextView) itemView.findViewById(R.id.name_textview);
            imageViewProfile = (ImageView) itemView.findViewById(R.id.prifle_imageview);
            imageViewPost = (ImageView) itemView.findViewById(R.id.feed_imageview);
            imageViewShare = (ImageView) itemView.findViewById(R.id.share_imageview);
//            videoViewPost = (VideoView) itemView.findViewById(R.id.feed_videoview);
        }

        public void onBind(Record record) {

            if (record != null) {
                Context context = itemView.getContext();
                if (record.getFields().getImages() != null) {
//                    videoViewPost.setVisibility(View.GONE);
                    List<Images> enrloment = record.getFields().getImages();
                    ArrayList<String> enrolmentData = new ArrayList();
                    for (int i = 0; i < enrloment.size(); i++) {
                        enrolmentData.add(enrloment.get(i).getUrl());
                    }

                }
                textViewProfileName.setText(record.getFields().getName());
                if (record.getFields() != null && record.getFields().getProfilePicture() != null && !record.getFields().getProfilePicture().isEmpty() && record.getFields().getProfilePicture().get(0).getUrl() != null) {
                    Glide.with(itemView.getContext()).load(record.getFields().getProfilePicture().get(0).getUrl()).into(imageViewProfile);
                } else {
                    Glide.with(itemView.getContext()).load("").into(imageViewProfile);
                }
                if (record.getFields() != null && record.getFields().getImages() != null && !record.getFields().getImages().isEmpty() && record.getFields().getImages().get(0).getUrl() != null) {
                    Glide.with(context)
                            .load(record.getFields().getImages().get(0).getUrl())
                            .into(imageViewPost);

                } else {
                    Glide.with(context)
                            .load("")
                            .into(imageViewPost);
                }
                imageViewShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri bmpUri = getLocalBitmapUri(imageViewPost);
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
            }
        }

    }

    @SuppressWarnings("WeakerAccess")
            //
    class VideoViewHolder extends BaseViewHolder implements ToroPlayer, ToroPlayer.EventListener {

        final PlayerView playerView;
        final ImageView posterView;
        final ImageView imageViewProfile;
        //        final ImageView feed_imageview;
        final ImageView imageViewShare;
        TextView textViewProfileName;
        ToroPlayerHelper helper;
        Uri mediaUri;

        VideoViewHolder(@NotNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            posterView = itemView.findViewById(R.id.posterView);
            imageViewProfile = itemView.findViewById(R.id.prifle_imageview);
//            feed_imageview = itemView.findViewById(R.id.feed_imageview);
            imageViewShare = itemView.findViewById(R.id.share_imageview);
            textViewProfileName = itemView.findViewById(R.id.name_textview);
            playerView.removeView(posterView);
            requireNonNull(playerView.getOverlayFrameLayout()).addView(posterView);
        }

        @NonNull
        @Override
        public View getPlayerView() {
            return this.playerView;
        }

        @NonNull
        @Override
        public PlaybackInfo getCurrentPlaybackInfo() {
            return helper != null ? helper.getLatestPlaybackInfo() : new PlaybackInfo();
        }

        @Override
        public void initialize(@NonNull Container container, @NonNull PlaybackInfo playbackInfo) {
            if (helper == null) {
                if (mediaUri != null) helper = new ExoPlayerViewHelper(this, mediaUri);
            }

            if (helper != null) {
                helper.addPlayerEventListener(this);
                helper.initialize(container, playbackInfo);
            }
        }

        @Override
        public void play() {
            if (helper != null) helper.play();
        }

        @Override
        public void pause() {
            if (helper != null) helper.pause();
        }

        @Override
        public boolean isPlaying() {
            return helper != null && helper.isPlaying();
        }

        @Override
        public void release() {
            if (helper != null) {
                helper.removePlayerEventListener(this);
                helper.release();
                helper = null;
                initialize(container,getCurrentPlaybackInfo());
            }
        }

        @Override
        public boolean wantsToPlay() {
            return ToroUtil.visibleAreaOffset(this, itemView.getParent()) >= 0.75;
        }

        @Override
        public int getPlayerOrder() {
            return getAdapterPosition();
        }

        @Override
        public void onFirstFrameRendered() {
            posterView.setVisibility(View.GONE);
        }

        @Override
        public void onBuffering() {
            // posterView.setVisibility(View.GONE);
        }

        @Override
        public void onPlaying() {
            posterView.setVisibility(View.GONE);
        }

        @Override
        public void onPaused() {
            posterView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onCompleted() {
             posterView.setVisibility(View.VISIBLE);
             release();
        }

        @Override
        public void onBind(@Nullable Record record) {
            super.onBind(record);
            posterView.setVisibility(View.VISIBLE);
            Context context = itemView.getContext();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlaying()) {
                        pause();
                    } else {
                        play();
                    }
                }
            });

            if (record != null && record.getFields() != null
                    && record.getFields().getVideos() != null
                    && !record.getFields().getVideos().isEmpty()
                    && record.getFields().getVideos().get(0) != null) {
                String videoUrl = record.getFields().getVideos().get(0).getUrl();
                this.mediaUri = Uri.parse(videoUrl);
                Glide.with(context)
                        .load(record.getFields().getVideos().get(0).getUrl())
                        .into(posterView);
            } else {
                this.mediaUri = Uri.parse("");
                Glide.with(context)
                        .load("")
                        .into(posterView);
            }
            textViewProfileName.setText(record.getFields().getName());
            if (record.getFields() != null && record.getFields().getProfilePicture() != null && !record.getFields().getProfilePicture().isEmpty() && record.getFields().getProfilePicture().get(0).getUrl() != null) {
                Glide.with(itemView.getContext()).load(record.getFields().getProfilePicture().get(0).getUrl()).into(imageViewProfile);
            } else {
                Glide.with(itemView.getContext()).load("").into(imageViewProfile);
            }

            imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri bmpUri = getLocalBitmapUri(posterView);
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
