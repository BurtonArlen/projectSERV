
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Student {

  private int id;
  private String student_name;
  private String bio;

  private String email;
  private String password;

  private Timestamp created_at;

  private int skill_id;
  private ArrayList<String> skillsArray = new ArrayList<String>();

  private int exp_id;
  private ArrayList<String> experienceArray = new ArrayList<String>();

  public Student(String student_name) {
    this.student_name = student_name;
    created_at = new Timestamp(new Date().getTime());
  }

  public Student(String student_name, String bio, String email, String password, ArrayList<String> skillsArray, ArrayList<String> experienceArray) {
    this.student_name = student_name;
    this.bio = bio;
    this.email = email;
    this.password = password;
    this.skillsArray = skillsArray;
    this.experienceArray = experienceArray;
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

      for (String skill : this.skillsArray) {
        String skillAdd = "INSERT INTO skills (skill) VALUES (:skill)";
        this.skill_id = (int) con.createQuery(skillAdd , true)
          .addParameter("skill", skill)
          .executeUpdate()
          .getKey();

        String joinstudent_skillsTableAdd = "INSERT INTO students_skills (student_id, skill_id) VALUES (:student_id, :skill_id)";
        con.createQuery(joinstudent_skillsTableAdd)
          .addParameter("student_id", this.getId())
          .addParameter("skill_id", this.getSkillId())
          .executeUpdate();
      }

      for (String exp : this.experienceArray) {
        String expAdd = "INSERT INTO work_exp (exp) VALUES (:exp)";
        this.exp_id = (int) con.createQuery(expAdd , true)
          .addParameter("exp", exp)
          .executeUpdate()
          .getKey();

        String joinstudent_expsTableAdd = "INSERT INTO students_exps (student_id, exp_id) VALUES (:student_id, :exp_id)";
        con.createQuery(joinstudent_expsTableAdd)
          .addParameter("student_id", this.getId())
          .addParameter("exp_id", this.getExpId())
          .executeUpdate();
      }
    }
  }

  //read
  public static List<Student> allStudents() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students";
     return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public static Student findStudentById(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
    }
  }

  public static Student findStudentByName(String name){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE student_name=:name";
      return con.createQuery(sql)
        .addParameter("name", name)
        .executeAndFetchFirst(Student.class);
    }
  }

  public static List<Student> findStudentsBySkill(String skill){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT students.* FROM students JOIN students_skills ON (students.id = students_skills.student_id) JOIN skills ON (students_skills.skill_id = skills.id) WHERE skills.skill = :skill";
      List<Student> students = con.createQuery(sql)
        .addParameter("skill", skill)
        .executeAndFetch(Student.class);
        return students;
    }
  }

  public List<String> getExps() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT work_exp.exp FROM students JOIN students_exps ON (students.id = students_exps.student_id) JOIN work_exp ON (students_exps.exp_id = work_exp.id) WHERE students.id = :student_id";
      List<String> skills = con.createQuery(joinQuery)
      .addParameter("student_id", this.getId())
      .executeAndFetch(String.class);
      return skills;
    }
  }

  public List<Integer> getExpIds() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT work_exp.id FROM students JOIN students_exps ON (students.id = students_exps.student_id) JOIN work_exp ON (students_exps.exp_id = work_exp.id) WHERE students.id = :student_id";
      List<Integer> skills = con.createQuery(joinQuery)
      .addParameter("student_id", this.getId())
      .executeAndFetch(Integer.class);
      return skills;
    }
  }

  public List<String> getSkills() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT skills.skill FROM students JOIN students_skills ON (students.id = students_skills.student_id) JOIN skills ON (students_skills.skill_id = skills.id) WHERE students.id = :student_id";
      List<String> skills = con.createQuery(joinQuery)
      .addParameter("student_id", this.getId())
      .executeAndFetch(String.class);
      return skills;
    }
  }

  public List<Integer> getSkillIds() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT skills.id FROM students JOIN students_skills ON (students.id = students_skills.student_id) JOIN skills ON (students_skills.skill_id = skills.id) WHERE students.id = :student_id";
      List<Integer> skills = con.createQuery(joinQuery)
      .addParameter("student_id", this.getId())
      .executeAndFetch(Integer.class);
      return skills;
    }
  }

  //update

  //delete
  public void removeExp(int workexp_id) {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM work_exp WHERE id = :id; DELETE FROM students_exps WHERE exp_id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", workexp_id)
          .executeUpdate();
    }
  }

  public void removeSkill(int skill_id) {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM skills WHERE id = :id; DELETE FROM students_skills WHERE skill_id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", skill_id)
          .executeUpdate();
    }
  }

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

  public int getSkillId(){
    return skill_id;
  }

  public int getExpId(){
    return exp_id;
  }

  public String getStudentName(){
    return student_name;
  }

  public Timestamp getCreatedAt(){
    return created_at;
  }

}
