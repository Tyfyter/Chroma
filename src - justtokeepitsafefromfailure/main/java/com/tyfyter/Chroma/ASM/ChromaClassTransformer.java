package com.tyfyter.Chroma.ASM;

import com.tyfyter.Chroma.Chroma;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class ChromaClassTransformer implements IClassTransformer{

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		if(transformedName.equals("net.minecraft.entity.player.EntityPlayer")){
			System.out.println("found EntityPlayer: "+name+";"+transformedName);
			try {
				ClassNode node = new ClassNode();
				ClassReader reader = new ClassReader(basicClass);
				reader.accept(node, 0);
				
				String Name = name.equals(transformedName) ?"onUpdate":"func_70071_h_";
				String Args = "()V";
				boolean found = false;
				for(MethodNode meth : node.methods){
					if(meth.name.equals(Name)&&meth.desc.equals(Args)){
						for (AbstractInsnNode INode : meth.instructions.toArray()) {
							if(INode.getOpcode() == PUTFIELD)if(((FieldInsnNode)INode).name == "noClip"){
								System.out.println("found putfield: "+INode.toString());
								InsnList l = new InsnList();
								l.add(new MethodInsnNode(INVOKESTATIC, Chroma.class.getCanonicalName(),"PostStupidSpectatorThing",name.equals(transformedName)?"(Lnet/minecraft/entity/player/EntityPlayer;)V":"EntityPlayer",false));
								meth.instructions.insert(l);
								found = true;
								System.out.println("hopefully didn't break putfield");
								break;
							}
						}
					}
					if(found)break;
				}
				
				ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
				node.accept(writer);
				return writer.toByteArray();
			} catch (Exception e) {
				// TOIGNORE: handle exception
				e.printStackTrace();
			}
		}
		return basicClass;
	}

}
