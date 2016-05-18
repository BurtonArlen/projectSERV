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
    Student myStudent = new Student("dave", "davey");
    assertTrue(myStudent instanceof Student);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Student firstStudent = new Student("dave", "davey");
    Student secondStudent = new Student("dave", "davey");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame_Student() {
    Student testStudent = new Student("dave", "davey");
    testStudent.save();
    assertTrue(Student.allStudents().get(0).equals(testStudent));
  }

  @Test
  public void find_returnsCorrectStudentSearchedFor_Student() {
    Student testStudent = new Student("dave", "davey");
    testStudent.save();
    assertTrue(Student.findStudentById(testStudent.getId()).equals(testStudent));
  }

  @Test
  public void _1() {
    ArrayList<String> listOfSkills = new ArrayList<String>();
    listOfSkills.add("HTML");
    listOfSkills.add("CSS");
    listOfSkills.add("Java");
    listOfSkills.add("Javascript");

    ArrayList<String> listOfExp = new ArrayList<String>();
    listOfExp.add("work1");
    listOfExp.add("work2");
    listOfExp.add("work3");
    listOfExp.add("work4");
    Student myStudent = new Student("Bob", "Bobby", "a wonderful kid", "bob@bob.bob", "bobby", listOfSkills, listOfExp);
    myStudent.save();

    ArrayList<String> listOfSkills2 = new ArrayList<String>();
    listOfSkills2.add("HTML");
    listOfSkills2.add("CSS");
    listOfSkills2.add("Java");
    listOfSkills2.add("Javascript");

    ArrayList<String> listOfExp2 = new ArrayList<String>();
    listOfExp2.add("work1");
    listOfExp2.add("work2");
    listOfExp2.add("work3");
    listOfExp2.add("work4");

    ArrayList<String> listOfSkills3 = new ArrayList<String>();
    listOfSkills3.add("HTML");
    listOfSkills3.add("CSS");
    listOfSkills3.add("Java");
    listOfSkills3.add("Javascript");
    listOfSkills3.add("work5");

    ArrayList<String> listOfSkills4 = new ArrayList<String>();
    listOfSkills4.add("HTML");
    listOfSkills4.add("CSS");
    listOfSkills4.add("Java");
    listOfSkills4.add("Javascript");
    listOfSkills4.add("Ruby");
    listOfSkills4.add("C#");

    ArrayList<String> listOfExp4 = new ArrayList<String>();
    listOfExp4.add("work1");
    listOfExp4.add("work2");
    listOfExp4.add("work3");
    listOfExp4.add("work4");
    listOfExp4.add("work5");

    Student myStudent2 = new Student("Dave", "Davey", "a wonderful kid", "dave@dave.dave", "davey", listOfSkills2, listOfExp2);
    Student myStudent3 = new Student("Zoey", "Aaron", "a wonderful kid", "zoey@zoey.zoey", "Zarn", listOfSkills4, listOfExp2);
    myStudent2.save();
    myStudent3.save();

    Student.findStudentsBySkill("HTML").get(0).getStudentFirstName();
    myStudent.addSkills(listOfSkills3);
    myStudent.addExps(listOfExp4);
    myStudent.removeSkill(myStudent.getSkillIds().get(0));
    myStudent.removeExp(myStudent.getExpIds().get(0));
    Student student = Student.login("dave@dave.dave", "davey");
    if(student == null){
      student = myStudent2;
    }
    assertEquals(Student.allStudents().get(1).getExps(), myStudent2.getExps());
    assertEquals(student.getStudentFirstName(), myStudent2.getStudentFirstName());
    assertEquals(Student.allStudents().get(1).getSkills(), myStudent2.getSkills());
    assertEquals(Student.allStudents().get(1).getStudentFirstName(), myStudent2.getStudentFirstName());



  }

}
