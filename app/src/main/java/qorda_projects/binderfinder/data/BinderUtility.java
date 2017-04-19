package qorda_projects.binderfinder.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;

import qorda_projects.binderfinder.R;


/**
 * Created by sorengoard on 12/03/2017.
 */

public class BinderUtility {

    public static final String LOG_TAG = BinderUtility.class.getSimpleName().toString();

    public BinderUtility() {

    }

    //TODO: refactor this - the maths is broken.

    public static String getSizeFromUserChestValue(Double userChest, ArrayList<BinderSize> binderSizeArrayList) {
        String size = "";
        BinderSize bestFit = binderSizeArrayList.get(0);
        //check that their size isn't outside the maximum
        if (userChest > binderSizeArrayList.get(binderSizeArrayList.size()-1).getUpperBound()){
            bestFit = binderSizeArrayList.get(binderSizeArrayList.size()-1);
        } else if (userChest > binderSizeArrayList.get(0).getLowerBound()) {
            for (int i = 0; i < binderSizeArrayList.size(); i++) {
                BinderSize currentSize = binderSizeArrayList.get(i);
                if (userChest <= currentSize.getUpperBound() && userChest >= currentSize.getLowerBound()) {
                    bestFit = currentSize;
                } else if (userChest > currentSize.getUpperBound() && binderSizeArrayList.get(i + 1) != null) {
                    Double lowerDifference = userChest - currentSize.getUpperBound();
                    Double upperDifference = (binderSizeArrayList.get(i + 1).getLowerBound()) - userChest;
                    if (lowerDifference < upperDifference) {
                        bestFit = currentSize;
                    } else {
                        bestFit = binderSizeArrayList.get(i + 1);
                    }

                }
            }
        }
        size = size + bestFit.getSizeName();
        return size;
    }


    //iterates through each list, calls getsSize.
    //Array of shops is constant, no need to change. Case to change: provide users with ability to add/
    //delete shops from their list.

    public static String[] binderListIterator(Context context) {
        Double chestSize = getUserChestFromSharedPrefs(context);
        ArrayList<ArrayList> listOfBinders = buildListOfBinderSizes();
        String[] sizes = new String[listOfBinders.size()];
        if (chestSize != null && listOfBinders != null) {
            for (int i = 0; i < listOfBinders.size(); i++) {
                sizes[i] = getSizeFromUserChestValue(chestSize, listOfBinders.get(i));
                Log.v(LOG_TAG, "size for " + chestSize + " is:" + sizes[i]);
            }
        }
        return sizes;
    }

    public static Double getUserChestFromSharedPrefs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String userBust = prefs.getString(context.getString(R.string.pref_bust_size_key), null);
        String userCup = prefs.getString(context.getString(R.string.pref_cup_size_key), null);
//        UserMeasurementsObject returnMeasurements = new UserMeasurementsObject(userBust, userCup);

