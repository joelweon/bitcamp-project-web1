package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;

@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends AbstractServlet{
  
  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existId(request.getParameter("id"))) {
        out.println("존재하지 않는 아이디 입니다.");
        return;
      }
    
      teacherDao.delete(request.getParameter("id"));
      out.println("삭제하였습니다.");  
      
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
