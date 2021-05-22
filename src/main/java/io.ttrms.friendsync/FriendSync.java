package io.ttrms.friendsync;

import io.ttrms.friendsync.util.ClientUtils;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "friendsync", name = "FriendSync", version = "1.1")
public class FriendSync {




    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientUtils.init();
        ClientCommandHandler.instance.registerCommand(new FriendSyncCommand());
    }

}

