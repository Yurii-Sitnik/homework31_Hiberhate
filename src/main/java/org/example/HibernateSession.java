package org.example;

import org.example.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateSession {
    private static SessionFactory sessionFactory;

    public static Session getSession() {
       if (sessionFactory == null){
           initSessionFactory();
       }
        return sessionFactory.openSession();
    }
    private static void initSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.addPackage("org.example.entity");
        configuration.addAnnotatedClass(StudentEntity.class);

        configuration.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/hillel");
        configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        configuration.setProperty(Environment.USER, "postgres");
        configuration.setProperty(Environment.PASS, "nk299359");

        configuration.setProperty(Environment.SHOW_SQL, "true");

        configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        configuration.setProperty(Environment.CONNECTION_PROVIDER, "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");

      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
              .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }
}
