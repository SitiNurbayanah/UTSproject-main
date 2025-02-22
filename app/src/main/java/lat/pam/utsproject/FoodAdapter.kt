package lat.pam.utsproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private val foodList: List<Food>,
    private val onItemClick: (Food) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
        val foodDescription: TextView = itemView.findViewById(R.id.foodDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.foodImage.setImageResource(food.imageResId)
        holder.foodTitle.text = food.title
        holder.foodDescription.text = food.description

        holder.itemView.setOnClickListener {
            onItemClick(food)
        }
    }

    override fun getItemCount(): Int = foodList.size
}
