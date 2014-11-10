package negotiation.db.bean.adaptor;

public class Container<T> {
    T sharedAttribute;

    public T getSharedAttribute() {
        return sharedAttribute;
    }

    public void setSharedAttribute(T sharedAttribute) {
        this.sharedAttribute = sharedAttribute;
    }

    public Container(T sharedAttribute) {
        this.sharedAttribute = sharedAttribute;
    }
}
