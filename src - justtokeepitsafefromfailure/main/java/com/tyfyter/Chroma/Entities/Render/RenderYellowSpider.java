package com.tyfyter.Chroma.Entities.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.tyfyter.Chroma.Entities.EntitySpiderYellow;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYellowSpider extends RenderSpider<EntitySpiderYellow>
{
    private static final ResourceLocation yellowSpiderTextures = new ResourceLocation("chroma:textures/entity/spider/yellow_spider.png");

    public RenderYellowSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize *= 1.25F;
    }
    
    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntitySpiderYellow entitylivingbaseIn, float partialTickTime)
    {
    	if(entitylivingbaseIn.getEntityWorld().getBlockState(entitylivingbaseIn.getPosition()).getBlock().getMaterial().isSolid()){
    		GlStateManager.scale(1.35f, 0.6f, 1.35f);
    	}else{
    		GlStateManager.scale(1.25f, 1.25f, 1.25f);
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpiderYellow entity)
    {
        return yellowSpiderTextures;
    }
}