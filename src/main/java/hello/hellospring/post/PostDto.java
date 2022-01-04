package hello.hellospring.post;

import org.jetbrains.annotations.NotNull;

public class PostDto {

    public static class PostSaveDto{
        private String songtitle= "";
        private String artist="";
        private String title= "";
        private String username= "";
        private String password= "";
        private String contents="";


        public PostSaveDto(String songtitle, String artist, String title, String username, String password, String contents) {
            this.songtitle = songtitle;
            this.artist = artist;
            this.title = title;
            this.username = username;
            this.password = password;
            this.contents = contents;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    public static class PostListDto{
        private Long id;
        private String songtitle;
        private String artist;
        private String title;
        private String username;
        private Long views;

        public PostListDto(Long id, String songtitle, String artist, String title, String username,Long views) {
            this.id = id;
            this.songtitle = songtitle;
            this.artist = artist;
            this.title = title;
            this.username = username;
            this.views=views;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Long getViews() {
            return views;
        }

        public void setViews(Long views) {
            this.views = views;
        }
    }

    public static class PostDetailDto{
        private Long id;
        private String songtitle= "";
        private String artist="";
        private String title= "";
        private String username= "";
        private String contents="";
        private Long views=0L;

        public PostDetailDto(Long id, String songtitle, String artist, String title, String username, String contents, long views) {
            this.id = id;
            this.songtitle = songtitle;
            this.artist = artist;
            this.title = title;
            this.username = username;
            this.contents = contents;
            this.views= views;
        }
        public PostDetailDto(Post post){
            this.id = post.getId();
            this.songtitle = post.getSongtitle();
            this.artist = post.getArtist();
            this.title = post.getTitle();
            this.username = post.getUsername();
            this.contents = post.getContents();
            this.views= post.getViews();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public Long getViews() {
            return views;
        }

        public void setViews(Long views) {
            this.views = views;
        }

    }

    public static class PostModifyDto{
        private Long id;
        private String password="";
        private String songtitle= "";
        private String artist="";
        private String title= "";
        private String username= "";
        private String contents="";

        public PostModifyDto(){

        }

        public PostModifyDto(Long id, String password, String songtitle, String artist, String title, String username, String contents) {
            this.id = id;
            this.password = password;
            this.songtitle = songtitle;
            this.artist = artist;
            this.title = title;
            this.username = username;
            this.contents = contents;
        }

        public PostModifyDto(Post post){
            this.id=post.getId();
            this.password=post.getPw();
            this.songtitle = post.getSongtitle();
            this.artist = post.getArtist();
            this.title = post.getTitle();
            this.username = post.getUsername();
            this.contents = post.getContents();

        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }


    }
}
