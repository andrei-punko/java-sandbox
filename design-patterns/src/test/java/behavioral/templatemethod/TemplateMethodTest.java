package behavioral.templatemethod;

import org.junit.Test;

/**
 * Шаблон, определяющий основу алгоритма, и позволяющий наследникам переопределять некоторые шаги алгоритма, не изменяя
 * его структуру в целом.
 */
public class TemplateMethodTest {

    @Test
    public void test() {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
