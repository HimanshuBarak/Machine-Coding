package fb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MostRecentPostStrategy implements FetchStrategy {
      @Override
      public List<Integer> getTopNPosts(int userId, int N,HashMap<Integer,User> users){
        User user = users.get(userId);
        HashSet<Integer> following = user.getFollowing();
        PriorityQueue<Post> posts = new PriorityQueue<Post>((a,b)->(Integer.compare(b.getTime(),a.getTime())));
        for(int id:following)
        {
            User followee = users.get(id);
            Post head = followee.getHead();
            Post tail = followee.getTail();
          
            if(head.getNext()!=tail) // this basically means there are some post present
            {
                Post p = head.getNext();
                while(p.getId()!=-1)
                {
                    posts.add(p);
                    p = p.getNext();
                }
            }
        }
        int n=0;
        List<Integer> feedPosts = new LinkedList<Integer>();
        // now our priority queue basically contains all the info about 
        while(!posts.isEmpty() && n<N)
        {
            Post p = posts.poll();
            n++;
            feedPosts.add(p.getId());  
        }
        return feedPosts;
    }
}
