/* 목적: javax.servlet.Servlet 인터페이스의 4 개 메서드를 미리 구현하여
 *       서버 클래스에게 상속해주는 용도로 사용.
 *       서브 클래스는 Servlet 인터페이스를 직접 구현하는 것 보다,
 *       이 클래스를 상속받게 되면 오직 service() 메서드만 구현하면 되기 때문에 편리하다.*/
package bitcamp.java89.ems.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

//  톰캣 서버가 실행할 수 있는 클래스는 반드시 Servlet 규격에 맞추어 제작해야 한다.
//  그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거롭다.
//  그래서 AbstractServlet이라는 추상 클래스를 만들어서,
//  이 클래스를 상속받아 간접적으로 Servlet인터페이스를 구현하는 방식을 취한다.
//  이 클래스를 상속받게 되면 오직 service() 메서드만 만들면 되기 때문에 코드가 편리하다.
public abstract class AbstractServlet implements Servlet{
  ServletConfig config; 
  
  @Override
  public void init(ServletConfig config) throws ServletException {// 톰캠서버가 호출하는 것
    this.config = config;
  }
  
  @Override
  public ServletConfig getServletConfig() { // 톰캠서버가 호출하는 것
    return this.config;
  }

  @Override
  public String getServletInfo() { // 톰캠서버가 호출하는 것
    return this.getClass().getName();
  }
  
  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    
  }



}
