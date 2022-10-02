package com.poc.code.practices.design.Trello;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CardServiceImpl implements CardService {
    private static final CardService cardService = new CardServiceImpl();
    private Map<String, Card> cards;

    private CardServiceImpl() {
        cards = new HashMap<>();
    }

    public static CardService getInstance() {
        return cardService;
    }

    @Override
    public Card create(String cardListId, String name) {
        Card newCard = new CardImpl(UUID.randomUUID().toString(), name, cardListId);
        cards.put(newCard.getId(), newCard);
        return newCard;
    }

    @Override
    public Card updateName(String id, String name) {
        if (cards.containsKey(id)) {
            Card card = cards.get(id);
            card.setName(name);
            return card;
        } else {
            throw new RuntimeException(String.format("card with id: %s not found", id));
        }
    }

    @Override
    public Card updateDescription(String id, String description) {
        if (cards.containsKey(id)) {
            Card card = cards.get(id);
            card.setName(description);
            return card;
        } else {
            throw new RuntimeException(String.format("card with id: %s not found", id));
        }
    }

    @Override
    public void assign(String id, User user) {
        if (cards.containsKey(id)) {
            Card card = cards.get(id);
            card.setAssignedUser(user);
        } else {
            throw new RuntimeException(String.format("card with id: %s not found", id));
        }
    }

    @Override
    public void unAssign(String id) {
        if (cards.containsKey(id)) {
            Card card = cards.get(id);
            card.setAssignedUser(null);
        } else {
            throw new RuntimeException(String.format("card with id: %s not found", id));
        }
    }

    @Override
    public void moveCard(String id, String cardListId) {
        if (cards.containsKey(id)) {
            Card card = cards.get(id);
            card.setCardListId(cardListId);
        } else {
            throw new RuntimeException(String.format("card with id: %s not found", id));
        }
    }
}
