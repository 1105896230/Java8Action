/**
 * Created by Administrator on 2016/8/14 0014.
 */
public interface Sized{
    int size();
    default boolean isEmpty(){
        return size()==0;
    }
}
