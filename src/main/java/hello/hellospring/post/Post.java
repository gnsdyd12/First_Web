package hello.hellospring.post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Post {
    private Long id;
    private String pw;
    private String username;
    private String title;
    private String contents;
    private String link;
    private String songtitle;
    private String artist;
    private Long views;


    public Post() {
    }

    public Post(PostDto.PostSaveDto postSaveDto) {
        this.id = -1L;
        this.username = postSaveDto.getUsername();
        this.pw = postSaveDto.getPassword();
        this.title = postSaveDto.getTitle();
        this.link = "";
        this.contents = postSaveDto.getContents();
        this.songtitle = postSaveDto.getSongtitle();
        this.artist = postSaveDto.getArtist();
        this.views=0L;
        /* ... */
    }

    public Map<String, Object> getHash() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", this.getId());
        parameters.put("password", this.getPw());
        parameters.put("username", this.getUsername());
        parameters.put("songtitle", this.getSongtitle());
        parameters.put("artist", this.getArtist());
        parameters.put("link", this.getLink());
        parameters.put("contents", this.getContents());
        parameters.put("title", this.getTitle());
        parameters.put("views", this.getViews());

        return parameters;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }


    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getCreated_time() {
        return created_time;
    }

    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    private LocalDateTime created_time;
}
