package com.poc.code.practices.design.Trello;

public class CardImpl implements Card {
    private String id;
    private String name;
    private String description;
    private User assignedUser;
    private String cardListId;

    public CardImpl(String id, String name, String cardListId) {
        this.id = id;
        this.name = name;
        this.cardListId = cardListId;
    }

    @Override
    public String getId() {
        return id;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public User getAssignedUser() {
        return assignedUser;
    }

    @Override
    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public String getCardListId() {
        return cardListId;
    }

    @Override
    public void setCardListId(String cardListId) {
        this.cardListId = cardListId;
    }
}
