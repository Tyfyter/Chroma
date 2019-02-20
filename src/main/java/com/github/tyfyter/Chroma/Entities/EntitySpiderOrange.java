package com.github.tyfyter.Chroma.Entities;

import com.github.tyfyter.Chroma.Chroma;
import com.github.tyfyter.Chroma.ItemString;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpiderOrange extends EntitySpider implements IRangedAttackMob
{
    public EntitySpiderOrange(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F/0.875F, 0.9F/0.875F);
        this.tasks.addTask(4, new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F));
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue()*1.5f);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue()*0.95f);
    }
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_){
    
        EntityOrangeWeb entityarrow = new EntityOrangeWeb(this.worldObj, this, 32732);
        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        entityarrow.rotationPitch -= -20.0F;
        double d1 = target.posX + target.motionX - this.posX;
        double d2 = d0 - this.posY;
        double d3 = target.posZ + target.motionZ - this.posZ;
        float f = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
    
        entityarrow.setThrowableHeading(d1, d2 + (double)(f * 0.2F), d3, 0.75F, 0.0F);
        this.worldObj.spawnEntityInWorld(entityarrow);
    }

    protected Item getDropItem()
    {
        return Chroma.ItemStrings[ItemString.colornames.inverse().get("orange")];
    }
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount){
        boolean a = super.attackEntityFrom(source, amount);
        if(source.damageType.toLowerCase().contains("lava")){
            this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2));
            return false;
        }else if(source.damageType.toLowerCase().contains("fire")){
            return false;
        }
        return a;
    }
}