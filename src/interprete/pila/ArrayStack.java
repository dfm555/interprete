package interprete.pila;

import interprete.comun.EmptyStructureException;



public class ArrayStack<E> implements Stack<E> {

    private E[] data;
    private int size;

    public ArrayStack() {
        data = (E[]) (new Object[1]);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected boolean isFull() {
        return size == data.length;
    }

    @Override
    public E peek() throws EmptyStructureException {
        if (isEmpty()) {
            throw new EmptyStructureException();
        }

        return data[size - 1];
    }

    @Override
    public E pop() throws EmptyStructureException {
        if (isEmpty()) {
            throw new EmptyStructureException();
        }
        size--;

        return data[size];
    }

    @Override
    public void push(E target) {
        if (isFull()) {
            stretch();
        }

        data[size] = target;
        size++;
    }

    protected void stretch() {
        E[] newData = (E[]) (new Object[2 * data.length]); // Warning

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public void fix() {
        if (!isFull()) {
            E[] newData = (E[]) (new Object[size]);

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    public int getSize() {
        return size;
    }

    public E get(int index) throws ArrayIndexOutOfBoundsException {
        return data[index - 1];
    }

    public void clear() {
        data = (E[]) (new Object[1]);
        size = 0;
    }

    public boolean contains(E target) {
        for (int i = 0; i < size; i++) {
            if (target.equals(data[i])) {
                return true;
            }
        }

        return false;
    }
}
