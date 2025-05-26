package com.ctis487.workerjsondatabase.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctis487.workerjsondatabase.R
import com.ctis487.workerjsondatabase.model.Cars
import com.ctis487.workerjsondatabase.util.Utils


class CustomRecyclerViewAdapter(private val context: Context):RecyclerView.Adapter<CustomRecyclerViewAdapter.RecyclerViewItemHolder>() {
    private var carsList: List<Cars> = emptyList()

    // Add click listener
    private var onItemClickListener: ((Cars) -> Unit)? = null

    fun setOnItemClickListener(listener: (Cars) -> Unit) {
        onItemClickListener = listener
    }

    fun setCarList(cars: List<Cars>) {
        this.carsList = cars
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewItemHolder {
        val inflator = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflator.inflate(R.layout.item_layout, viewGroup, false)
        return RecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewItemHolder, position: Int) {
        val car = carsList[position]
        holder.tvItemCarName.text = car.model.toString()
        when(car.id) {
            1 -> holder.imgItemCar.setImageResource(R.drawable.id1)
            2 -> holder.imgItemCar.setImageResource(R.drawable.id2)
            3 -> holder.imgItemCar.setImageResource(R.drawable.id3)
            4 -> holder.imgItemCar.setImageResource(R.drawable.id4)
            5 -> holder.imgItemCar.setImageResource(R.drawable.id5)
            6 -> holder.imgItemCar.setImageResource(R.drawable.id6)
            7 -> holder.imgItemCar.setImageResource(R.drawable.id7)
            8 -> holder.imgItemCar.setImageResource(R.drawable.id8)
            9 -> holder.imgItemCar.setImageResource(R.drawable.id9)
            10 -> holder.imgItemCar.setImageResource(R.drawable.id10)
            11 -> holder.imgItemCar.setImageResource(R.drawable.id11)
            12 -> holder.imgItemCar.setImageResource(R.drawable.id12)
            13 -> holder.imgItemCar.setImageResource(R.drawable.id13)
            14 -> holder.imgItemCar.setImageResource(R.drawable.id14)
            15 -> holder.imgItemCar.setImageResource(R.drawable.id15)
            16 -> holder.imgItemCar.setImageResource(R.drawable.id16)
            17 -> holder.imgItemCar.setImageResource(R.drawable.id17)
            18 -> holder.imgItemCar.setImageResource(R.drawable.id18)
            19 -> holder.imgItemCar.setImageResource(R.drawable.id19)
            20 -> holder.imgItemCar.setImageResource(R.drawable.id20)
            21 -> holder.imgItemCar.setImageResource(R.drawable.id21)
            22 -> holder.imgItemCar.setImageResource(R.drawable.id22)
            23 -> holder.imgItemCar.setImageResource(R.drawable.id23)
            24 -> holder.imgItemCar.setImageResource(R.drawable.id24)
            25 -> holder.imgItemCar.setImageResource(R.drawable.id25)
            26 -> holder.imgItemCar.setImageResource(R.drawable.id26)
            27 -> holder.imgItemCar.setImageResource(R.drawable.id27)
            28 -> holder.imgItemCar.setImageResource(R.drawable.id28)
            29 -> holder.imgItemCar.setImageResource(R.drawable.id29)
            30 -> holder.imgItemCar.setImageResource(R.drawable.id30)
        }

        if (carsList[position].year == 2020) {
            holder.tvItemCarName.text = car.model.toString()+ " "+car.make.toString() +" " + car.year.toString()
            holder.itemLayout.setBackgroundColor(context.resources.getColor(R.color.teal_700))
        }
        else {
            holder.tvItemCarName.text = car.model.toString() + " " + car.year.toString()
            holder.itemLayout.setBackgroundColor(context.resources.getColor(R.color.purple_200))
        }

    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    inner class RecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var itemLayout: LinearLayout
        lateinit var tvItemCarName: TextView
        lateinit var btnItemDetail: TextView
        lateinit var imgItemCar: ImageView
        
        init {
            itemLayout = itemView.findViewById(R.id.itemLayout)
            tvItemCarName = itemView.findViewById(R.id.tvItemCarName)
            btnItemDetail = itemView.findViewById(R.id.btnItemDetail)
            imgItemCar = itemView.findViewById(R.id.imgItemCar)

            // Update click listener
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.invoke(carsList[position])
                }
            }

            btnItemDetail.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val car = carsList[position]
                    Toast.makeText(
                        context,
                        "Showing details for ${car.make} ${car.model}",
                        Toast.LENGTH_SHORT
                    ).show()
                    onItemClickListener?.invoke(car)
                }
            }
        }
    }
}
