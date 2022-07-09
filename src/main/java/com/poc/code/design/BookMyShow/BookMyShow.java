package com.poc.code.design.BookMyShow;

import java.util.List;
import java.util.Map;

public interface BookMyShow {
    List<Show> getShows(City city);
    List<Show> getShows(Theatre theatre);
    Booking bookShow(Theatre theatre, Show show);

    Show createShow(City city, Theatre theatre, Movie movie, ShowTime showTime);
    Show createMovie(Map<String, String> details);
}
