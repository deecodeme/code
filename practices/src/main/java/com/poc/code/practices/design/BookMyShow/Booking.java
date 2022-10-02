package com.poc.code.practices.design.BookMyShow;

import com.poc.code.practices.design.BookMyShow.PubSub.Publisher;

import java.util.Date;

public class Booking {
    private Long id;
    private Long showId;
    private Long userId;
    private Date createdAt;
    private Publisher publisher;

    public Booking(Long id, Long showId, Long userId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.showId = showId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Date updatedAt;


    public Booking createBooking(Long showId, Long userId, Date createdAt, Date updatedAt) {
        return new Booking(generateNextId(), showId, userId, createdAt, updatedAt);
    }

    private synchronized Long generateNextId() {
        id++;
        return id;
    }
}
