package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ResultId implements Serializable {
    private static final long serialVersionUID = -8090226065145782572L;
    @Column(name = "event_id", nullable = false, length = 7)
    private String eventId;
    @Column(name = "player_id", nullable = false, length = 10)
    private String playerId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, playerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResultId entity = (ResultId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.playerId, entity.playerId);
    }
}