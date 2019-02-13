package com.tyfyter.Chroma.Networking;
//I have no clue how this works, I just got it from TheXnator's tutorial
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;

public interface IChromaProxy {

	public void preInit();
	
	public boolean isSinglePlayer();
	
	public boolean isDedicatedServer();
	
	public void init();
	
	public void registerRenderInformation();
	
	public EntityPlayer getPlayerFromHandler(INetHandler handler);
}
