package com.poc.code.practices.design.Trello;

public interface BoardService {
    Board create(String name);

    void delete(String id);

    Board updatePrivacy(String id, Privacy privacy);

    Board updateName(String id, String name);

    void addMember(String id, String userId);

    void deleteMember(String id, String userId);

    CardList createCardList(String boardId, String cardListName);
}
