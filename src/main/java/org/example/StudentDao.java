package org.example;

import org.example.entity.StudentEntity;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateSession.getSession()) {
            List<StudentEntity> studentEntities = session.createQuery("SELECT A FROM StudentEntity A", StudentEntity.class).list();

            System.out.println(studentEntities);
        }
    }
}