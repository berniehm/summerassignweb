/**
 * This class will enable there to be so many objects in a suite eg. 2 classes in the suite
 * @param memberid identifies the member
 *      created by Bernadette Murphy
 *      @07/08/17
 *
 */
package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class MemberToSuite extends Model
{
    public Long memberid;
    public String suite;
}
