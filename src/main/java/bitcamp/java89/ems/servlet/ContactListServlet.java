package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;

@WebServlet("/contact/list")
public class ContactListServlet extends AbstractServlet{
  
  @Override
  public void service(ServletRequest request, ServletResponse response) 
      throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      ArrayList<Contact> list = contactDao.getList();
      
//      웹 브라우저 쪽으로 출력할 수 있도록 출력 스트림 객체를 얻는
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      for (Contact contact : list) {
        out.printf("%s,%s,%s,%s\n",
            contact.getName(),
            contact.getPosition(),
            contact.getTel(),
            contact.getEmail());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
