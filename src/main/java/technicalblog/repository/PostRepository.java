package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    public EntityManagerFactory entityManagerFactory;

    public List<Post> getAllPosts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Post> query = entityManager.createQuery("SELECT p from Post p", Post.class);
        List<Post> resultList = query.getResultList();
        return resultList;
    }

    public Post getLatestPost() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Post post = entityManager.find(Post.class, 3);
        return post;
    }

    public Post createPost(Post newPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(newPost);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newPost;
    }

    public Post getPost(Integer postId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Post.class, postId);
    }

    public void updatePost(Post updatedPost) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(updatedPost);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(Integer postId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Post post = em.find(Post.class, postId);
            em.remove(post);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }

    public void deletePost(String postTitle) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Query query = em.createQuery("SELECT p from Post p where p.title = :title")
                    .setParameter("title", postTitle);
            List<Post> posts = query.getResultList();
            em.remove(posts);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }

}
