package com.rosiapps.daggerdemo.data.store;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rosiapps.daggerdemo.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UsersStore {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public UsersStore() {
        results = new ArrayList<>();
        info = new Info();
    }

    public static UsersStore create(Context context) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.usersstore);
        String json = IOUtils.toString(inputStream);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, UsersStore.class);
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
