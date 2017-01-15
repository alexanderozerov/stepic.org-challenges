package ascii;

public class AsciiCharSequence implements CharSequence{

    private byte[] ascii;

    public AsciiCharSequence(byte[] ascii){
        this.ascii = ascii;
    }

    @Override
    public int length() {
        return ascii.length;
    }

    @Override
    public char charAt(int index) {
        return (char) (ascii[index] & 0xFF);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        byte[] sub = new byte[end - start];
        int i, j;
        for(i = 0, j = start; i < end - start; i++, j++) {
            sub[i] = ascii[j];
        }
        return new AsciiCharSequence(sub);
    }

    @Override
    public String toString() {
        return new String(ascii);
    }
}