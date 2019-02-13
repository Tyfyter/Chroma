package com.github.tyfyter.Chroma.Effects;

import com.github.tyfyter.Chroma.Chroma;
import com.github.tyfyter.Chroma.Networking.NoClipPackage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class SandCatEffect extends Potion {
	public ResourceLocation Sprite = new ResourceLocation("chroma","/textures/gui/SandCat.png");
	public SandCatEffect(ResourceLocation location, boolean badEffect, int potionColor) {
		super(location, badEffect, potionColor);
	}
}
