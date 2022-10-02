package com.poc.code.practices.design.Trello;

public interface Card {
    String getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    User getAssignedUser();

    void setAssignedUser(User assignedUser);

    String getCardListId();

    void setCardListId(String cardListId);
}
