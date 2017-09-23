/**
 * This class renders the trainers dashboard.html file when they login
 * the trainer will be able to create goals and book assessments for a member
 * created by Bernadette Murphy >>
 * @07/09/17
 */
package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;
import utils.Analytics;
import utils.MemberStats;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class TrainerDashboard extends Controller
{
    public static void index()
    {
        List<Member> members = Member.findAll();
        Logger.info("Rendering Trainer Dashboard");
        render("trainerdashboard.html", members);
    }

    public static void trainerAssessment(Long id)
    {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        MemberStats memberStats = Analytics.generateMemberStats(member);
        Collections.reverse(assessments);
        render("trainerassessment.html", member, assessments, memberStats);
    }


    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        if (member != null) {
            member.delete();
        }
        redirect("/trainerdashboard");
    }
    public static void editComment(Long id, String comment)
    {
        Logger.info("Comment " + comment);
        Assessment assessment = Assessment.findById(id);
        assessment.comment = comment;
        assessment.save();
        redirect("/trainerdashboard");
    }

    public static void createClass(String name, String description, String duration, String capacity, String difficulty, String time, String date, String suite)
    {
        Logger.info("Creating Class");
        Trainer trainer = Accounts.getLoggedInTrainer();
        ScheduledClass c = new ScheduledClass(name, description, duration, capacity, difficulty, time, date, trainer.email, suite);
        trainer.classes.add(c);
        trainer.save();
        redirect("/trainerdashboard/allclasses");
    }

    public static void viewClass()
{
    Logger.info("Rendering Show Create A Class");
    render("../views/tags/scheduledClass/show-class.html");
}


    public static void allClasses()
    {
        Logger.info("Rendering All Classes");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<ScheduledClass> scheduledClasses = trainer.classes;
        render("../views/tags/scheduledClass/allclasses.html", trainer, scheduledClasses);
    }
    public static void viewGoal()
    {
        Logger.info("Rendering Show Create A Goal");
        render("../views/tags/goalsTrainer/create-goal.html");
    }


    public static void deleteGoal(Long memberId, Long goalId)
    {
        Goal goal = Goal.findById(goalId);
        Member member = Member.findById(memberId);
        Logger.info("Removing " + member.name  + "'s goal " + goal.name);
        member.goals.remove(goal);
        member.save();

        goal.delete();
        redirect("/trainerassessment/" + memberId);
    }

    public static void createGoal(Long id, String name, String description, String date, String target, int targetInt) throws ParseException {
       Logger.info("Creating Goal");
        Member member = Member.findById(id);
        Goal goal = new Goal(name, description, target, targetInt, date);

       member.goals.add(goal);
       member.save();redirect("/trainerassessment/" + id);

    }

    public static void deleteClass(Long trainerid, Long classid)
    {
        Trainer trainer = Trainer.findById(trainerid);
        ScheduledClass c = ScheduledClass.findById(classid);
        trainer.classes.remove(c);
        trainer.save();
        c.delete();
        redirect("/trainerdashboard/allclasses");
    }

    private static Long classId;

    public static void viewEditClass(Long trainerid, Long classid)
    {
        Trainer trainer = Trainer.findById(trainerid);
        ScheduledClass c = ScheduledClass.findById(classid);
        classId = c.id;
        render("../views/tags/scheduledClass/viewclass.html", c, trainer);
    }

    public static void editClass(String name, String description, String duration, String capacity, String difficulty, String time, String date)
    {
        ScheduledClass c = ScheduledClass.findById(classId);
        c.name = name;
        c.description = description;
        c.duration = duration;
        c.capacity = capacity;
        c.difficulty = difficulty;
        c.time = time;
        c.date = date;
        c.save();
        redirect("/trainerdashboard/allclasses");
    }
//public static void bookAssesmet(String date, String time, String trainer, String member, String status, String trainerEmail)
     //   List<Member> members = Member.findAll();
     //   Trainer currentTrainer = Accounts.getLoggedInTrainer();
            //    Logger.info("Creating Assessment");
             //   BookAssessment BookAssessment = new BookAssessment()
            //    m.assessments.add(BookAssessment);
            //    m.save();
             //   render("trainerdashboard.html", trainers);
      //  String what = "trainer;
     //   render("../views/tags/assessments/tags/bookassessment.html");
//
    public static void deleteAssessment(Long memberid, Long assessmentid)
    {
        Member member = Member.findById(memberid);
        Assessment assessment = Assessment.findById(assessmentid);
        member.assessments.remove(assessment);
        member.save();
        assessment.delete();
        redirect("/trainerassessment/" + memberid);
    }

    private static Long assessmentId;

    public static void showEdit(Long assessmentid, Long memberid)
    {
        Assessment assessId = Assessment.findById(assessmentid);

        assessmentId = assessmentid;

        Member memId = Member.findById(memberid);
        List<Assessment> assessments = memId.assessments;
        MemberStats memberStats = Analytics.generateMemberStats(memId);
        Collections.reverse(assessments);
        render("../views/tags/assessments/dashboard.html", memId, assessments, memberStats, assessId);
    }

    public static void saveEdit (double weight, double chest, double thigh, double upperarm, double waist, double hips, String member, double date, double month, double year, double hours, double mins)  {
        List<Member> members = Member.findAll();

        for(Member m : members)
        {
            if(member.equals(m.email))
            {
                Logger.info("Editing Assessment");
                Assessment aFromDB = Assessment.findById(assessmentId);
                aFromDB.weight = weight;
                aFromDB.chest = chest;
                aFromDB.thigh = thigh;
                aFromDB.upperarm = upperarm;
                aFromDB.waist = waist;
                aFromDB.hips = hips;
                aFromDB.date.setHours((int)hours);
                aFromDB.date.setMinutes((int)mins);
                aFromDB.date.setDate((int)date);
                aFromDB.date.setMonth((int)month);
                aFromDB.date.setYear((int)year);
                aFromDB.save();

                redirect("/trainerassessment/" + m.id);
            }
        }
        String what = "Member";
        render("../views/tags/scheduledClassMember/class-not-found.html", what);
    }
}