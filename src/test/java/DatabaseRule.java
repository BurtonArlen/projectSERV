import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  public void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/episerveus_test", "team_serv", "root");
  }

  @Override
  public void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteStudentsQuery = "DELETE FROM students *;";
      con.createQuery(deleteStudentsQuery).executeUpdate();
    }
  }
}
