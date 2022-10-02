package com.poc.code.practices.design.Trello;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CardListServiceImpl implements CardListService {
    private static final CardListServiceImpl instance = new CardListServiceImpl();
    private Map<String, CardList> cardLists;
    private CardService cardService;

    private CardListServiceImpl() {
        this.cardService = CardServiceImpl.getInstance();
        this.cardLists = new HashMap<>();
    }

    public static CardListService getInstance() {
        return instance;
    }

    @Override
    public CardList create(String boardId, String name) {
        CardList cardList = new CardListImpl(UUID.randomUUID().toString(), name, boardId);
        this.cardLists.put(cardList.getId(), cardList);
        return cardList;
    }

    @Override
    public CardList updateName(String id, String name) {
        if (cardLists.containsKey(id)) {
            CardList cardList = cardLists.get(id);
            cardList.setName(name);
            return cardList;
        } else {
            throw new RuntimeException(String.format("CardList with id: %s not found", id));
        }
    }

    public void createCard(String listId, String cardName) {
        CardList cardList = cardLists.get(listId);
        Card card = this.cardService.create(listId, cardName);
        cardList.addCard(card.getId());
    }
}
