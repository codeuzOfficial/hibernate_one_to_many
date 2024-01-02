package dasturlash.uz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        CommentEntity comment1 = new CommentEntity();
        comment1.setContent("Daxshat rasmku");
        comment1.setCreatedDate(LocalDateTime.now());
        session.save(comment1);

        CommentEntity comment2 = new CommentEntity();
        comment2.setContent("Qo'yni dumbasidek");
        comment2.setCreatedDate(LocalDateTime.now());
        session.save(comment2);

        CommentEntity comment3 = new CommentEntity();
        comment3.setContent("Mazgi o'chir bu rasmingni.");
        comment3.setCreatedDate(LocalDateTime.now());
        session.save(comment3);

        PostEntity post = new PostEntity();
        post.setTitle("Yangi rasm");
        post.setContent("Yangi rasm qalay bo'bdi");
        post.setCreatedDate(LocalDateTime.now());

        List<CommentEntity> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

        post.setCommentList(commentList);
        session.save(post);

        t.commit();

        factory.close();
        session.close();
    }
}