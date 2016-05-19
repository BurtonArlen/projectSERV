import java.util.*;
import java.io.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

    //  setPort(80);

    staticFileLocation("/public");
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/signUp", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ArrayList<Integer> errorsArray = request.session().attribute("errorsArray");
      if(errorsArray == null){
        errorsArray = new ArrayList<Integer>();
        request.session().attribute("errorsArray", errorsArray);
      }
      model.put("errorsArray", request.session().attribute("errorsArray"));
      errorsArray = new ArrayList<Integer>();
      request.session().attribute("errorsArray", errorsArray);
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/signUp.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/directory", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      if(request.session().attribute("students") == null){
        request.session().attribute("students", Student.allStudents());
      }
      model.put("students", request.session().attribute("students"));
      request.session().attribute("students", Student.allStudents());
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/directory.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());


    get("/member", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/member.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());


    get("/profile/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("student", Student.findStudentById(Integer.parseInt(request.params("id"))));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/profile.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/edit/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("student", Student.findStudentById(Integer.parseInt(request.params("id"))));
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      model.put("studentlog", request.session().attribute("studentlog"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/editProfile.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());


    post("/signUp", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ArrayList<Integer> errorTypes = new ArrayList<Integer>();

      String newUserFirstName = request.queryParams("newUserFirstName");
      String newUserLastName = request.queryParams("newUserLastName");
      String newUserEmail = request.queryParams("newUserEmail");
      String newPassword = request.queryParams("newPassword");
      String confirmPassword = request.queryParams("confirmPassword");

      if(!Student.checkEmailAvailable(newUserEmail)){
        errorTypes.add(1);
      }
      if(!newPassword.equals(confirmPassword)){
        errorTypes.add(2);
      }
      request.session().attribute("errorsArray", errorTypes);
      if(errorTypes.size() > 0){
        response.redirect("/signUp");
      }else{
      Student newStudent = new Student(newUserFirstName, newUserLastName, newUserEmail, newPassword);
      newStudent.save();
      request.session().attribute("studentlog", newStudent);
      response.redirect("/member");
      }
      return null;
    });

    post("/login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String userName = request.queryParams("userName");
      String password = request.queryParams("password");

      if(Student.login(userName, password) == null){
        request.session().attribute("loginError", 1);
        response.redirect(request.queryParams("url"));
      }
      else{
        request.session().attribute("loginError", 0);
        Student student = Student.login(userName, password);
        request.session().attribute("studentlog", student);
        response.redirect("/member");
      }
      return null;
    });

    post("/signout", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      request.session().attribute("studentlog", null);
      response.redirect("/");
      return null;
    });


    post("/edit/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      int id = Integer.parseInt(request.params("id"));
      String newUserFirstName = request.queryParams("newUserFirstName");
      String newUserLastName = request.queryParams("newUserLastName");
      String newUserEmail = request.queryParams("newUserEmail");
      String bio = request.queryParams("bio");
      String skills = request.queryParams("skills");
      String work_exp = request.queryParams("work_exp");
      ArrayList<String> arrayOfSkills = new ArrayList<String>(Arrays.asList(skills.split("\\n")));
      ArrayList<String> arrayOfExps = new ArrayList<String>(Arrays.asList(work_exp.split("\\n")));
      Student student = Student.findStudentById(id);
      student.update(newUserFirstName, newUserLastName, bio, newUserEmail, arrayOfSkills, arrayOfExps);

      response.redirect("/profile/" + Integer.toString(id));
      return null;
    });


    post("/search", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String search = request.queryParams("search");
      int filter = Integer.parseInt(request.queryParams("filter"));
      ArrayList<Student> students = new ArrayList<Student>();
      switch(filter){
        case 1:
          if(Student.findStudentByLastName(search) != null){
            students.add(Student.findStudentByLastName(search));
          }
          break;
        case 2:
          if(Student.findStudentByEmail(search) != null){
            students.add(Student.findStudentByEmail(search));
          }
          break;
        case 3:
          for(Student skill : Student.findStudentsBySkill(search)){
            students.add(skill);
          }
          break;
        default:
          break;
      }
      request.session().attribute("students", students);
      response.redirect("/directory");
      return null;
    });


    // post("/upload", (req, res) -> {
    //   final File upload = new File("upload");
    //   if (!upload.exists() && !upload.mkdirs()) {
    //     throw new RuntimeException("Failed to create directory " + upload.getAbsolutePath());
    //   }
    //
    //   // apache commons-fileupload to handle file upload
    //   DiskFileItemFactory factory = new DiskFileItemFactory();
    //   factory.setRepository(upload);
    //   ServletFileUpload fileUpload = new ServletFileUpload(factory);
    //   List<FileItem> items = fileUpload.parseRequest(req.raw());
    //
    //   // image is the field name that we want to save
    //   FileItem item = items.stream()
    //   .filter(e -> "image".equals(e.getFieldName()))
    //   .findFirst().get();
    //   String fileName = item.getName();
    //   item.write(new File("/public/images/users", fileName));
    //   halt(200);
    //   return null;
    // });


  }
}
