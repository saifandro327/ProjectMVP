package com.example.projectmvp.adapters;

import android.content.Context;
import android.os.Looper;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectmvp.R;
import com.example.projectmvp.helpers.MetalRecyclerViewPager;
import com.example.projectmvp.model.HomeFeedObjects;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class HomeFeedAdapter extends MetalRecyclerViewPager.MetalAdapter<HomeFeedAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private Context context;
    private ArrayList<HomeFeedObjects> homeFeedObjects;


    public HomeFeedAdapter(@NonNull DisplayMetrics metrics, ArrayList<HomeFeedObjects> homeFeedObjects, Context context) {
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

        holder.textViewProfileName.setText(homeFeedObjects.get(position).getName());
        holder.textViewCaption.setText(homeFeedObjects.get(position).getCaption());
        Glide.with(context)
                .load(homeFeedObjects.get(position).getPost())
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(holder.imageViewPost);
        Glide.with(context)
                .load(homeFeedObjects.get(position).getProfileImage())
//                .centerCrop()
                .into(holder.imageViewProfile);

        holder.imageViewPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imageViewPopupMenu);

                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(context, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return homeFeedObjects.size();
    }


//    private void showPopupMenu(View view) {
//        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//        popupMenu.inflate(R.menu.popup_menu);
//        popupMenu.setOnMenuItemClickListener(context);
//        popupMenu.show();
//    }


    public class ViewHolder extends MetalRecyclerViewPager.MetalViewHolder {


        TextView textViewCaption, textViewProfileName;
        ImageView imageViewProfile, imageViewPost;
        ImageView imageViewPopupMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCaption = (TextView) itemView.findViewById(R.id.caption_textview);
            textViewProfileName = (TextView) itemView.findViewById(R.id.name_textview);
            imageViewProfile = (ImageView) itemView.findViewById(R.id.prifle_imageview);
            imageViewPost = (ImageView) itemView.findViewById(R.id.feed_imageview);
            imageViewPopupMenu = (ImageView) itemView.findViewById(R.id.action_popup_menu_imageview);
        }

        public ImageView getForegroundContainer() {
            return imageViewPost;
        }

    }
    public void removeSwipeItem(int position){
        homeFeedObjects.remove(position);
        this.notifyItemRemoved(position);
    }

    /**
     * Add a Contact *contact* into the recyclerview at index *position*
     * @param position
     * @param contact
     */
    public void addSwipeItem(int position, HomeFeedObjects contact){
        homeFeedObjects.add(position,contact);
        this.notifyItemInserted(position);
    }

}