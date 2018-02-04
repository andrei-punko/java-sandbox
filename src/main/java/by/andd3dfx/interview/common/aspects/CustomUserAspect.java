package by.andd3dfx.interview.common.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class CustomUserAspect {

    @Before("execution(* by.andd3dfx.interview.common.aspects.User.printHello(..))")
    public void beforePrintHello() {
        System.out.println("Action before printHello() method call");
    }

    @After("execution(* by.andd3dfx.interview.common.aspects.User.printHello(..))")
    public void afterPrintHello() {
        System.out.println("Action after printHello() method call");
    }

    @Around("execution(* by.andd3dfx.interview.common.aspects.User.setAge(..)) && args(value)")
    public void aroundPrintHello(ProceedingJoinPoint point, Integer value) throws Throwable {
        System.out.println("Action around setAge() method call - first part");
        System.out.println("Parameter is " + value);

        point.proceed();

        System.out.println("Action around setAge() method call - second part");
    }

    @Pointcut("execution(* by.andd3dfx.interview.common.aspects.User.setName(String))")
    public void setNamePointcut() {}

    @Before("setNamePointcut()")
    public void beforeSetName() {
        System.out.println("Action before setName() method call");
    }
}
