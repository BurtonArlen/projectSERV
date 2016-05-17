import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.*;



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
    assertTrue(Student.findStudentById(testStudent.getId()).equals(testStudent));
  }

  @Test
  public void _1() {
    ArrayList<String> listOfSkills = new ArrayList<String>();
    listOfSkills.add("HTML");
    listOfSkills.add("CSS");
    listOfSkills.add("JAVA");
    listOfSkills.add("JAVASCRIPT");

    ArrayList<String> listOfExp = new ArrayList<String>();
    listOfExp.add("work1");
    listOfExp.add("work2");
    listOfExp.add("work3");
    listOfExp.add("work4");
    Student myStudent = new Student("Dave", "a wonderful kid", "dave@dave.dave", "davey", listOfSkills, listOfExp);
    myStudent.save();

    ArrayList<String> listOfSkills2 = new ArrayList<String>();
    listOfSkills2.add("HTML");
    listOfSkills2.add("CSS");
    listOfSkills2.add("JAVA");
    listOfSkills2.add("JAVASCRIPT");

    ArrayList<String> listOfExp2 = new ArrayList<String>();
    listOfExp2.add("work1");
    listOfExp2.add("work2");
    listOfExp2.add("work3");
    listOfExp2.add("work4");
    ArrayList<String> listOfSkills3 = new ArrayList<String>();
    listOfSkills3.add("HTML");
    listOfSkills3.add("CSS");
    listOfSkills3.add("JAVA");
    listOfSkills3.add("JAVASCRIPT");
    listOfSkills3.add("work5");

    ArrayList<String> listOfExp4 = new ArrayList<String>();
    listOfExp4.add("work1");
    listOfExp4.add("work2");
    listOfExp4.add("work3");
    listOfExp4.add("work4");
    listOfExp4.add("work5");

    Student myStudent2 = new Student("Dave", "a wonderful kid", "dave@dave.dave", "davey", listOfSkills2, listOfExp2);
    myStudent2.save();

    Student.findStudentsBySkill("HTML").get(0).getStudentName();
    myStudent.addSkills(listOfSkills3);
    myStudent.addExps(listOfExp4);
    myStudent.removeSkill(myStudent.getSkillIds().get(0));
    myStudent.removeExp(myStudent.getExpIds().get(0));
    assertEquals(Student.allStudents().get(0).getExps(), myStudent2.getExps());
    assertEquals(Student.allStudents().get(0).getSkills(), myStudent2.getSkills());
    assertEquals(Student.allStudents().get(0).getStudentName(), myStudent2.getStudentName());



  }

}
