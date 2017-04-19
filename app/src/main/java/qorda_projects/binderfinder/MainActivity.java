package qorda_projects.binderfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import qorda_projects.binderfinder.controller.GridviewAdapter;
import qorda_projects.binderfinder.data.BinderUtility;
import qorda_projects.binderfinder.data.UserMeasurementsObject;
import qorda_projects.binderfinder.view.measurementEntryDialog;

public class MainActivity extends AppCompatActivity implements measurementEntryDialog.measurementDialogListener{

    private final String DIALOG_TAG = "new measurements dialog";
    private final String LOG_TAG = MainActivity.class.getSimpleName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView avatar = (ImageView) findViewById(R.id.avatar_image);
        avatar.setVisibility(View.INVISIBLE);

        //check for sharedPrefs - if no data, launch the dialog
        if(getExistingUserMeasurements(this).mBustSize == null) {
            getMeasurementsDialog();
        }
        else {
            //set the gridview adapter.
            buildGrid();
        }

        FloatingActionButton optionsFab = (FloatingActionButton) this.findViewById(R.id.options_fab);
        optionsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMeasurementsDialog();

            }
        });


    }

    public void getMeasurementsDialog() {
        DialogFragment measurementsDialog = new measurementEntryDialog();
        FragmentManager manager = this.getSupportFragmentManager();
        measurementsDialog.show(manager, DIALOG_TAG);

    }

    public void buildGrid() {
        final GridviewAdapter gridviewAdapter = new GridviewAdapter(
                this, BinderUtility.getOrderedListOfShops(),
                BinderUtility.binderListIterator(this),
                BinderUtility.getUrlList());
        Log.v(LOG_TAG, "binderlistIterator:" + BinderUtility.binderListIterator(this));

        GridView binderGridView = (GridView) this.findViewById(R.id.gridview_main_activity);
        binderGridView.setAdapter(gridviewAdapter);
        gridviewAdapter.notifyDataSetChanged();
    }

    //checks sharedPref and returns a userSize object.
    private UserMeasurementsObject getExistingUserMeasurements(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userBust = sharedPreferences.getString(context.getString(R.string.pref_bust_size_key), null);
        String userCup = sharedPreferences.getString(context.getString(R.string.pref_cup_size_key), null);
        UserMeasurementsObject returnMeasurements = new UserMeasurementsObject(userBust, userCup);
        return returnMeasurements;
    }

}
