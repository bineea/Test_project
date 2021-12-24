package test_project;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * JAVA�����޷���ȡ�������������ƣ�ֻ��ͨ���ֽ����ļ��ı��ر�������Ϣ��ȡ��
 * @author bineea
 *
 */

//                                                   ��������ClassReader
//                                                   ��
//                           �������� asm.jar �����������������������੤����ClassVisitor
//                           ��                       ��
//                           ��                       ��������ClassWriter
//          �������� Core API ��������
//          ��                �������� asm-util.jar
//          ��                ��
// ObjectWeb ASM ��������                �������� asm-commons.jar
//          ��
//          ��                �������� asm-tree.jar
//          �������� Tree API ��������
//                           �������� asm-analysis.jar
public class Test_asm {
	
	/**
     * ��ȡ�����������б�
     * 
     * @param clazz
     * @param m
     * @return
     * @throws IOException
     */
    public static List<String> getMethodParamNames(Class<?> clazz, Method m) throws IOException {
        try (InputStream in = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + ".class")) {
            return getMethodParamNames(in,m);
        }
 
    }
    public static List<String> getMethodParamNames(InputStream in, Method m) throws IOException {
        try (InputStream ins=in) {
            return getParamNames(ins,
                    new EnclosingMetadata(m.getName(),Type.getMethodDescriptor(m), m.getParameterTypes().length));
        }
 
    }
    /**
     * ��ȡ�������������б�
     * 
     * @param clazz
     * @param constructor
     * @return
     */
    public static List<String> getConstructorParamNames(Class<?> clazz, Constructor<?> constructor) {
        try (InputStream in = clazz.getResourceAsStream("/" + clazz.getName().replace('.', '/') + ".class")) {
            return getConstructorParamNames(in, constructor);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return new ArrayList<String>();
    }
    public static List<String> getConstructorParamNames(InputStream ins, Constructor<?> constructor) {
        try (InputStream in = ins) {
            return getParamNames(in, new EnclosingMetadata(constructor.getName(),Type.getConstructorDescriptor(constructor),
                    constructor.getParameterTypes().length));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return new ArrayList<String>();
    }
    /**
     * ��ȡ�������б�������
     * 
     * @param in
     * @param m
     * @return
     * @throws IOException
     */
    private static List<String> getParamNames(InputStream in, EnclosingMetadata m) throws IOException {
        ClassReader cr = new ClassReader(in);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);// ����EXPAND_FRAMES
        // ASM���ӿ���ʽ����
        List<MethodNode> methods = cn.methods;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < methods.size(); ++i) {
            List<LocalVariable> varNames = new ArrayList<LocalVariable>();
            MethodNode method = methods.get(i);
            // ��֤����ǩ��
            if (method.desc.equals(m.desc)&&method.name.equals(m.name)) {
//                System.out.println("desc->"+method.desc+":"+m.desc);
                List<LocalVariableNode> local_variables = method.localVariables;
                for (int l = 0; l < local_variables.size(); l++) {
                    String varName = local_variables.get(l).name;
                    // index-��¼����ȷ�ķ������ر���������(�������ر���˳����ܻᱻ���ҡ���index��¼��ԭʼ��˳��)
                    int index = local_variables.get(l).index;
                    if (!"this".equals(varName)) // �Ǿ�̬����,��һ��������this
                        varNames.add(new LocalVariable(index, varName));
                }
                LocalVariable[] tmpArr = varNames.toArray(new LocalVariable[varNames.size()]);
                // ����index����������ȷ����ȷ��˳��
                Arrays.sort(tmpArr);
                for (int j = 0; j < m.size; j++) {
                    list.add(tmpArr[j].name);
                }
                break;
 
            }
 
        }
        return list;
    }
 
    /**
     * �������ر��������Ͳ�������װ
     * @author xby Administrator
     */
    static class LocalVariable implements Comparable<LocalVariable> {
        public int index;
        public String name;
 
        public LocalVariable(int index, String name) {
            this.index = index;
            this.name = name;
        }
 
        public int compareTo(LocalVariable o) {
            return this.index - o.index;
        }
    }
 
    /**
     * ��װ���������Ͳ�������
     * 
     * @author xby Administrator
     */
    static class EnclosingMetadata {
              //method name
              public String name;
        // method description
        public String desc;
        // params size
        public int size;
 
        public EnclosingMetadata(String name,String desc, int size) {
                      this.name=name;
            this.desc = desc;
            this.size = size;
        }
    }
 
    public static void main(String[] args) throws IOException {
        for (Method m : Test_add.class.getDeclaredMethods()) {
            List<String> list = getMethodParamNames(Test_add.class, m);
            System.out.println(m.getName() + ":");
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println("------------------------");
        }
    }

}
