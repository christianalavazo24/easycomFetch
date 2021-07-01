package ucucite.edu.lss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    //    private lateinit var textView: TextView
    private var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        title = "Order Details"
        requestQueue = Volley.newRequestQueue(this)
        jsonParse()
    }

    private fun jsonParse() {

        //Models
        val modelsmessages = ArrayList<OrderModels>()

        //Recycler View
        var push_notify_recyclerview: RecyclerView = findViewById(R.id.recycler_view)


        var layoutManager = LinearLayoutManager(this)
        push_notify_recyclerview.layoutManager = layoutManager

//        val queue = Volley.newRequestQueue(this)


        val url = "https://stg.letsstartshopping.com/api/path/order-record?page=1&limit=20&key=_all&module=request&type=0"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                try {

                    val jsonArray = response.getJSONObject("data") //parent
                    val SecondArray = jsonArray.getJSONArray("items") // child


                    for (i in 0 until SecondArray.length()) {

                        val notification_array = SecondArray.getJSONObject(i)

                        val fetch_message = notification_array.getString("order_no")
                        val fetch_name = notification_array.getString("name")
                        val fetch_concat_item = notification_array.getString("concat_item")
                        val fetch_total_amt = notification_array.getString("total_amt")


                        modelsmessages.add(OrderModels("$fetch_message","$fetch_name","$fetch_concat_item","$fetch_total_amt"))
                        var adapter = OrderAdapter(modelsmessages)
                        recycler_view.adapter = adapter

                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}