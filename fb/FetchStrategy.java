
package fb;

import java.util.List;
import java.util.HashMap;

public interface FetchStrategy {
    List<Integer> getTopNPosts(int userId, int n, HashMap<Integer,User> users);
}