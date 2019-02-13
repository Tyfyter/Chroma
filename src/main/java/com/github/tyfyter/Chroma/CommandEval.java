package com.github.tyfyter.Chroma;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;

public class CommandEval extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "eval";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     *  
     * @param sender The command sender that executed the command
     */
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.eval.usage";
    }

    /**
     * Callback when the command is invoked
     *  
     * @param sender The command sender that executed the command
     * @param args The arguments that were passed
     */
    @SuppressWarnings("unused")
	public void processCommand(ICommandSender sender, String[] args) throws CommandException{
    	String command;
        ICommandSender icommandsender1 = sender;
		//getScoreboard().getObjectivesForEntity(sender.getDisplayName().getUnformattedText())
        
        ICommandManager icommandmanager = null;
        boolean intonly = false;
        if(args[0].toLowerCase()=="intonly"){
        	args = RemoveAt(args, 0);
        	intonly = true;
        }
        	command = ArrtoSt(args);
    	    Matcher m = Pattern.compile("\\%.*?\\%").matcher(command);
        	String scores = "";
        	try{
        	Map<ScoreObjective, Score> sc = getScoreboard().getObjectivesForEntity(sender.getDisplayName().getUnformattedText());
        	for (Entry<ScoreObjective, Score> entry : sc.entrySet()) {
        		scores+=entry.getKey().getName()+":"+entry.getValue().getScorePoints()+",";
            }
        	}catch (Exception e) {
    			e.printStackTrace();
    		}
        	scores = scores.replaceAll(",$", "");
    	    while(m.find()){
	        	if(intonly){
	        		String finstring = "";
	        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
	        		sender.getCommandSenderEntity().writeToNBT(entityPlayerNBT);
	        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
	        		try {
	        			try {
	        				finstring = (Chroma.engine.eval("PlayerScores = {\n"+scores+"}\n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1)))+"";
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
	        		String finstring = "";
	        		NBTTagCompound entityPlayerNBT = new NBTTagCompound();
	        		sender.getCommandSenderEntity().writeToNBT(entityPlayerNBT);
	        		String entityplayerNBTStr = entityPlayerNBT.toString().replaceAll("(?<=[0-9])[sbdfL]?\\,", " ,").replaceAll("(?<=[0-9])[sbdfL]?\\}", " }").replaceAll("(?<=[0-9])[sbdfL]?\\]", " ]").replaceAll("(?<=[\\[,])[0-9]+:", "");
	        		try {
	        			try {
	        				finstring = Chroma.engine.eval("PlayerScores = {\n"+scores+"}\n PlayerNBT = "+entityplayerNBTStr+"\n"+m.group().substring(1, m.group().length()-1))+"";
	        			} catch (NullPointerException e2) {
	    	        		System.out.println("�;nullpointer;"+e2.getMessage()+";"+e2.getLocalizedMessage()+";");
	    	        		e2.printStackTrace();
	        			}
					} catch (ScriptException e) {
						
					}
	        		command = command.replaceFirst("\\%.*?\\%", finstring);
	        	}
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
	            try
	            {
	            	try
	            	{
	        	        if(command.contains("@s")){
	        	        	command = command.replaceAll("@s", icommandsender1.getName());
	        	        }
            			icommandmanager.executeCommand(sender, command);
	            	}
	            	catch (Throwable var24)
	            	{
	            		//throw new CommandException("commands.execute.failed", new Object[] {s, entity.getName()});
	            	}
		        }
	            catch(java.lang.NullPointerException gae){
	            	
	            }
    }

    protected static Scoreboard getScoreboard()
    {
        return MinecraftServer.getServer().worldServerForDimension(0).getScoreboard();
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return new java.util.ArrayList<String>(); //args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getAllUsernames()) : (args.length == 2 ? getListOfStringsMatchingLastWord(args, Potion.getPotionLocations()) : (args.length == 5 ? getListOfStringsMatchingLastWord(args, new String[] {"true", "false"}): null));
    }

    protected String[] getAllUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }
    
    private String ArrtoSt(String[] in){
    	String out = "";
    	boolean init = true;
    	for (String s : in) {
			out = out+(init?"":" ")+s;
			if(init)init = false;
		}
    	return out;
    }
    
    @SuppressWarnings("unchecked")
	private <T> T[] RemoveAt(T[] in, int index){
    	T[] out = (T[]) new Object[in.length-1];
    	int a = 0;
    	int b = 0;
    	for (T s : in) {
    		if(a++!=index){
    			out[b++] = s;
    		}
		}
    	return out;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *  
     * @param args The arguments that were passed
     */
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}
