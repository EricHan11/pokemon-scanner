# pokemon-scanner
Pokemon card scanner where you can upload pictures and get prices on your collection. First you need to log into your account. You can then select and "sell" cards to make money. We can get the prices of cards from PokeTrace API (https://poketrace.com/docs). There should be a collections page to view all your current pokemon cards in your collection, along with the pictures of each card you uploaded for them and their pricing and total collection price.

So login page, then collections tab/page where top bar allows you to add cards by importing images and next to each card there will be the option to remove a card or mark a card as sell. Then at the bottom there will always be a bar that shows which cards you have listed to sell and how much money you will get in total.
Bonus: Make music tab to play Pokemon music :), maybe have a history of when pokemon cards were added/sold in your collection and have a line graph representing your current profit margins

Use MySQL to store usernames and passwords for accounts, as well as the players collection. The tables will look something like this:

### users
```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  pass VARCHAR(50) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### user_cards

```sql
CREATE TABLE user_cards (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  card_api_id VARCHAR(100) NOT NULL,
  card_name VARCHAR(100) NOT NULL,
  set_name VARCHAR(100) NOT NULL,

  `condition` ENUM('NM', 'LP', 'MP', 'HP', 'DM') DEFAULT 'NM',
  is_graded BOOLEAN DEFAULT FALSE,
  grade_value INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

purchase_price (optional)
notes (optional)
```

### price_cache (so we don't hit the API constantly, store already searched up prices here. Also PokeTrace API has rate limit, so don't bother with live accuracy atm)
```sql
CREATE TABLE price_cache (
  card_api_id VARCHAR(100) NOT NULL PRIMARY KEY,
  market_price DECIMAL(10,2) NOT NULL,
  last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
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
