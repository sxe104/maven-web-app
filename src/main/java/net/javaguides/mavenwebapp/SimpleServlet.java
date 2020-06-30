package net.javaguides.mavenwebapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/saytime")
public class SimpleServlet extends HttpServlet {
	
	   private static final long serialVersionUID = 1L;
	   @Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	         throws ServletException, IOException {
		  System.out.println("<< -- enter into main method updated  -- >>");
		  String userInputFilePath = req.getParameter("myFilePath");
		  System.out.println("User Input -->  "+userInputFilePath);
		  
		 
		  if (userInputFilePath!=null ) {
			File file=new File(userInputFilePath);
		  }
		  cookieHeaderScenario(req, resp);
		  headerScenario(req, resp);
		  testCaseForDirectory();
	   }
	   
	   public void headerScenario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			System.out.println("<<< Header-scenario-method() >>>");
			String userInput=request.getParameter("myFilePath");
			System.out.println("user input value in header scenario -->>"+userInput);
			response.setHeader("Location", userInput);// in this line , it will throw Header manipulation issue 
		}
	   public void cookieHeaderScenario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			System.out.println("<< Cookie Header method() updated >>>>");
			String userInput=request.getParameter("myUserInput");
			System.out.println("user input value in cookie header <before validate >:::"+userInput);
					
			//Creating a cookie
		    Cookie c1=new Cookie("userName",userInput); //in this line , it will throw Header manipulation issue 
		     
		    //Adding the cookies to response header
	        response.addCookie(c1);
	       
	    }
	   public static void testCaseForDirectory() {
			try {
				System.out.println("enter into testCaseForDirectory...updated.");
				String logFile = null;
				Calendar now = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String printTime = sdf.format(now.getTime());
				String filePath = System.getProperty("user.dir");
				String fileSeparator = System.getProperty("file.separator");
				String fileName = "out_"+printTime+".log";
				logFile = filePath+fileSeparator+"logs"+fileSeparator+fileName;
				System.out.println("total directory file path --> "+logFile);
				
				//Due to line# 44 & 45, the fortify throwing vulnerability at below line
				File fLog = new File (logFile);
			}catch(Exception e) {
				System.out.println("Exception caught ::" + e.getMessage());
			}
		}
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
	   
	   
	   protected static Iterable<String>   getAppDefinedWhiteListedPaths() {
			ArrayList<String> whitelistedPaths = new ArrayList<String>(){
				{
					add("C:\\Equifax\\rajan-project\\eclipse-Wrkspace\\path-man-latest-code-2\\logs"); 
					add("C:\\Equifax\\rajan-project\\eclipse-jee-oxygen\\logs"); 
					add("C:\\Equifax\\rajan-project\\eclipse-jee-oxygen"); 
					
				}
			};
			return whitelistedPaths;
		}
		protected static Iterable<String>   getAppDefinedWhiteListedExtensions() {
			ArrayList<String> whitelistedExtensions =  new ArrayList<String>(){
				{
					add("txt"); 
					add("doc"); 
					add("docs"); 
					add("JPEG");
					add("jpeg");
					add("PNG");
					add("png");
					add("img");
					add("TIFF");
					add("tiff");
					add("gif");
				}
			};
			return whitelistedExtensions;
		}
}
