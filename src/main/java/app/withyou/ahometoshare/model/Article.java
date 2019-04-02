package app.withyou.ahometoshare.model;

import java.util.Date;

public class Article {
    private Integer id;

    private String title;

    private Date updateDate;

    private String content;

    public Article(Integer id, String title, Date updateDate, String content) {
        this.id = id;
        this.title = title;
        this.updateDate = updateDate;
        this.content = content;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}