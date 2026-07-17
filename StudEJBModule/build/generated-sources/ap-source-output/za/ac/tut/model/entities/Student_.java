package za.ac.tut.model.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import za.ac.tut.model.entities.Image;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-13T07:44:29")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile ListAttribute<Student, Image> images;
    public static volatile SingularAttribute<Student, String> gender;
    public static volatile SingularAttribute<Student, String> surname;
    public static volatile SingularAttribute<Student, Date> dob;
    public static volatile SingularAttribute<Student, String> name;
    public static volatile SingularAttribute<Student, Long> id;
    public static volatile SingularAttribute<Student, Integer> age;
    public static volatile SingularAttribute<Student, Date> timestamp;

}