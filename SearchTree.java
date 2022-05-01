package DecardTree;

public interface SearchTree{
    SearchTree add(int x) throws InterruptedException;
    SearchTree remove(int x);
    boolean contains(int x) throws InterruptedException;
}
