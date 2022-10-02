package com.poc.code.practices.design.Trello;

import java.util.List;

public class CardListImpl implements CardList {
    private String id;
    private String name;
    private List<String> cards;
    private String boardId;

    @Override
    public String getId() {
        return id;
    }

    public CardListImpl(String id, String name, String boardId) {
        this.id = id;
        this.name = name;
        this.boardId = boardId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<String> getCards() {
        return cards;
    }

    @Override
    public void addCard(String card) {
        this.cards.add(card);
    }

    @Override
    public String getBoard() {
        return boardId;
    }

    @Override
    public void setBoard(String boardId) {
        this.boardId = boardId;
    }
}
