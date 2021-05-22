package io.ttrms.friendsync.util;

import java.util.HashMap;
import java.util.Map;

public class FriendUtil {
    private static Map<String,String> friendList = new HashMap<>();

    public static Boolean delFriend(String name){
        if(friendList.containsKey(name)){
            friendList.remove(name);
            return true;
        }else{
            return false;
        }
    }

    public static Boolean addFriend(String name, String alias){
        if(friendList.containsKey(name)){
            return false;
        }else{
            ClientUtils.read();
            friendList.put(name,alias);
            ClientUtils.write();
            return true;
        }
    }

    public static Map<String, String> getFriendList(){
        return friendList;
    }
}
