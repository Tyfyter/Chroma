package com.github.tyfyter.Chroma.Entities.Render;

import com.github.tyfyter.Chroma.Entities.EntitySpiderOrange;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOrangeSpider extends RenderSpider<EntitySpiderOrange>
{
    private static final ResourceLocation orangeSpiderTextures = new ResourceLocation("chroma:textures/entity/spider/orange_spider.png");

    public RenderOrangeSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize /= 0.875F;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntitySpiderOrange entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1/0.875F, 1/0.875F, 1/0.875F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpiderOrange entity)
    {
        return orangeSpiderTextures;
    }
}