package com.rosiapps.daggerdemo.tests.assertions;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rosiapps.daggerdemo.utils.Action1;

import org.hamcrest.Matcher;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by shmulik on 19/07/2017.
 * .
 */
public class RecyclerViewAssertions {
    public static AllViewHoldersAssertion allViewHolders(Matcher<? super RecyclerView.ViewHolder> viewHolderMatcher) {
        return new AllViewHoldersAssertion(viewHolderMatcher);
    }

    public static AnyOfViewHoldersAssertion anyOfViewHolders(Matcher<? super RecyclerView.ViewHolder> viewHolderMatcher) {
        return new AnyOfViewHoldersAssertion(viewHolderMatcher);
    }

    public static ItemCountAssertion itemsCount(int expectedCount) {
        return new ItemCountAssertion(expectedCount);
    }


    public static class RecyclerViewAssertion implements ViewAssertion {
        private Action1<RecyclerView> mAssertAction;

        public RecyclerViewAssertion(Action1<RecyclerView> assertAction) {
            mAssertAction = assertAction;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            mAssertAction.call(recyclerView);
        }
    }

    public static class AllViewHoldersAssertion extends RecyclerViewAssertion {
        public AllViewHoldersAssertion(final Matcher<? super RecyclerView.ViewHolder> viewHolderMatcher) {
            super(new Action1<RecyclerView>() {
                @Override
                public void call(RecyclerView recyclerView) {
                    int count = recyclerView.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View child = recyclerView.getChildAt(i);
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(child);
                        assertThat(viewHolder, viewHolderMatcher);
                    }
                }
            });
        }
    }

    public static class AnyOfViewHoldersAssertion extends RecyclerViewAssertion {
        public AnyOfViewHoldersAssertion(final Matcher<? super RecyclerView.ViewHolder> viewHolderMatcher) {
            super(new Action1<RecyclerView>() {
                @Override
                public void call(RecyclerView recyclerView) {
                    boolean itemFound = false;
                    int count = recyclerView.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View child = recyclerView.getChildAt(i);
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(child);
                        if (viewHolderMatcher.matches(viewHolder)) {
                            itemFound = true;
                            break;
                        }
                    }

                    assertTrue("No view holder that match was found", itemFound);
                }
            });
        }
    }

    public static class ItemCountAssertion extends RecyclerViewAssertion {
        public ItemCountAssertion(final int expectedCount) {
            super(new Action1<RecyclerView>() {
                @Override
                public void call(RecyclerView recyclerView) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    assertThat(adapter.getItemCount(), is(expectedCount));
                }
            });
        }
    }
}
