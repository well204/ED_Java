
public class Test<E> {
    protected transient Object[] vector; // vetor
    protected int size; // tamanho
    protected int capacity; // capacidade

    public Test() {
        this.vector = null;
        this.capacity = 0;
        this.size = 0;
    }

    protected void ensureCapacity(int minCapacity) {
        if (minCapacity <= 0) {
            return;
        }

        int newCapacity = Math.max(2 * this.capacity, minCapacity);
        Object[] newVector = new Object[newCapacity];

        if (this.capacity != 0) {
            // Cópia manual dos elementos anteriores
            for (int i = 0; i < this.size; i++) {
                newVector[i] = this.vector[i];
            }
        }

        this.vector = null; // "Desalocar" vetor anterior
        this.vector = newVector;
        this.capacity = newCapacity;
    }

    protected void increaseCapacity() {
        if (this.capacity == 0) {
            this.vector = new Object[2];
            this.capacity = 2;
        } else {
            int newCapacity = this.capacity * 2;
            Object[] newVector = new Object[newCapacity];

            // Cópia manual dos elementos anteriores
            for (int i = 0; i < this.size; i++) {
                newVector[i] = this.vector[i];
            }

            this.vector = null; // "Desalocar" vetor anterior
            this.vector = newVector;
            this.capacity = newCapacity;
        }
    }

    // Com deslocamento
    @SuppressWarnings("unchecked")
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }

        Object removed = this.vector[index];

        for (int k = index; index <= this.size - 2; k++) {
            this.vector[k] = this.vector[k + 1];
        }

        this.size--;

        // * NOTE: Compilador reclama, mas sabemos que o tipo está correto.
        return (E) removed;
    }

    // Com deslocamento
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(index);
        }

        // Caso não haja capacidade disponível
        if (this.size == this.capacity) {
            // Dobra a capacidade atual
            this.increaseCapacity();
        }

        for (int k = this.size - 1; k >= index; k--) {
            this.vector[k + 1] = this.vector[k];
        }

        this.vector[index] = element;
        this.size++; // Esqueci de adicionar durante a aula!!
    }

    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }

        // * NOTE: Compilador reclama, mas sabemos que o tipo está correto.
        return (E) this.vector[index];
    }

    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if (this.vector[i] == element) {
                return i;
            }
        }

        return -1;
    }

}