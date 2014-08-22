package harvestmoon.handlers.events;

import static harvestmoon.HarvestMoon.handler;
import harvestmoon.network.PacketHandler;
import harvestmoon.network.PacketSetCalendar;
import harvestmoon.player.PlayerDataServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class FMLEvents {
    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        handler.getServer().getPlayerData(event.player).setBirthday();
        EntityPlayer player = event.player;
        if (player instanceof EntityPlayerMP) {
            PacketHandler.sendToClient(new PacketSetCalendar(handler.getServer().getCalendar().getDate()), (EntityPlayerMP) player);
            PlayerDataServer data = handler.getServer().getPlayerData(player);
            handler.getServer().getPlayerData(event.player).syncPlayerStats((EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent
    public void onTick(ServerTickEvent event) {
        if (event.phase != Phase.START) return;
        World world = MinecraftServer.getServer().getEntityWorld();
        if (world.getWorldTime() % 24000L == 1) {
            handler.getServer().getCalendar().newDay();
        }
    }
}
