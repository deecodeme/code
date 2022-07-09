package com.poc.code.design.Trello;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoardServiceImpl implements BoardService {
    private static final BoardServiceImpl boardService = new BoardServiceImpl();
    private Map<String, Board> boards;
    private CardListService cardListService;

    private BoardServiceImpl() {
        this.boards = new HashMap<>();
        cardListService = CardListServiceImpl.getInstance();
    }

    public static BoardService getInstance() {
        return boardService;
    }

    @Override
    public Board create(String name) {
        Board newBoard = new BoardImpl(UUID.randomUUID().toString(), name);
        this.boards.put(newBoard.getId(), newBoard);
        return newBoard;
    }

    @Override
    public void delete(String id) {
        if (this.boards.containsKey(id)) {
            this.boards.remove(id);
        } else {
            throw new RuntimeException(String.format("Board with id: %s not found", id));
        }
    }

    @Override
    public Board updatePrivacy(String id, Privacy privacy) {
        if (this.boards.containsKey(id)) {
            Board board = this.boards.get(id);
            board.setPrivacy(privacy);
            return board;
        } else {
            throw new RuntimeException(String.format("Board with id: %s not found", id));
        }
    }

    @Override
    public Board updateName(String id, String name) {
        if (this.boards.containsKey(id)) {
            Board board = this.boards.get(id);
            board.setName(name);
            return board;
        } else {
            throw new RuntimeException(String.format("Board with id: %s not found", id));
        }
    }

    @Override
    public void addMember(String id, String userId) {
        if (this.boards.containsKey(id)) {
            Board board = this.boards.get(id);
            board.addMembers(userId);
        } else {
            throw new RuntimeException(String.format("Board with id: %s not found", id));
        }
    }

    @Override
    public void deleteMember(String id, String userId) {
        if (this.boards.containsKey(id)) {
            Board board = this.boards.get(id);
            board.deleteMember(userId);
        } else {
            throw new RuntimeException(String.format("Board with id: %s not found", id));
        }
    }

    public CardList createCardList(String boardId, String cardListName) {
        Board board = boards.get(boardId);
        CardList cardList = cardListService.create(boardId, cardListName);
        board.addCardList(cardList.getId());
        return cardList;
    }
}
