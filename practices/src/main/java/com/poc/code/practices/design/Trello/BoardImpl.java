package com.poc.code.practices.design.Trello;

import java.util.List;

public class BoardImpl implements Board {
    private String id;
    private String name;
    private Privacy privacy;
    private String url;
    private List<String> members;
    private List<String> cardLists;

    public BoardImpl(String id, String name) {
        this.id = id;
        this.name = name;
        this.privacy = Privacy.PUBLIC;
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
    public Privacy getPrivacy() {
        return privacy;
    }

    @Override
    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<String> getMembers() {
        return members;
    }

    @Override
    public void addMembers(String memberId) {
        this.members.add(memberId);
    }

    @Override
    public void deleteMember(String memberId) {
        this.members.remove(memberId);
    }

    @Override
    public List<String> getCardLists() {
        return cardLists;
    }

    @Override
    public void addCardList(String cardListId) {
        this.cardLists.add(cardListId);
    }
}
