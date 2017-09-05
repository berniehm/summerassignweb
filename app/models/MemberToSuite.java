package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class MemberToSuite extends Model
{
    public Long memberid;
    public String suite;
}
