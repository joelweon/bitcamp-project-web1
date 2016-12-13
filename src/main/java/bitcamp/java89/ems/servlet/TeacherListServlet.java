package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

@WebServlet("/teacher/list")
public class TeacherListServlet extends AbstractServlet{
  
  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();
      
//      웹 브라우저 쪽으로 출력할 수 있도록 출력 스트림 객체를 얻는
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      for (Teacher teacher : list) {
        out.printf("%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
          teacher.getId(),
          teacher.getName(),
          teacher.getEmail(),
          teacher.getTel(),
          teacher.getMajor(),
          teacher.getMajorLanguage(),
          teacher.getGitAddress(),
          teacher.getWorkExperience(),
          teacher.getLectureExperience(),
          teacher.getAge(),
          teacher.getSalary());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
