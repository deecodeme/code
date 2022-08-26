package com.poc.code.ps.random;

/*
Users should be able to see the top K trending hashtags in the last 24 hours.
 Here K can be dynamically updated and/or the time interval can be updated
  from 24 hours to say 1 hour and so on.
 */

/*
Problem Solving

Intution
1. I have to keep the data for last T hours
    1. We can use a DLL with nodes associated with timestamp
    2. We can delete the nodes whose timestamp is older than T window time   `
2. keep a copy of the data in the sorted order

To maintain above data efficiently
1. Delete should be efficient
2. Update should be efficient


Balanced BST
1. Insert O(logN)
2. Delete O()

 */
public class TopKHashtag {
}
