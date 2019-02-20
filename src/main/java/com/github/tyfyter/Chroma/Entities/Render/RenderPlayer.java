package com.github.tyfyter.Chroma.Entities.Render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPlayer extends net.minecraft.client.renderer.entity.RenderPlayer
{
	/** this field is used to indicate the 3-pixel wide arms */
	private boolean smallArms;
	
	public RenderPlayer(RenderManager renderManager)
	{
		this(renderManager, false);
	}
	
	public RenderPlayer(RenderManager renderManager, boolean useSmallArms)
	{
		super(renderManager, useSmallArms);
	}
	protected void preRenderCallback(EntityPlayer entitylivingbaseIn, float partialTickTime)
	{
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
	}
}