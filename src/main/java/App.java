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
      request.session().attribute("loginError", 0);
      model.put("template", "templates/signUp.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/directory/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/directory.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/newMember/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/newMember.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/profile/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/profile.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/member/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/member.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/profile/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("url", request.url());
      model.put("loginError", request.session().attribute("loginError"));
      request.session().attribute("loginError", 0);
      model.put("template", "templates/profile.vtl");
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

      response.redirect("/profile/" + Integer.toString(newStudent.getId()));
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
        response.redirect("/profile/" + Integer.toString(student.getId()));
      }
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
