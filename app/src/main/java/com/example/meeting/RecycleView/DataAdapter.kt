package com.example.meeting.RecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meeting.Model.DataModel
import com.example.meeting.R
import java.text.SimpleDateFormat
import java.util.*


class DataAdapter(private var context: Context, var arrayList: ArrayList<DataModel>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    //Click
    var mListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.layout_file, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val model = arrayList[position]

        val sTime: String? = model.Start
        val eTime: String? = model.End


        val originalStringFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val desiredStringFormat = "HH:mm"
        val readingFormat =
            SimpleDateFormat(originalStringFormat)
        val outputFormat =
            SimpleDateFormat(desiredStringFormat)
        val dateStart = readingFormat.parse(sTime)
        val dateEnd = readingFormat.parse(eTime)
        holder.tvTimeStart.text = outputFormat.format(dateStart)
        holder.tvTimeEnd.text = outputFormat.format(dateEnd)

        holder.tvId.text = model.Subject.toString()
        holder.tvName.text = model.Name.toString()
        holder.textView.text = model.Location.toString()
    }

    //val itemCount: Int
    //get() = arrayList.size

    inner class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvId: TextView = itemView.findViewById(R.id.tvID)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvTimeStart: TextView = itemView.findViewById(R.id.tvTimeStart)
        var tvTimeTo: TextView = itemView.findViewById(R.id.tvTimeTo)
        var tvTimeEnd: TextView = itemView.findViewById(R.id.tvTimeEnd)
        var textView: TextView = itemView.findViewById(R.id.textView)

        override fun onClick(v: View) {
            if (mListener != null) mListener!!.onItemClick(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    //Create Interface
    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    //Create SetClick
    fun setItemClickListener(listener: ItemClickListener?) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


}