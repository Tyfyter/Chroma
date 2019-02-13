package com.github.tyfyter.Chroma.Networking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommonProxy implements IChromaProxy {
	
	public void registerRenders() {
		// TODO Auto-generated method stub
		
	}
	public World getClientWorld() {
		return null;
		// TODO Auto-generated method stub
		
	}
	public EntityPlayer getClientPlayer() {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSinglePlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDedicatedServer() {
		// TODO Auto-generated method stub
		return MinecraftServer.getServer().isDedicatedServer();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRenderInformation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityPlayer getPlayerFromHandler(INetHandler handler) {
		if(handler instanceof NetHandlerPlayServer)return ((NetHandlerPlayServer)handler).playerEntity;
		return null;
	}
	
}
