package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class App extends HttpServlet{
    /**
     *
     */
    private static final long serialVersionUID = -3967314453512919811L;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java 1");
    }

    private String mymsg;
    public void init(ServletConfig config) throws ServletException 
    {     
       super.init(config);
       mymsg = config.getInitParameter("title");
       //mymsg = Constants.TITOLO;   
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String p = req.getHeader("p");
        
        // Setting up the content type of web page      
        res.setContentType("text/html");
        // Writing the message on the web page      
        PrintWriter out = res.getWriter(); 
        String value = req.getParameter("title");
        if(value == null){
            value = mymsg; 
        }
        out.println("<h1>" + value + "</h1>");      
        out.println("<p>" + "Hello Friends!" + p + "</p>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        // Setting up the content type of web page      
        res.setContentType("text/html");
        // Writing the message on the web page      
        String value = req.getParameter("title");
        PrintWriter out = res.getWriter();  
        out.println("post ok!" + value);
    }

}