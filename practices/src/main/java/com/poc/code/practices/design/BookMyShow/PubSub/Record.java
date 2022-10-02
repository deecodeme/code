package com.poc.code.practices.design.BookMyShow.PubSub;

public class Record {
    private String id;
    private Object data;
    private Object meta;

    public Record(String id, Object data, Object meta, Object header) {
        this.id = id;
        this.data = data;
        this.meta = meta;
        this.header = header;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    private Object header;
}
