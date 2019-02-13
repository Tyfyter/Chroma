package com.github.tyfyter.Chroma;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.util.EnumHand;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandResultStats;
//import net.minecraft.util.EnumActionResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
public class ItemCommandPanelChargable extends Item {

	public int reacttime = 0;
	public int reaction = 0;
    ScriptEngineManager mgr = new ScriptEngineManager(null);
    ScriptEngine engine = mgr.getEngineByName("nashorn");
	protected ItemCommandPanelChargable() {
		//this.setCreativeTab(EpikMod.tabEpikMod);
		this.setMaxStackSize(1);
        /*this.addPropertyOverride(new ResourceLocation("state"), new IItemPropertyGetter()
        {
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
            	if(reacttime <= 0){
            		reaction = 0;
            	}else{
            		reacttime--;
            	}
            	return reaction;
            }
        });*/
	}
    private final CommandPanelLogic commandPanelLogic = new CommandPanelLogic()
    {
        /**
         * Sets the command.
         */
        public void setCommand(String command)
        {
            super.setCommand(command, 0);
        }
        /**
         * Returns the entity associated with the command sender. MAY BE NULL!
         */
        public Entity getCommandSenderEntity()
        {
            return null;
        }
		@Override
		public BlockPos getPosition() {
			return null;
		}
		@Override
		public Vec3 getPositionVector() {
			return null;
		}
		@Override
		public World getEntityWorld() {
			return null;
		}
		@Override
		public void updateCommand() {
			
		}
    };
	
	/*@Override
	public boolean itemInteractionForEntity(ItemStack stack, final EntityPlayer playerIn, EntityLivingBase target) {

        if (stack.getTagCompound() == null)
        {
            return false;
        }else{
        	NBTTagCompound compound = stack.getTagCompound();
        	if (!compound.hasKey("Commands"))
            {
        		reaction = 2;
        		reacttime = 20;
                return false;
            }
        	String command;
        	NBTTagCompound compound1;
        	final Entity entity = (Entity)target;
        	final Entity entity1 = (Entity)playerIn;
            final double d0 = entity.posX;
            final double d1 = entity.posY;
            final double d2 = entity.posZ;
            final double d3 = entity1.posX;
            final double d4 = entity1.posY;
            final double d5 = entity1.posZ;
            boolean lastcommandsuccess = false;

            ICommandSender icommandsender = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d0, d1, d2);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d0, d1, d2);
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender1 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender2 = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d0, d1, d2);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d0, d1, d2);
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender3 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandManager icommandmanager = null;
        	for(int i = 0; i < compound.getTagList("Commands", 10).tagCount(); i++){
        		compound1 = (NBTTagCompound)compound.getTagList("Commands", 10).get(i);
	        	command = compound1.getString("Command");
	        	if(command.contains("@t")){
	        		command = command.replaceAll("@t", icommandsender.getCommandSenderEntity().getUniqueID()+""/*.func_189512_bd()*//*);
	        	}
	            //entity.setCustomNameTag(stack.getDisplayName());
	            //entity.enablePersistence();
	            //--stack.stackSize;
	            try
	            {
	            	icommandmanager = Minecraft.getMinecraft().getIntegratedServer().getCommandManager();
		        }
	            catch(java.lang.NullPointerException gae){
	            	
	            }
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || (lastcommandsuccess == (compound1.getInteger("Conditional") == 1)))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			            		if(compound1.getInteger("Executor") == 1){
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender1.getName());
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command) > 0;
			            		}else{
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender.getCommandSenderEntity().getUniqueID()+"");
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender2, command) > 0;
			            		}
		            		}else{
			            		if(compound1.getInteger("Executor") == 1){
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender1.getName());
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command) > 0;
			            		}else{
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender.getCommandSenderEntity().getUniqueID()+"");
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender, command) > 0;
			            		}
		            		}
		            	}
		            	catch (Throwable var24)
		            	{
		            		//throw new CommandException("commands.execute.failed", new Object[] {s, entity.getName()});
		            	}
			        }
		            catch(java.lang.NullPointerException gae){
		            	
		            }
	            }else{
	        		reaction = 2;
	        		reacttime = 20;
	            	return false;
	            }
            }
	            
            return true;
        }
		//return super.itemInteractionForEntity(stack, playerIn, target);
	}*/
	
