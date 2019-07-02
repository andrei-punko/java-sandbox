package structural.facade;

public class Computer {

    private Cpu cpu;
    private Memory memory;
    private HardDrive hardDrive;
    private final long BOOT_ADDRESS = 10L;
    private final long BOOT_SECTOR = 21L;
    private final int SECTOR_SIZE = 1024;

    public Computer() {
        this.cpu = new Cpu();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}
