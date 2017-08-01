package com.ttpai.hongbo.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ASMTest3 {

	public static void test1() throws Exception{
		ClassWriter cw = AsmUtil.createClassWriter("Test1");
		FieldVisitor fv= cw.visitField(Opcodes.ACC_PUBLIC, "age", "I", null, null);
		fv.visitEnd();
		
		MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "getAge","()I", null, null);
		
		mv.visitEnd();
		AsmUtil.outFile(cw.toByteArray(), "Test1.class");
		
	}
	
	public static void main(String[] args) throws Exception{
		test1();
	}
	
}
