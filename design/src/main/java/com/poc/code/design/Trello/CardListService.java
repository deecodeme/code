package com.poc.code.design.Trello;

public interface CardListService {
    CardList create(String boardId, String name);

    CardList updateName(String id, String name);

    void createCard(String listId, String cardName);
}
