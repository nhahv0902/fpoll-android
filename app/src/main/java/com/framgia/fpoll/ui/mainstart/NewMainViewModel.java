package com.framgia.fpoll.ui.mainstart;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.framgia.fpoll.R;
import com.framgia.fpoll.data.model.PollItem;
import com.framgia.fpoll.ui.feedback.FeedbackFragment;
import com.framgia.fpoll.ui.history.HistoryFragment;
import com.framgia.fpoll.ui.joinpoll.JoinPollFragment;
import com.framgia.fpoll.ui.pollcreation.PollCreationActivity;
import com.framgia.fpoll.ui.profile.ProfileFragment;
import com.framgia.fpoll.util.ActivityUtil;

import static com.framgia.fpoll.util.Constant.RequestCode.REQUEST_CREATE_POLL;

/**
 * Exposes the data to be used in the NewMain screen.
 */

public class NewMainViewModel extends BaseObservable implements NewMainContract.ViewModel {
    private static final int NUMBER_IMAGE_CHILD = 0;
    private NewMainContract.Presenter mPresenter;
    private final AppCompatActivity mActivity;
    private ObservableBoolean mIsBottomNavigationShow = new ObservableBoolean(true);

    public NewMainViewModel(AppCompatActivity activity) {
        mActivity = activity;
        startUIHome();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(NewMainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onBottomBarClick(View view) {
        switch (view.getId()) {
            case R.id.relative_home:
                startUIHome();
                break;
            case R.id.relative_join_poll:
                addFragment(JoinPollFragment.newInstance());
                break;
            case R.id.relative_feedback:
                addFragment(FeedbackFragment.newInstance());
                break;
            case R.id.relative_profile:
                addFragment(ProfileFragment.newInstance());
                break;
            default:
                break;
        }
        LinearLayout container = (LinearLayout) view.getParent();
        for (int i = 0; i < container.getChildCount(); i++) {
            RelativeLayout v = (RelativeLayout) container.getChildAt(i);
            if (v.getChildCount() != 0) {
                v.getChildAt(NUMBER_IMAGE_CHILD).setSelected(false);
            }
        }

        if (view instanceof RelativeLayout) {
            ((RelativeLayout) view).getChildAt(NUMBER_IMAGE_CHILD).setSelected(true);
        }
    }

    private void startUIHome() {
        Fragment fragment =
                mActivity.getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if (!(fragment instanceof HistoryFragment)) {
            addFragment(HistoryFragment.newInstance());
        }
    }

    @Override
    public void onStartPollCreate() {
        mActivity.startActivityForResult(PollCreationActivity.getIntent(mActivity, new PollItem()),
                REQUEST_CREATE_POLL);
    }

    private void addFragment(Fragment fragment) {
        ActivityUtil.addFragment(mActivity.getSupportFragmentManager(), fragment,
                R.id.frame_layout);
    }

    @Override
    public void hideBottomNavigation() {
        mIsBottomNavigationShow.set(false);
    }

    @Override
    public void showBottomNavigation() {
        mIsBottomNavigationShow.set(true);
    }

    public ObservableBoolean getIsBottomNavigationShow() {
        return mIsBottomNavigationShow;
    }
}
