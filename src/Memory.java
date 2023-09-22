
public class Memory {
    private MemoryEntry root;

    public Memory() {
        root = new MemoryEntry((byte) 0);
    }

    public MemoryEntry getRoot() {
        return root;
    }

    public void print(){
        System.out.println("MEMORY:");
        short address = 0;

        MemoryEntry iter = root;
        while (iter.previous != null){
            iter = iter.previous;
            address -= 1;
        }

        while(iter.next != null) {
            System.out.println(address + "->" + iter.unsignedValue());
            iter = iter.next;
            address += 1;
        }
        System.out.println(address + "->" + iter.unsignedValue());
    }
}
