## Problem Statement
1. Rate limit to have control over usage
2. Smoother traffic flow to avoid burst traffic

### Token Bucket
1. Limited number of tokens are allocated to utilise.
2. Requests are dropped, if you do not have tokens available
3. Tokens should be replenished at interval
#### Pros
1. Easy to implement, have a map of counters for all the keys.
#### Cons
1. Bursts of calls near reset window

### Leaky Bucket
1. Queue based implementation
2. Fixed queue size with push and pop
3. pop at fixed rate
#### Pros
#### Cons
1. A slower request at the front of the queue can increase response time of other requests.

### Fixed Window
1. Similar to Token Bucket
2. Every request timestamp gets mapped to a window 
3. If the window counter is > 0, request is allowed
4. No need to replenish a counter as windows are initialised with counter.
#### Cons
1. Requests coming at end and start of consecutive windows can cause burst traffic.

### Event log
1. Query number of request in last window interval and check if new request is allowed.
#### Pros
1. No bursts
#### Cons
1. Query time adds to response time

### Sliding Window
1. Maintain a sliding window using DLL
2. Each request is a node in DLL with a timestamp 
3. every time a request comes, delete the old elements and insert new request if DLL size is less than window
#### Pros
1. No burst
#### Cons
1. Maintaining the DLL for every rate limiter