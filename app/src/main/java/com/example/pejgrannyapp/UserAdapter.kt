package com.example.pejgrannyapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors


class UserAdapter : ListAdapter<User, UserAdapter.UserAdapter>(UserViewHolder())
{
    class UserAdapter(view : View): RecyclerView.ViewHolder(view)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter
    {
        val inflater = LayoutInflater.from(parent.context)
        return com.example.pejgrannyapp.UserAdapter.UserAdapter(inflater.inflate(
            R.layout.activity_userlayout,parent,false))
    }

    override fun onBindViewHolder(holder: UserAdapter, position: Int)
    {
        val user = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.txtNameUser).text = user.Name
        holder.itemView.findViewById<TextView>(R.id.passwordUser).text = user.Password

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null
        val imageView = holder.itemView.findViewById<ImageView>(R.id.inPP)
        executor.execute{
            val imageUrl = user.imageURL
            try {
                val `in` = java.net.URL(imageUrl).openStream()
                image = BitmapFactory.decodeStream(`in`)
                Log.d("AddNewUser","Image in text" + image.toString())
                handler.post {
                    Log.d("AddNewUser","Image Added")
                }
            }catch (e: java.lang.Exception){
                Log.d("AddNewUser", "Error happened "+ e.toString())
                e.printStackTrace()
            }

        }
        //button click listener
        holder.itemView.findViewById<Button>(R.id.btnUserAction).setOnClickListener{
            Log.d("AddNewUser", "Button Pressed ${user.Name} was pressed")
        }
    }
}

class UserViewHolder : DiffUtil.ItemCallback<User>()
{
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean
    {
        return oldItem.Name == newItem.Name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean
    {
        return areItemsTheSame(oldItem, newItem)
    }

}
