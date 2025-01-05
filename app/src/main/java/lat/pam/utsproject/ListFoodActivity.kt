package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fields = R.drawable::class.java.fields
        foodList = fields.mapNotNull { field ->
            val resId = field.getInt(null)
            val resourceName = resources.getResourceEntryName(resId)
            if (!resourceName.contains("ic_launcher")) { // Hindari ikon launcher
                Food(
                    title = resourceName.replace("_", " ").capitalize(),
                    description = "Deskripsi untuk $resourceName",
                    imageResId = resId
                )
            } else {
                null
            }
        }

        adapter = FoodAdapter(foodList) { selectedFood ->
            // Intent ke OrderActivity dengan membawa data nama makanan
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("food_name", selectedFood.title)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
