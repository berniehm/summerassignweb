



package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.*;

@Entity
public class Goal extends Model
{
    public double weight;
    public double chest;
    public double thigh;
    public double upperarm;
    public double waist;
    public double hips;
    public boolean trend;
    public String trainer;
    public String description;

    @Lob
    public String comment;
    public Date date;

    public Goal(double weight, double chest, double thigh, double upperarm, double waist, double hips, String trainer, String description)
    {   this.description=description;
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperarm = upperarm;
        this.waist = waist;
        this.hips = hips;
        this.trainer = trainer;


    }
}
