package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

@WebServlet("/teacher/add")
public class TeacherAddServlet extends AbstractServlet{
  
  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();

      if (teacherDao.existId(request.getParameter("id"))) {
        out.println("같은 아이디가 존재합니다. 등록을 취소합니다.");
        return;
      }
      
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
      
      teacherDao.insert(teacher);
      out.println("등록하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
