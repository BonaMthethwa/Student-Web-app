/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.tut.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.ac.tut.model.bl.StudentFacadeLocal;

/**
 *
 * @author User
 */
public class GetStatsServlet extends HttpServlet {
@EJB
private StudentFacadeLocal sfl;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        
        Long numF=sfl.countFemale();
        Long numM=sfl.countMale();
        
        request.setAttribute("numF", numF);
        request.setAttribute("numM", numM);
         RequestDispatcher disp=request.getRequestDispatcher("stats.jsp");
         disp.forward(request, response);
    }

}
