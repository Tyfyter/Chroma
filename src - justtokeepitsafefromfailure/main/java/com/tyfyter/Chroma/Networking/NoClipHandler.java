package com.tyfyter.Chroma.Networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NoClipHandler implements IMessageHandler<NoClipPackage, IMessage> {
	@Override
	public IMessage onMessage(NoClipPackage message, MessageContext ctx) {
		System.out.println("packet1:"+ctx.side+";"+message.toSend);
		Minecraft.getMinecraft().thePlayer.noClip = message.toSend;
		System.out.println("packet2:"+ctx.side+";"+message.toSend);
		return null;
	}

}
