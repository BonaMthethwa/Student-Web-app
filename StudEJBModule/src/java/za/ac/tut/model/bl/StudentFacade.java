/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.model.entities.Student;

/**
 *
 * @author User
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> implements StudentFacadeLocal {

    @PersistenceContext(unitName = "StudEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }

    @Override
    public Long countFemale() {
        Query query=em.createQuery("Select count(s) from Student s where s.gender='F'");
        Long f=(Long)query.getSingleResult();
        return f;
    }

    @Override
    public Long countMale() {
        Query query=em.createQuery("Select count(s) from Student s where s.gender='M'");
        Long m=(Long)query.getSingleResult();
        return m;
    }

    @RolesAllowed("admin")
    @Override
    public void createStudent(Student student) {
        create(student);
    }

    @Override
    public void ediStudentt(Student student) {
        edit(student);
    }

    @Override
    public void removeStudent(Student student) {
        remove(student);
    }

    @RolesAllowed({"admin", "student"})
    @Override
    public Student findStudent(Object id) {
        Student stud=find(id);
        return stud;
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> students=findAll();
        return students;
    }
    
}
