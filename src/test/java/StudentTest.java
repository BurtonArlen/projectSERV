import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;



public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Student_instantiatesCorrectly_true(){
    Student myStudent = new Student("sakdjas");
    assertTrue(myStudent instanceof Student);
  }

  @Test
  public void getCreatedAtAndgetUpdatedAt_returnTimestamps_hours(){
    Student myStudent = new Student("dave");
    Timestamp testCreatedAt = new Timestamp(new Date().getTime());
    assertEquals(myStudent.getCreatedAt().getHours(), testCreatedAt.getHours());
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Student firstStudent = new Student("dave");
    Student secondStudent = new Student("dave");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame_Student() {
    Student testStudent = new Student("dave");
    testStudent.save();
    assertTrue(Student.allStudents().get(0).equals(testStudent));
  }

  @Test
  public void find_returnsCorrectStudentSearchedFor_Student() {
    Student testStudent = new Student("dave");
    testStudent.save();
    assertTrue(Student.findStudent(testStudent.getId()).equals(testStudent));
  }

}
