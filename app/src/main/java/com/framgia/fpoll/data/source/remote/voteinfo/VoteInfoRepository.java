package com.framgia.fpoll.data.source.remote.voteinfo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.framgia.fpoll.data.model.FpollComment;
import com.framgia.fpoll.data.model.poll.ParticipantVotes;
import com.framgia.fpoll.data.model.poll.Poll;
import com.framgia.fpoll.data.model.poll.ResultVoteItem;
import com.framgia.fpoll.data.model.poll.VoteInfo;
import com.framgia.fpoll.data.source.DataCallback;
import com.framgia.fpoll.networking.api.VoteInfoAPI;

/**
 * Created by anhtv on 07/03/2017.
 */
public class VoteInfoRepository implements VoteInfoDataSource {
    private static VoteInfoRepository sRepository;
    private VoteInfoDataSource mDataSource;

    public VoteInfoRepository(@NonNull VoteInfoDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static VoteInfoRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new VoteInfoRepository(VoteInfoRemoteDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public void getVoteInfo(String token, @NonNull final DataCallback<VoteInfo> callback) {
        if (mDataSource == null) return;
        mDataSource.getVoteInfo(token, new DataCallback<VoteInfo>() {
            @Override
            public void onSuccess(VoteInfo data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void getVoteResult(@NonNull String token,
                              @NonNull final DataCallback<ResultVoteItem> callback) {
        if (mDataSource == null) return;
        mDataSource.getVoteResult(token, new DataCallback<ResultVoteItem>() {
            @Override
            public void onSuccess(ResultVoteItem data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void postComment(VoteInfoAPI.CommentBody comment,
                            final DataCallback<FpollComment> callback) {
        if (mDataSource == null) return;
        mDataSource.postComment(comment, new DataCallback<FpollComment>() {
            @Override
            public void onSuccess(FpollComment data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void votePoll(VoteInfoAPI.OptionsBody optionsBody,
                         final DataCallback<ParticipantVotes> callback) {
        if (mDataSource == null) return;
        mDataSource.votePoll(optionsBody, new DataCallback<ParticipantVotes>() {
            @Override
            public void onSuccess(ParticipantVotes data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }

    @Override
    public void updateNewOption(int pollId, VoteInfoAPI.NewOptionBody newOptionBody,
                                final DataCallback<Poll> callback) {
        if (mDataSource == null) return;
        mDataSource.updateNewOption(pollId, newOptionBody, new DataCallback<Poll>() {
            @Override
            public void onSuccess(Poll data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String msg) {
                callback.onError(msg);
            }
        });
    }
}
