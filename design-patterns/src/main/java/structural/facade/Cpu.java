package structural.facade;

public class Cpu {

    public void freeze() {
        System.out.println("freeze");
    }

    public void jump(long position) {
        System.out.println("jump");
    }

    public void execute() {
        System.out.println("execute");
    }
}
