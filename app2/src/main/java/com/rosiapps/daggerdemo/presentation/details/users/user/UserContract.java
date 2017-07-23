package com.rosiapps.daggerdemo.presentation.details.users.user;

import com.rosiapps.daggerdemo.data.User;

/**
 * Created by Shmulik on 24/07/2017.
 * .
 */
public interface UserContract {
    interface View {
        void renderName(String name);
        void renderDetails(String details);
        void renderPicture(String url);
    }

    interface Presenter {
        void setModel(User model);
        void bindView(View view);
        void unbindView();
    }
}
