package org.example;

import org.example.entity.StudentEntity;
import org.hibernate.Session;

import java.util.List;

public class StudentDao {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        studentDao.getAll();
        studentDao.add();
        studentDao.getById(3);
        studentDao.update("Stepan", 3);
        studentDao.delete(4);

    }

    public void getAll() {
        try (Session session = HibernateSession.getSession()) {
            List<StudentEntity> studentEntities = session.createQuery("SELECT A FROM StudentEntity A", StudentEntity.class).list();

            System.out.println(studentEntities);
        }
    }

    public void add() {
        try (Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            StudentEntity studentEntity1 = new StudentEntity("Mark", "mark@gmail.com");
            StudentEntity studentEntity2 = new StudentEntity("Jim", "jim@gmail.com");
            StudentEntity studentEntity3 = new StudentEntity("Bread", "bread@gmail.com");
            StudentEntity studentEntity4 = new StudentEntity("Brendon", "brendon@gmail.com");

            session.save(studentEntity1);
            session.save(studentEntity2);
            session.save(studentEntity3);

            session.getTransaction().commit();
        }

    }

    public void getById(int id) {
        try (Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            StudentEntity studentEntity = session.get(StudentEntity.class, id);
            System.out.println(studentEntity);
            session.getTransaction().commit();
        }

    }

    public void update(String name, int id) {
        try (Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            StudentEntity studentEntity = session.get(StudentEntity.class, id);
            studentEntity.setName(name);
            studentEntity.setEmail(name + "@gmail.com");
            System.out.println("Student " + studentEntity.getName() + " updated");
            session.getTransaction().commit();
        }
    }

    public void delete(int id) {
        try (Session session = HibernateSession.getSession()) {
            session.beginTransaction();
            StudentEntity studentEntity = session.get(StudentEntity.class, id);
            session.remove(studentEntity);
            System.out.println("Student " + studentEntity.getName() + " deleted");
            session.getTransaction().commit();
        }
    }
}