/*
 * 
 * (Create Post, Delete Post, Get Feed, Get Feed Paginated, Follow, Unfollow).
 *   user should be able to follow other users
 *   user should be able to create a post 
 *   delete a post 
 *   get feed this will be posts from all the user that currently the user follow
 *   
 *   pagination feature 
 * 
 *  Facebook:
 *  -- users(uid ,users) hashmap
 *  -- PAGE SIZE , int
 * 
 *  -- get_feed(user) => void display all the posts of people the user follows in sorted in descending order
 *  -- create_post(userid) -> void
 *  -- delete_post(userid) -> bool   
 *  -- get_feed_pagingated  
 
 *  User
 *  --  name string
 *  --  id    int
 *   -- double linked list of type post  
 *  --  following <set of type user> integer
 *   
 *  -- addFollowee  void
 *  -- remove Followee  void
    -- create post      => boolean
    --> remove post      => boolean
    --> get all posts     => scrollable  (prev,next)   doubley linked list of type posts 


    Post 
    -- post id    => int
    -- timestamp  => int
    -- post name  str
    -- get post id
    -- gettime 
    -- getPrev
    -- getNext
    --setPrev
    --setNext
 * 
 */
package fb;
import java.util.*;


class Post{
    int postId;
    int time;
    
    Post next;
    Post prev;

    Post(int postId)
    {
        this.postId = postId;
        this.time = Facebook.timestamp++;
    }

    public int getId()
    {
        return postId;
    }

    public void setPostId(int postId)
    {
        this.postId = postId;
    }

    public int getTime()
    {
        return time;
    }

   

    public Post getPrev() {
        return prev;
    }

    public void setPrev(Post prev) {
        this.prev = prev;
    }

    public Post getNext() {
        return next;
    }

    public void setNext(Post next) {
        this.next = next;
    }
 }


class User {
    // WE TAKE LINKED LIST BECAUSE DELETION AND UPDATION AS IN O(1)
    int id;
    HashMap<Integer, Post> userPosts;
    Post head;
    Post tail;
    HashSet<Integer> following;
  

    User(int id)
    {
        this.id = id;
        following = new HashSet<Integer>();
        userPosts = new HashMap<Integer, Post>();
        head = new Post(-1);
        tail = new Post(-1);
        head.setNext(tail);
        tail.setPrev(head);
        head.setPrev(null);
        tail.setNext(null);

    }
    public int getId() {
        return id;
    }
    public Post getHead() {
        return head;
    }
    public Post getTail() {
        return tail;
    }
    public HashSet<Integer> getFollowing(){
        return following;
    }
    public void follow(int userId){
         following.add(userId);
    }

    public void unfollow(int userId)
    {
         following.remove(userId);
    }


    public int createPost(int postId) {
        Post post = new Post(postId);
        userPosts.put(postId,post);
        Post next = head.getNext();
        head.setNext(post);
        post.setNext(next);
        post.setPrev(head);
        next.setPrev(post);
        return postId;
    }   
    
    public void deletePost(int postId)
    {  
        if(userPosts.containsKey(postId)){
            Post post = userPosts.get(postId);
            userPosts.remove(postId); 
            Post next = post.getNext();
            Post prev = post.getPrev();
    
            prev.setNext(next);
            next.setPrev(prev);
            return; 
        }
        System.out.println("User doesnt have a post with given it"); 
    }
 }


public class Facebook{
    public  HashMap<Integer, User> users;
    public static int timestamp;
    public static int PAGE_SIZE;
    public static int FEED_SIZE;
    FetchStrategy strategy;

    public Facebook(int PAGE_SIZE, int FEED_SIZE){
        this.users = new HashMap<Integer, User>();
        timestamp =0;
        Facebook.PAGE_SIZE = PAGE_SIZE;
        Facebook.FEED_SIZE = FEED_SIZE;
        users = new HashMap<>();
        strategy = new MostRecentPostStrategy();
    }

    public void createPost(int userid, int postId)
    {
        if(!users.containsKey(userid))
        {
            User user = new User(userid);
           users.put(userid, user);
        }
        User user = users.get(userid);
        user.createPost(postId);
        System.out.println("Successfully created the post " + postId);
    }


    public boolean deletePost(int userid, int postId)
    {
        if(users.containsKey(userid) )
        {     
              User user = users.get(userid);
              user.deletePost(postId);
              return true;
        }
         System.out.println("Invalid user " + userid);
        return false;
    }
     
    
    public void getNewsFeedPaginated(Integer userId, Integer pageNumber) {
        User user = users.get(userId);
        if (user == null)
            return;
        List<Integer> feed = strategy.getTopNPosts(userId, Integer.MAX_VALUE,users); // we fetch all the posts in this case
        Integer start = pageNumber * PAGE_SIZE -PAGE_SIZE;
        Integer end = Math.min(start + PAGE_SIZE, feed.size());
        if (start > end)
            return;

        //List<Integer> paginatedFeed = feed.subList(start, end);
        System.out.println("Page number " + pageNumber + " of user " + userId + " feed");
        for (int i = start; i < feed.size(); i++)
            System.out.println("Post " + (i + 1) + " " + feed.get(i));
    }
    
   
    public void getNewsFeed(int userId){
        if(!users.containsKey(userId))
        {
            System.out.println("invalid user");
            return;
        } 
        List<Integer> posts = strategy.getTopNPosts(userId,FEED_SIZE,users);
        for(int p:posts){
            System.out.println("Post is "+p);
        }
    }

    public void follow(int userId, int followeeId)
    {
        User user = users.get(userId);
        User followee = users.get(followeeId);
        if(user==null)
        {
             user = new User(userId);
            users.put(userId, user);
        }
        if(followee==null)
        {
             followee = new User(followeeId);
            users.put(followeeId, followee);  
        }
        user.follow(followeeId);
        System.out.println(userId+" is now following "+followeeId);
    }

    public void unfollow(int userId, int followeeId)
    {
        User user = users.get(userId);
        User followee = users.get(followeeId);
        if(user==null)
        {
             user = new User(userId);
            users.put(userId, user);
        }
        if(followee==null)
        {
             followee = new User(followeeId);
            users.put(followeeId, followee);  
        }
        user.unfollow(followeeId);
        System.out.println(userId+" is no longer following "+followeeId);
    }

}