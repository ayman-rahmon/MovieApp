package com.example.movieapp2.repository.storage.model;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;


@Entity(tableName = "Movies")
public class Movie extends BaseObservable  {

    @PrimaryKey()
    @ColumnInfo(name = "id") @SerializedName(value="id") private Integer mId;
    @ColumnInfo(name = "vote_count") @SerializedName(value="vote_count") private Integer mVoteCount;
    @ColumnInfo(name = "video") @SerializedName(value="video") private Boolean mVideo;
    @ColumnInfo(name = "vote_average") @SerializedName(value="vote_average") private Float mVoteAverage;
    @ColumnInfo(name = "title") @SerializedName(value="title") private String mTitle;
    @ColumnInfo(name = "popularity") @SerializedName(value="popularity") private Float mPopularity;
    @ColumnInfo(name = "poster_path") @SerializedName(value="poster_path") private String mPosterPath;
    @ColumnInfo(name = "original_language") @SerializedName(value="original_language") private String mOriginalLanguage;
    @ColumnInfo(name = "original_title") @SerializedName(value="original_title") private String mOriginalTitle;
    @ColumnInfo(name = "backdrop_path") @SerializedName(value="backdrop_path") private String mBackdropPath;
    @ColumnInfo(name = "adult") @SerializedName(value="adult") private Boolean mAdult;
    @ColumnInfo(name = "overview") @SerializedName(value="overview") private String mOverview;
    @ColumnInfo(name = "release_date") @SerializedName(value="release_date") private String mReleaseDate;


    //use for ordering the items in the view ...
    public static DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.equals(newItem.getmId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getmId().equals(newItem.getmId());
        }
    };

    @Bindable
    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    @Bindable
    public Integer getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(Integer mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    @Bindable
    public Boolean getmVideo() {
        return mVideo;
    }

    public void setmVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    @Bindable
    public Float getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(Float mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    @Bindable
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Bindable
    public Float getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(Float mPopularity) {
        this.mPopularity = mPopularity;
    }


    @Bindable
    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    @Bindable
    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    @Bindable
    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    @Bindable
    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    @Bindable
    public Boolean getmAdult() {
        return mAdult;
    }

    public void setmAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    @Bindable
    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    @Bindable
    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
