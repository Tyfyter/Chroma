package com.github.tyfyter.Chroma.Entities.Render;

import com.github.tyfyter.Chroma.Entities.EntitySpiderBrown;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBrownSpider extends RenderSpider<EntitySpiderBrown>
{
    private static final ResourceLocation brownSpiderTextures = new ResourceLocation("chroma:textures/entity/spider/brown_spider.png");

    public RenderBrownSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize *= 0.2F;
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntitySpiderBrown entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.2F, 0.2F, 0.2F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpiderBrown entity)
    {
        return brownSpiderTextures;
    }
}