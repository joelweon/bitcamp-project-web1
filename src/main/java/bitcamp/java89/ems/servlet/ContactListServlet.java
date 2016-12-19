package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;

@WebServlet("/contact/list")
public class ContactListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>연락처관리-목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>연락처 정보</h1>");

    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      ArrayList<Contact> list = contactDao.getList();
    
      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>이름</th><th>직위</th><th>전화</th><th>이메일</th>");
      out.println("</tr>");
      
      for (Contact contact : list) {
        out.println("<tr> ");
        out.printf("  <td><a href='view?email=%4$s'>%s</a></td><td>%s</td><td>%s</td><td>%s</td>\n",
          contact.getName(),
          contact.getPosition(),
          contact.getTel(),
          contact.getEmail());
        out.println("</tr>");
      }
      
      out.println("</table>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println("</body>");
    out.println("</html>");
  }
}








