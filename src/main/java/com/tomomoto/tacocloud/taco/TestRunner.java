package com.tomomoto.tacocloud.taco;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.postgresql.Driver;

public class TestRunner {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.configure();
        conf.addAnnotatedClass(Taco.class);
        conf.addAnnotatedClass(TacoIngredient.class);
        conf.addAnnotatedClass(TacoOrder.class);
        @Cleanup SessionFactory factory = conf.buildSessionFactory();
        @Cleanup Session session = factory.openSession();
        session.beginTransaction();

        Taco taco = session.get(Taco.class, 10);
        session.getTransaction().commit();
        System.out.println(taco.getId());
        System.out.println(taco.getName());
        taco.getIngredients().forEach(tacoIngredient -> System.out.println(tacoIngredient.getName()));
    }
}
