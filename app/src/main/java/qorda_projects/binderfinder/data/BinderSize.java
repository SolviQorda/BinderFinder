package qorda_projects.binderfinder.data;

/**
 * Created by sorengoard on 12/03/2017.
 */

public class BinderSize {
    public String mSizeName;
    public double mUpperBound;
    public double mLowerBound;

    public BinderSize(String sizeName, double upperBound, double lowerBound) {
        this.mSizeName = sizeName;
        this.mUpperBound = upperBound;
        this.mLowerBound = lowerBound;
    }

    public String getSizeName() {
        return mSizeName;
    }

    public void setSizeName(String sizeName) {
        mSizeName = sizeName;
    }

    public double getUpperBound() {
        return mUpperBound;
    }

    public void setUpperBound(double upperBound) {
        mUpperBound = upperBound;
    }

    public double getLowerBound() {
        return mLowerBound;
    }

    public void setLowerBound(double lowerBound) {
        mLowerBound = lowerBound;
    }
}
