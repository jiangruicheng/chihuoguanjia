package com.cndll.chgj.mvp.mode.bean.request;

/**
 * Created by jiang_ruicheng on 17/5/20.
 */

public class RequestUpdateStoery extends BaseRequest{

    /**
     * id : 3
     * name : 乡村18碗
     * type : 1
     * province : 2
     * city : 23
     */

    private String id;
    private String name;
    private String type;
    private int province;
    private int city;

    public String getId() {
        return id;
    }

    public RequestUpdateStoery setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RequestUpdateStoery setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public RequestUpdateStoery setType(String type) {
        this.type = type;
        return this;
    }

    public int getProvince() {
        return province;
    }

    public RequestUpdateStoery setProvince(int province) {
        this.province = province;
        return this;
    }

    public int getCity() {
        return city;
    }

    public RequestUpdateStoery setCity(int city) {
        this.city = city;
        return this;
    }
}
