package by.andd3dfx.aspects;

import java.io.StringWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CustomUserAspect {

    private static final StringWriter writer = new StringWriter();

    @Before("execution(* by.andd3dfx.aspects.User.printHello(..))")
    public void beforePrintHello() {
        writer.write("Action before printHello() method call");
    }

    @After("execution(* by.andd3dfx.aspects.User.printHello(..))")
    public void afterPrintHello() {
        writer.append("Action after printHello() method call");
    }

    @Around("execution(* by.andd3dfx.aspects.User.setAge(..)) && args(value)")
    public void aroundPrintHello(ProceedingJoinPoint point, Integer value) throws Throwable {
        writer.append("Action around setAge() method call - first part");
        writer.append("Parameter is " + value);

        point.proceed();

        writer.append("Action around setAge() method call - second part");
    }

    @Pointcut("execution(* by.andd3dfx.aspects.User.setName(String))")
    public void setNamePointcut() {}

    @Before("setNamePointcut()")
    public void beforeSetName() {
        writer.append("Action before setName() method call");
    }

    public static StringWriter getWriter() {
        return writer;
    }
}
