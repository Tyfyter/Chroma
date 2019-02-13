package com.github.tyfyter.Chroma.Entities;

import com.github.tyfyter.Chroma.Chroma;
import com.github.tyfyter.Chroma.ItemString;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpiderRed extends EntitySpider
{
    public EntitySpiderRed(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 0.45F);
        this.isImmuneToFire = true;
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
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue()*0.5f);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()*1.2f);
    }

    protected Item getDropItem()
    {
        return Chroma.ItemStrings[ItemString.colornames.inverse().get("red")];
    }
    public void onLivingUpdate(){
    	super.onLivingUpdate();
    	if(this.isBurning())this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2));
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 2;

                if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 4;
                }
                else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 6;
                }
                if(this.isBurning())i*=2;
                ((EntityLivingBase)entityIn).setFire(i);
                if(!entityIn.isImmuneToFire)entityIn.attackEntityFrom(new EntityDamageSource("inFire", this){public boolean isDifficultyScaled(){return false;}}.setFireDamage(), i);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}