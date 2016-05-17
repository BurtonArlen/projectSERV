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
  //     String deleteStudentsQuery = "DELETE FROM students *;";
  //     String deleteskillsQuery = "DELETE FROM skills *;";
  //     String deletework_expQuery = "DELETE FROM work_exp *;";
  //     String deletestudents_skillsQuery = "DELETE FROM students_skills *;";
  //     String deletestudents_expsQuery = "DELETE FROM students_exps *;";
  //
  //     con.createQuery(deleteStudentsQuery).executeUpdate();
  //     con.createQuery(deleteskillsQuery).executeUpdate();
  //     con.createQuery(deletework_expQuery).executeUpdate();
  //     con.createQuery(deletestudents_skillsQuery).executeUpdate();
  //     con.createQuery(deletestudents_expsQuery).executeUpdate();
  //   }
  // }
}
