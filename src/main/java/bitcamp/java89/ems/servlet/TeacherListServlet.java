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

@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    try {
    TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
    ArrayList<Teacher> list = teacherDao.getList();
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 정보</h1>");
    out.println("<a href='form.html'>추가</a><br>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("  <th>아이디</th><th>이름</th><th>이메일</th><th>전화번호</th>");
    out.println("</tr>");
//      웹 브라우저 쪽으로 출력할 수 있도록 출력 스트림 객체를 얻는
      for (Teacher teacher : list) {
        out.println("<tr>");
        out.printf("  <td><a href='view?id=%s'>%1$s</a></td><td>%s</td><td>%s</td><td>%s</td>\n",
          teacher.getId(),
          teacher.getName(),
          teacher.getEmail(),
          teacher.getTel());

        out.println("</tr>");
      }
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
