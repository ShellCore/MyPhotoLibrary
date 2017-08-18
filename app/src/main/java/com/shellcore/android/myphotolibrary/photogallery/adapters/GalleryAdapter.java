package com.shellcore.android.myphotolibrary.photogallery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shellcore.android.myphotolibrary.R;
import com.shellcore.android.myphotolibrary.db.entities.Photo;
import com.shellcore.android.myphotolibrary.libs.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 17/08/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private List<Photo> list;
    private OnItemClickListener clickListener;

    public GalleryAdapter(ImageLoader imageLoader, List<Photo> list, OnItemClickListener clickListener) {
        this.imageLoader = imageLoader;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_element, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = list.get(position);
        holder.setOnClickListener(photo, clickListener);
        imageLoader.load(holder.imgPhoto, photo.getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Photo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View v;

        // Components
        @BindView(R.id.img_photo)
        ImageView imgPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            v = itemView;
        }

        public void setOnClickListener(final Photo photo, final OnItemClickListener listener) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(photo);
                }
            });
        }
    }
}
