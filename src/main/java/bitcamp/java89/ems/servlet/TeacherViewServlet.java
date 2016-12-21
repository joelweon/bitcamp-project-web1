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

@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String id = request.getParameter("id");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사 정보</h1>");
    out.println("<form action='update' method='POST'>");
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      Teacher teacher = teacherDao.getDetail(id);
      
      if (teacher == null) {
        throw new Exception("해당 아이디의 강사가 없습니다.");
      }
      
      out.println("<table border='1'>");
      out.printf("<tr><th>아이디</th><td>"
          + "<input name='id' type='text' value='%s' readonly></td></tr>\n", teacher.getId());
      out.printf("<tr><th>이름</th><td>"
          + "<input name='name' type='text' value='%s'></td></tr>\n", teacher.getName());
      out.printf("<tr><th>이메일</th><td>"
          + "<input name='email' type='text' value='%s'></td></tr>\n", teacher.getEmail());
      out.printf("<tr><th>전화번호</th><td>"
          + "<input name='tel' type='text' value='%s'></td></tr>\n", teacher.getTel());
      out.printf("<tr><th>전공</th><td>"
          + "<input name='major' type='text' value='%s'></td></tr>\n", teacher.getMajor());
      out.printf("<tr><th>주요언어</th><td>"
          + "<input name='majorLanguage' type='text' value='%s'></td></tr>\n", teacher.getMajorLanguage());
      out.printf("<tr><th>Git주소</th><td>"
          + "<input name='gitAddress' type='text' value='%s'></td></tr>\n", teacher.getGitAddress());
      out.printf("<tr><th>근무년수</th><td>"
          + "<input name='workExperience' type='number' value='%d'></td></tr>\n", teacher.getWorkExperience());
      out.printf("<tr><th>강의년수</th><td>"
          + "<input name='lectureExperience' type='number' value='%d'></td></tr>\n", teacher.getLectureExperience());
      out.printf("<tr><th>나이</th><td>"
          + "<input name='age' type='number' value='%d'></td></tr>\n", teacher.getAge());
      out.printf("<tr><th>희망급여</th><td>"
          + "<input name='salary' type='number' value='%d'></td></tr>\n", teacher.getSalary());
      
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?id=%s'>삭제</a>", teacher.getId());
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println(" <a href='list'>목록</a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
