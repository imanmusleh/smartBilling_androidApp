package example.com.smartbilling2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by iman on 5/27/2018.
 */


public class WaterBills  extends Activity{

    private EditText usernameEdit , accountIDEdit;
    private Button viewResult ;
    private TextView result;
//    String username , accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_bill);


        accountIDEdit = (EditText) findViewById(R.id.accountIDBill);
        result = (TextView) findViewById(R.id.result);
        viewResult = (Button)findViewById(R.id.viewResult);

        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map reply = null;
                try {
                    reply = getServerResponse(  accountIDEdit.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<Map<String, String>> rows = (List<Map<String, String>>) reply
                        .get("rows");

                if (rows.size() != 0) {
                    result.setText(rows.toString());
                }
            }
        });
        }

    public  static HttpsURLConnection getServerConnection() throws Exception{
        URL url = new URL("http://localhost:8080/swagger-ui.html#!/water-bills-controller/WaterBillsUsingGET");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        return connection;
    }

    public static Map getServerResponse(  String accountId) throws Exception{
        Gson gson = new Gson();
        HttpsURLConnection connection =  getServerConnection();
        LinkedHashMap<String, Object> request = new LinkedHashMap<String, Object>();
        request.put("id",accountId);

        String message = gson.toJson(request);
        OutputStream out = connection.getOutputStream();
        message.getBytes();
        out.write(message.getBytes());
        out.close();
        // Get Response
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        Map reply = gson.fromJson(response.toString(), Map.class);
//        if (reply.get("key") != null  &&  !reply.get("key").toString() .equals( BisanSalesApp.getKey_ID()) )
//            BisanSalesApp.setKey_ID(reply.get("key").toString() );
        return reply;
    }
}



