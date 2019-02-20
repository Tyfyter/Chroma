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

public class EntitySpiderBrown extends EntitySpider
{
    public EntitySpiderBrown(World worldIn)
    {
        super(worldIn);
        this.setSize(0.28F, 0.18F);
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue()*0.5f);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()*1.3f);
    }

    protected Item getDropItem()
    {
        return Chroma.ItemStrings[ItemString.colornames.inverse().get("brown")];
    }
}