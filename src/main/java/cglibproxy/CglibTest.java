package cglibproxy;

public class CglibTest {
	public static void main(String[] args) {
		Station station = new Station();
		//创建代理对象
		Station proxy = (Station) new MyCglibProxy(station).getProxy();
		//调用代理对象的方法（实际上执行的是代理类中的intercept方法）
		proxy.sell();
	}

}
