package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.*;
import java.util.*;

@Entity
public class ScheduledClass extends Model {

    public String name, description, duration, capacity, difficulty, time, date, trainer, suite;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> members = new ArrayList<>();

    @ElementCollection
    public Map<Member, String> suites = new HashMap<>(); // maps from attribute name to value


    public ScheduledClass(String name, String description, String duration, String capacity, String difficulty, String time, String date, String trainer, String suite)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.capacity = capacity;
        this.difficulty = difficulty;
        this.time = time;
        this.date = date;
        this.suite = suite;
        this.trainer = trainer;
    }
}
