package com.example.nasaapi_20

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class UserAdapter(private val userList: ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var context: Context


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //replace with nasa items
        val img: ImageView = itemView.findViewById(R.id.imageView)
        val name: TextView = itemView.findViewById(R.id.textView)
        val constraint_row : ConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.constraint_row)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.user_items, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(context).load(user.url).into(holder.img)

        val stringBuilder = StringBuilder()
        stringBuilder.append(user.title)

        holder.name.text = stringBuilder

        val cont = holder.constraint_row.context
        holder.constraint_row.setOnClickListener {

            val intent = Intent(it.context, SecondActivity::class.java)

            intent.putExtra("image", user.url)
            intent.putExtra("title", user.title)
            intent.putExtra("explanation", user.explanation)

            it.context.startActivity(intent)


            Toast.makeText(cont, "the item ${user.title} is clicked", Toast.LENGTH_SHORT).show()
        }

        holder.constraint_row.setOnLongClickListener(View.OnLongClickListener {
            Toast.makeText(cont, "the item ${user.title} is long clicked", Toast.LENGTH_SHORT).show()

            return@OnLongClickListener true
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}