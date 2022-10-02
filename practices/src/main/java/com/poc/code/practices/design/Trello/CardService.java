package com.poc.code.practices.design.Trello;

public interface CardService {
    Card create(String cardListId, String name);

    Card updateName(String id, String name);

    Card updateDescription(String id, String description);

    void assign(String id, User userId);

    void unAssign(String id);

    void moveCard(String id, String cardListId);
}
