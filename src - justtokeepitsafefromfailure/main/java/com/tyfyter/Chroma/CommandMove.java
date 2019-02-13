package com.tyfyter.Chroma;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.util.Vec3;

@SuppressWarnings("unused")
public class CommandMove extends CommandBase {

	@Override
	public String getCommandName() {
		return "motion";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 4)
        {
            throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
        }
        List<Entity> entities = PlayerSelector.matchEntities(sender, args[0], Entity.class);
	        if(!(args.length >= 5 && "true".equalsIgnoreCase(args[4]))){
	            for(int i = 0;i < entities.size();i++){
		            Entity entity = entities.get(i);
			        double normarg = parseDouble(args[1]);
			        double normarg1 = parseDouble(args[2]);
			        double normarg2 = parseDouble(args[3]);
		        	entity.motionX += normarg;
		        	entity.motionY += normarg1;
		        	entity.motionZ += normarg2;
		        	if(entity instanceof EntityPlayer){
		        		((EntityPlayer)entity).velocityChanged = true;
		        		((EntityPlayer)entity).isAirBorne = true;
		        	}
	        	}
	        }else{
	        	double normarg = 0;
	        	double normarg1 = 0;
	        	double normarg2 = 0;
	            if(args[1].startsWith("~")){
	            	normarg += sender.getPositionVector().xCoord;
	            	args[1] = args[1].substring(1);
	            }
	            if(args[2].startsWith("~")){
	            	normarg1 += sender.getPositionVector().yCoord;
	            	args[2] = args[2].substring(1);
	            }
	            if(args[3].startsWith("~")){
	            	normarg2 += sender.getPositionVector().zCoord;
	            	args[3] = args[3].substring(1);
	            }
	            normarg += parseDouble(args[1]);
	            normarg1 += parseDouble(args[2]);
		        normarg2 += parseDouble(args[3]);
	            double commandbase$coordinatearg3 = parseDouble(args[5]);
	            for(int i = 0;i < entities.size();i++){
	            	Entity entity = entities.get(i);
		            Vec3 vector = new Vec3(entity.posX-normarg, entity.posY-normarg1, entity.posZ-normarg2);
		            vector = vector.normalize();
		            vector = new Vec3(vector.xCoord * commandbase$coordinatearg3, vector.yCoord * commandbase$coordinatearg3, vector.zCoord * commandbase$coordinatearg3);
	                entity.motionX += vector.xCoord;
		        	entity.motionY += vector.yCoord;
		        	entity.motionZ += vector.zCoord;
		        	if(entity instanceof EntityPlayer){
		        		((EntityPlayer)entity).velocityChanged = true;
		        		((EntityPlayer)entity).isAirBorne = true;
		        	}
	                notifyOperators(sender, this, "commands.tp.success.coordinates", new Object[] {entity.getName(), vector.xCoord, vector.yCoord, vector.zCoord});
	                notifyOperators(sender, this, "commands.tp.success.coordinates", new Object[] {entity.getName(), normarg, normarg1, normarg2});
	            }
	        }
	}

}
