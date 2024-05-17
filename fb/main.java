package fb;
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
 *  -- PAGE SIZE
 * 
 *  -- get_feed(user) => display all the posts of people the user follows in sorted in descending order
 *  -- create_post()
 *  -- user   
 
 *  User
 *  --  name
 *  --  id
 *   -- double linked list of type post
 *  --  following <set of type user> 
 *   
 *  -- addFollowee
 *  -- remove Followee
    -- create post
    --> remove post
    --> get all posts     => scrollable  (prev,next)   


    Post 
    -- post id
    -- timestamp
    -- post name
    -- get post id
    -- gettime 
    -- getPrev
    -- getNext
    --setPrev
    --setNext
 * 
 */



class Main{
    public static void main(String[] args)
    {   
        Facebook facebook = new Facebook(5,10);
        facebook.follow(1, 2);
        facebook.follow(1, 3);
        facebook.follow(1, 4);
        facebook.follow(1, 5);
        facebook.follow(1, 6);
        facebook.follow(1, 7);
        facebook.follow(1, 8);
        facebook.follow(1, 9);
        facebook.follow(1, 10);
        facebook.follow(1, 11);
        facebook.follow(1, 12);
        facebook.follow(1, 13);
        facebook.createPost(1, 1000);
        facebook.createPost(2, 1002);
        facebook.createPost(3, 1003);
        facebook.createPost(4, 1004);
        facebook.createPost(5, 1005);
        facebook.createPost(6, 1006);
        facebook.createPost(7, 1007);
        facebook.createPost(8, 1008);
        facebook.createPost(9, 1009);
        facebook.createPost(10, 1010);
        facebook.createPost(11, 1011);
        facebook.createPost(12, 1012);
        facebook.createPost(13, 1013);
        facebook.getNewsFeed(1);
        facebook.unfollow(1, 13);
        facebook.getNewsFeed(1);
        facebook.deletePost(12, 1012);
        facebook.getNewsFeed(1);
        facebook.getNewsFeedPaginated(1, 2);
        facebook.getNewsFeedPaginated(1, 5);
    }
}