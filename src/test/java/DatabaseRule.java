import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  public void before() {

    //TODO CHANGE THIS  when putting it on server to:  "team_serv", "root"
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/episerve_test", null, null);
  }

  // @Override
  // public void after() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String dropStudentsQuery = "DROP TABLE students;";
  //     String dropskillsQuery = "DROP TABLE skills;";
  //     String dropwork_expQuery = "DROP TABLE work_exp;";
  //     String dropstudents_skillsQuery = "DROP TABLE students_skills;";
  //     String dropstudents_expsQuery = "DROP TABLE students_exps;";
  //
  //     con.createQuery(dropStudentsQuery).executeUpdate();
  //     con.createQuery(dropskillsQuery).executeUpdate();
  //     con.createQuery(dropwork_expQuery).executeUpdate();
  //     con.createQuery(dropstudents_skillsQuery).executeUpdate();
  //     con.createQuery(dropstudents_expsQuery).executeUpdate();
  //
  //     String createStudentsQuery = "CREATE TABLE students (id serial PRIMARY KEY, student_first_name varchar, student_last_name varchar, bio text, email varchar, password varchar);";
  //     String createskillsQuery = "CREATE TABLE skills (id serial PRIMARY KEY, skill varchar);";
  //     String creatework_expQuery = "CREATE TABLE work_exp (id serial PRIMARY KEY, exp varchar);";
  //     String createstudents_skillsQuery = "CREATE TABLE students_skills (id serial PRIMARY KEY, student_id int, skill_id int);";
  //     String createstudents_expsQuery = "CREATE TABLE students_exps (id serial PRIMARY KEY, student_id int, exp_id int);";
  //
  //     con.createQuery(createStudentsQuery).executeUpdate();
  //     con.createQuery(createskillsQuery).executeUpdate();
  //     con.createQuery(creatework_expQuery).executeUpdate();
  //     con.createQuery(createstudents_skillsQuery).executeUpdate();
  //     con.createQuery(createstudents_expsQuery).executeUpdate();
  //   }
  // }
}
