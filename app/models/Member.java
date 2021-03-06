/**
 * Constructor for objects of class Member
 *
 *
 * @param name The Members name
 *
 * @param email The Person's e-mail
 *
 ** @param address There is no validation on the member's address.
 *
 * @param gender The member's gender can be either "M" or "F". lower case entries
 *               will be changed to upper case. ".
 * @param height the members height as specified in assesment
 *               @param startingweight the weight the meber starts outh with all this is entered when the member sign
 *  created by Bernadette Murphy
 * @07/09/17
 */
package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model
{
    public String email;
    public String name;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double startingweight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    public List<Goal> goals = new ArrayList<>();

    public Member(String email, String name, String password, String address, String gender, double height, double startingweight)
    {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingweight = startingweight;
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}