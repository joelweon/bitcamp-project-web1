package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    
    Teacher teacher = new Teacher();
    teacher.setId(request.getParameter("id"));
    teacher.setName(request.getParameter("name"));
    teacher.setEmail(request.getParameter("email"));
    teacher.setTel(request.getParameter("tel"));
    teacher.setMajor(request.getParameter("major"));
    teacher.setMajorLanguage(request.getParameter("majorLanguage"));
    teacher.setGitAddress(request.getParameter("gitAddress"));
    teacher.setWorkExperience(Integer.parseInt(request.getParameter("workExperience")));
    teacher.setLectureExperience(Integer.parseInt(request.getParameter("lectureExperience")));
    teacher.setAge(Integer.parseInt(request.getParameter("age")));
    teacher.setSalary(Integer.parseInt(request.getParameter("salary")));
    
    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>변경 결과</h1>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      
      if (!teacherDao.existId(request.getParameter("id"))) {
        throw new Exception("해당 아이디가 없습니다."); 
      }
      
      teacherDao.update(teacher);
      out.println("<p>변경 완료했습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println("</body>");
    out.println("</html>");
  }
}
