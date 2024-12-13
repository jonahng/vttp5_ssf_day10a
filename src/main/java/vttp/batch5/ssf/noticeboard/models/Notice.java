package vttp.batch5.ssf.noticeboard.models;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Notice {

    private String title;
    
    private String poster;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date postDate;

    private String[] categories;

    private String text;

    



    public Notice() {
    }

    

    public Notice(String title, String poster, Date postDate, String[] categories, String text) {
        this.title = title;
        this.poster = poster;
        this.postDate = postDate;
        this.categories = categories;
        this.text = text;
    }

    



    @Override
    public String toString() {
        return "notice [title=" + title + ", poster=" + poster + ", postDate=" + postDate + ", categories="
                + Arrays.toString(categories) + ", text=" + text + "]";
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    




    
}
