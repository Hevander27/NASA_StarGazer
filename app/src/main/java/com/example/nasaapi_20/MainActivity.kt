package com.example.nasaapi_20

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var userList = arrayListOf<User>()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private var startDate = dateFormat.format(Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000))
    private var endDate = dateFormat.format(Date(System.currentTimeMillis()))
    private val apiKey = "W8vActipgxiG2XqYxoeaNT2omRFhskxxOTjplgqZ"
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val backButton: Button = findViewById(R.id.backButton)
        val forwardButton: Button = findViewById(R.id.forwardButton)

        backButton.setOnClickListener {
            moveDates(-7)
            fetchData()
        }

        forwardButton.setOnClickListener {
            moveDates(7)
            fetchData()
        }

        moveDates(-7) // Initially set the date range to 7 days before the current date to the current date
        fetchData()
    }

    private fun moveDates(days: Int) {
        val calendar = Calendar.getInstance()

        // Adjust the start date based on the given days
        calendar.time = dateFormat.parse(startDate)!!
        calendar.add(Calendar.DAY_OF_YEAR, days)
        startDate = dateFormat.format(calendar.time)

        // Ensure that the end date does not go beyond the current date
        val currentDate = Calendar.getInstance()
        val parsedEndDate = dateFormat.parse(endDate)!!
        calendar.time = parsedEndDate
        calendar.add(Calendar.DAY_OF_YEAR, days)
        endDate = if (calendar > currentDate) {
            dateFormat.format(currentDate.time)
        } else {
            dateFormat.format(calendar.time)
        }
        userList.clear() // Clear the list to fetch data for the new date range
    }




    private fun fetchData() {
        val reqQueue: RequestQueue = Volley.newRequestQueue(this)
        val apiSample = "https://api.nasa.gov/planetary/apod?start_date=$startDate&end_date=$endDate&api_key=$apiKey"
        val request = JsonArrayRequest(Request.Method.GET, apiSample, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val item = response.getJSONObject(i)
                        val user = User(
                            item.getString("date"),
                            item.getString("explanation"),
                            item.getString("title"),
                            item.getString("url")
                        )
                        userList.add(user)
                    }

                    recyclerView?.layoutManager = LinearLayoutManager(this)
                    recyclerView?.adapter = UserAdapter(userList)
                } catch (e: JSONException) {
                    Log.e("API Call", "Error parsing JSON: ${e.message}")
                }
            },
            { error ->
                Log.e("API Call", "Error: $error")
            }
        )

        reqQueue.add(request)
    }
}
