package rp.edu.sg.c346.id20021576.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    private int _id;
    private String title;
    private String singers;
    private int year;
    private String stars;

    public Song(String title, String singers, int year, String stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id){this._id = _id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSingers() {
        return singers;
    }
    public void setSingers(String singers) {
        this.singers = singers;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year){this.year = year; }
    public String getStar() { return stars; }
    public void setStars(String stars) {this.stars = stars; }

    @Override
    public String toString() { return title + "\n" + singers + "-" + year + "\n" + stars;  }

}
