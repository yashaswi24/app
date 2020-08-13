package com.example.nairobi;

class csvsample {
    public   Double timestamp;
    public Double v1,v2,v3,e1,e2,e3;

    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public Double getV1() {
        return v1;
    }

    public void setV1(Double v1) {
        this.v1 = v1;
    }

    public Double getV2() {
        return v2;
    }

    public void setV2(Double v2) {
        this.v2 = v2;
    }

    public Double getV3() {
        return v3;
    }

    public void setV3(Double v3) {
        this.v3 = v3;
    }

    public Double getE1() {
        return e1;
    }

    public void setE1(Double e1) {
        this.e1 = e1;
    }

    public Double getE2() {
        return e2;
    }

    public void setE2(Double e2) {
        this.e2 = e2;
    }

    public Double getE3() {
        return e3;
    }

    public void setE3(Double e3) {
        this.e3 = e3;
    }

    @Override
    public String toString() {
        return "csvsample{" +
                "timestamp='" + timestamp + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", e1='" + e1 + '\'' +
                ", e2='" + e2 + '\'' +
                ", e3='" + e3 + '\'' +
                '}';
    }
}
