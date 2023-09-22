public class Pointer {
    private Memory memory;

    private short address;
    private MemoryEntry ptr;

    public Pointer(Memory memory, short address){
        this.memory = memory;
        this.address = address;
        this.ptr = memory.getRoot();

        if(address == 0) return;

        if(isNegative(address)){
            for (int i = 0; i < -address; i++) {
                if(ptr.previous == null){
                    ptr.previous = new MemoryEntry((byte) 0);
                    ptr.previous.next = ptr;
                }
                ptr = ptr.previous;
            }
        }else {
            for (int i = 0; i < address; i++) {
                if(ptr.next == null){
                    ptr.next = new MemoryEntry((byte) 0);
                    ptr.next.previous = ptr;
                }
                ptr = ptr.next;
            }
        }
    }

    public void move(short memoryDistance){
        this.address += memoryDistance;

        if(isNegative(memoryDistance)){
            for (int i = 0; i < -memoryDistance; i++) {
                if(ptr.previous == null){
                    ptr.previous = new MemoryEntry((byte) 0);
                    ptr.previous.next = ptr;
                }
                ptr = ptr.previous;
            }
        }else {
            for (int i = 0; i < memoryDistance; i++) {
                if(ptr.next == null){
                    ptr.next = new MemoryEntry((byte) 0);
                    ptr.next.previous = ptr;
                }
                ptr = ptr.next;
            }
        }
    }

    public void increment(){
        ptr.value += 1;
    }

    public void decrement(){
        ptr.value -= 1;
    }

    public boolean isNegative(short s) {
        return (s >> 15) == -1;
    }

    public void print() {
        System.out.print(ptr.unsignedValue() + " char=" + (char)ptr.unsignedValue());
    }

    public void set(byte b) {
        ptr.value = b;
    }

    public int get() {
        return ptr.value;
    }
}
