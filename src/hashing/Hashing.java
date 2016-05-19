package hashing;

import main.Student;

public interface Hashing
{
    public void addWithLinearProbe(Student student);

    public void addWithDiscreteLeash(Student student);

    Student getStudent(Integer studentNumber);
}
