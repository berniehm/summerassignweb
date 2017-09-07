/**
 * This Class is to define what appears on the dashboard when a member logins
 * Created by Berndette Murphy
 * @07/08/17
 */
package controllers;

import models.Assessment;
import models.Member;
import models.ScheduledClass;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;
import utils.MemberStats;
//import java.text.ParseException;

import java.util.*;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        assert member != null;
        List<Assessment> assessments = member.assessments;
        MemberStats memberStats = Analytics.generateMemberStats(member);
        Collections.reverse(assessments);
        render("dashboard.html", member, assessments, memberStats);
    }

    public static void addAssessment(double weight, double chest, double thigh, double upperarm, double waist, double hips, String trainer, double date, double month, double year, double hours, double mins)
    {
        List<Trainer> trainers = Trainer.findAll();

        for(Trainer t : trainers)
        {
            if(trainer.equals(t.email))
            {
                Logger.info("Creating Assessment");
                Member member = Accounts.getLoggedInMember();
                Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips, trainer, date, month, year, hours, mins);
                MemberStats memberStats = Analytics.generateMemberStats(member);
                assessment.trend = memberStats.trend;
                member.assessments.add(assessment);
                member.save();
                redirect("/dashboard");
            }
        }

        String what = "Trainer";
        render("../views/tags/scheduledClassMember/class-not-found.html", what);
    }

       public static void addGoal() {

      render("addgoal.html");

    }
    public static void bookAssessment()
       {

           List<Trainer> trainers = Trainer.findAll();
  }



    public static void saveEdit(double weight, double chest, double thigh, double upperarm, double waist, double hips, String trainer, double date, double month, double year, double hours, double mins)
    {
        List<Trainer> trainers = Trainer.findAll();

        for(Trainer t : trainers)
        {
            if(trainer.equals(t.email))
            {
                Logger.info("Editing Assessment");
                Member member = Accounts.getLoggedInMember();
                MemberStats memberStats = Analytics.generateMemberStats(member);

                Assessment aFromDB = Assessment.findById(assessmentId);
                aFromDB.weight = weight;
                aFromDB.chest = chest;
                aFromDB.thigh = thigh;
                aFromDB.upperarm = upperarm;
                aFromDB.waist = waist;
                aFromDB.hips = hips;
                aFromDB.trainer = trainer;
                aFromDB.date.setHours((int)hours);
                aFromDB.date.setMinutes((int)mins);
                aFromDB.date.setDate((int)date);
                aFromDB.date.setMonth((int)month);
                aFromDB.date.setYear((int)year);
                aFromDB.trend = memberStats.trend;
                aFromDB.save();

                redirect("/dashboard");
            }
        }
        String what = "Trainer";
        render("../views/tags/scheduledClassMember/class-not-found.html", what);
    }

    public static void deleteAssessment(Long memberid, Long assessmentid)
    {
        Member member = Member.findById(memberid);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        redirect("/dashboard");
    }

    public static void allClasses()
    {
        Logger.info("Rendering All Classes");
        Member member = Accounts.getLoggedInMember();
        List<ScheduledClass> scheduledClasses = ScheduledClass.findAll();
        render("../views/tags/scheduledClassMember/allclasses.html", member, scheduledClasses);
    }

    public static void enrollMember(Long memberid, Long classid)
    {
        Logger.info("Enrolling into a class");

        Member member = Member.findById(memberid);
        ScheduledClass c = ScheduledClass.findById(classid);

        int capacity = Integer.parseInt(c.capacity);

        if(capacity > c.members.size() && !c.members.contains(member))
        {c.members.add(member);}

        c.save();

        redirect("/dashboard/allclasses");
    }

    public static void unenrollMember(Long memberid, Long classid)
    {
        Logger.info("Unenrolling from a class");

        Member member = Member.findById(memberid);
        ScheduledClass c = ScheduledClass.findById(classid);


        if(c.members.contains(member))
        {c.members.remove(member);}

        c.save();

        redirect("/dashboard/allclasses");
    }

    public static void searchForClass()
    {
        render("../views/tags/scheduledClassMember/search-for-class.html", "Class");
    }

    public static void searchResult(String name)
    {
        List<ScheduledClass> allClasses = ScheduledClass.findAll();

        List<ScheduledClass> found = new LinkedList<>();
        for(ScheduledClass c : allClasses)
        {
            if(name.equals(c.name))
            {found.add(c);}
        }

        if(found.size() != 0)
        {
            Member member = Accounts.getLoggedInMember();
            render("../views/tags/scheduledClassMember/search-result.html", found, member);
        }
        else
        {
            String what = "Class";
            render("../views/tags/scheduledClassMember/class-not-found.html", what);
        }
    }

    public static void saveSuite(Long memberid, Long scheduledclassid, String suite)
    {
        Logger.info("Saving suite from a class");

        Member member = Member.findById(memberid);
        ScheduledClass c = ScheduledClass.findById(scheduledclassid);

        c.suites.remove(member);
        c.suites.put(member,suite);
        c.save();

        redirect("/dashboard/allclasses");
    }

    private static Long assessmentId;

    public static void showEdit(Long assessmentid)
    {
        Assessment assessment = Assessment.findById(assessmentid);

        assessmentId = assessmentid;

        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        MemberStats memberStats = Analytics.generateMemberStats(member);
        Collections.reverse(assessments);

        render("../views/tags/assessmentsMember/dashboard.html", member, assessments, memberStats, assessment);
    }
}