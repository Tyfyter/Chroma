package com.tyfyter.Chroma.Entities.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.Entity;

import com.tyfyter.Chroma.Entities.EntitySpiderBlack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBlackSpider extends RenderSpider<EntitySpiderBlack>
{
    private static final ResourceLocation blackSpiderTextures = new ResourceLocation("chroma:textures/entity/spider/black_spider.png");

    public RenderBlackSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.removeLayer(this.layerRenderers.get(this.layerRenderers.size()-1));
    }
    @Override
    public void doRender(EntitySpiderBlack entity, double x, double y, double z, float entityYaw, float partialTicks) {
    	GlStateManager.pushMatrix();
    	GlStateManager.enableAlpha();
    	GlStateManager.enableBlend();
    	super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySpiderBlack entity)
    {
        return blackSpiderTextures;
    }
}