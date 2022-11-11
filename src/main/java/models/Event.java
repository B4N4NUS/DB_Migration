package models;

import jakarta.persistence.*;

@Entity
@Table(name = "events", schema = "olympics")
public class Event {
    @Id
    @Column(name = "event_id", nullable = false, length = 7)
    private String id;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "eventtype", length = 20)
    private String eventtype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "olympic_id")
    private Olympic olympic;

    @Column(name = "is_team_event")
    private Integer isTeamEvent;

    @Column(name = "num_players_in_team")
    private Integer numPlayersInTeam;

    @Column(name = "result_noted_in", length = 100)
    private String resultNotedIn;

    public String getResultNotedIn() {
        return resultNotedIn;
    }

    public void setResultNotedIn(String resultNotedIn) {
        this.resultNotedIn = resultNotedIn;
    }

    public Integer getNumPlayersInTeam() {
        return numPlayersInTeam;
    }

    public void setNumPlayersInTeam(Integer numPlayersInTeam) {
        this.numPlayersInTeam = numPlayersInTeam;
    }

    public Integer getIsTeamEvent() {
        return isTeamEvent;
    }

    public void setIsTeamEvent(Integer isTeamEvent) {
        this.isTeamEvent = isTeamEvent;
    }

    public Olympic getOlympic() {
        return olympic;
    }

    public void setOlympic(Olympic olympic) {
        this.olympic = olympic;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + eventtype + " (olymp: " +olympic.getId() + ") " + isTeamEvent + " " +numPlayersInTeam + " " + resultNotedIn;
    }
}