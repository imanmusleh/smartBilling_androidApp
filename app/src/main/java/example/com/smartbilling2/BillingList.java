package example.com.smartbilling2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by iman on 5/27/2018.
 */

public class BillingList extends Activity{

    private Button waterBills, electricBills, internetBills;
    String username , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_list);

        Log.i("Billing list  ", " before start next activity");


        waterBills = (Button) findViewById(R.id.waterBill);
        electricBills = (Button) findViewById(R.id.electricBill);
        internetBills = (Button) findViewById(R.id.internetBill);

        Log.i("2 ", " before start next activity");

        waterBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
                Log.i("3 ", " before start next activity");

                Intent intent = new Intent(getApplicationContext(), WaterBills.class);
//                bundle.putString("username","");
//                bundle.putString("password", password);
//                intent.putExtras(bundle);
                BillingList.this.overridePendingTransition(
                        R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                startActivity(intent);
                Log.i("4", " before start next activity");

            }
        });
        electricBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ElectricBills.class);
//                bundle.putString("username","");
//                bundle.putString("password", password);
//                intent.putExtras(bundle);
                BillingList.this.overridePendingTransition(
                        R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                startActivity(intent);

            }
        });
        internetBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
