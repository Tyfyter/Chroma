package com.github.tyfyter.Chroma.Networking;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NoClipHandler implements IMessageHandler<NoClipPackage, IMessage> {
	@Override
	public IMessage onMessage(NoClipPackage message, MessageContext ctx) {
		Minecraft.getMinecraft().thePlayer.noClip = message.toSend;
		return null;
	}

}