        //currently we only have binder vendors that size on chest - need to scale this up for vendors
        //offering cup size too.
        Double chestSize = 0.0;
//            String chestString = returnMeasurements.getBustSize();
        if(userBust!=null) {
            Log.v(LOG_TAG, "chestSize: " + userBust);
            chestSize = Double.parseDouble(userBust);
            //convert to cm
            chestSize = chestSize * 2.54;
        }
        return chestSize;

    }

    public static String[] getOrderedListOfShops() {
        String[] orderedListOfShops = new String[] {
                "Danaë", "XBODY UK", "Loveboat", "T-Kingdom", "gc2b", "Underworks", "Flavnt"
        };
        return orderedListOfShops;
    }


    public static ArrayList<ArrayList> buildListOfBinderSizes() {
        ArrayList<ArrayList>listOfBinders = new ArrayList<>();

        ArrayList<BinderSize> danaeSizes = new ArrayList<>();
        danaeSizes.add(new BinderSize("XS", 68.00, 75.00));
        danaeSizes.add(new BinderSize("S", 76.00, 83.00));
        danaeSizes.add(new BinderSize("M", 84.00, 101.00));
        danaeSizes.add(new BinderSize("L", 102.00, 109.00));
        danaeSizes.add(new BinderSize("XL", 110.00, 121.00));
        danaeSizes.add(new BinderSize("2XL", 122.00, 133.00));
        danaeSizes.add(new BinderSize("3XL", 134.00, 140.00));

        listOfBinders.add(danaeSizes);

        ArrayList<BinderSize> xbodySizes = new ArrayList<>();
        xbodySizes.add(new BinderSize("M", 91.00, 99.00));
        xbodySizes.add(new BinderSize("L", 101.00, 109.00));
        xbodySizes.add(new BinderSize("XL", 111.00, 119.00));
        xbodySizes.add(new BinderSize("XXL", 121.00, 129.00));
        xbodySizes.add(new BinderSize("XXXL", 132.00, 139.00));

        listOfBinders.add(xbodySizes);

        ArrayList<BinderSize> loveboatSizes = new ArrayList<>();
        loveboatSizes.add(new BinderSize("S", 71.00, 83.00));
        loveboatSizes.add(new BinderSize("M", 84.00, 91.00));
        loveboatSizes.add(new BinderSize("L", 92.00, 99.00));
        loveboatSizes.add(new BinderSize("XL", 100.00, 107.00));
        loveboatSizes.add(new BinderSize("XXL", 108.00, 115.00));
        loveboatSizes.add(new BinderSize("XXXL", 116.00, 122.00));

        listOfBinders.add(loveboatSizes);

        ArrayList<BinderSize> tkingdomSizes = new ArrayList<>();
        tkingdomSizes.add(new BinderSize("S", 71.00, 76.00));
        tkingdomSizes.add(new BinderSize("M", 78.7, 84.00));
        tkingdomSizes.add(new BinderSize("L", 86.00, 91.4));
        tkingdomSizes.add(new BinderSize("XL", 94.00, 99.00));
        tkingdomSizes.add(new BinderSize("XXL", 102.00, 107.00));

        listOfBinders.add(tkingdomSizes);

        ArrayList<BinderSize> gc2bSizes = new ArrayList<>();
        gc2bSizes.add(new BinderSize("XXS", 70.00, 76.00));
        gc2bSizes.add(new BinderSize("XS", 76.00, 81.00));
        gc2bSizes.add(new BinderSize("S", 81.00, 87.00));
        gc2bSizes.add(new BinderSize("M", 87.00, 92.00));
        gc2bSizes.add(new BinderSize("L", 92.00, 97.00));
        gc2bSizes.add(new BinderSize("XL", 97.00, 102.00));
        gc2bSizes.add(new BinderSize("2XL", 102.00, 107.00));
        gc2bSizes.add(new BinderSize("3XL", 107.00, 112.00));
        gc2bSizes.add(new BinderSize("4XL", 112.00, 119.00));
        gc2bSizes.add(new BinderSize("5XL", 119.00, 127.00));

        listOfBinders.add(gc2bSizes);

        ArrayList<BinderSize> underworksSizes = new ArrayList<>();
        underworksSizes.add(new BinderSize("S",79.00 ,84.00));
        underworksSizes.add(new BinderSize("M",86.00 ,91.00));
        underworksSizes.add(new BinderSize("L",94.00 ,102.00));
        underworksSizes.add(new BinderSize("XL",104.00 ,112.00));
        underworksSizes.add(new BinderSize("2XL",114.00 ,122.00));
        underworksSizes.add(new BinderSize("3XL",125.00 ,135.00));

        listOfBinders.add(underworksSizes);

        ArrayList<BinderSize> flavntSizes = new ArrayList<>();
        flavntSizes.add(new BinderSize("XS", 71.00, 79.00));
        flavntSizes.add(new BinderSize("S", 76.00, 84.00));
        flavntSizes.add(new BinderSize("M", 84.00, 89.00));
        flavntSizes.add(new BinderSize("L", 91.00, 94.00));
        flavntSizes.add(new BinderSize("XL", 96.50, 101.50));

        listOfBinders.add(flavntSizes);

        return listOfBinders;
    }

    public static String[] getUrlList() {
        String[] urls = {
                "https://www.danae.info/en/female-male/tops?___from_store=de",
                "https://xbody.co.uk/collections/compression-vests",
                "http://www.lesloveboat.com/shop/index.php?cPath=77",
                "http://www.t-kingdom.com/index.php/product",
                "https://www.gc2b.co/collections/all",
                "http://www.underworks.com/extreme-magicotton-sports-and-binding-bra",
                "http://www.flavnt.com/bareskin/"
        };
        return urls;
    }




}
