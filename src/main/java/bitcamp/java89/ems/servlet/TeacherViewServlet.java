package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;

@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String id = request.getParameter("id");
      ArrayList<Teacher> list = teacherDao.getListById(id);
      
      for (Teacher teacher : list) {
        out.println("-----------------------------------");
        out.printf("아이디: %s\n", teacher.getId());
        out.printf("이름: %s\n", teacher.getName());
        out.printf("이메일: %s\n", teacher.getEmail());
        out.printf("전화번호: %s\n", teacher.getTel());
        out.printf("주요언어: %s\n", teacher.getMajorLanguage());
        out.printf("Git주소: %s\n", teacher.getGitAddress());
        out.printf("직장경력: %d\n", teacher.getWorkExperience());
        out.printf("강의경력: %d\n", teacher.getLectureExperience());
        out.printf("나이: %d\n", teacher.getAge());
        out.printf("희망급여: %d\n", teacher.getSalary());
        out.println("-----------------------------------");
        return;
      }
      out.println("존재하지 않는 아이디입니다.");
      
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
