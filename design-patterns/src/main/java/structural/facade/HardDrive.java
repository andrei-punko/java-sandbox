package structural.facade;

public class HardDrive {

    public byte[] read(long lba, int size) {
        System.out.println("read");
        return new byte[10];
    }
}
