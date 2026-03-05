package io.github.erichan11.pokemonscanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_cards")
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    //Owner of ForeignKey
    //fetch LAZY = will not load the users table when a UserCard is asked for, only when this field is explicitly asked
    //for. Ex: UserCard card = userCardRepository.findById(1).get(); Hibernate runs
    //SELECT * FROM user_cards WHERE id = 1;. It won't get users table, until you access user field like so:
    //card.getUser().getUsername();. Then Hibernate runs SELECT * FROM users WHERE id = ?;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "card_api_id", nullable = false, length = 100)
    private String cardApiId;

    @Column(name = "card_name", nullable = false, length = 100)
    private String cardName;

    @Column(name = "set_name", nullable = false, length = 100)
    private String setName;

    @Enumerated(EnumType.STRING)
    @Column(name="card_condition", length = 2)
    private Condition cardCondition = Condition.NM;

    @Column(name = "is_graded")
    private Boolean isGraded = false;

    @Column(name = "grade_value")
    private Integer gradeValue;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public UserCard() { //set the fields that will be default on table
        this.createdAt = LocalDateTime.now();
    }

    public UserCard(User user, String cardApiId, String cardName, String setName, Condition cardCondition, Boolean isGraded,
                    Integer gradeValue, LocalDateTime createdAt) {
        this.user = user;
        this.cardApiId = cardApiId;
        this.cardName = cardName;
        this.setName = setName;
        this.cardCondition = cardCondition;
        this.isGraded = isGraded;
        this.gradeValue = gradeValue;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCardApiId() {
        return cardApiId;
    }

    public void setCardApiId(String cardApiId) {
        this.cardApiId = cardApiId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Condition getCardCondition() {
        return cardCondition;
    }

    public void setCardCondition(Condition cardCondition) {
        this.cardCondition = cardCondition;
    }

    public Boolean getIsGraded() {
        return isGraded;
    }

    public void setIsGraded(Boolean isGraded) {
        this.isGraded = isGraded;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", user=" + user +
                ", cardApiId='" + cardApiId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", setName='" + setName + '\'' +
                ", condition=" + cardCondition +
                ", isGraded=" + isGraded +
                ", gradeValue=" + gradeValue +
                ", createdAt=" + createdAt +
                '}';
    }
}


