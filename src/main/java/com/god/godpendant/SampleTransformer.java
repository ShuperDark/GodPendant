package com.god.godpendant;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import net.minecraft.launchwrapper.IClassTransformer;

public class SampleTransformer implements IClassTransformer {
	
	public static final String TARGET = "net.minecraft.entity.player.EntityPlayer";

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		
		if(!accept(transformedName))
			return bytes;
		
		ClassReader cr = new ClassReader(bytes);
		ClassWriter cw = new ClassWriter(1);
		ClassVisitor cv = new SampleAdapter.ClassAdapter(cw);
		cr.accept(cv, 0);
		
		return cw.toByteArray();
	}
	
	private boolean accept(String className) {
		return TARGET.equals(className);
	}

}
