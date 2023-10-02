public class ArrayList<T> {
    protected  Object[] vector;
    protected int size;
    protected int capacity;

    public ArrayList() {
        this.vector = null; 
        this.capacity = 0;
        this.size = 0;
    }

    public int findLastOcurrence (T element) { // encontra a ultima ocorrencia de um elemento T, retoena -1 se nao encontrar
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.vector[i] == element)
                index = i;
        }
        return index;
    }

    public int numberOfOcurrence(T element) {
        int ocurrence = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.vector[i] == element)
                ocurrence++;
        }
        return ocurrence;
    }
    
    protected void ensureCapacity(int minCapacity) { // garantir capacidade do vetor, aumenta com um valor passado pelo teclado
        if (minCapacity <= 0) {
            return;
        }

        int newCapacity = Math.max(2 * this.capacity, minCapacity); 
        Object[] newVector = new Object[newCapacity];

        if (this.capacity != 0) {
            // Cópia manual dos elementos anteriores
            for (int i = 0; i < this.size; i++) 
                newVector[i] = this.vector[i];
        }

        this.vector = null; // "Desalocar" vetor anterior
        this.vector = newVector;
        this.capacity = newCapacity;
    }
 
    //FIXME: Versao que nao recebe parametro
    protected void increaseCapacity() { // aumentar capacidade
        if (this.capacity == 0) {
            this.vector = new Object[2];
            this.capacity = 2;
        }
        else {
            int newCapacity = this.capacity *  2;
            Object[] newVector = new Object[newCapacity];
            for (int i = 0; i < this.size; i++)
            newVector[i] = this.vector[i];
            this.vector = null;
            this.vector = newVector;
            this.capacity = newCapacity;
        }
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index >= this.size)
            throw new IndexOutOfBoundsException(index);
        Object removed = this.vector[index];

        for (int k = index; index <= this.size -2; k++) 
            this.vector[k] = this.vector[k+1];

        this.size--;
        return (T) removed;
    }

    public void add(int index, T element) throws IndexOutOfBoundsException {
        if (index > this.size)
            throw new IndexOutOfBoundsException(index);
        
        if (this.size == capacity)
            this.increaseCapacity();

        for (int k = this.size - 1; k >= index; k--) 
            this.vector[k + 1] = this.vector[k];
        
        this.vector[index] = element;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        return (T) this.vector[index];
    }

    public int indexOf(T element) {
        for (int i = 0; i < this.size; i++) {
            if (this.vector[i] == element)
            return i;
        }
        return -1;
    }

    public void showArray() {
        for (int i = 0; i < this.size; i++) 
            System.out.println(this.vector[i]);
    }
}