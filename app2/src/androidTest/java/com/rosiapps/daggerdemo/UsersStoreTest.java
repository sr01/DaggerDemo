package com.rosiapps.daggerdemo;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.rosiapps.daggerdemo.data.store.UsersStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Shmulik on 24/07/2017.
 * .
 */
@RunWith(AndroidJUnit4.class)
public class UsersStoreTest {

    private Context mContext;

    @Before
    public void initTargetContext() {
        mContext = getTargetContext();
        assertThat(mContext, notNullValue());
    }

    @Test
    public void verifyStorageJsonDesrialization() throws IOException {
        UsersStore usersStore = UsersStore.create(mContext);
        assertThat(usersStore, notNullValue());
        assertThat(usersStore.getInfo().getResults(), is(10));
        assertThat(usersStore.getResults().size(), is(10));
    }
}
