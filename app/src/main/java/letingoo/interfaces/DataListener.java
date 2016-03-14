package letingoo.interfaces;

/**
 * Created by barcelona on 2015/9/19.
 * 处理通过网络接收的数据
 */
public interface DataListener<T> {

    public void onComplete(T result);
}
