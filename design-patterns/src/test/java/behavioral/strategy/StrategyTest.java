package behavioral.strategy;

import org.junit.Test;

/**
 * По типу клиента (или по типу обрабатываемых данных) выбрать подходящий алгоритм, который следует применить. Если
 * используется правило, которое не подвержено изменениям, нет необходимости обращаться к шаблону «стратегия».
 */
public class StrategyTest {

    @Test
    public void test() {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
