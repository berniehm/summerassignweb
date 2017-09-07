/**
 * This class will enable a member or a trainer to book an assessment
 * created by Bernadette Murphy
 * @ 05/09/17
 */
package models;

import play.db.jpa.Model;


import javax.persistence.Entity;
import javax.persistence.Lob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




@Entity
public class BookAssessment extends Model {
    public String date;
    public String time;
    public String trainer;
    public String member;
    public String status;
    public String trainerEmail;

    //  public Member member;
    //  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //  Date date1 = sdf.parse(date);

    public BookAssessment(String date, String time, String trainer, String member, String status, String trainerEmail) throws ParseException {
        this.date = date;
        this.time = time;
        this.trainer = trainer;
        this.member = member;
        this.status = status;
        this.trainerEmail = trainerEmail;
        //  this.trainer = trainer;
        //this.member = member;
        //    this.date1 = date1;

    }


}