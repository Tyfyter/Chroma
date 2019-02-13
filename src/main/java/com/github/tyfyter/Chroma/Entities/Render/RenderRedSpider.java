package com.github.tyfyter.Chroma.Entities.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import com.github.tyfyter.Chroma.Entities.EntitySpiderRed;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedSpider extends RenderSpider<EntitySpiderRed>
{
    private static final ResourceLocation redSpiderTextures = new ResourceLocation("chroma:textures/entity/spider/red_spider.png");

    public RenderRedSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize *= 0.5F;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntitySpiderRed entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpiderRed entity)
    {
        return redSpiderTextures;
    }
}