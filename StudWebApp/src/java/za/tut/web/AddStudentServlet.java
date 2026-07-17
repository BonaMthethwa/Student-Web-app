/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.tut.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import za.ac.tut.model.bl.StudentFacadeLocal;
import za.ac.tut.model.entities.Image;
import za.ac.tut.model.entities.Student;

/**
 *
 * @author User
 */
@MultipartConfig
public class AddStudentServlet extends HttpServlet {
@EJB
private StudentFacadeLocal sfl;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        HttpSession session=request.getSession();
        
        Long studNum=Long.parseLong(request.getParameter("id"));
        String name=request.getParameter("name");
        String surname=request.getParameter("surname");
        Integer age=Integer.parseInt(request.getParameter("age"));
        String gender=request.getParameter("gender");
        String dob=request.getParameter("dob");
        
        SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd");
    
        Date dOB=df.parse(dob);
        
        
        Collection<Part> parts=request.getParts();
        List<Image> images=new ArrayList<>();
        
        for(Part part:parts){
            if(part.getContentType() !=null){
                String imageName=part.getSubmittedFileName();
                InputStream is=part.getInputStream();
                byte[] img_src=convertToByte(is);
                Image img=new Image(imageName, img_src);
                images.add(img);
            }
        }
        
        Student student=new Student(studNum, name, surname, age, gender, dOB, images);
        sfl.createStudent(student);
         RequestDispatcher disp=request.getRequestDispatcher("addStudent_outcome.jsp");
         disp.forward(request, response);
        
    } catch (ParseException ex) {
        Logger.getLogger(AddStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    private byte[] convertToByte(InputStream is) throws IOException {
        int value;
        byte[] buffer=new byte[1024];
        byte[] image=null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        
        while((value=is.read(buffer)) !=-1){
           baos.write(buffer, 0, value);
        }
        image=baos.toByteArray();
        return image;
    }

    private void updateSession(HttpSession session){
        int tot=(Integer) session.getAttribute("total");
        tot++;
        session.setAttribute("tot", tot);
    }
    
    

}
