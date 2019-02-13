package com.tyfyter.Chroma.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
	
	public void registerRenders() {
		RenderManager renderman = Minecraft.getMinecraft().getRenderManager();
		
	}
	public World getClientWorld() {
		return null;
		// TODO Auto-generated method stub
		
	}
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
		// TODO Auto-generated method stub
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSinglePlayer() {
		return Minecraft.getMinecraft().isSingleplayer();
	}

	@Override
	public boolean isDedicatedServer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRenderInformation() {
		// TODO Auto-generated method stub
		
	}
	
}
