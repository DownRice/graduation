package cn.downrice.graduation.library.model.library;

import java.util.Date;

/**
 * 文档
 * @author 下饭
 */
public class Document {
    private int id;
    private String realId;
    private String title;
    private int uploader;
    private Date uploadTime;
    private String author;
    /**
     * 文档类型，doc(x)/ppt(x)/xls(x)/pdf/txt/
     */
    private String type;
    /**
     * 文档所在路径
     */
    private String loc;
    /**
     * 是否公开
     */
    private Boolean isOpen;
    /**
     * 是否已经转换
     */
    private Boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealId() {
        return realId;
    }

    public void setRealId(String realId) {
        this.realId = realId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUploader() {
        return uploader;
    }

    public void setUploader(int uploader) {
        this.uploader = uploader;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
