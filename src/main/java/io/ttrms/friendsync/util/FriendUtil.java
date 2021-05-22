package io.ttrms.friendsync.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FriendUtil {
    private static final Map<String,String> friendMap = new HashMap<>();
    private static final Map<String, String> friendUUIDMap = new HashMap<>();

    public static Boolean delFriend(String name){
        if(friendMap.containsKey(name)){
            friendMap.remove(name);
            friendUUIDMap.remove(name);
            return true;
        }else{
            return false;
        }
    }

    public static Boolean addFriend(String name, String alias, String UUID){
        if(friendMap.containsKey(name)){
            return false;
        }else{
            ClientUtils.read();
            if(UUID != null){
                try {
                    friendUUIDMap.put(name,HttpUtil.getUUIDFromName(name));
                } catch (IOException ignored) {}
            }else{
                friendUUIDMap.put(name,UUID);
            }

            friendMap.put(name,alias);
            ClientUtils.write();
            return true;
        }
    }

    public static Map<String, String> getFriendMap(){return friendMap;}

    public static Map<String, String> getFriendUUIDMap(){return friendUUIDMap;}
}
