package hello.hellospring.post;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcPostRepo implements PostRepository{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostRepo(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("ID");
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(post.getHash()));
        post.setId(key.longValue());
        return post;
    }

    @Override
    public List<PostDto.PostListDto> findByAll() {
        return jdbcTemplate.query("select * from POST", postListDtoRowMapper());
    }

    @Override
    public Optional<PostDto.PostDetailDto> findById(Long id) {
        List<PostDto.PostDetailDto> result = jdbcTemplate.query("select * from POST where id= " + id, postDetailRowMapper());

        if (result.size() == 1) {
            return Optional.of(result.get(0));
        }
        return Optional.empty();
    }
    @Override
    public void view_Count(Long id){

        jdbcTemplate.update("update POST set views = views+1 where id="+ id);
    }

    private RowMapper<PostDto.PostListDto> postListDtoRowMapper() {
        return ((rs, rowNum) -> {
            PostDto.PostListDto post = new PostDto.PostListDto(
                    rs.getLong("id"),
                    rs.getString("songtitle"),
                    rs.getString("artist"),
                    rs.getString("title"),
                    rs.getString("username"),
                    rs.getLong("views")
            );
            return post;
        });
    }

    private RowMapper<PostDto.PostDetailDto> postDetailRowMapper() {
        return ((rs, rowNum) -> {
            PostDto.PostDetailDto post = new PostDto.PostDetailDto(
                    rs.getString("songtitle"),
                    rs.getString("artist"),
                    rs.getString("title"),
                    rs.getString("username"),
                    rs.getString("contents"),
                    rs.getLong("views")
            );
            return post;
        });
    }
    private RowMapper<PostDto.PostViewCountDto> postViewCountRowMapper() {
        return ((rs, rowNum) -> {
            PostDto.PostViewCountDto view = new PostDto.PostViewCountDto(
                    rs.getLong("views")
            );
            return view;
        });
    }

}
