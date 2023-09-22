public class MemoryEntry {
    public byte value;
    public MemoryEntry previous;
    public MemoryEntry next;

    public MemoryEntry(byte value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    public int unsignedValue(){
        if(((value & (1 << 7)) >> 7) == 1) return ((int)value) + 256;
        return value;
    }
}
