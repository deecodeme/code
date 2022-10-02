package com.poc.code.practices.design.Trello;

import java.util.List;

public interface Board {
    String getId();

    String getName();

    void setName(String name);

    Privacy getPrivacy();

    void setPrivacy(Privacy privacy);

    String getUrl();

    void setUrl(String url);

    List<String> getMembers();

    void addMembers(String memberId);

    void deleteMember(String memberId);

    List<String> getCardLists();

    void addCardList(String cardListId);
}
