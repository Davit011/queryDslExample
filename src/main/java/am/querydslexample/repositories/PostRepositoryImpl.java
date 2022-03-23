package am.querydslexample.repositories;

import am.querydslexample.model.Post;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static am.querydslexample.model.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<Post> findByFilter(String text, String field) {
        List<Post> fetch = null;
        switch (field) {
            case "text":
                fetch = new JPAQuery<Post>(entityManager)
                        .select(post)
                        .from(post)
                        .where(post.text.containsIgnoreCase(text))
                        .fetch();
                break;
            case "title":
                fetch = new JPAQuery<Post>(entityManager)
                        .select(post)
                        .from(post)
                        .where(post.title.containsIgnoreCase(text))
                        .fetch();
                break;
        }
        return fetch;
    }
}
