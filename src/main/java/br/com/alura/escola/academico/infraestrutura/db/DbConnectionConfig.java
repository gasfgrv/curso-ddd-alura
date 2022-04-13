package br.com.alura.escola.academico.infraestrutura.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DbConnectionConfig {

    public static SessionFactory getSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        return new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

}
