# Design a stock brokerage system
## Requirement
1. Any user of our system should be able to buy and sell stocks.
2. Any user can have multiple watchlists containing multiple stock quotes.
3. Users should be able to place stock trade orders of the following types: 1) market, 2) limit, 3) stop loss and, 4) stop limit.
4. Users can have multiple ‘lots’ of a stock. This means that if a user has bought a stock multiple times, the system should be able to differentiate between different lots of the same stock.
5. The system should be able to generate reports for quarterly updates and yearly tax statements.
6. Users should be able to deposit and withdraw money either via check, wire or electronic bank transfer.
7. The system should be able to send notifications whenever trade orders are executed.

## Use case
1. User buys stocks
2. User sells stock
3. User creates watchlists for stock quotes
4. Notify on trade order execution

## Event Storming
1. Buy stock event
2. Sell stock event
3. watchlist condition met
4. Stock price change

## Domains
1. User
2. Stock
3. Order(market/limit/stop_loss/stop_limit)
4. StockWatch

## Services
1. OrderService
2. UserService
3. StockInventory
4. WatchlistService
5. ReportingService
6. StockExchangeService