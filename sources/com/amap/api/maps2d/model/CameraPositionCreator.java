package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps2d.model.b */
class CameraPositionCreator implements Creator<CameraPosition> {
    CameraPositionCreator() {
    }

    /* renamed from: a */
    public CameraPosition createFromParcel(Parcel parcel) {
        float readFloat = parcel.readFloat();
        float readFloat2 = parcel.readFloat();
        float readFloat3 = parcel.readFloat();
        float readFloat4 = parcel.readFloat();
        return new CameraPosition(new LatLng((double) readFloat2, (double) readFloat3), parcel.readFloat(), readFloat4, readFloat);
    }

    /* renamed from: a */
    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}
