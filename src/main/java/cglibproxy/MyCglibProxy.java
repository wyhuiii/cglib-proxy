package cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//生成代理对象的类，实现MethodInterceptor接口
public class MyCglibProxy implements MethodInterceptor {
	
	//维护一个被代理类对象
	private Object target;
	
	//构造方法中传入被代理类对象，以便在getProxy时创建代理对象
	public MyCglibProxy(Object target) {
		this.target = target;
	}
	
	//生成代理对象
	public Object getProxy() {
		//创建代理对象的属性类
		Enhancer enhancer = new Enhancer();
		//设置父类（也就是要为哪个类创建代理类对象），参数是从被代理类对象中获取所属类
		enhancer.setSuperclass(target.getClass());
		//设置回调函数,即设置代理对象为当前对象
		enhancer.setCallback(this);
		//创建子类对象即代理对象
		return enhancer.create();
	}
	
	/**
	 * 拦截所有被代理类方法的调用
	 * obj:被代理类对象
	 * method:目标方法的反射对象
	 * args:目标方法的参数数组
	 * proxy:代理类对象
	 */
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		//调用被代理类对象方法前添加逻辑
		System.out.println("调用被代理对象方法前添加的逻辑");
		//调用真正的被代理类对象的方法，返回的是被代理类对象中方法的返回值，此处方法返回类型是void，所以返回的对象是null
		Object returnValue = method.invoke(target, args);
		//调用方法后添加的逻辑
		System.out.println("调用被代理类对象方法后添加的逻辑");
		return returnValue;
	}

}
