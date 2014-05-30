public class Some<T> implements Option<T> {

	private final T obj;

	public Some(T obj) {
		this.obj = obj;
	}

	public T getOrElse(T defObj) {
		return (this.obj == null) ? defObj : this.obj;
	}

}