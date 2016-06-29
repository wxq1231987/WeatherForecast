package com.wangxuqin.weatherforecast.entity;

/**
 * Created by acer on 2016/6/7.
 */
public class Region {
    String id;
    String name;
    String FullName;
    String ParentId;
    String zip;
    String CreateTime;

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(String name, String id) {
        this.name = name;

        this.id = id;
    }

    public Region(String id, String name, String fullName, String parentId, String zip, String createTime) {
        this.id = id;
        this.name = name;
        FullName = fullName;
        ParentId = parentId;
        this.zip = zip;
        CreateTime = createTime;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return FullName;
    }

    public String getParentId() {
        return ParentId;
    }

    public String getZip() {
        return zip;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", FullName='" + FullName + '\'' +
                ", ParentId='" + ParentId + '\'' +
                ", zip='" + zip + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                '}';
    }
}
