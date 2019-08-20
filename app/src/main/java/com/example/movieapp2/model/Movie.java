package com.example.movieapp2.model;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;




@Entity(tableName = "Movie")
public class Movie extends ViewModel implements Parcelable {
    @ColumnInfo(name = "id")
    private @PrimaryKey int id ;
    @ColumnInfo(name = "voteCount")
    private int voteCount ;
    @ColumnInfo(name="poster_path")
    private String poster_path ;
    @ColumnInfo(name="popularity")
    private Double popularity ;
    @ColumnInfo(name="originalLanguage")
    private String originalLanguage ;
    @ColumnInfo(name="originalTitle")
    private String originalTitle ;
    @ColumnInfo(name="backdropPath")
    private String backdropPath ;
    @ColumnInfo(name="adult")
    private boolean adult ;
    @ColumnInfo(name="overview")
    private String overview ;
    @ColumnInfo(name="releaseDate")
    private String releaseDate ;
    @ColumnInfo(name="voteAvarage")
    private double voteAvarage ;



    private Movie(Parcel in) {
        id = in.readInt();
        poster_path = in.readString();
        originalTitle = in.readString() ;
        backdropPath = in.readString() ;
        overview = in.readString() ;
        releaseDate = in.readString() ;
        voteAvarage = in.readDouble();
    }

    public Movie(int id,String poster_path, String originalTitle, String backdropPath, String overview, String releaseDate, double voteAvarage) {
        // the ordered in the details page from udacity :) ...
        this.id = id;
        this.poster_path = poster_path;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAvarage = voteAvarage;
    }


    @Ignore
    public Movie(){}

    @Ignore
    public Movie(int id, int voteCount, String poster_path, Double popularity, String originalLanguage, String originalTitle, String backdropPath, boolean adult, String overview, String releaseDate, ArrayList<Integer> genreIDS, double voteAvarage) {
        this.id = id;
        this.voteCount = voteCount;
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAvarage = voteAvarage;
    }


    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public double getVoteAvarage() {
        return voteAvarage;
    }

    public void setVoteAvarage(double voteAvarage) {
        this.voteAvarage = voteAvarage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", voteCount=" + voteCount +
                ", poster_path='" + poster_path + '\'' +
                ", popularity=" + popularity +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAvarage=" + voteAvarage +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path) ;
        dest.writeString(originalLanguage) ;
        dest.writeString(originalTitle) ;
        dest.writeString(backdropPath) ;
        dest.writeString(overview) ;
        dest.writeString(releaseDate) ;
        dest.writeInt(id);
        dest.writeInt(voteCount);
        dest.writeDouble(popularity);
    }
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


}
