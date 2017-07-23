package com.rosiapps.daggerdemo.presentation.details.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserPresenter;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shmulik on 24/07/2017.
 * .
 */
public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final List<UserPresenter> users;

    public UsersAdapter() {
        users = new ArrayList<>(10);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserViewHolder viewHolder = new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
        injectDependencies(viewHolder);
        return viewHolder;
    }

    protected void injectDependencies(UserViewHolder viewHolder){

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void add(UserPresenter user) {
        users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    public void add(List<UserPresenter> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }
}