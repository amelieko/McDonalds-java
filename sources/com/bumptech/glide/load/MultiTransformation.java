package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;
import java.util.Arrays;
import java.util.Collection;

public class MultiTransformation<T> implements Transformation<T> {
    /* renamed from: id */
    private String f5573id;
    private final Collection<? extends Transformation<T>> transformations;

    @SafeVarargs
    public MultiTransformation(Transformation<T>... transformations) {
        if (transformations.length < 1) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.transformations = Arrays.asList(transformations);
    }

    public Resource<T> transform(Resource<T> resource, int outWidth, int outHeight) {
        Resource<T> previous = resource;
        for (Transformation<T> transformation : this.transformations) {
            Resource<T> transformed = transformation.transform(previous, outWidth, outHeight);
            if (!(previous == null || previous.equals(resource) || previous.equals(transformed))) {
                previous.recycle();
            }
            previous = transformed;
        }
        return previous;
    }

    public String getId() {
        if (this.f5573id == null) {
            StringBuilder sb = new StringBuilder();
            for (Transformation<T> transformation : this.transformations) {
                sb.append(transformation.getId());
            }
            this.f5573id = sb.toString();
        }
        return this.f5573id;
    }
}
