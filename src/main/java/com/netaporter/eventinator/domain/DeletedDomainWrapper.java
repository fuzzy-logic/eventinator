package com.netaporter.eventinator.domain;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * User: gawain
 */
public class DeletedDomainWrapper {


    public static <S,W> W createWrapper(final S source, final Class<W> wrapperClass) {

    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(wrapperClass);
    enhancer.setInterfaces(wrapperClass.getInterfaces());
    enhancer.setCallback(new MethodInterceptor() {

        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

            if ("getSource".equals(method.getName()) && Class.class.equals(method.getDeclaringClass())) {
                return source;
            }

            if (Arrays.asList(wrapperClass.getDeclaredMethods()).contains(method)) {
                return methodProxy.invokeSuper(proxy, args);
            }

            return methodProxy.invoke(source, args);
        }
    });

    return (W) enhancer.create();
}



    public class DeletedDomainWrapperCgLib {
        private boolean deleted = false;

        public boolean isDeleted() {
            return deleted;
        }

        public void markDeleted() {
            this.deleted = true;
        }
    }
}
