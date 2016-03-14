package letingoo.net.handlers;

/**
 * Created by barcelona on 2015/9/19.
 */
public interface RespHandler<T, D> {

    public T parse(D data);
}
