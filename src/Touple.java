public class Touple<C,T> {
        private C first;
        private T second;

        public Touple(C first, T second) {
            this.first = first;
            this.second = second;
        }

        public C getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        public void setSecond(T second) {
            this.second = second;
        }

}
