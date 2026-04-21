package io.github.erichan11.pokemonscanner.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_cache")
public class PriceCache {
    @Id
    @Column(name = "card_api_id", length = 100)
    private String cardApiId;

    @Column(name = "market_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal marketPrice;

    @Column(name="last_updated")
    private LocalDateTime lastUpdated;

    public PriceCache() {
        this.lastUpdated = LocalDateTime.now();
    }

    public String getCardApiId() {
        return cardApiId;
    }

    public void setCardApiId(String cardApiId) {
        this.cardApiId = cardApiId;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "PriceCache{" +
                "cardApiId='" + cardApiId + '\'' +
                ", marketPrice=" + marketPrice +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
