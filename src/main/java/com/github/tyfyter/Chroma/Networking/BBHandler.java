package com.github.tyfyter.Chroma.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BBHandler implements IMessageHandler<BBPackage, IMessage> {
	@Override
	public IMessage onMessage(BBPackage message, MessageContext ctx) {
		try{
			System.out.println("not is "+Minecraft.getMinecraft().thePlayer.worldObj.isRemote);
			Minecraft.getMinecraft().thePlayer.setSize(message.toSend[0],message.toSend[1]);
			Minecraft.getMinecraft().thePlayer.eyeHeight = (float)(Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().maxY-Minecraft.getMinecraft().thePlayer.getEntityBoundingBox().minY)*0.85F;
		}catch(Exception e){}
		return null;
	}
	/*
	@Override
	public IMessage onMessage(BBPackage message, MessageContext ctx) {
		Minecraft.getMinecraft().thePlayer.setEntityBoundingBox(message.toSend);
		return null;
	}
	 */
}