	@Override
	public int getMaxItemUseDuration(ItemStack itemStackIn) {
    	NBTTagCompound compound = itemStackIn.getTagCompound();
		if(compound.hasKey("MaxChargeDur")){
			return compound.getInteger("MaxChargeDur");
		}
		return 72000;
	}

	@Override
	public void onUsingTick(ItemStack itemStackIn, final EntityPlayer playerIn, int count) {
        if (itemStackIn.getTagCompound() == null)
        {
        }else{
        	NBTTagCompound compound = itemStackIn.getTagCompound();
        	if (!compound.hasKey("ChargeCommands"))
            {
        		return;
            }
        	String command;
        	NBTTagCompound compound1;
        	final Entity entity1 = (Entity)playerIn;
            final double d3 = entity1.posX;
            final double d4 = entity1.posY;
            final double d5 = entity1.posZ;
            int lastcommandsuccess = 0;

            ICommandSender icommandsender1 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity1.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender3 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity1.worldObj).getMinecraftServer();
                }
            };
            
            ICommandManager icommandmanager = null;
        	for(int i = 0; i < compound.getTagList("ChargeCommands", 10).tagCount(); i++){
        		compound1 = (NBTTagCompound)compound.getTagList("ChargeCommands", 10).get(i);
	        	command = compound1.getString("Command");
        	    Matcher m = Pattern.compile("\\%.*?\\%").matcher(command);
            	if(compound1.hasKey("Frequency")){
            		System.out.println("�hasit");
        			System.out.println((getMaxItemUseDuration(itemStackIn)-count)%compound1.getFloat("Frequency"));
            		if((getMaxItemUseDuration(itemStackIn)-count)%compound1.getFloat("Frequency") != 0){
            			System.out.println((getMaxItemUseDuration(itemStackIn)-count)%compound1.getFloat("Frequency"));
            			continue;
            		}
            	}
        	    while(m.find()){
		        	if(compound1.getBoolean("IntOnly")){
		        		int charge2 = (int)(((getMaxItemUseDuration(itemStackIn)-count)));
		        		String finstring = ""+charge2;
		        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
		        		NBTTagCompound targetNBT = new NBTTagCompound();
		        		playerIn.writeToNBT(entityPlayerNBT);
		        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
		        		try {
		        			try {
		        				finstring = (engine.eval("charge = "+charge2+"\n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1)))+"";
		        			} catch (NullPointerException e2) {
		    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
		    	        		e2.printStackTrace();
		        			}
						} catch (ScriptException e) {
							
						}
		        		if(finstring.contains(".")){
	        				finstring = finstring.substring(0, finstring.indexOf("."));
		        		}
		        		command = command.replaceFirst("\\%.*?\\%", finstring);
		        	}else{
		        		float charge = ((float)((getMaxItemUseDuration(itemStackIn)-count)));
		        		String finstring = ""+charge;
		        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
		        		playerIn.writeToNBT(entityPlayerNBT);
		        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
		        		try {
		        			try {
		        				finstring = engine.eval("lastsuccess = "+lastcommandsuccess+"\n charge = "+charge+"/20 \n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1))+"";
		        			} catch (NullPointerException e2) {
		    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
		    	        		e2.printStackTrace();
		        			}
						} catch (ScriptException e) {
							
						}
		        		command = command.replaceFirst("\\%.*?\\%", finstring);
		        	}
        	    }
        	    if(compound1.getBoolean("Output"))System.out.println(command);
	            //entity.setCustomNameTag(stack.getDisplayName());
	            //entity.enablePersistence();
	            //--stack.stackSize;
	            try
	            {
	            	icommandmanager = Minecraft.getMinecraft().getIntegratedServer().getCommandManager();
		        }
	            catch(java.lang.NullPointerException gae){
	            	
	            }
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || ((lastcommandsuccess>0) == (compound1.getInteger("Conditional") == 1)))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			        	        if(command.contains("@s")){
			        	        	command = command.replaceAll("@s", icommandsender1.getName());
			        	        }
			            		lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command);
		            		}else{
			        	        if(command.contains("@s")){
			        	        	command = command.replaceAll("@s", icommandsender1.getName());
			        	        }
			            		lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command);
		            		}
			            	if(compound1.getBoolean("Output"))System.out.println(command);
		            	}
		            	catch (Throwable var24)
		            	{
		            		//throw new CommandException("commands.execute.failed", new Object[] {s, entity.getName()});
		            	}
			        }
		            catch(java.lang.NullPointerException gae){
		            	
		            }
	            }
            }
        }
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, final EntityPlayer playerIn, int timeLeft) {
    	if(worldIn.isRemote) return;
        if (stack.getTagCompound() == null)
        {
        }else{
        	NBTTagCompound compound = stack.getTagCompound();
        	if (!compound.hasKey("Commands"))
            {
        		reaction = 2;
        		reacttime = 20;
            }
        	String command;
        	NBTTagCompound compound1;
        	final Entity entity1 = (Entity)playerIn;
            final double d3 = entity1.posX;
            final double d4 = entity1.posY;
            final double d5 = entity1.posZ;
            int lastcommandsuccess = 0;

            ICommandSender icommandsender1 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity1.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender3 = new ICommandSender()
            {
                public String getName()
                {
                    return entity1.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity1.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d3, d4, d5);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d3, d4, d5);
                }
                public World getEntityWorld()
                {
                    return entity1.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity1;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity1.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity1.worldObj).getMinecraftServer();
                }
            };
            
            ICommandManager icommandmanager = null;
        	for(int i = 0; i < compound.getTagList("Commands", 10).tagCount(); i++){
        		compound1 = (NBTTagCompound)compound.getTagList("Commands", 10).get(i);
	        	command = compound1.getString("Command");
        	    Matcher m = Pattern.compile("\\%.*?\\%").matcher(command);
        	    while(m.find()){
		        	if(compound1.getBoolean("IntOnly")){
		        		int charge2 = (int)(((getMaxItemUseDuration(stack)-timeLeft)));
		        		String finstring = "Critical Eval Failure";
		        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
		        		playerIn.writeToNBT(entityPlayerNBT);
		        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
		        		try {
		        			try {
		        				finstring = (engine.eval("lastsuccess = "+lastcommandsuccess+"\n charge = "+charge2+"\n PlayerName = '"+playerIn.getName()+"'\n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1)))+"";
		        			} catch (NullPointerException e2) {
		    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
		    	        		e2.printStackTrace();
		        			}
						} catch (ScriptException e) {
							System.out.println("�;ScriptException");
							e.printStackTrace();
						}
		        		if(finstring.contains(".")){
	        				finstring = finstring.substring(0, finstring.indexOf("."));
		        		}
		        		command = command.replaceFirst("\\%.*?\\%", finstring);
		        	}else{
		        		float charge = ((float)((getMaxItemUseDuration(stack)-timeLeft)));
		        		String finstring = "Critical Eval Failure";
		        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
		        		playerIn.writeToNBT(entityPlayerNBT);
		        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
		        		try {
		        			try {
		        				finstring = engine.eval("charge = "+charge+"/20 "+"\n PlayerName = '"+playerIn.getName()+"'\n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1))+"";
		        			} catch (NullPointerException e2) {
		    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
		    	        		e2.printStackTrace();
		        			}
						} catch (ScriptException e) {
							System.out.println("�;ScriptException");
							e.printStackTrace();
						}
		        		command = command.replaceFirst("\\%.*?\\%", finstring);
		        	}
        	    }
        	    if(compound1.getBoolean("Output"))System.out.println(command);
	            //entity.setCustomNameTag(stack.getDisplayName());
	            //entity.enablePersistence();
	            //--stack.stackSize;
	            try
	            {
	            	icommandmanager = Minecraft.getMinecraft().getIntegratedServer().getCommandManager();
		        }
	            catch(java.lang.NullPointerException gae){
	            	
	            }
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || ((lastcommandsuccess>0) == (compound1.getInteger("Conditional") == 1)))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			        	        if(command.contains("@s")){
			        	        	command = command.replaceAll("@s", icommandsender1.getName());
			        	        }
			            		lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command);
		            		}else{
			        	        if(command.contains("@s")){
			        	        	command = command.replaceAll("@s", icommandsender1.getName());
			        	        }
			            		lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command);
		            		}
		            	}
		            	catch (Throwable var24)
		            	{
		            		//throw new CommandException("commands.execute.failed", new Object[] {s, entity.getName()});
		            	}
			        }
		            catch(java.lang.NullPointerException gae){
		            	
		            }
	            }else{
	        		reaction = 2;
	        		reacttime = 20;
	            }
            }
        }
	}
	
	/*@Override
	public boolean onItemUse(ItemStack stack, final EntityPlayer playerIn, World worldIn, final BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {

		/*if(playerIn.isSneaking() && this.commandPanelLogic.tryOpenEditCommandBlock(playerIn)){
			return playerIn.capabilities.isCreativeMode;
		}//*//*
        //pos = pos.offset(facing);
        if (stack.getTagCompound() == null)
        {
            return false;
        }else{
        	NBTTagCompound compound = stack.getTagCompound();
        	if (!compound.hasKey("BlockCommands"))
            {
        		reaction = 2;
        		reacttime = 20;
                return false;
            }
        	String command;
        	NBTTagCompound compound1;
        	final Entity entity = (Entity)playerIn;
            final double d0 = entity.posX;
            final double d1 = entity.posY;
            final double d2 = entity.posZ;
            boolean lastcommandsuccess = false;

            ICommandSender icommandsender = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return pos;
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(pos.getX(), pos.getY(), pos.getZ());
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender1 = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d0, d1, d2);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d0, d1, d2);
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return true;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender2 = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return pos;
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(pos.getX(), pos.getY(), pos.getZ());
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandSender icommandsender3 = new ICommandSender()
            {
                public String getName()
                {
                    return entity.getName();
                }
                public IChatComponent getDisplayName()
                {
                    return entity.getDisplayName();
                }
                public void addChatMessage(IChatComponent component)
                {
                    //playerIn.addChatMessage(component);
                }
                public boolean canCommandSenderUseCommand(int permLevel, String commandName)
                {
                    return true;
                }
                public BlockPos getPosition()
                {
                    return new BlockPos(d0, d1, d2);
                }
                public Vec3 getPositionVector()
                {
                    return new Vec3(d0, d1, d2);
                }
                public World getEntityWorld()
                {
                    return entity.worldObj;
                }
                public Entity getCommandSenderEntity()
                {
                    return entity;
                }
                public boolean sendCommandFeedback()
                {
                    return false;
                }
                public void setCommandStat(CommandResultStats.Type type, int amount)
                {
                    entity.setCommandStat(type, amount);
                }
                public MinecraftServer getServer()
                {
                    return ((WorldServer)entity.worldObj).getMinecraftServer();
                }
            };
            
            ICommandManager icommandmanager = null;
        	for(int i = 0; i < compound.getTagList("BlockCommands", 10).tagCount(); i++){
        		compound1 = (NBTTagCompound)compound.getTagList("BlockCommands", 10).get(i);
	        	command = compound1.getString("Command");
	            //entity.setCustomNameTag(stack.getDisplayName());
	            //entity.enablePersistence();
	            //--stack.stackSize;
	            try
	            {
	            	icommandmanager = Minecraft.getMinecraft().getIntegratedServer().getCommandManager();
		        }
	            catch(java.lang.NullPointerException gae){
	            	
	            }
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || (lastcommandsuccess && compound1.getInteger("Conditional") == 1) || (!lastcommandsuccess && compound1.getInteger("Conditional") == 2))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			            		if(compound1.getInteger("Executor") == 1){
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command) > 0;
			            		}else{
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender2, command) > 0;
			            		}
		            		}else{
			            		if(compound1.getInteger("Executor") == 1){
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command) > 0;
			            		}else{
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender, command) > 0;
			            		}
		            		}
		            	}
		            	catch (Throwable var24)
		            	{
		            		//throw new CommandException("commands.execute.failed", new Object[] {s, entity.getName()});
		            	}
			        }
		            catch(java.lang.NullPointerException gae){
		            	
		            }
	            }else{
	        		reaction = 2;
	        		reacttime = 20;
	            	return false;
	            }
            }
            return true;
        }
	}*/

}
