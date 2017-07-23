package com.rosiapps.daggerdemo.presentation.details.users.user;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosiapps.daggerdemo.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by Shmulik on 24/07/2017.
 * .
 */
public class UserViewHolder extends RecyclerView.ViewHolder implements UserContract.View {
    private final ImageView imageView;
    private final TextView nameTextView;
    private final TextView detailsTextView;
    @Inject
    Picasso picasso;
    private UserContract.Presenter presenter;

    public UserViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.iv_picture);
        nameTextView = (TextView) itemView.findViewById(R.id.txt_name);
        detailsTextView = (TextView) itemView.findViewById(R.id.txt_details);
    }

    public void bind(UserPresenter presenter) {
        this.presenter = presenter;
        presenter.bindView(this);
    }

    @Override
    public void renderName(String name) {
        nameTextView.setText(name);
    }

    @Override
    public void renderDetails(String details) {
        detailsTextView.setText(details);
    }

    @Override
    public void renderPicture(String url) {
        picasso.load(url).into(imageView);
    }
}
