package employees;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class DbInit {

    public void init(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Session hibernateSession = em.unwrap(Session.class);

            hibernateSession.doWork(new org.hibernate.jdbc.Work() {

                @Override
                public void execute(Connection connection) throws SQLException {
                    try {
                        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                        Liquibase liquibase = new Liquibase("/db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
                        liquibase.update(new Contexts());
                    } catch (LiquibaseException e) {
                            throw new IllegalStateException("Can not update", e);
                        }
                }
            });


            em.getTransaction().commit();
        }

        finally {
            em.close();
        }
    }
}
