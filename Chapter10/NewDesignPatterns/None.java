public class None<T> implements Option<T> {

	public T getOrElse(T defObj) {
		return obj;
	}

}