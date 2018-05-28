package example.com.smartbilling2;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 5/27/2018.
 */

public class SignUp extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    final List<String> permissionsList = new ArrayList<String>();
    private Button regist, cancel;
    private EditText customer, user, password;
    private TextView error;
    private String customerVal = "", userVal = "", passwordVal = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        InitializeUI();
//        CheckForCoarseLocationPermission2();

    }
    private void CheckForCoarseLocationPermission2() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {//(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            int permissionCheck = ContextCompat.checkSelfPermission(
                    SignUp.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat
                        .requestPermissions(
                                SignUp.this,
                                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                                1);
            } else {
                // TODO
            }
            int permissionCheck2 = ContextCompat.checkSelfPermission(
                    SignUp.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat
                        .requestPermissions(
                                SignUp.this,
                                new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                                2);
            } else {
                // TODO
            }
        }
    }
    private void CheckForCoarseLocationPermission() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {//(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            int permissionCheck = ContextCompat.checkSelfPermission(
                    SignUp.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat
                        .requestPermissions(
                                SignUp.this,
                                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                                1);
            } else {
                // TODO
            }
            int permissionCheck2 = ContextCompat.checkSelfPermission(
                    SignUp.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat
                        .requestPermissions(
                                SignUp.this,
                                new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                                2);
            } else {
                // TODO
            }



//				final int REQUEST_EXTERNAL_STORAGE = 3;
//				String[] permissionCheck3 = {
//						Manifest.permission.READ_EXTERNAL_STORAGE,
//						Manifest.permission.WRITE_EXTERNAL_STORAGE
//				};
//				int permission = ActivityCompat.checkSelfPermission(RegistrationPage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//				if (permission != PackageManager.PERMISSION_GRANTED) {
//					// We don't have permission so prompt the user
//					ActivityCompat.requestPermissions(
//							RegistrationPage.this,
//							permissionCheck3,
//							REQUEST_EXTERNAL_STORAGE
//					);
//				}

        }

    }


    private void InitializeUI(){
        regist = (Button) findViewById(R.id.button_reg_register2);
        cancel = (Button) findViewById(R.id.button_cancel_reg2);
        user = (EditText) findViewById(R.id.editText_reg_user2);
        password = (EditText) findViewById(R.id.editText_reg_password2);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on exit destroy and close database connection
                if (user.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty())
                    error.setText("Please enter username and password");
                else {

                    Intent myIntent = new Intent(SignUp.this, MainActivity.class);// go to activity log in
                    Bundle b = new Bundle();
//                    b.putString("CurrentUser",user.getText().toString() );
//                    b.putString("CurrentPassword",password.getText().toString() );
                    b.putString("LastActivity", "Login");
                    myIntent.putExtras(b);
                    finish();
                    startActivity(myIntent);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        customer.setText(customerVal);
//        user.setText(userVal);
//        password.setText(passwordVal);


        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    regist.callOnClick();
                }
                return false;
            }
        });


        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userVal = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {}
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordVal = s.toString();
            }
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {}
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.signup);
        InitializeUI();
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    //Handling dialogs from this activity::
    public void doPositiveClick() {
        //Do the cancel button clicked event:
    }

    public void doNegativeClick() {
//		  requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                  REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        //Do the OK button clicked event
    }

    public void doNutralClick() {
        //Do the central button clicked event
    }

    public static class MyAlertDialogFragment extends DialogFragment {
        int numOfButtons;
        String[] dialogStrings;
        public static MyAlertDialogFragment newInstance(int numOfButtons, String[] dialogStrings) {
            MyAlertDialogFragment frag = new MyAlertDialogFragment();
            Bundle args = new Bundle();
            args.putInt("numOfButtons", numOfButtons);
            args.putStringArray("dialogStrings", dialogStrings);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int numOfButtons = getArguments().getInt("numOfButtons");
            String[] dialogStrings = getArguments().getStringArray("dialogStrings");
            if(dialogStrings.length == numOfButtons + 2){ //Plus the title and the message
                switch(numOfButtons){
                    case 1:
                        return new AlertDialog.Builder(getActivity())
                                .setTitle(dialogStrings[0])
                                .setMessage(dialogStrings[1])
                                .setNegativeButton(dialogStrings[2],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doNegativeClick();
                                            }
                                        }
                                ).create();
                    case 2:
                        return new AlertDialog.Builder(getActivity())
                                .setTitle(dialogStrings[0])
                                .setMessage(dialogStrings[1])
                                .setPositiveButton(dialogStrings[2],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doPositiveClick();
                                            }
                                        }
                                )
                                .setNegativeButton(dialogStrings[3],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doNegativeClick();
                                            }
                                        }
                                ).create();
                    case 3:
                        return new AlertDialog.Builder(getActivity())
                                .setTitle(dialogStrings[0])
                                .setMessage(dialogStrings[1])
                                .setPositiveButton(dialogStrings[2],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doPositiveClick();
                                            }
                                        }
                                )
                                .setNegativeButton(dialogStrings[3],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doNegativeClick();
                                            }
                                        }
                                )
                                .setNeutralButton(dialogStrings[4],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((SignUp)getActivity()).doNutralClick();
                                            }
                                        }
                                ).create();
                    default:
                        return new AlertDialog.Builder(getActivity())
                                .setTitle("Error in creating dialog")
                                .setMessage("Contact system administrator to fix this: check the parameters sent to the dialog fragment")
                                .setPositiveButton("OK, will do",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                dialog.dismiss();
                                            }
                                        }
                                ).create();
                }
            } else {
                return new AlertDialog.Builder(getActivity())
                        .setTitle("Error in creating dialog")
                        .setMessage("Contact system administrator to fix this: check the parameters sent to the dialog fragment")
                        .setPositiveButton("OK, will do",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        ).create();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;
            case 2:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;
//	        case 2:
//	            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//	                //TODO
//	            }
//	            break;

            default:
                break;
        }
    }



}
