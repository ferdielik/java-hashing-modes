package hashing;

import controller.HashFileController.HashMode;
import main.Student;

public interface Hashing
{
    void addWithLinearProbe(Student student);

    void addWithDiscreteLeash(Student student);

    Student getStudent(Integer studentNumber);

    boolean existStudents(Integer index);

    boolean existStudentsLinear(Integer index);
}
