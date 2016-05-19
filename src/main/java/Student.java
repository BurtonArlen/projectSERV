
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Student {

  private int id;
  private String student_first_name;
  private String student_last_name;
  private String bio;

  private String email;
  private String password;

  private int skill_id;
  private ArrayList<String> skillsArray = new ArrayList<String>();

  private int exp_id;
  private ArrayList<String> experienceArray = new ArrayList<String>();

  public Student(String student_first_name, String student_last_name, String email, String password) {
    this.student_first_name = student_first_name;
    this.student_last_name = student_last_name;
    this.email = email;
    this.password = password;
  }

  public Student(String student_first_name, String student_last_name, String bio, String email, String password, ArrayList<String> skillsArray, ArrayList<String> experienceArray) {
    this.student_first_name = student_first_name;
    this.student_last_name = student_last_name;
    this.bio = bio;
    this.email = email;
    this.password = password;
    this.skillsArray = skillsArray;
    this.experienceArray = experienceArray;
  }


  //create
  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO students (student_first_name, student_last_name, bio, email, password) VALUES ( :student_first_name, :student_last_name, :bio, :email, :password)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("email", email)
      .addParameter("password", password)
      .addParameter("student_first_name", student_first_name)
      .addParameter("student_last_name", student_last_name)
      .addParameter("bio", bio)
      .executeUpdate()
      .getKey();

      //To skill table
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

      //To experience table
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

  public void addSkills(ArrayList<String> newSkillsArray){
    try(Connection con = DB.sql2o.open()) {

      for (String newSkill : newSkillsArray) {
        if (this.getSkills().contains(newSkill)){
          continue;
        }
        String skillAdd = "INSERT INTO skills (skill) VALUES (:skill)";
        this.skill_id = (int) con.createQuery(skillAdd , true)
        .addParameter("skill", newSkill)
        .executeUpdate()
        .getKey();

        String joinstudent_skillsTableAdd = "INSERT INTO students_skills (student_id, skill_id) VALUES (:student_id, :skill_id)";
        con.createQuery(joinstudent_skillsTableAdd)
        .addParameter("student_id", this.getId())
        .addParameter("skill_id", this.getSkillId())
        .executeUpdate();
      }

    }
  }

  public void addExps(ArrayList<String> newExpsArray){
    try(Connection con = DB.sql2o.open()) {

      for (String newExp : newExpsArray) {
        if (this.getExps().contains(newExp)){
          continue;
        }
        String expAdd = "INSERT INTO work_exp (exp) VALUES (:exp)";
        this.exp_id = (int) con.createQuery(expAdd , true)
        .addParameter("exp", newExp)
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
      String sql = "SELECT * FROM students ORDER BY student_last_name ASC";
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  public static Student findStudentById(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE id=:id ORDER BY student_last_name ASC";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Student.class);
    }
  }

  public static Student findStudentByEmail(String email){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE email=:email ORDER BY student_last_name ASC";
      return con.createQuery(sql)
      .addParameter("email", email)
      .executeAndFetchFirst(Student.class);
    }
  }

  public static boolean checkEmailAvailable(String email){
    if(Student.findStudentByEmail(email) == null){
      return true;
    }else{
      return false;
    }
  }

  public static List<Student> findStudentsBySkill(String skill){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT students.* FROM students JOIN students_skills ON (students.id = students_skills.student_id) JOIN skills ON (students_skills.skill_id = skills.id) WHERE skills.skill = :skill ORDER BY student_last_name ASC";
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

  public static Student login (String email, String password){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students WHERE email=:email AND password=:password";
      return con.createQuery(sql)
      .addParameter("email", email)
      .addParameter("password", password)
      .executeAndFetchFirst(Student.class);
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
      && this.getStudentFirstName().equals(newStudent.getStudentFirstName());
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

  public String getStudentFirstName(){
    return student_first_name;
  }

  public String getStudentEmail(){
    return email;
  }

  public String getStudentLastName(){
    return student_last_name;
  }

}
