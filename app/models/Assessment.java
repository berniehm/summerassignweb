/**
 * This  class will store assessments that have values such as
 * weight
 * chest
 * thigh
 * upperarm
 * waist
 * hips
 * trend
 * comment
 */
package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.*;

@Entity
public class Assessment extends Model
{
    public double weight;
    public double chest;
    public double thigh;
    public double upperarm;
    public double waist;
    public double hips;
    public boolean trend;
    public String trainer;

    @Lob
    public String comment;
    public Date date;

    public Assessment(double weight, double chest, double thigh, double upperarm, double waist, double hips, String trainer, double date, double month, double year, double hours, double mins)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperarm = upperarm;
        this.waist = waist;
        this.hips = hips;
        this.trainer = trainer;

        this.date = new Date((int) year-1900, (int)month-1, (int)date, (int)hours, (int)mins);
    }
}