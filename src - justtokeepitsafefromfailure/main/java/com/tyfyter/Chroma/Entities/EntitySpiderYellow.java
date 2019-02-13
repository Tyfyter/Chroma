package com.tyfyter.Chroma.Entities;

import java.util.*;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.tyfyter.Chroma.Chroma;
import com.tyfyter.Chroma.ItemString;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;

public class EntitySpiderYellow extends EntitySpider
{
    Multimap<String, AttributeModifier> SandRange;
    public EntitySpiderYellow(World worldIn)
    {
        super(worldIn);
        SandRange = HashMultimap.<String, AttributeModifier>create();
        SandRange.put("SandRange", new AttributeModifier(new UUID(191144, 1811475), "SandRange", -0.95f, 1));
        this.tasks.removeTask(new EntityAIWander(this, 0.8D));
        this.tasks.removeTask(new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.setSize(1.75F, 0.45F);
        this.targetTasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true){protected double func_179512_a(EntityLivingBase attackTarget){return (double)(4.0F + attackTarget.width);}public boolean shouldExecute(){EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();if (entitylivingbase == null){return false;}else if (!entitylivingbase.isEntityAlive()){return false;}return true;}public boolean continueExecuting(){EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();return entitylivingbase == null ? false : entitylivingbase.isEntityAlive();}});
        /*
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntitySpider.AISpiderAttack(this, EntityPlayer.class));
        this.tasks.addTask(4, new EntitySpider.AISpiderAttack(this, EntityIronGolem.class));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntitySpider.AISpiderTarget(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new EntitySpider.AISpiderTarget(this, EntityIronGolem.class));
        //*/
    }
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        if (net.minecraftforge.common.ForgeHooks.onLivingUpdate(this)) return;
    	this.onEntityUpdate();
        if (!this.worldObj.isRemote)
        {
            this.updateLeashedState();
            if (this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
            {
                this.setDead();
            }
            this.setBesideClimbableBlock(this.isCollidedHorizontally&&this.attackingPlayer!=null);
        }
        if (!this.worldObj.isRemote)
        {
            int i = this.getArrowCountInEntity();

            if (i > 0)
            {
                if (this.arrowHitTimer <= 0)
                {
                    this.arrowHitTimer = 20 * (30 - i);
                }

                --this.arrowHitTimer;

                if (this.arrowHitTimer <= 0)
                {
                    this.setArrowCountInEntity(i - 1);
                }
            }

            for (int j = 0; j < 5; ++j)
            {
                ItemStack itemstack = this.previousEquipment[j];
                ItemStack itemstack1 = this.getEquipmentInSlot(j);

                if (!ItemStack.areItemStacksEqual(itemstack1, itemstack))
                {
                    ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity(this, new S04PacketEntityEquipment(this.getEntityId(), j, itemstack1));

                    if (itemstack != null)
                    {
                        this.getAttributeMap().removeAttributeModifiers(itemstack.getAttributeModifiers());
                    }

                    if (itemstack1 != null)
                    {
                        this.getAttributeMap().applyAttributeModifiers(itemstack1.getAttributeModifiers());
                    }

                    this.previousEquipment[j] = itemstack1 == null ? null : itemstack1.copy();
                }
            }

            if (this.ticksExisted % 20 == 0)
            {
                this.getCombatTracker().reset();
            }
        }

        this.onLivingUpdate();
        double d0 = this.posX - this.prevPosX;
        double d1 = this.posZ - this.prevPosZ;
        float f = (float)(d0 * d0 + d1 * d1);
        float f1 = this.renderYawOffset;
        float f2 = 0.0F;
        this.prevOnGroundSpeedFactor = this.onGroundSpeedFactor;
        float f3 = 0.0F;

        if (f > 0.0025000002F)
        {
            f3 = 1.0F;
            f2 = (float)Math.sqrt((double)f) * 3.0F;
            f1 = (float)MathHelper.atan2(d1, d0) * 180.0F / (float)Math.PI - 90.0F;
        }

        if (this.swingProgress > 0.0F)
        {
            f1 = this.rotationYaw;
        }

        if (!this.onGround)
        {
            f3 = 0.0F;
        }

        this.onGroundSpeedFactor += (f3 - this.onGroundSpeedFactor) * 0.3F;
        this.worldObj.theProfiler.startSection("headTurn");
        f2 = this.updateDistance(f1, f2);
        this.worldObj.theProfiler.endSection();
        this.worldObj.theProfiler.startSection("rangeChecks");

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F)
        {
            this.prevRenderYawOffset -= 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F)
        {
            this.prevRenderYawOffset += 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch < -180.0F)
        {
            this.prevRotationPitch -= 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYawHead - this.prevRotationYawHead < -180.0F)
        {
            this.prevRotationYawHead -= 360.0F;
        }

        while (this.rotationYawHead - this.prevRotationYawHead >= 180.0F)
        {
            this.prevRotationYawHead += 360.0F;
        }

        this.worldObj.theProfiler.endSection();
        this.movedDistance += f2;
    }
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount){
    	if(source == DamageSource.inWall){
    		this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 10, 1));
        	if(this.getAbsorptionAmount()<10)this.setAbsorptionAmount(this.getAbsorptionAmount()+0.125f);
            this.tasks.removeTask(new EntityAIWander(this, 0.8D));
            this.tasks.removeTask(new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    		return false;
    	}
    	return super.attackEntityFrom(source, amount);
    }
    public void onLivingUpdate(){
    	super.onLivingUpdate();
        this.getAttributeMap().removeAttributeModifiers(SandRange);
    	if(this.attackingPlayer==null){
    		this.getAttributeMap().applyAttributeModifiers(SandRange);
    	}
    	if(this.getEntityWorld().getBlockState(new BlockPos(this.getPosition().getX(), this.getPosition().getY()-1, this.getPosition().getZ())).getBlock().getMaterial().isSolid()&&!this.getEntityWorld().getBlockState(this.getPosition()).getBlock().getMaterial().isSolid()&&this.attackingPlayer==null){
    		this.posY-=0.1f;
    	}
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()*0.9f);
    }

    protected Item getDropItem()
    {
        return Chroma.ItemStrings[ItemString.colornames.inverse().get("yellow")];
    }


    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 0;

                if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 2;
                }
                else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 3;
                    if(entityIn.isBurning())i+=1;
                }
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, i*10));
        		if(!entityIn.noClip){
        		    entityIn.setVelocity(0,-1,0);
        		    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Chroma.noClip.id, 1));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

}
