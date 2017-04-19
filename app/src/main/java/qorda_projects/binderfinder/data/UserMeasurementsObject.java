package qorda_projects.binderfinder.data;

/**
 * Created by sorengoard on 05/04/2017.
 */

public class UserMeasurementsObject {
    public String mCupSize;
    public String mBustSize;


    public UserMeasurementsObject(String cupSize, String bustSize) {
        this.mBustSize = bustSize;
        this.mCupSize = cupSize;
    }

    public String getCupSize() {
        return mCupSize;
    }

    public void setCupSize(String cupSize) {
        mCupSize = cupSize;
    }

    public String getBustSize() {
        return mBustSize;
    }

    public void setBustSize(String bustSize) {
        mBustSize = bustSize;
    }


}
