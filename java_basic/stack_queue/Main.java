/**
 * Класс СтекТипоОчереди создает очередь из нескольких стеков.
 * Основная идея:
 * создаем 2 стека, записываем значения в 1й стек, когда
 * нужно вытаскивать значения - копируем все во 2й стек. Таким
 * образом элементы 2го стека встанут задом наперед и будут
 * доступны в порядке, соответствующем обычной очереди.
 * Реализовано минимальное количество методов для проверки
 * алгоритма.
 */


import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        String[] test2 = {"a", "b", "c", "d", "e"};

        StackLikeQueue<Integer> testStack1 = new StackLikeQueue<>();
        StackLikeQueue<String> testStack2 = new StackLikeQueue<>();

        for (int i = 0; i < 5; i++) {
            testStack1.offer(test1[i]);
            testStack2.offer(test2[i]);
        }

        while (! testStack1.empty()) {
            System.out.print(testStack1.poll() + " ");
        }
        System.out.print("\n");

        while (! testStack2.empty()) {
            System.out.print(testStack2.poll() + " ");
        }
    }
}



class StackLikeQueue<E> {
    private Stack<E> stack;
    private Stack<E> reversedStack;

    /**
     * Логическая переменная будут использована, когда
     * пользователь запросит значения из СтекаТипоОчереди.
     * Пока пользователь не начал вытаскивать значения,
     * данные берутся из первого стека. После использования
     * poll() происходит копирование значений, и на запросы
     * о состоянии уже отвечает второй стек.
     */

    boolean stackAccessible = true;

    public StackLikeQueue() {
        stack = new Stack<E>();
        reversedStack = new Stack<E>();
    }

    public boolean offer(E e) {
        if (e == null || !stackAccessible) { return false; }
        stack.push(e);
        return true;
    }

    public E poll() {
        if (stackAccessible) {
            while (!stack.empty()) {
                E value = stack.pop();
                reversedStack.add(value);
            }
            stackAccessible = false;
        }
        if (reversedStack.empty()) { return null; }
        return reversedStack.pop();
    }

    public E peek() {
        if (stackAccessible) { return stack.peek(); }
        return reversedStack.peek();
    }

    public int size() {
        if (stackAccessible) { return stack.size(); }
        return reversedStack.size();
    }

    public boolean empty() {
        if (stackAccessible) { return stack.empty(); }
        return reversedStack.empty();
    }
}
