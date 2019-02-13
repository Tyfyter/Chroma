package com.github.tyfyter.Chroma;

import javax.script.ScriptException;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandResultStats;
//import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.Minecraft;

@SuppressWarnings("unused")
public class ItemCommandPanel extends Item {

	public int reacttime = 0;
	public int reaction = 0;
	protected ItemCommandPanel() {
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
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public Vec3 getPositionVector() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public World getEntityWorld() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void updateCommand() {
			// TODO Auto-generated method stub
			
		}
    };
	
	@Override
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
            int lastcommandsuccess = 0;

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
            //ScoreObjective scoreobjective = this.getObjective(, true);
            Scoreboard scoreboard = MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
            //scoreboard.getValueFromObjective(s1, scoreobjective);
            System.out.println(scoreboard.getObjectivesForEntity(playerIn.getName()));
        	for(int i = 0; i < compound.getTagList("Commands", 10).tagCount(); i++){
        		compound1 = (NBTTagCompound)compound.getTagList("Commands", 10).get(i);
	        	command = compound1.getString("Command");
        	    Matcher m = Pattern.compile("\\%.*?\\%").matcher(command);
            	String scores = "";
            	try{
            	Map<ScoreObjective, Score> sc = CommandEval.getScoreboard().getObjectivesForEntity(icommandsender1.getDisplayName().getUnformattedText());
            	for (Entry<ScoreObjective, Score> entry : sc.entrySet()) {
            		scores+=entry.getKey().getName()+":"+entry.getValue().getScorePoints()+",";
                }
            	}catch (Exception e) {
        			e.printStackTrace();
        		}
            	scores = scores.replaceAll(",$", "");
        	    while(m.find()){
	        		String finstring = "Critical Eval Failure";
	        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
	        		NBTTagCompound targetNBT = new NBTTagCompound();
	        		playerIn.writeToNBT(entityPlayerNBT);
	        		target.writeToNBT(targetNBT);
	        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
	        		String targetNBTStr = targetNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
	        		try {
	        			try {
	        				finstring = Chroma.eval("PlayerScores = {\n"+scores+"}\n lastsuccess = "+lastcommandsuccess+"\n PlayerNBT = "+entityplayerNBTStr+" \n TargetNBT = "+targetNBTStr+"\n"+m.group().substring(1, m.group().length()-1))+"";
	        			} catch (NullPointerException e2) {
	    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
	    	        		e2.printStackTrace();
	        			}
					} catch (ScriptException e) {
    	        		e.printStackTrace();
					}
	        		command = command.replaceFirst("\\%.*?\\%", finstring);
        	    }
	        	if(command.contains("@t")){
	        		command = command.replaceAll("@t", icommandsender.getCommandSenderEntity().getUniqueID()+""/*.func_189512_bd()*/);
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
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || ((lastcommandsuccess>0) == (compound1.getInteger("Conditional") == 1)))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			            		if(compound1.getInteger("Executor") == 1){
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender1.getName());
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command);
			            		}else{
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender.getCommandSenderEntity().getUniqueID()+"");
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender2, command);
			            		}
		            		}else{
			            		if(compound1.getInteger("Executor") == 1){
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender1.getName());
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command);
			            		}else{
			        	        	if(command.contains("@s")){
			        	        		command = command.replaceAll("@s", icommandsender.getCommandSenderEntity().getUniqueID()+"");
			        	        	}
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender, command);
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
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, final EntityPlayer playerIn, World worldIn, final BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {

		/*if(playerIn.isSneaking() && this.commandPanelLogic.tryOpenEditCommandBlock(playerIn)){
			return playerIn.capabilities.isCreativeMode;
		}*/
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
            int lastcommandsuccess = 0;

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
	            if(icommandmanager != null && (compound1.getInteger("Conditional") == 0 || ((lastcommandsuccess>0) && compound1.getInteger("Conditional") == 1) || (!(lastcommandsuccess>0) && compound1.getInteger("Conditional") == 2))){
		            try
		            {
		            	try
		            	{
			            	if(compound1.getBoolean("Output") == false){
			            		if(compound1.getInteger("Executor") == 1){
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender3, command);
			            		}else{
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender2, command);
			            		}
		            		}else{
			            		if(compound1.getInteger("Executor") == 1){
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender1, command);
			            		}else{
			            			lastcommandsuccess = icommandmanager.executeCommand(icommandsender, command);
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
	}

}
