package com.github.tyfyter.Chroma.Networking;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BBPackage implements IMessage {
    //AxisAlignedBB toSend;
    float[] toSend = new float[]{};
    public BBPackage() {}
    public BBPackage(float w, float h){
        toSend = new float[]{w,h};
    }
    /*public BBPackage(AxisAlignedBB toSend) {
        this.toSend = toSend;
    }*/
    @Override public void toBytes(ByteBuf buf) {
        // Writes the int into the buf
        buf.writeFloat(toSend[0]);
        buf.writeFloat(toSend[1]);
    }
    
    @Override public void fromBytes(ByteBuf buf) {
        toSend = new float[]{buf.readFloat(),buf.readFloat()};
    }
    /*@Override public void toBytes(ByteBuf buf) {
        // Writes the int into the buf
        buf.writeDouble(toSend.minX);
        buf.writeDouble(toSend.minY);
        buf.writeDouble(toSend.minZ);
        buf.writeDouble(toSend.maxX);
        buf.writeDouble(toSend.maxY);
        buf.writeDouble(toSend.maxZ);
    }

    @Override public void fromBytes(ByteBuf buf) {
        toSend = new AxisAlignedBB(buf.readDouble(),buf.readDouble()+1,buf.readDouble(),buf.readDouble(),buf.readDouble()+1,buf.readDouble());
    }*/
}
