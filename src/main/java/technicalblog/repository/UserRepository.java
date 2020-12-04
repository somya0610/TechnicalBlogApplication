package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "techblog")
    public EntityManagerFactory entityManagerFactory;

    public void registerUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public User checkUser(String username, String password) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("Select u from User u where u.username = :username and u.password = :password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User result = query.getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }


}
