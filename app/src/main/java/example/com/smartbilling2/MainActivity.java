package example.com.smartbilling2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by iman on 5/27/2018.
 */

public class MainActivity  extends Activity {
    private Context cont;
    private IntentFilter intentFilter;
    private Button signIn, signUp;
    private EditText username, password;
    private CheckBox showPassword;
    private MyProgressDialog newProgressDialog;
    private TextView errorPassword;
    private boolean isActivityPaused = false;
    private boolean showPassVal = false;
    private boolean finishedLoading = false;
    private int accountSpinnerVal = 0;
    private int dialogId = 0;
    private String postponedMessage = null;
    private String lastActivity;
    private String userNameVal = "", passwordVal = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login); // view login layout

//        lastActivity = getIntent().getExtras().getString("LastActivity");
        cont = this;

//		CheckForCoarseLocationPermission();

        InitializeUI();


    }
    private void InitializeUI(){
        // define all widget location
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("SMS_RECEIVED_ACTION");
        username = (EditText) findViewById(R.id.edittext_login_username);
        password = (EditText) findViewById(R.id.edittext_login_password);
        signIn = (Button) findViewById(R.id.button1_login_sumbit);
        showPassword = (CheckBox) findViewById(R.id.checkBox_showpassword_Login);
        signUp = (Button) findViewById(R.id.button_logIn_addacount);
        errorPassword = (TextView) findViewById(R.id.textView_errorPassword);

        TableLayout layout = (TableLayout)findViewById(R.id.TableLayout2);


//        for (Entity ent : Entity.getAllRecords(Account.class, false)) {
//            boolean avaliable = false;
//            for (int i = 0; accountsList.size() > i; i++)
//                if (accountsList.get(i).equals(ent.getCode()))
//                    avaliable = true;
//            if (!(avaliable))
//                accountsList.add(ent.getCode());
//        }

//             if (lastActivity.equals("signUp")) {
//
//            username.setText(getIntent().getExtras().getString("CurrentUser")
//                    .toString());
//            password.setText(getIntent().getExtras().getString("CurrentPassword")
//                    .toString());
//            password.requestFocus();
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(password, 0);
//        } else {
//            username.setText(userNameVal);
//            password.setText(passwordVal);
//            showPassword.setChecked(showPassVal);
//        }

         username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userNameVal = s.toString();
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

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());
                    showPassVal = false;
                } else {
                    // hide password
                    password.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());
                    showPassVal = true;
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                Intent myIntent = new Intent(MainActivity.this, SignUp.class);
                startActivity(myIntent);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {// check username and password
            @Override
            public void onClick(View v) {
                errorPassword.setText("");
//                BisanSalesApp.setAccount(accountSpinner.getSelectedItem().toString());
//                BisanSalesApp.setUserName(username.getText().toString());
//                BisanSalesApp.setUserPassword(password.getText().toString());

//                accountObj = Account.findAccount(BisanSalesApp.getAccount(), BisanSalesApp.getUserName(), password.getText().toString());

//                if (accountObj != null) {
//                    dialogId = 1;
                    newProgressDialog = new MyProgressDialog();
                    newProgressDialog.dialogStrings = new String[] {"Please Wait",
                            "loading"};
                    newProgressDialog.show(getFragmentManager(), "progressDialog");
                    newProgressDialog.setCancelable(false);
//                try {
//                    wait(1000);
//
                Log.i("before start ", " before start next activity");
                    MainActivity.this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    Intent myIntent = new Intent(MainActivity.this, BillingList.class);
                    startActivity(myIntent);
                Log.i("afetr  start ", " after  start next activity");
                    newProgressDialog.dismiss();

                    finish();
    //                } catch (InterruptedException e) {
    //                    e.printStackTrace();
    //                }



                //                    LoaderAsyncTask task = new LoaderAsyncTask();
//                    task.execute();
//                }
//                else {
//                    errorPassword.setText(cont.getResources().getString(
//                            R.string.incorrectPassword));
//                    username.setText("");
//                    password.setText("");
//                    username.requestFocus();
//                }
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signIn.callOnClick();
                }
                return false;
            }
        });
    }



    public void doPositiveClick() {
        //Do the cancel button clicked event:
    }

    public void doNegativeClick() {
        //Do the OK button clicked event
        if(dialogId == 1){
//            accountSpinner.setEnabled(false);
            username.setEnabled(false);
            password.setEnabled(false);
            signIn.setEnabled(false);
            showPassword.setEnabled(false);
            errorPassword.setEnabled(false);
        } else if(dialogId == 2){
            MainActivity.this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            Intent myIntent = new Intent(MainActivity.this, BillingList.class);
            startActivity(myIntent);
            finish();
        }
        dialogId = 0;
    }

    public void doNutralClick() {
        //Do the central button clicked event
    }

    @SuppressLint("ValidFragment")
    public class MyAlertDialogFragment extends DialogFragment {
        int numOfButtons;
        String[] dialogStrings;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if(dialogStrings.length == numOfButtons + 2){ //Plus the title and the message
                switch(numOfButtons){
                    case 1:
                        return new AlertDialog.Builder(getActivity())
                                .setTitle(dialogStrings[0])
                                .setMessage(dialogStrings[1])
                                .setNegativeButton(dialogStrings[2],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((MainActivity)getActivity()).doNegativeClick();
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
                                                ((MainActivity)getActivity()).doPositiveClick();
                                            }
                                        }
                                )
                                .setNegativeButton(dialogStrings[3],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((MainActivity)getActivity()).doNegativeClick();
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
                                                ((MainActivity)getActivity()).doPositiveClick();
                                            }
                                        }
                                )
                                .setNegativeButton(dialogStrings[3],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((MainActivity)getActivity()).doNegativeClick();
                                            }
                                        }
                                )
                                .setNeutralButton(dialogStrings[4],
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                ((MainActivity)getActivity()).doNutralClick();
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

    @SuppressLint("ValidFragment")
    public class MyProgressDialog extends DialogFragment {
        String[] dialogStrings;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            ProgressDialog dialog = new ProgressDialog(getActivity(), getTheme());
            dialog.setTitle(dialogStrings[0]);
            dialog.setMessage(dialogStrings[1]);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            return dialog;
        }
    }
}