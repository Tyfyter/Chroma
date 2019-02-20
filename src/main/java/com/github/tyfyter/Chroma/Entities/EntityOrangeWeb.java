package com.github.tyfyter.Chroma.Entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class EntityOrangeWeb extends EntityThrowable
{
	public EntityOrangeWeb(World worldIn)
	{
		super(worldIn);
	}
	
	public EntityOrangeWeb(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}
	
	public EntityOrangeWeb(World worldIn, double x, double y, double z)
	{
		super(worldIn, x, y, z);
	}
	public  EntityOrangeWeb(World worldIn, EntityLivingBase shooter, float velocity){
		super(worldIn, shooter);
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity * 1.5F, 1.0F);
	}
	
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition p_70184_1_)
	{
		if (p_70184_1_.entityHit != null)
		{
			int i = 3;
			
			if (p_70184_1_.entityHit.isImmuneToFire)
			{
				i = 0;
			}
			if(this.getThrower()==null)return;
			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
			Vec3 vel = new Vec3(this.getThrower().posX-p_70184_1_.entityHit.posX,this.getThrower().posY-p_70184_1_.entityHit.posY,this.getThrower().posZ-p_70184_1_.entityHit.posZ);
			vel = vel.normalize();
			vel = vel.add(vel.add(vel.add(vel)));
			p_70184_1_.entityHit.addVelocity(vel.xCoord,vel.yCoord,vel.zCoord);
		}
		
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
}