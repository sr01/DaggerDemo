package com.rosiapps.daggerdemo.presentation.details.users;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.presentation.details.ProgressViewInteractor;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserPresenter;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserViewHolder;
import com.rosiapps.daggerdemo.utils.Injector;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UsersFragment extends Fragment implements UsersContract.View {
    @Inject
    UsersContract.Presenter presenter;
    @Inject
    ProgressViewInteractor progressViewInteractor;
    @Inject
    Injector<UserViewHolder> userViewHolderInjector;
    private UsersAdapter adapter;

    public UsersFragment() {
    }

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new UsersAdapter() {
            @Override
            protected void injectDependencies(UserViewHolder viewHolder) {
                userViewHolderInjector.doInject(viewHolder);
            }
        };
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void showProgress() {
        progressViewInteractor.showProgress();
    }

    @Override
    public void hideProgress() {
        progressViewInteractor.hideProgress();
    }

    @Override
    public void renderUsers(List<UserPresenter> users) {
        adapter.add(users);
    }
}
