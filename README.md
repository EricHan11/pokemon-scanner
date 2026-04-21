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

## Plan
<img width="652" height="1017" alt="image" src="https://github.com/user-attachments/assets/fd6a1b80-6f61-420a-8756-c21b2c55aa23" />
<img width="483" height="457" alt="image" src="https://github.com/user-attachments/assets/5ddcef25-a014-4d5d-93ae-b11cff698e89" />
<img width="632" height="807" alt="image" src="https://github.com/user-attachments/assets/0a82aa16-87f2-4f54-8445-d0330b4928d1" />
<img width="642" height="881" alt="image" src="https://github.com/user-attachments/assets/b96287c2-d63e-430a-8b62-125171a8499e" />
<img width="647" height="863" alt="image" src="https://github.com/user-attachments/assets/246bdde6-aa64-4cf0-977f-5bb866990b09" />



