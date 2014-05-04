package com.aldoborrero.minirefreshactionitem.sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.aldoborrero.minirefreshactionitem.IndeterminateRefreshMenuItem;
import com.aldoborrero.minirefreshactionitem.RefreshableMenuItem;
import com.aldoborrero.minirefreshactionitem.RotatingIndeterminateRefreshMenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    public static class MainFragment extends ListFragment {

        private List<FragmentEntry> fragmentEntries = Arrays.asList(
                new FragmentEntry(R.string.indeterminate_fragment_title, IndeterminateFragment.TAG, new IndeterminateFragment()),
                new FragmentEntry(R.string.rotating_indeterminate_fragment_title, RotatingIndeterminateFragment.TAG, new RotatingIndeterminateFragment())
        );

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setListAdapter(
                    new ArrayAdapter<String>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,
                            getFragmentTitles()
                    )
            );
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Fragment fragment = fragmentEntries.get(position).getFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(fragmentEntries.get(position).getTag())
                    .commit();
        }

        private List<String> getFragmentTitles() {
            List<String> titles = new ArrayList<>();
            for (FragmentEntry entry : fragmentEntries) {
                titles.add(getResources().getString(entry.getTitleResId()));
            }
            return titles;
        }
    }

    public static class FragmentEntry {

        private int titleResId;
        private String tag;
        private Fragment fragment;

        public FragmentEntry(int titleResId, String tag, Fragment fragment) {
            this.titleResId = titleResId;
            this.tag = tag;
            this.fragment = fragment;
        }

        public int getTitleResId() {
            return titleResId;
        }

        public String getTag() {
            return tag;
        }

        public Fragment getFragment() {
            return fragment;
        }

    }

    public static class IndeterminateFragment extends Fragment {

        public static final String TAG = IndeterminateFragment.class.getSimpleName();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_showcase, container, false);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.indeterminate, menu);
            MenuItem refreshMenuItem = menu.findItem(R.id.action_refresh);
            IndeterminateRefreshMenuItem mRefreshMenuItem =
                    (IndeterminateRefreshMenuItem) refreshMenuItem.getActionView();
            mRefreshMenuItem.setMenuItem(refreshMenuItem);
            mRefreshMenuItem.setOnRefreshActionListener(new RefreshableMenuItem.OnRefreshActionListener() {
                @Override
                public <T extends RefreshableMenuItem> void onRefreshButtonClick(final T sender) {
                    sender.showProgress(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isAdded()) {
                                return;
                            }
                            sender.showProgress(false);
                            Toast.makeText(getActivity(), R.string.done_refreshing, Toast.LENGTH_SHORT).show();
                        }
                    }, 5000);
                }
            });
        }

    }

    public static class RotatingIndeterminateFragment extends Fragment {

        public static final String TAG = RotatingIndeterminateFragment.class.getSimpleName();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_showcase, container, false);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.rotating_indeterminate, menu);
            MenuItem refreshMenuItem = menu.findItem(R.id.action_refresh);
            RotatingIndeterminateRefreshMenuItem mRefreshMenuItem =
                    (RotatingIndeterminateRefreshMenuItem) refreshMenuItem.getActionView();
            mRefreshMenuItem.setMenuItem(refreshMenuItem);
            mRefreshMenuItem.setOnRefreshActionListener(new RefreshableMenuItem.OnRefreshActionListener() {
                @Override
                public <T extends RefreshableMenuItem> void onRefreshButtonClick(final T sender) {
                    sender.showProgress(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!isAdded()) {
                                return;
                            }
                            sender.showProgress(false);
                            Toast.makeText(getActivity(), R.string.done_refreshing, Toast.LENGTH_SHORT).show();
                        }
                    }, 5000);
                }
            });
        }

    }

}
