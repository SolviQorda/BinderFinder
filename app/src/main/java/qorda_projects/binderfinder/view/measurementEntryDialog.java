package qorda_projects.binderfinder.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import qorda_projects.binderfinder.R;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by sorengoard on 12/03/2017.
 */

public class measurementEntryDialog extends DialogFragment{

    //update sharedPrefs
    private final String LOG_TAG = measurementEntryDialog.class.getSimpleName().toString();

    public interface measurementDialogListener {
        void buildGrid();
    }

    public EditText mBustSize;
//    public EditText mCupSize;

    private measurementDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //verify that host activity implements the callback interface
        try {
            //instantiate the listender so that we can send events to the host
            mListener = (measurementDialogListener) activity;
        } catch (ClassCastException e) {
            // if this happens the activity doesn't implement the interface so throw an exception
            throw new ClassCastException(activity.toString() + " must implement keywordsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.measurement_entry_dialog, null))
                .setPositiveButton(R.string.save_measurements, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog dialogBox = (Dialog) dialog;

                        mBustSize = (EditText) dialogBox.findViewById(R.id.chest_size_entry);
//                        mCupSize = (EditText) dialogBox.findViewById(R.id.cup_size_entry);

                        String bustSize = mBustSize.getText().toString();
                        Log.v(LOG_TAG, "string from bustSize textEntry" + bustSize);
//                        String cupSize = mCupSize.getText().toString();
                        if(!bustSize.isEmpty() && bustSize != null) {

//                        double bustSize = Double.parseDouble(mBustSize.getText().toString());
//                        double cupSize = Double.parseDouble(mCupSize.getText().toString());

                            SharedPreferences prefs = getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = prefs.edit();

                            editor.remove(getString(R.string.pref_bust_size_key));

//                        Log.v(LOG_TAG, "sharedPrefbust before putting new string:"
//                                + prefs.getString(getString(R.string.pref_bust_size_key), null));

                            editor.putString(getString(R.string.pref_bust_size_key), bustSize);

//                        editor.putString(getString(R.string.pref_cup_size_key), cupSize);

                            editor.commit();

//                        Log.v(LOG_TAG, "sharedPrefbust after putting new string:"
//                                + prefs.getString(getString(R.string.pref_bust_size_key), null));


                            mListener.buildGrid();

                        } else {
                            Toast noEntryToast = Toast.makeText(getContext(), getString(R.string.no_entry_toast), Toast.LENGTH_LONG);
                            noEntryToast.show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }


}
