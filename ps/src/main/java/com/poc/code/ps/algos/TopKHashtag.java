package com.poc.code.ps.algos;

/*
Users should be able to see the top K trending hashtags in the last 24 hours.
 Here K can be dynamically updated and/or the time interval can be updated
  from 24 hours to say 1 hour and so on.
 */

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Problem Solving

Input:
1. hashtag streams

Intuition
1. I have to keep the data for last T hours
    1. We can use a LinkedList with nodes associated with timestamp
    2. We can delete the nodes whose timestamp is older than T window time   `
2. Keep ths hashtags sorted in a sorted set
    1. We can use TreeSet(BST)

To maintain above data efficiently
1. Delete should be efficient
2. Update should be efficient


Balanced BST
1. Insert O(logN)
2. Delete O()


Steps:
1. Maintain a sliding window of all the incoming hashtags with timestamps.
2. Maintain a sorted set of hashtags using count
1. Delete all the hashtags which are older than now() - T
    1. Update the count in sorted set. Remove if count becomes zero.
 */
public class TopKHashtag {
    private static final Logger logger = Logger.getLogger(TopKHashtag.class.getName());
    private Map<String, LinkedList<HashTagEvent>> slidingWindowMap;
    private TreeSet<HashTagCount> sortedSet;
    private Lock lock;
    private Lock slidingWindowLock;
    private long durationInSec;

    public TopKHashtag(long durationInSec) {
        this.durationInSec = durationInSec;
        this.slidingWindowMap = new HashMap<>();
        this.sortedSet = new TreeSet<>(Comparator.comparingInt(HashTagCount::getCount));
        this.lock = new ReentrantLock();
        this.slidingWindowLock = new ReentrantLock();
    }

    public void ingest(HashTagEvent hashTagEvent) {
        if (!this.slidingWindowMap.containsKey(hashTagEvent.hashTag)) {
            addNewHashTag(hashTagEvent.hashTag);
        }
        removeExpiredItems(hashTagEvent.hashTag);
        try {
            this.lock.lock();
            this.slidingWindowMap.get(hashTagEvent.hashTag).addFirst(hashTagEvent);
        } catch (Exception e) {

        } finally {
            this.lock.unlock();
        }
    }

    private void addNewHashTag(final String hashTag) {
        try {
            this.slidingWindowLock.lock();
            if (!this.slidingWindowMap.containsKey(hashTag)) {
                this.slidingWindowMap.put(hashTag, new LinkedList<>());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("Error while adding a new hashtag. Error: %s", e.getMessage()));
        } finally {
            this.slidingWindowLock.unlock();
        }
    }

    private void removeExpiredItems(String hashTag) {
        try {
            this.lock.lock();
            LinkedList<HashTagEvent> slidingWindow = this.slidingWindowMap.get(hashTag);
            while (slidingWindow.size() > 0
                    && slidingWindow.getLast().receivedAt.isBefore(Instant.now().minusSeconds(this.durationInSec))) {
                this.slidingWindowMap.get(hashTag).removeLast();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("Error while removing expired elements. Error: %s", e.getMessage()));
        } finally {
            this.lock.unlock();
        }
    }

    public static class HashTagEvent {
        public Instant receivedAt;
        public String hashTag;
    }

    public static class HashTagCount {
        private String hashTag;
        private int count;

        private HashTagCount(String hashTag, int count) {
            this.hashTag = hashTag;
            this.count = count;
        }

        public static HashTagCount of(String hashTag, int count) {
            return new HashTagCount(hashTag, count);
        }

        public String getHashTag() {
            return hashTag;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "HashTagCount{" +
                    "hashTag='" + hashTag + '\'' +
                    ", count=" + count +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(hashTag);
        }
    }

}
