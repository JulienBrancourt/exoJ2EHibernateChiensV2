package org.example.exochiensv2.repository;

import org.example.exochiensv2.model.Dog;
import org.example.exochiensv2.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DogRepository {
    //variables globales dans notre repository accesibles par toute les méthodes du repository
    private SessionFactory sessionFactory;
    private Session session;

    public DogRepository() {
        //a la creation de notre repository on vient recupérer l'instance
        // de SessionFactory pour pouvoir créer des sessions dans notre repository
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public Dog create(Dog dog) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(dog);
        session.getTransaction().commit();
        session.close();
        return dog;
    }

    public Dog findById(int id) {
        session = sessionFactory.openSession();
        Dog dog = session.get(Dog.class, id);
        return dog;
    }

    public List<Dog> findAll() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Dog> dogs = session.createQuery("from Dog").list();
        session.getTransaction().commit();
        session.close();
        return dogs;
    }
}