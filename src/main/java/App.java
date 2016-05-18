import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {

    //  setPort(80);

    staticFileLocation("/public");

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("loginError", request.session().attribute("loginError"));
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
      model.put("loginError", request.session().attribute("loginError"));
      model.put("template", "templates/signUp.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/newMember/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/newMember.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/member/test", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/member.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/profile/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("loginError", request.session().attribute("loginError"));
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
      ArrayList<Integer> errorTypes = new ArrayList<Integer>();

      String userName = request.queryParams("userName");
      String password = request.queryParams("password");

      if(Student.login(userName, password)){
        request.session().attribute("loginError", 1);
        response.redirect(request.url());
      }
      if(errorTypes.size() > 0){

      }else{
      Student newStudent = new Student(newUserFirstName, newUserLastName, newUserEmail, newPassword);
      newStudent.save();

      response.redirect("/profile/" + Integer.toString(newStudent.getId()));
      }
      return null;
    });

  }
}
