
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Student {

  private int id;
  private String email;
  private String password;
  private String student_name;
  private String bio;
  private Timestamp created_at;

  private int skill_id;
  private ArrayList<String> skillsArray = new ArrayList<String>();

  private int exp_id;
  private ArrayList<String> experienceArray = new ArrayList<String>();

  public Student(String student_name) {
    this.student_name = student_name;
    created_at = new Timestamp(new Date().getTime());
  }

  //create
  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students (email, password, student_name, bio, created_at) VALUES ( :email, :password, :student_name, :bio, :created_at)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("email", email)
        .addParameter("password", password)
        .addParameter("student_name", student_name)
        .addParameter("bio", bio)
        .addParameter("created_at", created_at)
        .executeUpdate()
        .getKey();
      // 
      // for (String skill : this.skillsArray) {
      //   String skillAdd = "INSERT INTO skills (skill) VALUES (:skill)";
      //   this.skillid = (int) con.createQuery(skillAdd , true)
      //     .addParameter("skill", skill)
      //     .executeUpdate()
      //     .getKey();
      //
      //   String joinstudent_skillsTableAdd = "INSERT INTO student_skills (student_id, skill_id) VALUES (:student_id, :skill_id)";
      //   con.createQuery(joinstudent_skillsTableAdd)
      //     .addParameter("student_id", this.getId())
      //     .addParameter("skill_id", this.getIngredientsId())
      //     .executeUpdate();
      // }

      // for (String exp : this.arrayOfDirect) {
      //   String expAdd = "INSERT INTO exps (exp) VALUES (:exp)";
      //   this.expid = (int) con.createQuery(expAdd , true)
      //     .addParameter("exp", exp)
      //     .executeUpdate()
      //     .getKey();
      //
      //   String joinrecipe_expsTableAdd = "INSERT INTO recipe_exps (recipe_id, exp_id) VALUES (:recipe_id, :exp_id)";
      //   con.createQuery(joinrecipe_expsTableAdd)
      //     .addParameter("recipe_id", this.getId())
      //     .addParameter("exp_id", this.getDirectionId())
      //     .executeUpdate();
      // }
    }
  }

  //read
  public static List<Student> allStudents() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students";
     return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public static Student findStudent(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
    }
  }

  //update

  //delete


  @Override
  public boolean equals(Object otherStudent) {
    if(!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
      return this.getId() == newStudent.getId()
      && this.getStudentName().equals(newStudent.getStudentName());
    }
  }

  public int getId(){
    return id;
  }

  public String getStudentName(){
    return student_name;
  }

  public Timestamp getCreatedAt(){
    return created_at;
  }

}
