package com.sujianan.bean.blog;

import java.util.Date;

public class Blog {
    private String id;

    private String type;

    private String blogTitle;

    private String blogUrl;

    private String blogAuthor;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;


	public Blog() {
	}

	public Blog(String id, String type, String blogTitle, String blogUrl, String blogAuthor, String createUser,
			Date createTime, String updateUser, Date updateTime) {
		super();
		this.id = id;
		this.type = type;
		this.blogTitle = blogTitle;
		this.blogUrl = blogUrl;
		this.blogAuthor = blogAuthor;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle == null ? null : blogTitle.trim();
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl == null ? null : blogUrl.trim();
    }

    public String getBlogAuthor() {
        return blogAuthor;
    }

    public void setBlogAuthor(String blogAuthor) {
        this.blogAuthor = blogAuthor == null ? null : blogAuthor.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}