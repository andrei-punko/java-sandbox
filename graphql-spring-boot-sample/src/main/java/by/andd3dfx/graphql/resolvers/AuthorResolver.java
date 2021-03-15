package by.andd3dfx.graphql.resolvers;

import by.andd3dfx.graphql.dao.PostDao;
import by.andd3dfx.graphql.model.Author;
import by.andd3dfx.graphql.model.Post;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorResolver implements GraphQLResolver<Author> {
    private PostDao postDao;

    @Autowired
    public AuthorResolver(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<Post> getPosts(Author author) {
        return postDao.getAuthorPosts(author.getId());
    }
}
