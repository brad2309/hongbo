package com.ttpai.hongbo.asm;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class AsmUtil {

	/**
	 * 动态创建一个类，有一个无参数的构造函数
	 */
	public static ClassWriter createClassWriter(String className) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		// 声明一个类，使用JDK1.8版本，public的类，父类是java.lang.Object，没有实现任何接口
		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, className, null,Type.getInternalName(Object.class), null);

		// 初始化一个无参的构造函数
		MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>", "()V", null, null);
		// 这里请看截图
		constructor.visitVarInsn(Opcodes.ALOAD, 0);
		// 执行父类的init初始化
		constructor.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(Object.class), "<init>", "()V", false);
		// 从当前方法返回void
		constructor.visitInsn(Opcodes.RETURN);
		constructor.visitMaxs(1, 1);
		constructor.visitEnd();
		return cw;
	}
	
	public static void outFile(byte[] code,String filename) throws Exception{
		FileOutputStream fos = new FileOutputStream(filename);
		fos.write(code);
		fos.close();
	}
	
	public static byte[] generateProxyClass(Class<?> cls){
		ClassWriter cw = createClassWriter("MyProxy");
		
		MethodVisitor constructor = cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>", "(Lcom/ttpai/hongbo/asm/MyInvocationHandler;)V", null, null);
		constructor.visitVarInsn(Opcodes.ALOAD, 0);
		constructor.visitInsn(Opcodes.RETURN);
		constructor.visitMaxs(1, 1);
		constructor.visitEnd();
		
		for(Method m:cls.getMethods()){
			FieldVisitor fv= cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "x_"+m.getName(), "Ljava.lang.reflect.Method;", null, null);
			fv.visitEnd();
		}

		FieldVisitor fv= cw.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "a", "I", null, null);
		fv.visitEnd();
		MethodVisitor mv= cw.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null,null);  
		Label l0 = new Label();
		Label l1 = new Label();
		mv.visitTryCatchBlock(l0,l1, l1, "java/lang/Exception");  
		mv.visitLabel(l0);  
//		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0);
//		mv.visitVarInsn(Opcodes.BIPUSH, 77);
//		mv.visitInsn(Opcodes.ICONST_2);
		mv.visitFieldInsn(Opcodes.PUTSTATIC, "MyProxy", "a", "I");
		mv.visitLabel(l1);
//		mv.visitInsn(Opcodes.RETURN);
//		mv.visitMaxs(2, 0);
		mv.visitEnd();
		return cw.toByteArray();
	}
	public static void main(String[] args) throws Exception{
		byte[] code = generateProxyClass(IHello.class);
		outFile(code, "MyProxy.class");
	}
	
}
