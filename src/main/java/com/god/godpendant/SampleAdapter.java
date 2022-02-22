package com.god.godpendant;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;

public class SampleAdapter {
	
	public static class ClassAdapter extends ClassVisitor {
		
		public ClassAdapter(ClassVisitor cv) {
			super(Opcodes.ASM4, cv);
		}
		
		@Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
			String srgMethod = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(name, name, desc);
			String srgDesc = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(desc);
			
            if("func_70665_d".equals(srgMethod) || "(Lnet/minecraft/util/DamageSource;F)V".equals(srgDesc)){
                return new MethodAdapter(super.visitMethod(access, name, desc, signature, exceptions));
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
		
	}
	
	public static class MethodAdapter extends MethodVisitor {
		
		public MethodAdapter(MethodVisitor mv) {
			super(Opcodes.ASM4, mv);
		}
		
		@Override
		public void visitMethodInsn(int opcode, String owner, String methodName, String desc,
                boolean itf) {
			super.visitVarInsn(Opcodes.ALOAD, 1);
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "com/god/godpendant/SampleHooks", "NodamageEntity", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getObjectType("net/minecraft/util/DamageSource")), false);
			
			super.visitMethodInsn(opcode, owner, methodName, desc, itf);
		}
	}

}