/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbw.cms.mock;

import ch.bbw.cms.enums.UserGender;
import ch.bbw.cms.enums.UserType;
import ch.bbw.cms.models.Post;
import ch.bbw.cms.models.User;
import ch.bbw.cms.inf.DatabaseControlInf;


import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author guenthard
 */
public class DatabaseControlMock implements DatabaseControlInf{
    ArrayList<User> users = new ArrayList<User>();
	
    public DatabaseControlMock(){
        users.add(new User(1, "dummy1", "asdf", "dummy@dummy1", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(2, "dummy1", "asdf", "dummy@dummy2", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(3, "dummy1", "asdf", "dummy@dummy3", UserGender.FEMALE, UserType.NORMAL));
        users.add(new User(4, "dummy1", "asdf", "dummy@dummy4", UserGender.FEMALE, UserType.NORMAL));
    }
    
    @Override
    public ArrayList<Post> getPosts(User user){
	return getPostList(user.getUserId());
    }
    
    @Override
    public ArrayList<Post> getPosts(){
	return getPostList(null);
    }
    
    @Override
    public ArrayList<Post> getPosts(int userId){
        return getPostList(userId);
    }

    @Override
    public ArrayList<Post> getPostList(Integer userId){
	ArrayList<Post> posts = new ArrayList<Post>();
	
        posts.add(new Post(0, "title of blabla 1", "blabla", 2));
        posts.add(new Post(1, "hihi", "asdf", 3));
        posts.add(new Post(2, "show edit button!", "asdf", 1));
        
	return posts;
    }
    
    @Override
    public ArrayList<User> getUserList(){
        
	return users;
    }
    


    @Override
    public ArrayList<Post> getPosts(String searchterm) {
        ArrayList<Post> posts = new ArrayList<Post>();
	
        posts.add(new Post(0, "title of omg 1", "omg", 4));
        posts.add(new Post(1, "abc", "some text Lorem Ipsum hello world Telephone Banana Java Abstract etc. ", 1));
        posts.add(new Post(3, "Lorem Ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas congue ligula ac quam viverra nec consectetur ante hendrerit. Donec et mollis dolor. Praesent et diam eget libero egestas mattis sit amet vitae augue. Nam tincidunt congue enim, ut porta lorem lacinia consectetur. Donec ut libero sed arcu vehicula ultricies a non tortor. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean ut gravida lorem. Ut turpis felis, pulvinar a semper sed, adipiscing id dolor. Pellentesque auctor nisi id magna consequat sagittis. Curabitur dapibus enim sit amet elit pharetra tincidunt feugiat nisl imperdiet. Ut convallis libero in urna ultrices accumsan. Donec sed odio eros. Donec viverra mi quis quam pulvinar at malesuada arcu rhoncus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In rutrum accumsan ultricies. Mauris vitae nisi at sem facilisis semper ac in est."
                    + "Vivamus fermentum semper porta. Nunc diam velit, adipiscing ut tristique vitae, sagittis vel odio. Maecenas convallis ullamcorper ultricies. Curabitur ornare, ligula semper consectetur sagittis, nisi diam iaculis velit, id fringilla sem nunc vel mi. Nam dictum, odio nec pretium volutpat, arcu ante placerat erat, non tristique elit urna et turpis. Quisque mi metus, ornare sit amet fermentum et, tincidunt et orci. Fusce eget orci a orci congue vestibulum. Ut dolor diam, elementum et vestibulum eu, porttitor vel elit. Curabitur venenatis pulvinar tellus gravida ornare. Sed et erat faucibus nunc euismod ultricies ut id justo. Nullam cursus suscipit nisi, et ultrices justo sodales nec. Fusce venenatis facilisis lectus ac semper. Aliquam at massa ipsum. Quisque bibendum purus convallis nulla ultrices ultricies. Nullam aliquam, mi eu aliquam tincidunt, purus velit laoreet tortor, viverra pretium nisi quam vitae mi. Fusce vel volutpat elit. Nam sagittis nisi dui."
                    + "Suspendisse lectus leo, consectetur in tempor sit amet, placerat quis neque. Etiam luctus porttitor lorem, sed suscipit est rutrum non. Curabitur lobortis nisl a enim congue semper. Aenean commodo ultrices imperdiet. Vestibulum ut justo vel sapien venenatis tincidunt. Phasellus eget dolor sit amet ipsum dapibus condimentum vitae quis lectus. Aliquam ut massa in turpis dapibus convallis. Praesent elit lacus, vestibulum at malesuada et, ornare et est. Ut augue nunc, sodales ut euismod non, adipiscing vitae orci. Mauris ut placerat justo. Mauris in ultricies enim. Quisque nec est eleifend nulla ultrices egestas quis ut quam. Donec sollicitudin lectus a mauris pulvinar id aliquam urna cursus. Cras quis ligula sem, vel elementum mi. Phasellus non ullamcorper urna."
                    + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In euismod ultrices facilisis. Vestibulum porta sapien adipiscing augue congue id pretium lectus molestie. Proin quis dictum nisl. Morbi id quam sapien, sed vestibulum sem. Duis elementum rutrum mauris sed convallis. Proin vestibulum magna mi. Aenean tristique hendrerit magna, ac facilisis nulla hendrerit ut. Sed non tortor sodales quam auctor elementum. Donec hendrerit nunc eget elit pharetra pulvinar. Suspendisse id tempus tortor. Aenean luctus, elit commodo laoreet commodo, justo nisi consequat massa, sed vulputate quam urna quis eros. Donec vel. ", 0));
        
	return posts;
    }
    
    @Override
    public Post getPost(int postId){
        return new Post(1, "abc", "some text Lorem Ipsum hello world Telephone Banana Java Abstract etc. ", 1);
    }
    
    @Override
    public int checkUser(String username, String password){
        
        return 1;
    }

    @Override
    public boolean createUser(String username, String password, String email, UserGender gender, UserType type) {
        return true;
    }
    
    @Override
    public boolean createUser(User user){
        return true;
    }
    
    @Override
    public boolean createPost(Post post){
        return true;
    }
    
    @Override
    public boolean createPost(String title, String content, int userId){
        return true;
    }

    @Override
    public boolean changeUserBio(int userId, String bio) {
        return true;
    }

    @Override
    public boolean changeUserPassword(int userId, String newPw) {
        return true;
    }

    @Override
    public int getUserId(String nameOrEmail) {
        //Random rand = new Random();
        return 2;//;rand.nextInt(10);
    }

    @Override
    public boolean updatePost(int postid, String title, String content) {
        return true;
    }
    
    @Override
    public User getUser(int id){
        return new User("User 1", "abc123", "abc@def", UserGender.FEMALE, (id == 1 ? UserType.NORMAL : id == 2 ? UserType.CONTENT : UserType.TECHNICAL));
    }

    @Override
    public boolean changeUserType(int userId, UserType type){
        return true;
    }
    
    
    
    
}
