package com.github.tyfyter.Chroma.Effects;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class NoFallEffect extends Potion {
	public ResourceLocation Sprite = new ResourceLocation("chroma","/textures/gui/noFall.png");
	public NoFallEffect(ResourceLocation location, boolean badEffect, int potionColor) {
		super(location, badEffect, potionColor);
	}
	
	@Override
	public boolean shouldRenderInvText(PotionEffect effect){
		return false;
	}
	
	@Override
	public boolean shouldRender(PotionEffect effect){
		return false;
	}
}
