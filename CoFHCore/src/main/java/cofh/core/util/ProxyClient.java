package cofh.core.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.Map;

public class ProxyClient extends Proxy {

    protected Map<ResourceLocation, Object> modelMap = new Object2ObjectOpenHashMap<>();

    // region HELPERS
    @Override
    public void addIndexedChatMessage(ITextComponent chat, int index) {

        if (chat == null) {
            Minecraft.getInstance().ingameGUI.getChatGUI().deleteChatLine(index);
        } else {
            Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(chat, index);
        }
    }

    @Override
    public PlayerEntity getClientPlayer() {

        return Minecraft.getInstance().player;
    }

    @Override
    public World getClientWorld() {

        return Minecraft.getInstance().world;
    }

    @Override
    public boolean isClient() {

        return true;
    }

    @Override
    public Object addModel(ResourceLocation loc, Object model) {

        return modelMap.put(loc, model);
    }

    @Override
    public Object getModel(ResourceLocation loc) {

        return modelMap.get(loc);
    }
    // endregion
}
