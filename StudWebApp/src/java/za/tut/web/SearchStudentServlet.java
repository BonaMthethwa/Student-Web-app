/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.tut.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.ac.tut.model.bl.StudentFacadeLocal;
import za.ac.tut.model.entities.Image;
import za.ac.tut.model.entities.Student;

/**
 *
 * @author User
 */
@MultipartConfig
public class SearchStudentServlet extends HttpServlet {
@EJB
private StudentFacadeLocal sfl;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        
        Long studNum=Long.parseLong(request.getParameter("id"));
        
        Student student=sfl.findStudent(studNum);
        request.setAttribute("student", student);
       
        RequestDispatcher disp=request.getRequestDispatcher("findStudent_outcome.jsp");
         disp.forward(request, response);
        
    }
    
}
