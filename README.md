# pokemon-scanner
Pokemon card scanner where you can upload pictures and get prices on your collection. First you need to log into your account. You can then select and "sell" cards to make money. We can get the prices of cards from PokeTrace API (https://poketrace.com/docs). There should be a collections page to view all your current pokemon cards in your collection, along with the pictures of each card you uploaded for them and their pricing and total collection price.

So login page, then collections tab/page where top bar allows you to add cards by importing images and next to each card there will be the option to remove a card or mark a card as sell. Then at the bottom there will always be a bar that shows which cards you have listed to sell and how much money you will get in total.
Bonus: Make music tab to play Pokemon music :), maybe have a history of when pokemon cards were added/sold in your collection and have a line graph representing your current profit margins

Use MySQL to store usernames and passwords for accounts, as well as the players collection. The tables will look something like this:

### users
```sql
users
------
id (PK)
username (unique)
pass
created_at
```

### user_cards

```sql
user_cards
-----------
id (PK)
user_id (FK → users.id)

card_api_id (string)   -- e.g. "sv1-1"
card_name              -- stored for fast display
set_name

condition              -- "NM", "LP", etc.
is_graded (boolean)
grade_value (int)      -- PSA 10 etc.

purchase_price (optional)
notes (optional)

created_at
```

### price_cache (so we don't hit the API constantly, store already searched up prices here)
```sql
price_cache
------------
card_api_id (PK)
market_price
last_updated
```
We can get the price of cards with PokeTrace API GET calls, and grab the unique id and prices they list and store it into the price_cache table.

Use Spring Boot, REST, JDBC/JPA, MySQL, React.

Maybe use Cloudinary to store images of the uploaded cards in collection.

## Tech Stack

- Backend: Spring Boot, REST API, JPA/Hibernate
- Frontend: React
- Database: MySQL
- Image Storage: Cloudinary
- Pricing Data: PokeTrace API
