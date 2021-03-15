package by.andd3dfx.graphql.resolvers;

import by.andd3dfx.graphql.dao.AuthorDao;
import by.andd3dfx.graphql.model.Author;
import by.andd3dfx.graphql.model.Post;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostResolver implements GraphQLResolver<Post> {
    private AuthorDao authorDao;

    @Autowired
    public PostResolver(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Author getAuthor(Post post) {
        return authorDao.getAuthor(post.getAuthorId()).orElseThrow(RuntimeException::new);
    }
}
