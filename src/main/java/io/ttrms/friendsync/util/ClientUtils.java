package io.ttrms.friendsync.util;

import com.google.gson.*;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class ClientUtils {

    public static void init(){
        FutureUtil.init();
        RusherhackUtil.init();
        PyroUtil.init();
    }

    public static void read() {
        FutureUtil.read();
        RusherhackUtil.read();
        PyroUtil.read();
    }

    public static void write() {
        FutureUtil.write();
        RusherhackUtil.write();
        PyroUtil.write();
    }


    private static class FutureUtil {
        private static File futureFriends;
        private static Gson gson;
        private static Boolean exists = false;

        public static void init() {
            gson = new GsonBuilder().setPrettyPrinting().create();
            futureFriends = new File(System.getProperty("user.home") + "/Future/friends.json");
            if(futureFriends.exists())exists = true;
        }

        public static void read() {
            if(!exists) return;
            JsonArray array = null;
            try {
                array = gson.fromJson(new FileReader(futureFriends), JsonArray.class);
            } catch (FileNotFoundException ignored) {
            }
            for (int i = 0; i < Objects.requireNonNull(array).size(); i++) {
                JsonObject object = (JsonObject) array.get(i);
                FriendUtil.addFriend(object.get("friend-label").getAsString(), object.get("friend-alias").getAsString());
            }
        }

        public static void write() {
            if(!exists) return;
            JsonArray array = new JsonArray();
            for (Map.Entry<String, String> entry : FriendUtil.getFriendList().entrySet()) {
                JsonObject object = new JsonObject();
                object.add("friend-label", new JsonPrimitive(entry.getKey()));
                object.add("friend-alias", new JsonPrimitive(entry.getValue()));
                array.add(object);
            }
            try {
                FileWriter writer = new FileWriter(futureFriends);
                writer.write(gson.toJson(array));
                writer.flush();
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    private static class PyroUtil {
        private static File pyroFriends;
        private static Gson gson;
        private static Boolean enabled = true;
        private static JsonArray enemies;
        private static Boolean exists;

        public static void init() {
            gson = new GsonBuilder().setPrettyPrinting().create();
            pyroFriends = new File("pyro/friends.json");
            if(pyroFriends.exists()) exists=true;
        }

        public static void read() {
            if(!exists) return;
            JsonObject object = null;
            try {
                object = gson.fromJson(new FileReader(pyroFriends), JsonObject.class);
            } catch (FileNotFoundException ignored) {
            }
            enabled = object.get("enabled").getAsBoolean();
            enemies = object.getAsJsonArray("enemies");
            for (JsonElement element : object.getAsJsonArray("friends")) {
                FriendUtil.addFriend(element.getAsJsonObject().get("c").getAsString(), element.getAsJsonObject().get("0").getAsString());
            }
        }

        public static void write() {
            if(!exists)return;
            JsonObject object = new JsonObject();
            object.add("enabled", new JsonPrimitive(enabled));
            JsonArray friends = new JsonArray();
            for (Map.Entry<String, String> entry : FriendUtil.getFriendList().entrySet()) {
                JsonObject friendObj = new JsonObject();
                friendObj.add("c", new JsonPrimitive(entry.getKey()));
                friendObj.add("0", new JsonPrimitive(entry.getValue()));
                friends.add(friendObj);
            }
            object.add("enemies", enemies);
            try {
                FileWriter writer = new FileWriter(pyroFriends);
                writer.write(gson.toJson(object));
                writer.flush();
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    private static class RusherhackUtil {
        private static File rusherFriends;
        private static Gson gson;
        private static Boolean exists;

        public static void init() {
            gson = new GsonBuilder().setPrettyPrinting().create();
            rusherFriends = new File("rusherhack/friends.json");
            if(rusherFriends.exists()) exists = true;
        }

        public static void read() {
            if(!exists) return;
            JsonArray array = null;
            try {
                array = gson.fromJson(new FileReader(rusherFriends), JsonArray.class);
            } catch (FileNotFoundException ignored) {
            }
            for (int i = 0; i < Objects.requireNonNull(array).size(); i++) {
                JsonObject object = (JsonObject) array.get(i);
                FriendUtil.addFriend(object.get("name").getAsString(), object.get("name").getAsString());
            }
        }

        public static void write() {
            if(!exists) return;
            JsonArray array = new JsonArray();
            for (Map.Entry<String, String> entry : FriendUtil.getFriendList().entrySet()) {
                JsonObject object = new JsonObject();
                object.add("name", new JsonPrimitive(entry.getKey()));
                array.add(object);
            }
            try {
                FileWriter writer = new FileWriter(rusherFriends);
                writer.write(gson.toJson(array));
                writer.flush();
                writer.close();
            } catch (IOException ignored) {
            }
        }

    }
}
