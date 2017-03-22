package com.framgia.fpoll.ui.votemanager.vote;

import com.framgia.fpoll.data.model.poll.Option;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.ui.base.BaseView;
import com.framgia.fpoll.ui.votemanager.itemmodel.OptionModel;
import com.framgia.fpoll.ui.votemanager.itemmodel.VoteInfoModel;

import java.util.List;

/**
 * Created by tran.trung.phong on 23/02/2017.
 */
public interface VoteContract {
    interface View extends BaseView {
        void updateVoteChoice(OptionModel option);
        void onSubmitSuccess(List<Option> options);
        void onSubmitFailed(String messages);
        void onNotifyVote();
        void setLoading(boolean isShow);
        void showGallery();
        void updateAdditionOptionSuccess();
        void showVoteRequirement(int msg);
        void resetChoiceBox();
    }

    interface Presenter {
        void voteOption(OptionModel option);
        void submitVote(VoteInfoModel voteInfoModel);
        void openGallery();
        void setImageOption(String imagePath);
        void updateAdditionOption(VoteInfoModel voteInfoModel);
    }
}
