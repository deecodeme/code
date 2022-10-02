package com.poc.code.practices.design.Trello;

import java.util.List;

public interface CardList {
    String getId();

    String getName();

    void setName(String name);

    List<String> getCards();

    void addCard(String card);

    String getBoard();

    void setBoard(String boardId);
}
