package moviedbcom.valdir.themoviedb;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie  implements Parcelable{
    public String title;
    public String image;
    public String overview;
    public String vote_overage;
    public String date_release;

    public Movie(String title, String image, String overview, String vote_overage, String date_release) {
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.vote_overage = vote_overage;
        this.date_release = date_release;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        image = in.readString();
        overview = in.readString();
        vote_overage = in.readString();
        date_release = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_overage() {
        return vote_overage;
    }

    public String getDate_release() {
        return date_release;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(overview);
        dest.writeString(vote_overage);
        dest.writeString(date_release);
    }
}
