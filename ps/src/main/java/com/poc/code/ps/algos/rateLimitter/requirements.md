“Each customer can make X requests per Y seconds”

// Perform rate limiting logic for provided customer ID. Return true if the
// request is allowed, and false if it is not.
boolean rateLimit(int customerId)

r(customerId, timestamp) -> r(customerId, timestamp) -> r(customerId, timestamp) .......


Example
10 requests per second

Fixed Counter based approach
1. HashMap<CustomerId, Integer> -> expire the data after every Y sec

Pros
-Simple Implementation 

Cons-
- more number of requests near the transition boundry

Requests Log approach
count requests for last Y sec, decision on the limit available

Pros
1. No burst kind of traffic near boundry
Cons
1. Query is going to add latency to the response

Maintain a log of requests for Y sec, 

pros
1. query time is O(Y) 




