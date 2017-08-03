package com.rosiapps.daggerdemo.tests.matchers;


import android.content.res.Resources;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by shmulik on 18/07/2017.
 * .
 */
@SuppressWarnings("ALL")
public class RecyclerViewMatchers {
    public static Matcher<RecyclerView.ViewHolder> firstViewHolderOfType(final Class<?> t) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecyclerView.ViewHolder>(RecyclerView.ViewHolder.class) {
            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder item) {
                return t.isInstance(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("view holder of type  title: " + t.getName());
            }
        };
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    /**
     * Created by dannyroa on 5/10/15.
     */
    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;
                private static final int ERROR_RECYCLERVIEW_NOT_FOUND = 1;
                private static final int ERROR_ITEM_NOT_FOUND = 2;
                private static final int NO_ERROR = 0;
                int errorCode = NO_ERROR;

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolderForAdapterPosition != null) {
                                childView = viewHolderForAdapterPosition.itemView;
                            } else {
                                errorCode = ERROR_ITEM_NOT_FOUND;
                                return false;
                            }
                        } else {
                            errorCode = ERROR_RECYCLERVIEW_NOT_FOUND;
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }

                }

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);

                    switch (errorCode) {
                        case ERROR_RECYCLERVIEW_NOT_FOUND:
                            description.appendText(", RecyclerView not found ");
                            break;
                        case ERROR_ITEM_NOT_FOUND:
                            description.appendText(", item not found at position " + position + " ");
                            break;
                    }

                }
            };
        }
    }
}