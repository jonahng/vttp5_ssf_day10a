package vttp.batch5.ssf.noticeboard.models;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.MatrixVariable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Notice {

    @NotEmpty(message = "Title required")
    @Size(min = 3, max = 128, message = "Must be 3-128characters")
    private String title;
    

    @Email(message = "invalid email format")
    @NotBlank (message = "must have email")
    private String poster;


    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDate;


    @NotEmpty(message = "category required")
    private String[] categories;

    @NotEmpty(message = "text required")
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
