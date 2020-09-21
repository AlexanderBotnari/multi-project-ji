package instruments;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

public class ClassTransformerAgent {

	public static void premain(String args, Instrumentation instrumentation) {
		System.out.println("Test Agent RUNNING!");
		instrumentation.addTransformer(new ClassFileTransformer() {
		      @Override
		       public byte[] transform(Module module, 
		                               ClassLoader loader, 
		                               String name, 
		                               Class<?> typeIfLoaded, 
		                               ProtectionDomain domain, 
		                               byte[] buffer) {
		        
		    	  if(name.split("/")[0].equals("entities")) {
		    		  System.err.println("Loaded class from entities : "+name);
		    		  
		    		  ClassPool pool = ClassPool.getDefault();
		    		  pool.appendClassPath(new LoaderClassPath(loader));
		    		  
		    		  try {
						CtClass ctClass = pool.get(name.replace("/", "."));
						
						if(ctClass.hasAnnotation(AddConstructor.class)) {
							System.err.println("This class will be transformed : "+name);
							
							StringBuilder arguments = new StringBuilder("public " + name.substring(name.indexOf("/") + 1) + "(");
							StringBuilder body = new StringBuilder("{\n");

							CtField[] fields = ctClass.getDeclaredFields();
							    for (CtField ctField : fields) {
									System.out.println("The field has modified code: "+ctField.getModifiers());
									if((ctField.getModifiers() & Modifier.PRIVATE) != 0) {
										System.err.println(">>>"+ctField.getName());
								
								arguments.append(ctField.getType().getName()).append(" ");
								arguments.append(ctField.getName()).append(", ");
                                                         body.append("this.").append(ctField.getName()).append(" = ").append(ctField.getName()).append(";\n");
							
							       }
								}
							    arguments.delete(arguments.lastIndexOf(","),arguments.lastIndexOf(",") + 2).append(") ");
								body.append("}\n");
								
								CtConstructor cstructor = CtNewConstructor.make(arguments.append(body).toString(),ctClass);

								ctClass.addConstructor(cstructor);
								
								System.err.println("Constructor : \n"+arguments+"Is created on the class: "+name);
								
								return ctClass.toBytecode();
						}
					} catch (NotFoundException | CannotCompileException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		  
		    	  }
		      
		      
		         return null;
		      }
		  });
		} 
		}
